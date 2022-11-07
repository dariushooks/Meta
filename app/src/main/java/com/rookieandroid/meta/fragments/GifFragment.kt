package com.rookieandroid.meta.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rookieandroid.meta.R
import com.rookieandroid.meta.adapters.GifAdapter
import com.rookieandroid.meta.architecture.GifViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GifFragment : Fragment(R.layout.fragment_gif)
{
    private lateinit var recyclerView: RecyclerView
    private val viewModel : GifViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.gif_grid)
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        val adapter = GifAdapter()
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(context, layoutManager.orientation))

        viewLifecycleOwner.lifecycleScope.launch{
            viewModel.gifs.collectLatest {
                adapter.submitData(it)
            }
        }
    }
}