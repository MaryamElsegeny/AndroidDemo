package com.example.androiddemo.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androiddemo.data.api.RetrofitSingleton
import com.example.androiddemo.data.model.PostModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class PostViewModel : ViewModel() {

    val postListMutableLiveData = MutableLiveData<List<PostModel>>()
    val errorMessageMutableLiveData = MutableLiveData<String>()

    fun getPost() {
        var getPost: Observable<List<PostModel>> = RetrofitSingleton.instance.getPost()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        getPost.subscribe { postListMutableLiveData.postValue(it)}
    }
}