package com.santamaria.possiblecodingchallenge.Retrofit

import com.santamaria.possiblecodingchallenge.Domain.Book
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Santamaria on 28/10/2017.
 */
interface BookService {

    @GET("/{item}.{ext}")
    fun getBooks(
            @Path("item") item : String,
            @Path("ext") ext : String
    ) : Call<List<Book>>
}