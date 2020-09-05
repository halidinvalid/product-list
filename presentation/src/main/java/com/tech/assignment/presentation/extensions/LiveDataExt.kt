package com.tech.assignment.presentation.extensions

import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.tech.assignment.presentation.entities.DataHolder
import com.tech.assignment.presentation.entities.Error
import com.tech.assignment.presentation.entities.Status

inline fun <T : Any?> LiveData<DataHolder<T?>>.observeResponse(
    owner: LifecycleOwner,
    progressView: ProgressBar,
    crossinline success: (T?) -> Unit = {
        // no-op
    },
    crossinline fail: (Error?) -> Unit = {
        // no-op
    }
) {
    this.observe(owner, { holder: DataHolder<T?>? ->
        when (holder?.responseType) {
            is Status.SUCCESSFUL -> {
                progressView.visibility = View.GONE
                success(holder.data)
            }
            is Status.ERROR -> {
                progressView.visibility = View.GONE
                fail(holder.error)
            }
            is Status.LOADING -> {
                progressView.visibility = View.VISIBLE
            }
        }
    })

}

