package com.example.androiddemo.ui


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androiddemo.api.RetrofitSingleton
import com.example.androiddemo.model.PostModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostViewModel  : ViewModel() {

    val postListMutableLiveData = MutableLiveData<List<PostModel>>()
    val errorMessageMutableLiveData = MutableLiveData<String>()

     fun getPost(){
        val apiInterface =  RetrofitSingleton.instance.getPost()
        apiInterface.enqueue(object : Callback<List<PostModel>> {
            override fun onResponse(call: Call<List<PostModel>>?, response: Response<List<PostModel>>?) {
                if(response?.body() != null)
                    postListMutableLiveData.postValue(response.body())
            }
            override fun onFailure(call: Call<List<PostModel>>?, t: Throwable?) {
                errorMessageMutableLiveData.postValue(t?.message)
            }
        })
    }
}