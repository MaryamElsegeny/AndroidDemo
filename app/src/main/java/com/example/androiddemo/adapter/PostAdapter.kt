package com.example.androiddemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androiddemo.R
import com.example.androiddemo.model.PostModel

class PostAdapter : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    private var postList : List<PostModel> = listOf()
    fun setPostListItems(postList: List<PostModel>){
        this.postList = postList
        notifyDataSetChanged()
    }
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView){
        val title: TextView = itemView.findViewById(R.id.titleTV)
        val body: TextView = itemView.findViewById(R.id.bodyTv)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val postModel = postList[position]
        holder.title.text=postModel.title
        holder.body.text=postModel.body
    }

    override fun getItemCount(): Int {
        return postList.size
    }
}