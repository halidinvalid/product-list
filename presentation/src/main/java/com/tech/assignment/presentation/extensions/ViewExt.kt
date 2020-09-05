package com.tech.assignment.presentation.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import com.tech.assignment.presentation.R

fun String.loadImage(
    context: Context?,
    networkPolicy: NetworkPolicy = NetworkPolicy.OFFLINE,
    imageView: ImageView?
) {
    Picasso.with(context)
        .load(this)
        .networkPolicy(networkPolicy)
        .placeholder(R.drawable.ic_placeholder)
        .into(imageView, object : Callback {
            override fun onSuccess() {}

            override fun onError() {
                //Try again online if cache failed
                Picasso.with(context)
                    .load(this@loadImage)
                    .placeholder(R.drawable.ic_placeholder)
                    .into(imageView)
            }

        })
}


@SuppressLint("WrongConstant")
fun RecyclerView.setup(
    layoutManager: LinearLayoutManager,
    orientation: Int = LinearLayoutManager.VERTICAL,
    adapter: RecyclerView.Adapter<*>? = null
) {
    layoutManager.orientation = orientation
    this.layoutManager = layoutManager
    this.setHasFixedSize(false)
    adapter?.let {
        this.adapter = adapter
    }
}

fun View.snackAlert(
    onClickListener: (() -> Unit?)? = null,
    message: String
) {
    val snackBarError =
        Snackbar.make(
            this,
            message,
            Snackbar.LENGTH_INDEFINITE
        )
    snackBarError.setAction("Try Again!") {
        onClickListener?.invoke()
        snackBarError.dismiss()
    }
    snackBarError.show()
}
