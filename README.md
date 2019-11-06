# Flickr Demo App

- This is a sample app which uses the Flickr image search API and shows the results in a 3-column scrollable view.

- It contains one page which displays the first page of results returns by the API. If I had more time, I would have used the Paging library from jetpack to handle endless scrolling to display more images related to the searched value.

- The app will display a random list of images for the first time that the user opens the app.There is an edit text in the title bar which can be used to query a new list.

## Design decisions and considerations

- The orientation changes have been handled via the ViewModel and savedInstanceState method, which helps the searched term and results remains unchanged during an orientation change.

- The app is written in Kotlin. I have used Retrofit and RxJava for API call and threading, MVVM architecture, LiveData to notify the view, Glide to display images in the recyclerView.

- With SwipeRefreshLayout, the user can refresh the contents of the view via a vertical swipe gesture.

- A Network Security Configuration xml file has been added to the app to specify the settings for the app.(Needed for API 28)
