package com.zeienko.rxjavatutorialapp.ui.fragments.task05

import com.zeienko.rxjavatutorialapp.log
import com.zeienko.rxjavatutorialapp.logObserver
import com.zeienko.rxjavatutorialapp.safeSubscribeOnAndObserveOn
import com.zeienko.rxjavatutorialapp.ui.fragments.task04.TaskFourPresenter
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

class TaskFivePresenter : TaskFiveContract.TaskFivePresenter {

    var view: TaskFiveContract.TaskFiveView? = null
    private val taskFourPresenter: TaskFourPresenter =
        TaskFourPresenter()

    override fun bind(view: TaskFiveContract.TaskFiveView) {
        this.view = view
        startTask()
    }


    /*
    Task 5
1. Use maybe source from the previous task
2. Transform it to single
3. Print “Bang” from maybe in onSuccess() or "You're live" in other case (do not
implement onError)
     */
    private fun startTask() {
        taskFourPresenter
            .emmitBangOrNothing()
            .safeSubscribeOnAndObserveOn()
            .doOnComplete {
                view?.showToast("You`re alive")
            }
            .toSingle()
            .logObserver()
            .subscribe(object : SingleObserver<String> {
                override fun onSuccess(t: String) {
                    view?.showToast(t)
                    "onSuccess() $t".log("duck")
                }

                override fun onSubscribe(d: Disposable) {
                    "onSubscribe() $d".log("duck")
                }

                override fun onError(e: Throwable) {
                    "onError()".log("duck")
                }
            })
    }

    override fun unbind() {
        view = null
    }
}
