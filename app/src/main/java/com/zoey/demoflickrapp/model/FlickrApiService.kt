package com.zoey.demoflickrapp.model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class FlickrApiService {
    private val base_url = "https://api.flickr.com/services/rest/"

    private val api = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(base_url)
        .build()
        .create(FlickrApi::class.java)

    fun getPhotos(text: String): Single<PhotoModel> {
        return api.getPhotos(text)
    }
}