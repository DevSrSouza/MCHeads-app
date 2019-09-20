package br.com.devsrsouza.mcheads.ui.home

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.devsrsouza.mcheads.R
import br.com.devsrsouza.mcheads.common.Head
import coil.api.load

@BindingAdapter("head_render")
fun ImageView.headRender(head: Head?) {
    head?.let {
        load(it.imageUrl) {
            placeholder(R.drawable.default_head)
        }
    }
}

@BindingAdapter("head_grid_adapter")
fun RecyclerView.recyclerViewGridAdapter(data: List<Head>?) {
    data?.let {
        val adapter = adapter as HeadGridAdapter
        adapter.submitList(it)
    }
}