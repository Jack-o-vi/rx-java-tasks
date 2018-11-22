package com.zeienko.rxjavatutorialapp.ui.fragments.task07

import android.os.Build
import android.util.Log
import com.zeienko.rxjavatutorialapp.data.net.manager.NetworkManager
import com.zeienko.rxjavatutorialapp.data.net.models.story.NewsBean
import com.zeienko.rxjavatutorialapp.safeSubscribeOnAndObserveOn
import io.reactivex.Observable
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import java.util.concurrent.TimeUnit

class TaskSevenPresenter : TaskSevenContract.TaskSevenPresenter {

    companion object {
        val TAG = TaskSevenPresenter::class.java.simpleName
    }

    var view: TaskSevenContract.TaskSevenView? = null

    /*
    1. Create an observable that emits values from 0 to 10 every second.
    2. Accumulate every 2 values
    3. Load stories with a page equals to the emitted value
     */
    private fun taskSeven() {
        Observable.intervalRange(0, 10, 0, 1, TimeUnit.SECONDS)
            .reduce(0, BiFunction { t1, t2 ->
                return@BiFunction t1 + t2.toInt()
            }).flatMap {
                return@flatMap NetworkManager.getAlgoliaApi().getStories(it)
            }
            .safeSubscribeOnAndObserveOn()
            .subscribe(object : SingleObserver<NewsBean> {
                override fun onSubscribe(d: Disposable) {
                    Log.d(TAG, "onSubscribe Disposable $d")
                }

                override fun onSuccess(result: NewsBean) {
                    Log.d(TAG, "onSuccess $result ")
                    val stringBuilder = StringBuilder()
                    stringBuilder.append(result)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        stringBuilder.append(System.lineSeparator())
                    }
                    view?.setText(stringBuilder.toString())
                    view?.hideProgressBar()

                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, "onError Exception")
                    e.printStackTrace()
                    view?.hideProgressBar()
                    view?.showToast(e.toString())
                }
            })
    }

    override fun bind(view: TaskSevenContract.TaskSevenView) {
        this.view = view
        view.showProgressBar()
        taskSeven()
    }

    override fun unbind() {
        view = null
    }
}