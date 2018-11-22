package com.zeienko.rxjavatutorialapp.ui.fragments.task07

import com.zeienko.rxjavatutorialapp.ui._base.BasePresenter
import com.zeienko.rxjavatutorialapp.ui._base.BaseView

interface TaskSevenContract {
    interface TaskSevenPresenter : BasePresenter<TaskSevenView>
    interface TaskSevenView : BaseView<TaskSevenPresenter> {
        fun showToast(string: String?)
        fun setText(string: String?)
        fun showProgressBar()
        fun hideProgressBar()
    }
}