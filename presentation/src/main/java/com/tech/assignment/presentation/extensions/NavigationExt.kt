package com.tech.assignment.presentation.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.tech.assignment.presentation.R

fun Fragment.navigate(fm: FragmentManager): Int? {
    return fm.run {
        beginTransaction()
            .addToBackStack(null)
            .replace(R.id.frameLayoutMain, this@navigate, this@navigate::class.simpleName)
            .commit()
    }
}