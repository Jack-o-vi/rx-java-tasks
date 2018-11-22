package com.zeienko.rxjavatutorialapp.ui.fragments.task03

import com.zeienko.rxjavatutorialapp.ui._base.BasePresenter
import com.zeienko.rxjavatutorialapp.ui._base.BaseView

interface TaskThreeContract {
    interface TaskThreeView : BaseView<TaskThreePresenter> {
        fun showToast(string: String?)
        fun setText(string: String?)
    }

    interface TaskThreePresenter : BasePresenter<TaskThreeView>
}