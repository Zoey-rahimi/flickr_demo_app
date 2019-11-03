package com.zoey.demoflickrapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zoey.demoflickrapp.R
import com.zoey.demoflickrapp.model.Photo
import kotlinx.android.synthetic.main.photo_item.view.*

class PhotoListAdapter(private val photoList: ArrayList<Photo>) :
    RecyclerView.Adapter<PhotoListAdapter.PhotoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.photo_item, parent, false)
        return PhotoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {

        Glide.with(holder.view)
            .load(getPhotoUrl(photoList[position]))
            .centerCrop()
            .override(200,200)
            .into(holder.view.imageView)
    }

    private fun getPhotoUrl(photo: Photo): String {
        return "http://farm" + photo.photoFarm +
                ".static.flickr.com/" + photo.photoServer +
                "/" + photo.photoId + "_" + photo.photoSecret + ".jpg"
    }

    fun updatePhotoList(list: List<Photo>) {

        photoList.clear()
        photoList.addAll(list)
        notifyDataSetChanged()
    }

    class PhotoViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}