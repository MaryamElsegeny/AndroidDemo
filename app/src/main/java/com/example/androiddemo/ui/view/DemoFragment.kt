package com.example.androiddemo.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androiddemo.R
import com.example.androiddemo.data.model.PostModel
import com.example.androiddemo.ui.adapter.PostAdapter
import com.example.androiddemo.ui.viewmodel.PostViewModel
import io.realm.Realm
import io.realm.RealmConfiguration


class DemoFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: PostAdapter
    private lateinit var postViewModel: PostViewModel
    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_demo, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        getData()
        Realm.init(requireContext())
        val config = RealmConfiguration.Builder().name("post.realm").build()
        realm = Realm.getInstance(config)
    }

    private fun setupRecyclerView() {
        recyclerView = view?.findViewById(R.id.recycler_view)!!
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerAdapter = PostAdapter()
        recyclerView.adapter = recyclerAdapter
    }

    private fun getData() {
        postViewModel = ViewModelProvider(this).get(PostViewModel::class.java)
        postViewModel.postListMutableLiveData.observe(requireActivity(), {
            recyclerAdapter.setPostListItems(it)

            realm.beginTransaction()
            realm.delete(PostModel::class.java)
            realm.commitTransaction()

            realm.beginTransaction()
            for (i in 0 until it.size) {
                var post = realm.createObject(PostModel::class.java, it[i].id)
                post.body = it[i].body
                post.title = it[i].title
            }
            realm.commitTransaction()
        })
        postViewModel.errorMessageMutableLiveData.observe(requireActivity(), {
            Toast.makeText(
                requireContext(),
                it,
                Toast.LENGTH_SHORT
            ).show()
        })
        postViewModel.offlineMutableLiveData.observe(requireActivity() , {
            val data = realm.where(PostModel::class.java).findAll()
            recyclerAdapter.setPostListItems(data)
        })
        postViewModel.getPost()
    }

}