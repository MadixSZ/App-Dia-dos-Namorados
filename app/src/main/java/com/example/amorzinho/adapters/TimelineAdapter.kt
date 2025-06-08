package com.example.amorzinho.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.amorzinho.R
import com.example.amorzinho.models.Memory

class TimelineAdapter(private val memories: List<Memory>) :
    RecyclerView.Adapter<TimelineAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.memoryTitle)
        val date: TextView = itemView.findViewById(R.id.memoryDate)
        val description: TextView = itemView.findViewById(R.id.memoryDesc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_item_memory, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val memory = memories[position]
        holder.title.text = memory.title
        holder.date.text = memory.date
        holder.description.text = memory.description
    }

    override fun getItemCount(): Int = memories.size
}