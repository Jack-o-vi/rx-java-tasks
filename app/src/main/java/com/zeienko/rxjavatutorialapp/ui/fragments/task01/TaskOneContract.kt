package com.zeienko.rxjavatutorialapp.ui.fragments.task01

import com.zeienko.rxjavatutorialapp.ui._base.BasePresenter
import com.zeienko.rxjavatutorialapp.ui._base.BaseView

interface TaskOneContract {
    interface TaskOnePresenter : BasePresenter<TaskOneView>
    interface TaskOneView : BaseView<TaskOnePresenter> {
        fun showToast(string: String?)
        fun setText(string: String?)
        fun showProgressBar()
        fun hideProgressBar()
    }
}