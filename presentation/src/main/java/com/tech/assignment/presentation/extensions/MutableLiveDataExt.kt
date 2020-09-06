package com.tech.assignment.presentation.extensions

import androidx.lifecycle.MutableLiveData
import com.tech.assignment.presentation.entities.DataHolder
import com.tech.assignment.presentation.entities.Error
import com.tech.assignment.presentation.entities.Status
import retrofit2.Response


fun <T : Any?> MutableLiveData<DataHolder<T?>>.loadingData() = apply {
    postValue(DataHolder(responseType = Status.LOADING))
}

fun <T : Any?> MutableLiveData<DataHolder<T?>>.responseData(responseMethod: Response<T>?) = apply {
    responseMethod?.apply {
        when {
            isSuccessful -> {
                postValue(
                    DataHolder(responseType = Status.SUCCESSFUL, data = this.body())
                )
            }
            this.code() in 400..600 -> {
                postValue(
                    DataHolder(
                        responseType = Status.ERROR,
                        error = Error(message())
                    )
                )
            }
            this.code() == 3002 -> {
                postValue(
                    DataHolder(
                        responseType = Status.ERROR,
                        error = Error("No internet connection!")
                    )
                )
            }
            else -> {
                postValue(
                    DataHolder(
                        responseType = Status.ERROR,
                        error = Error(
                            "Unknown error"
                        )
                    )
                )
            }
        }
    }
}