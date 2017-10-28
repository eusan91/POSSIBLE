package com.santamaria.possiblecodingchallenge.Activities

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.santamaria.possiblecodingchallenge.Domain.Book
import com.santamaria.possiblecodingchallenge.R
import com.santamaria.possiblecodingchallenge.Retrofit.BookAPICall
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var bookList : List<Book>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun getBooks()  {

        var bookCall : Call<List<Book>> = BookAPICall.getBooks().getBooks("books", "json")

        bookCall.enqueue(object : Callback<List<Book>> {
            override fun onResponse(call: Call<List<Book>>?, response: Response<List<Book>>?) {
                if (response != null && response.isSuccessful) {
                    bookList = response.body()
                }
            }

            override fun onFailure(call: Call<List<Book>>?, t: Throwable?) {
                Toast.makeText(applicationContext, getString(R.string.retrofit_comm_error), Toast.LENGTH_SHORT).show()

            }
        })
    }
}
