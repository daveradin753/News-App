package com.newsapplication.mandiri.presentation.helper

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.newsapplication.mandiri.databinding.ItemSourcesBinding
import com.newsapplication.mandiri.domain.model.SourceModel
import com.newsapplication.mandiri.util.StringUtils.capitalizeWords

class SourcesAdapter: RecyclerView.Adapter<SourcesAdapter.ViewHolder>(){

    private var sources: List<SourceModel> = emptyList()
    fun setSources(sources: List<SourceModel>) {
        this.sources = sources
        notifyItemRangeChanged(0, sources.size)
    }

    private var onItemClick: (SourceModel) -> Unit = {}
    fun setOnItemClickListener(listener: (SourceModel) -> Unit) {
        onItemClick = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val binding = ItemSourcesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
        holder.bind(sources[position])
    }

    override fun getItemCount(): Int = sources.size

    inner class ViewHolder(private val binding: ItemSourcesBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(source: SourceModel) {
            binding.apply {
                tvSourceName.text = source.name?.capitalizeWords()
                tvSourceDescription.text = source.description
                root.setOnClickListener {
                    onItemClick(source)
                }
            }
        }

    }
}