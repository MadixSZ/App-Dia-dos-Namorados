package com.example.amorzinho.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.amorzinho.R
import com.example.amorzinho.adapters.GalleryAdapter

class GalleryFragment : Fragment(R.layout.activity_fragment_gallery) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageResources: List<Int> = listOf(
            R.drawable.photo1,
            R.drawable.photo2,
            R.drawable.photo3
        )

        val recyclerView: RecyclerView = view.findViewById(R.id.galleryRecycler)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        recyclerView.adapter = GalleryAdapter(imageResources)
    }
}