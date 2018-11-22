package com.chisw.rxjavakasilovtasks.ui.presenter.task03

import android.util.Log
import com.zeienko.rxjavatutorialapp.ui.fragments.task03.TaskThreeContract
import io.reactivex.Maybe
import io.reactivex.MaybeObserver
import io.reactivex.disposables.Disposable
import java.util.*

class TaskThreePresenter : TaskThreeContract.TaskThreePresenter {

    var view: TaskThreeContract.TaskThreeView? = null

    companion object {
        val TAG = TaskThreePresenter::class.java.simpleName
    }

    override fun bind(view: TaskThreeContract.TaskThreeView) {
        this.view = view
        taskThree()
    }

    private fun emmitBangOrException(): Maybe<String> {
        return Maybe.create<String> { emitter ->
            if (Random().nextBoolean()) {
                if (!emitter.isDisposed) {
                    emitter.onSuccess("Bang!")
                }
            } else {
                if (!emitter.isDisposed) {
                    emitter.onError(IllegalArgumentException("Finish with exception"))
                }
            }
        }
    }

    private fun taskThree() {
        emmitBangOrException().subscribe(object : MaybeObserver<String> {
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