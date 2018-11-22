package com.zeienko.rxjavatutorialapp.ui.fragments.task04

import android.util.Log
import io.reactivex.Maybe
import io.reactivex.MaybeObserver
import io.reactivex.disposables.Disposable
import java.util.*

class TaskFourPresenter : TaskFourContract.TaskFourPresenter {


    var view: TaskFourContract.TaskFourView? = null

    companion object {
        val TAG = TaskFourPresenter::class.java.simpleName
    }

    override fun bind(view: TaskFourContract.TaskFourView) {
        this.view = view
        taskFour()
    }

    fun emmitBangOrNothing(): Maybe<String> {
        return Maybe.create<String> { emitter ->
            if (Random().nextBoolean()) {
                if (!emitter.isDisposed) {
                    emitter.onSuccess("Bang!")
                }
            } else {
                if (!emitter.isDisposed) {
                    emitter.onComplete()
                }
            }
        }
    }

    private fun taskFour() {
        emmitBangOrNothing().subscribe(object : MaybeObserver<String> {
            override fun onSuccess(t: String) {
                Log.d(TAG, "onSuccess $t")
                view?.showToast(t)
            }

            override fun onComplete() {
                Log.d(TAG, "onComplete")

            }

            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "onSubscribe $d")

            }

            override fun onError(e: Throwable) {
                Log.e(TAG, "onError $e")
                view?.showToast(e.toString())
            }
        })
    }

    override fun unbind() {
        view = null
    }
}