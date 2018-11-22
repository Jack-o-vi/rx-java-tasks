package com.zeienko.rxjavatutorialapp.ui.fragments.task10

import com.zeienko.rxjavatutorialapp.ui._base.BasePresenter
import com.zeienko.rxjavatutorialapp.ui._base.BaseView

interface TaskTenContract {
    interface TaskTenView : BaseView<TaskTenPresenter> {
        fun showProgressBar()
        fun hideProgressBar()
        fun showToast(string: String?)
        fun setText(string: String?)
    }

    interface TaskTenPresenter : BasePresenter<TaskTenView>
}