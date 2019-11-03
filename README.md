# flickr_demo_app

This is a sample app which uses the Flickr image search API and shows the results in a 3-column scrollable view.
It contains one page which displays the first page of results returns by the API. If I have more time, I would have used the Paging library from jetpack to handle endless scrolling to display more images related to the searched value.
There is an edit text in the title bar which the user can query a new list.
The orientation changes have been handled via the ViewModel and savedInstanceState method, which helps the searched term and results remains unchanged during an orientation change.
The app is written in Kotlin. I have used Retrofit and RxJava for API call and threading, MVVM architecture, LiveData to notify view, Glide to display images in the list.