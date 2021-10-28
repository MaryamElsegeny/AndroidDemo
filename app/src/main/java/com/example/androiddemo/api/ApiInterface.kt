package com.example.androiddemo.api

import com.example.androiddemo.model.PostModel
import retrofit2.Call
import retrofit2.http.GET

const val posts = "posts"
interface ApiInterface {

    @GET(posts)
    fun getPost(): Call<List<PostModel>>

}






