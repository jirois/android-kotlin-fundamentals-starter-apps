

package com.example.android.devbyteviewer.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.devbyteviewer.domain.DevByteVideo


/**
 * Database entities go in this file. These are responsible for reading and writing from the
 * database.
 */

@Entity
data class DatabaseVideo constructor(
        @PrimaryKey
        val url: String,
        val updated: String,
        val title: String,
        val description: String,
        val thumbnail: String)

fun List<DatabaseVideo>.asDomainModel():List<DevByteVideo>{
    return map {
        DevByteVideo(
                url = it.url,
                title = it.title,
                description = it.description,
                updated = it.updated,
                thumbnail = it.thumbnail)
    }
}