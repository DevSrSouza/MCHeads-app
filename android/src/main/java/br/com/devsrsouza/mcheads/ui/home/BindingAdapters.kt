package br.com.devsrsouza.mcheads.ui.home

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import br.com.devsrsouza.mcheads.R
import br.com.devsrsouza.mcheads.common.Head
import coil.api.load

@BindingAdapter
fun ImageView.headRender(head: Head) {
    load(head.imageUrl) {
        placeholder(R.drawable.default_head)
    }
}