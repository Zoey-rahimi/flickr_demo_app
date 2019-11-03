package com.zoey.demoflickrapp.model

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApi {
    @GET("?method=flickr.photos.search&api_key=96358825614a5d3b1a1c3fd87fca2b47&format=json&nojsoncallback=1")
    fun getPhotos(@Query("text") text: String): Single<PhotoModel>
}