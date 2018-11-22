package com.zeienko.rxjavatutorialapp.ui.fragments.task11

import com.zeienko.rxjavatutorialapp.ui._base.BasePresenter
import com.zeienko.rxjavatutorialapp.ui._base.BaseView

interface TaskElevenContract {
    interface TaskElevenView : BaseView<TaskElevenPresenter> {
        fun showProgressBar()
        fun hideProgressBar()
        fun showToast(string: String?)
        fun setText(string: String?)
    }

    interface TaskElevenPresenter : BasePresenter<TaskElevenView>
}