package com.santamaria.possiblecodingchallenge.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.santamaria.possiblecodingchallenge.Domain.Book
import com.santamaria.possiblecodingchallenge.R
import com.squareup.picasso.Picasso

/**
 * Created by Santamaria on 28/10/2017.
 */
class AdapterBooks (private var context: Context, private var booksList : List<Book>, private var layout : Int) : BaseAdapter() {

    override fun getItem(position: Int): Any {
        return booksList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        if (booksList != null ) {
            return booksList.size
        }

        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var viewHolder : ViewHolder
        var view : View

        if (convertView == null){
            view = LayoutInflater.from(context).inflate(layout, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = convertView.tag as ViewHolder
        }

        val book = booksList[position]

        Picasso.with(context).load(book.imageURL).resize(300,300).into(viewHolder.ivBook)
        if (book.author != null) {
            viewHolder.tvAuthor.text= "Author: " + book.author
        } else {
            viewHolder.tvAuthor.text= ""
        }
        viewHolder.tvTitle.text= book.title

        return view
    }


    inner class ViewHolder (var view : View){
        var tvAuthor : TextView
        var ivBook : ImageView
        var tvTitle : TextView

        init {
            tvAuthor = view.findViewById(R.id.idAuthor)
            ivBook = view.findViewById(R.id.idBookImage)
            tvTitle = view.findViewById(R.id.idTitle)
        }

    }

}