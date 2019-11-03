package com.zoey.demoflickrapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.zoey.demoflickrapp.viewmodel.PhotoViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_toolbar.*
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.view.KeyEvent
import android.widget.Toast
import android.content.Context
import android.view.inputmethod.InputMethodManager
import com.zoey.demoflickrapp.R


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: PhotoViewModel
    private val photoListAdapter = PhotoListAdapter(arrayListOf())
    private var searchedText: String? = null
    private val initialSearchValue: String = "random"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        savedInstanceState?.run {
            searchedText = savedInstanceState.getCharSequence("SEARCHED_TEXT")?.toString()
        }

        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.my_toolbar))

        viewModel = ViewModelProviders.of(this).get(PhotoViewModel::class.java)

        if (searchedText != null) {
            viewModel.refresh(searchedText!!)
        } else {
            viewModel.refresh(initialSearchValue)
        }

        recycler_view.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = photoListAdapter

        }

        refresh_layout.setOnRefreshListener {
            if (searchedText != null) {
                viewModel.refresh(searchedText!!)
            } else {
                viewModel.refresh(initialSearchValue)
            }
            recycler_view.visibility = View.GONE
            error_view.visibility = View.GONE
            progress_bar.visibility = View.VISIBLE
            refresh_layout.isRefreshing = false
        }

        search_editText.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(textView: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                hideKeyboard()
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchedText = textView?.text.toString()
                    if (searchedText != null) {
                        viewModel.refresh(searchedText!!)
                    } else {
                        Toast.makeText(
                            applicationContext,
                            resources.getString(R.string.empty_search_text),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    return true
                }
                return false
            }
        })
        observeViewModel()
    }

    private fun hideKeyboard() {
        val view = this.currentFocus
        view?.let { v ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putCharSequence("SEARCHED_TEXT", searchedText)
        super.onSaveInstanceState(outState)
    }

    private fun observeViewModel() {
        viewModel.photoList.observe(this, Observer { photoList ->
            photoList?.let {
                recycler_view.visibility = View.VISIBLE
                photoListAdapter.updatePhotoList(photoList)
            }
        })

        viewModel.loadingError.observe(this, Observer { isError ->
            isError?.let {
                error_view.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                progress_bar.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    recycler_view.visibility = View.GONE
                    error_view.visibility = View.GONE
                }
            }
        })
    }
}
