package br.com.devsrsouza.mcheads.data.domain

import br.com.devsrsouza.mcheads.common.HeadCategory

data class Head(
    val id: Int,
    val name: String,
    val uuid: String,
    val mojangId: String,
    val category: HeadCategory,
    val imageUrl: String
)