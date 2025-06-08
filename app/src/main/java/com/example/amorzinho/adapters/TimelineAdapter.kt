package com.example.amorzinho.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.amorzinho.R
import com.example.amorzinho.models.Memory

class TimelineAdapter(
    private val memories: List<Memory>,
    private val context: Context
) : RecyclerView.Adapter<TimelineAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.memoryTitle)
        val date: TextView = itemView.findViewById(R.id.memoryDate)
        val description: TextView = itemView.findViewById(R.id.memoryDesc)
        val timelineLine: View = itemView.findViewById(R.id.timelineLine)
        val timelineMarker: View = itemView.findViewById(R.id.timelineMarker)
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

        // Configurar a linha da timeline
        setupTimelineLine(holder, position)

        // Aplicar animação
        setAnimation(holder.itemView, position)
    }

    override fun getItemCount(): Int = memories.size

    private var lastPosition = -1

    private fun setAnimation(viewToAnimate: View, position: Int) {
        if (position > lastPosition) {
            val animation = AnimationUtils.loadAnimation(context, R.anim.activity_slide_in_right)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }

    private fun setupTimelineLine(holder: ViewHolder, position: Int) {
        when (position) {
            // Primeiro item
            0 -> {
                holder.timelineLine.visibility = View.VISIBLE
                val params = holder.timelineLine.layoutParams
                params.height = dpToPx(24) // Altura inicial
                holder.timelineLine.layoutParams = params

                // Ajustar margem superior para alinhar com o centro do marcador
                (holder.timelineLine.layoutParams as ViewGroup.MarginLayoutParams).topMargin = dpToPx(16)
            }

            // Último item
            itemCount - 1 -> {
                holder.timelineLine.visibility = View.VISIBLE
                val params = holder.timelineLine.layoutParams
                params.height = dpToPx(24) // Altura final reduzida
                holder.timelineLine.layoutParams = params
            }

            // Itens intermediários
            else -> {
                holder.timelineLine.visibility = View.VISIBLE
                val params = holder.timelineLine.layoutParams
                params.height = ViewGroup.LayoutParams.MATCH_PARENT
                holder.timelineLine.layoutParams = params
            }
        }
    }

    private fun dpToPx(dp: Int): Int {
        return (dp * context.resources.displayMetrics.density).toInt()
    }
}