package com.zeienko.rxjavatutorialapp.ui.fragments.task09

import android.util.Log
import com.zeienko.rxjavatutorialapp.safeSubscribeOnAndObserveOn
import com.zeienko.rxjavatutorialapp.ui.fragments.task07.TaskSevenPresenter
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit


class TaskNinePresenter : TaskNineContract.TaskNinePresenter {

    companion object {
        val TAG = TaskNinePresenter::class.java.simpleName
    }

    var view: TaskNineContract.TaskNineView? = null

    /*
    1. Create an observable that emits values from 0 to 10 every second
2. Prints “Subscribed” when it is subscribed and prints values in onNext()
3. In onError it prints an error message
4. When the value 7 is emitted it should throw Exception(“Your error message”)
     */
    private fun taskNine() {
        Observable.intervalRange(0, 10, 0, 1, TimeUnit.SECONDS)
            .safeSubscribeOnAndObserveOn()
            .map {
                if (it == 7L) {
                    throw Exception("Your error message")
                }
                return@map it
            }
            .subscribe(object : Observer<Long> {
                override fun onComplete() {
                    view?.showToast("Complete")
                }

                override fun onNext(result: Long) {
                    Log.d(TaskSevenPresenter.TAG, "onSuccess $result ")
                    view?.showToast(result.toString())
                }

                override fun onSubscribe(d: Disposable) {
                    Log.d(TaskSevenPresenter.TAG, "onSubscribe Disposable $d")
                }

                override fun onError(e: Throwable) {
                    Log.e(TaskSevenPresenter.TAG, "onError Exception")
                    e.printStackTrace()
                    view?.showToast(e.toString())
                }
            })
    }

    override fun bind(view: TaskNineContract.TaskNineView) {
        this.view = view
        taskNine()
    }

    override fun unbind() {
        view = null
    }
}