package com.example.androiddemo.data.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass


@RealmClass
open class PostModel() : RealmObject(){
 @PrimaryKey
 var id : Long = 0
 var title: String? = null
 var body: String?=null
}
