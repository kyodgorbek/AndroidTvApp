package com.tvmaze.app.search.ui

import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleObserver
import com.tvmaze.app.app.extensions.getRootView
import com.tvmaze.app.app.extensions.isKeyboardOpen

class KeyboardEventListener(
    private val activity: AppCompatActivity,
    private val callback: (isOpen: Boolean) -> Unit
) : LifecycleObserver {

    private val listener = object : ViewTreeObserver.OnGlobalLayoutListener {
        private var lastState: Boolean = activity.isKeyboardOpen()

        override fun onGlobalLayout() {
            val isOpen = activity.isKeyboardOpen()
            if (isOpen == lastState) {
                return
            } else {
                dispatchKeyboardEvent(isOpen)
                lastState = isOpen
            }
        }
    }

     fun registerKeyboardListener() {
        activity.getRootView().viewTreeObserver.addOnGlobalLayoutListener(listener)
    }

    private fun dispatchKeyboardEvent(isOpen: Boolean) {
        when {
            isOpen  -> callback(true)
            !isOpen -> callback(false)
        }
    }

     fun unregisterKeyboardListener() {
        activity.getRootView().viewTreeObserver.removeOnGlobalLayoutListener(listener)
    }
}
