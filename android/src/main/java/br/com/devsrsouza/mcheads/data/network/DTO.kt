package br.com.devsrsouza.mcheads.data.network

import br.com.devsrsouza.mcheads.data.database.DatabaseHead
import br.com.devsrsouza.mcheads.data.domain.Head
import br.com.devsrsouza.mcheads.common.Head as NetworkHead

fun NetworkHead.asDomainModel(): Head = Head(
    id, name, uuid, mojangId, category, imageUrl
)

fun NetworkHead.asDatabaseModel(): DatabaseHead = DatabaseHead(
    id, name, uuid, mojangId, category, imageUrl
)

fun List<NetworkHead>.asDatabaseModel(): Array<DatabaseHead> {
    return map { it.asDatabaseModel() }.toTypedArray()
}