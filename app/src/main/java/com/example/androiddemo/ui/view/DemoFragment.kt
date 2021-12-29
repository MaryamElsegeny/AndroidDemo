package com.example.androiddemo.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androiddemo.R
import com.example.androiddemo.ui.viewmodel.PostViewModel
import com.example.androiddemo.ui.adapter.PostAdapter


class DemoFragment : Fragment() {
    private lateinit var recyclerView : RecyclerView
    private lateinit var recyclerAdapter: PostAdapter
    private lateinit var postViewModel: PostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =inflater.inflate(R.layout.fragment_demo, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        getData()
    }
    private fun setupRecyclerView() {
        recyclerView = view?.findViewById(R.id.recycler_view)!!
        recyclerView.layoutManager= LinearLayoutManager(requireContext())
        recyclerAdapter = PostAdapter()
        recyclerView.adapter = recyclerAdapter
    }
    private fun getData() {
        postViewModel = ViewModelProvider(this).get(PostViewModel::class.java)
        postViewModel.postListMutableLiveData.observe(requireActivity(), {
            recyclerAdapter.setPostListItems(it)
        })
        postViewModel.errorMessageMutableLiveData.observe(requireActivity(),{
            Toast.makeText(requireContext() , getString(R.string.something_error) , Toast.LENGTH_SHORT ).show()
        })
        postViewModel.getPost()
    }

}