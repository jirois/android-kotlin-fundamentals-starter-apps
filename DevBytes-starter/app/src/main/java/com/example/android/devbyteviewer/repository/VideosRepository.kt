
package com.example.android.devbyteviewer.repository

import android.view.animation.Transformation
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.android.devbyteviewer.database.VideosDatabase
import com.example.android.devbyteviewer.database.asDomainModel
import com.example.android.devbyteviewer.domain.DevByteVideo
import com.example.android.devbyteviewer.network.DevByteNetwork
import com.example.android.devbyteviewer.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

/**
 * Repository for fetching devbytes videos from network and storing on desk
 */

class VideosRepository (private val database: VideosDatabase){
    val videos : LiveData<List<DevByteVideo>> = Transformations.map(database.videoDao.getVideos()){
        it.asDomainModel()
    }


    suspend fun refreshVideos(){
        withContext(Dispatchers.IO){
          Timber.d("refresh videos is called")
          val playlist = DevByteNetwork.devbytes.getPlaylist().await()
          database.videoDao.insertAll(playlist.asDatabaseModel())
        }
    }
}
