package com.zeienko.rxjavatutorialapp.ui.fragments.task02

import com.zeienko.rxjavatutorialapp.ui._base.BasePresenter
import com.zeienko.rxjavatutorialapp.ui._base.BaseView

interface TaskTwoContract {
    interface TaskTwoPresenter : BasePresenter<TaskTwoView>
    interface TaskTwoView : BaseView<TaskTwoPresenter> {
        fun showToast(string: String?)
        fun setText(string: String?)
        fun showProgressBar()
        fun hideProgressBar()
    }
}