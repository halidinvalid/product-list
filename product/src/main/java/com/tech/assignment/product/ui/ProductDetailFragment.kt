package com.tech.assignment.product.ui

import android.os.Bundle
import com.tech.assignment.presentation.base.BaseFragment
import com.tech.assignment.presentation.extensions.loadImage
import com.tech.assignment.presentation.extensions.observeResponse
import com.tech.assignment.presentation.extensions.snackAlert
import com.tech.assignment.presentation.navigation.UiNavigation
import com.tech.assignment.product.R
import kotlinx.android.synthetic.main.fragment_product_detail.*
import kotlinx.android.synthetic.main.progress_dialog.*
import org.koin.android.viewmodel.ext.android.viewModel


class ProductDetailFragment : BaseFragment() {

    private val productViewModel: ProductViewModel by viewModel()
    private var productId: String? = null

    override fun getLayoutRes() = R.layout.fragment_product_detail

    override val uiNavigation = UiNavigation.BACK

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            productId = it.getString(BUNDLE_PRODUCT_ID, null)
        }
    }

    override fun observeData() {
        super.observeData()
        productViewModel.productDetailsLiveData.observeResponse(
            owner = viewLifecycleOwner,
            progressView = progressBar,
            success = {
                it?.let { productItem ->
                    productItem.name?.apply {
                        textViewProductName.text = this
                        actionBar.title = this
                    }
                    textViewPrice.text = productItem.price.toString()
                    textViewDescription.text = productItem.description
                    productItem.image?.loadImage(context = context, imageView = imageViewProduct)
                }
            },
            fail = {
                constraintLayoutMain?.snackAlert(onClickListener = {
                    productViewModel.getProductDetails(productId)
                }, it?.message!!)
            }
        )
    }

    override fun fetchData() {
        super.fetchData()
        productId?.let {
            productViewModel.getProductDetails(it)
        }
    }

    companion object {
        private const val BUNDLE_PRODUCT_ID = "product-id"

        fun newInstance(
            productId: String?
        ) = ProductDetailFragment().apply {
            arguments = Bundle().apply {
                putString(BUNDLE_PRODUCT_ID, productId)
            }
        }
    }

}