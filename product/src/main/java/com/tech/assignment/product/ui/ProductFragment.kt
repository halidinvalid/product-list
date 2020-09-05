package com.tech.assignment.product.ui

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.tech.assignment.domain.model.ProductItem
import com.tech.assignment.presentation.base.BaseFragment
import com.tech.assignment.presentation.extensions.navigate
import com.tech.assignment.presentation.extensions.observeResponse
import com.tech.assignment.presentation.extensions.setup
import com.tech.assignment.presentation.extensions.snackAlert
import com.tech.assignment.presentation.navigation.UiNavigation
import com.tech.assignment.product.R
import kotlinx.android.synthetic.main.fragment_product.*
import kotlinx.android.synthetic.main.progress_dialog.*
import org.koin.android.viewmodel.ext.android.viewModel

class ProductFragment : BaseFragment() {
    private val productViewModel: ProductViewModel by viewModel()
    private val productAdapter by lazy {
        ProductAdapter(onItemClickListener = {
            ProductDetailFragment.newInstance(it).navigate(fragmentManager!!)
        })
    }

    override val uiNavigation = UiNavigation.ROOT

    override fun getLayoutRes() = R.layout.fragment_product

    override fun initView() {
        super.initView()
        actionBar.title = getString(R.string.screen_title)
        recyclerViewProducts.apply {
            setup(
                layoutManager = GridLayoutManager(context, GRID_SPAN_COUNT),
                adapter = productAdapter
            )
        }
    }

    override fun observeData() {
        super.observeData()
        productViewModel.productsLiveData.observeResponse(
            owner = viewLifecycleOwner,
            progressView = progressBar,
            success = {
                it?.products?.let { productList ->
                    recyclerViewProducts.visibility = View.VISIBLE
                    productAdapter.updateList(productList as MutableList<ProductItem>)
                }
            },
            fail = {
                constraintLayoutMain?.snackAlert(onClickListener = {
                    productViewModel.getProducts()
                }, it?.message!!)
            }
        )
    }

    override fun fetchData() {
        super.fetchData()
        productViewModel.getProducts()
    }

    companion object {
        private const val GRID_SPAN_COUNT = 2
        fun newInstance() = ProductFragment()
    }
}