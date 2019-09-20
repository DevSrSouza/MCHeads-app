package br.com.devsrsouza.mcheads.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.devsrsouza.mcheads.common.Head
import br.com.devsrsouza.mcheads.databinding.GridCatalogItemBinding

class HeadGridAdapter : ListAdapter<Head, HeadGridAdapter.ViewHolder>(HeadDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder private constructor(val binding: GridCatalogItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(head: Head) {
            binding.head = head
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = GridCatalogItemBinding.inflate(inflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

class HeadDiffCallback : DiffUtil.ItemCallback<Head>() {
    override fun areItemsTheSame(oldItem: Head, newItem: Head) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Head, newItem: Head) = oldItem == newItem
}