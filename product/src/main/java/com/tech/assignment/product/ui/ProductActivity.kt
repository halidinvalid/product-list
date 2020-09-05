package com.tech.assignment.product.ui

import com.tech.assignment.presentation.base.BaseActivity

class ProductActivity : BaseActivity() {
    override fun provideInitialFragment() = ProductFragment.newInstance()
}
