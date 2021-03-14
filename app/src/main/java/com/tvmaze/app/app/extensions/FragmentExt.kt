package com.tvmaze.app.app.extensions

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


fun Fragment.hideKeyboard() {
    val inputManager: InputMethodManager =
        (activity as AppCompatActivity)
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(view?.windowToken, 0)
}