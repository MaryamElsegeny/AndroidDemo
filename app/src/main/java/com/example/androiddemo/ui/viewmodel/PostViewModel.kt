package com.example.androiddemo.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androiddemo.data.model.PostModel
import com.example.androiddemo.data.repositry.Repository
import com.example.androiddemo.data.saveOffline
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
private var repository: Repository
) : ViewModel() {

    val postListMutableLiveData = MutableLiveData<List<PostModel>>()
    val errorMessageMutableLiveData = MutableLiveData<String>()
    val offlineMutableLiveData = MutableLiveData<String>()

    fun getPost() {
        var getPost: Observable<Response<List<PostModel>>> = repository.getPost()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        getPost.subscribe({
            if (it.code() == 200) postListMutableLiveData.postValue(it.body())
            else errorMessageMutableLiveData.postValue(it.message())
        }, {
            offlineMutableLiveData.postValue(it.saveOffline())
        })
    }
}