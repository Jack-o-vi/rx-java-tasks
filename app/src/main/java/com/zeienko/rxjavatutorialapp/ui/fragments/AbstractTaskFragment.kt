package com.zeienko.rxjavatutorialapp.ui.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class AbstractTaskFragment : Fragment() {

    protected fun inflateView(
        @LayoutRes layoutId: Int,
        inflater: LayoutInflater,
        container: ViewGroup?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onResume() {
        super.onResume()
        activity?.title = getFragmentTitle()
    }

    abstract fun getFragmentTitle(): CharSequence?
}