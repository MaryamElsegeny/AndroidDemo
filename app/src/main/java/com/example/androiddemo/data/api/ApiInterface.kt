package com.example.androiddemo.data.api

import com.example.androiddemo.data.model.PostModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface ApiInterface {
    @GET("posts")
    fun getPost(): Observable<List<PostModel>>
}






