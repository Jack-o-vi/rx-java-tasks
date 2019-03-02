package com.zeienko.rxjavatutorialapp.ui.fragments.task02

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import com.zeienko.rxjavatutorialapp.data.net.manager.NetworkManager
import com.zeienko.rxjavatutorialapp.data.net.models.user.UserBean
import io.reactivex.Observable
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class TaskTwoPresenter :
    TaskTwoContract.TaskTwoPresenter {

    private var view: TaskTwoContract.TaskTwoView? = null
    private var disposable: Disposable? = null

    companion object {
        val TAG = TaskTwoPresenter::class.java.simpleName

    }

    @SuppressLint("CheckResult")
    override fun bind(view: TaskTwoContract.TaskTwoView) {
        this.view = view
        Log.d(TAG, "Bind $view")
        taskTwo()
    }

    /*
    Task 2
    1. Get list of stories from page 0
    2. For all stories load author’s info
    3. Print only authors with karma greater than 3000 as a single list
     */
    private fun taskTwo() {
        view?.showProgressBar()
        NetworkManager.getAlgoliaApi().getStories(0)
            .subscribeOn(Schedulers.io())
            .toObservable()
            .flatMap { data ->
                val resList = data.hits?.map { hit -> hit.author }
                Observable.fromIterable(resList?.asIterable())
            }.flatMap { author ->
                NetworkManager.getAlgoliaApi().getUsers(author).toObservable()
            }.filter { user ->
                user.karma?.let { it > 3000 } ?: false
            }.toList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<MutableList<UserBean>> {
                override fun onSubscribe(d: Disposable) {
                    Log.d(TAG, "onSubscribe Disposable $d")
                    disposable = d
                }

                override fun onSuccess(result: MutableList<UserBean>) {
                    Log.d(TAG, "onSuccess $result ")
                    val stringBuilder = StringBuilder()
                    stringBuilder.append(result)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        stringBuilder.append(System.lineSeparator())
                    }
                    view?.setText(stringBuilder.toString())
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, "onError Exception")
                    e.printStackTrace()
                    view?.hideProgressBar()
                    view?.showToast(e.toString())
                }

//                override fun onComplete() {
//                    view?.hideProgressBar()
//                    Log.d(TAG, "onComplete")
//                }
            })
    }

    override fun unbind() {
        Log.d(TAG, "Unbind $view")
        view = null
        disposable?.apply {
            if (!isDisposed) {
                dispose()
            }
        }
    }
}