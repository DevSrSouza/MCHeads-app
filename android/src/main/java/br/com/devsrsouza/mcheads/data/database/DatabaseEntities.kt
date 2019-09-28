package br.com.devsrsouza.mcheads.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.devsrsouza.mcheads.common.HeadCategory
import br.com.devsrsouza.mcheads.data.domain.Head

@Entity(tableName = "heads")
data class DatabaseHead(
    @PrimaryKey
    val id: Int,

    val name: String,
    val uuid: String,

    @ColumnInfo(name = "mojang_id")
    val mojangId: String,

    val category: HeadCategory,

    @ColumnInfo(name = "image_url")
    val imageUrl: String
)

fun DatabaseHead.asDomainModel(): Head = Head(
    id, name, uuid, mojangId, category, imageUrl
)

fun List<DatabaseHead>.asDomainModel(): List<Head> = map { it.asDomainModel() }