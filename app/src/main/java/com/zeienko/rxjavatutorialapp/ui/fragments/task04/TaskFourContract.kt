package com.zeienko.rxjavatutorialapp.ui.fragments.task04

import com.zeienko.rxjavatutorialapp.ui._base.BasePresenter
import com.zeienko.rxjavatutorialapp.ui._base.BaseView

interface TaskFourContract {
    interface TaskFourView : BaseView<TaskFourPresenter> {
        fun showToast(string: String?)
        fun setText(string: String?)
    }

    interface TaskFourPresenter : BasePresenter<TaskFourView>
}