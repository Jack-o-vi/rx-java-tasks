package com.zeienko.rxjavatutorialapp.ui.fragments.task05

import com.zeienko.rxjavatutorialapp.ui._base.BasePresenter
import com.zeienko.rxjavatutorialapp.ui._base.BaseView

interface TaskFiveContract {
    interface TaskFiveView : BaseView<TaskFivePresenter> {
        fun showToast(s: String)
    }

    interface TaskFivePresenter : BasePresenter<TaskFiveView>
}