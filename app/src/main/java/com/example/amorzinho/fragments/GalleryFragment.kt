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
            R.drawable.photo3,
            R.drawable.photo4,
            R.drawable.photo5,
            R.drawable.photo6,
            R.drawable.photo7,
            R.drawable.photo8,
            R.drawable.photo9,
            R.drawable.photo10,
            R.drawable.photo11,
            R.drawable.photo12,
            R.drawable.photo13,
            R.drawable.photo14,
            R.drawable.photo15,
            R.drawable.photo16,
            R.drawable.photo17,
            R.drawable.photo18,
            R.drawable.photo19,
            R.drawable.photo20,
            R.drawable.photo21,
            R.drawable.photo22,
            R.drawable.photo23,
            R.drawable.photo24,
            R.drawable.photo25,
            R.drawable.photo26,
            R.drawable.photo27,
            R.drawable.photo28,
            R.drawable.photo29,
            R.drawable.photo30
        )

        val recyclerView: RecyclerView = view.findViewById(R.id.galleryRecycler)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        recyclerView.adapter = GalleryAdapter(imageResources)
    }
}