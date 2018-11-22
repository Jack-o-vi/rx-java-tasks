package com.zeienko.rxjavatutorialapp.ui.fragments.task08

import com.zeienko.rxjavatutorialapp.ui._base.BasePresenter
import com.zeienko.rxjavatutorialapp.ui._base.BaseView

interface TaskEightContract {
    interface TaskEightPresenter : BasePresenter<TaskEightView>
    interface TaskEightView : BaseView<TaskEightPresenter> {
        fun showProgressBar()
        fun hideProgressBar()
        fun showToast(string: String?)
        fun setText(string: String?)
        fun safeSetText(string: String)
    }
}