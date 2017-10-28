package com.santamaria.possiblecodingchallenge.Activities


import android.opengl.Visibility
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.santamaria.possiblecodingchallenge.Adapter.AdapterBooks
import com.santamaria.possiblecodingchallenge.Domain.Book
import com.santamaria.possiblecodingchallenge.R
import com.santamaria.possiblecodingchallenge.Retrofit.BookAPICall
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var bookList : List<Book>? = null
    var listViewBooks : ListView? = null
    var pbLoading : ProgressBar? = null
    var tvInformation : TextView? = null

    var customBookAdapter : BaseAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getViews()

        getBooks()

    }

    private fun getViews(){
        listViewBooks = findViewById(R.id.idListViewBooks)
        pbLoading = findViewById(R.id.idLoading)
        tvInformation = findViewById(R.id.idInformation)
    }

    //Do the call to the WS in order to get the book list
    private fun getBooks()  {

        var bookCall : Call<List<Book>> = BookAPICall.getBooks().getBooks("books", "json")

        bookCall.enqueue(object : Callback<List<Book>> {
            override fun onResponse(call: Call<List<Book>>?, response: Response<List<Book>>?) {
                if (response != null && response.isSuccessful) {
                    bookList = response.body()

                    loadListView()
                }
            }

            override fun onFailure(call: Call<List<Book>>?, t: Throwable?) {
                Toast.makeText(applicationContext, getString(R.string.retrofit_comm_error), Toast.LENGTH_SHORT).show()

                //Hide progress bar
                stopProgressBar()

                //show error to user, no data to display
                tvInformation?.visibility = View.VISIBLE

            }
        })
    }

    //Once data is retrieved from server this function will load the UI
    private fun loadListView(){
        if (bookList != null) {
            customBookAdapter = AdapterBooks(applicationContext, bookList!!, R.layout.listview_book_item)
        }

        if (customBookAdapter != null) {
            listViewBooks?.adapter = customBookAdapter

            //Hide progress bar
            stopProgressBar()

            //hide any possible error to user
            tvInformation?.visibility = View.INVISIBLE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_exit, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item?.itemId){
            R.id.idExit -> {this.finish()}
        }
        return super.onOptionsItemSelected(item)
    }

    private fun stopProgressBar(){
        if (pbLoading != null) {
            pbLoading?.visibility = View.GONE
        }
    }
}
