package com.tech.assignment.product.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tech.assignment.domain.interactor.ProductInteractor
import com.tech.assignment.domain.model.ProductItem
import com.tech.assignment.domain.model.ProductResponse
import com.tech.assignment.presentation.base.BaseViewModel
import com.tech.assignment.presentation.entities.DataHolder
import com.tech.assignment.presentation.extensions.launchViewModelScope
import com.tech.assignment.presentation.extensions.loadingData
import com.tech.assignment.presentation.extensions.responseData

class ProductViewModel(
    private val productInteractor: ProductInteractor
) : BaseViewModel() {

    private val productsMutableLiveData = MutableLiveData<DataHolder<ProductResponse?>>()
    private val productDetailsMutableLiveData = MutableLiveData<DataHolder<ProductItem?>>()

    val productsLiveData: LiveData<DataHolder<ProductResponse?>>
        get() = productsMutableLiveData
    val productDetailsLiveData: LiveData<DataHolder<ProductItem?>>
        get() = productDetailsMutableLiveData

    fun getProducts() = launchViewModelScope {
        productsMutableLiveData
            .loadingData()
            .responseData(
                productInteractor.getProducts()
            )
    }

    fun getProductDetails(productId: String?) = launchViewModelScope {
        productDetailsMutableLiveData
            .loadingData()
            .responseData(
                productInteractor.getProductDetails(productId)
            )
    }

    companion object {
        private const val TAG = "ProductViewModel"
    }
}