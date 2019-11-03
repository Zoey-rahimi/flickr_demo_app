package com.zoey.demoflickrapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zoey.demoflickrapp.model.FlickrApiService
import com.zoey.demoflickrapp.model.Photo
import com.zoey.demoflickrapp.model.PhotoModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class PhotoViewModel : ViewModel() {

    private val service = FlickrApiService()
    private val compositeDisposable = CompositeDisposable()

    val photoList = MutableLiveData<List<Photo>>()
    val loading = MutableLiveData<Boolean>()
    val loadingError = MutableLiveData<Boolean>()
    fun refresh(text: String) {
        fetchFromRemote(text)
    }

    private fun fetchFromRemote(text: String) {
        loading.value = true

        val disposable = service.getPhotos(text)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<PhotoModel>() {
                override fun onSuccess(photoModel: PhotoModel) {
                    loading.value = false
                    loadingError.value = false
                    photoList.value = photoModel.photos.photoList
                }

                override fun onError(e: Throwable) {
                    loading.value = false
                    loadingError.value = true

                }

            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}