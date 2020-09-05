package com.tech.assignment.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.tech.assignment.presentation.navigation.UiNavigation

abstract class BaseFragment : Fragment() {

    open val uiNavigation = UiNavigation.BACK
    open lateinit var actionBar: ActionBar

    @LayoutRes
    protected abstract fun getLayoutRes(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutRes(), null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actionBar = (activity as? AppCompatActivity)?.supportActionBar!!
        initView()
        initNavigation(uiNavigation)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeData()
        if (retainInstance) {
            fetchData()
        }
    }

    private fun initNavigation(uiNavigation: UiNavigation) {
        when (uiNavigation) {
            UiNavigation.BACK -> {
                actionBar.setDisplayHomeAsUpEnabled(true)
                actionBar.setHomeButtonEnabled(true)
            }
            UiNavigation.ROOT -> {
                actionBar.setDisplayHomeAsUpEnabled(false)
                actionBar.setHomeButtonEnabled(false)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        actionBar.title = ""
        retainInstance = false
    }

    open fun observeData() {
        // can be overridden
    }

    open fun fetchData() {
        // can be overridden
    }

    open fun initView() {
        // can be overridden
    }

}


