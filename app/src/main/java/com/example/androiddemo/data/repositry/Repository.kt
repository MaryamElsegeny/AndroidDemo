package com.example.androiddemo.data.repositry

import com.example.androiddemo.data.api.ApiInterface
import com.example.androiddemo.data.model.PostModel
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private var apiInterfaces: ApiInterface) {

    fun getPost(): io.reactivex.rxjava3.core.Observable<Response<List<PostModel>>> {
        return apiInterfaces.getPost()
    }
}