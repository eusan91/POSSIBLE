package com.santamaria.possiblecodingchallenge.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Santamaria on 28/10/2017.
 */
class BookAPICall {

    companion object {
        private val baseURL = "http://de-coding-test.s3.amazonaws.com"

        fun getBooks() : BookService {
            return Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(BookService::class.java)
        }
    }

}