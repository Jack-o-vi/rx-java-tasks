package com.zeienko.rxjavatutorialapp.ui.fragments.task09

import com.zeienko.rxjavatutorialapp.ui._base.BasePresenter
import com.zeienko.rxjavatutorialapp.ui._base.BaseView

interface TaskNineContract {
    interface TaskNineView : BaseView<TaskNinePresenter> {
        fun showProgressBar()
        fun hideProgressBar()
        fun showToast(string: String?)
        fun setText(string: String?)
    }

    interface TaskNinePresenter : BasePresenter<TaskNineView>
}