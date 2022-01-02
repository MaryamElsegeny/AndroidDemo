package com.example.androiddemo.data

import java.net.ConnectException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException


fun Throwable.saveOffline(): String {
    var error = ""
    if (this is UnknownHostException || this is TimeoutException || this is ConnectException) {
        error = this.message!!
    }
    return error

}