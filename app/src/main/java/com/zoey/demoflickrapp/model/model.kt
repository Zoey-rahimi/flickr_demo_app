package com.zoey.demoflickrapp.model

import com.google.gson.annotations.SerializedName

data class PhotoModel(
    @SerializedName("photos")
    var photos: Photos,

    @SerializedName("stat")
    var stat: String?
)

data class Photos(
    @SerializedName("total")
    var total: String?,

    @SerializedName("photo")
    var photoList: List<Photo>?,

    @SerializedName("perpage")
    var perPage: Int?,

    @SerializedName("page")
    var page: Int?,

    @SerializedName("pages")
    var pages: Int?
)

data class Photo(
    @SerializedName("id")
    var photoId: String?,

    @SerializedName("owner")
    var photoOwner: String?,

    @SerializedName("secret")
    var photoSecret: String?,

    @SerializedName("server")
    var photoServer: String?,

    @SerializedName("farm")
    var photoFarm: String?,

    @SerializedName("title")
    var photoTitle: String?
)
