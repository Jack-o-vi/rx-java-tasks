package com.zeienko.rxjavatutorialapp.ui.fragments.task01

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import com.zeienko.rxjavatutorialapp.data.net.manager.NetworkManager
import com.zeienko.rxjavatutorialapp.data.net.models.story.NewsBean
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

class TaskOnePresenter
    : TaskOneContract.TaskOnePresenter {

    private var view: TaskOneContract.TaskOneView? = null
    private var disposable: Disposable? = null

    companion object {
        val TAG = TaskOnePresenter::class.java.simpleName
    }

    @SuppressLint("CheckResult")
    override fun bind(view: TaskOneContract.TaskOneView) {
        this.view = view
        Log.d(TAG, "Bind $view")
        taskOne()
    }

    /**
    Task 1
    1. Load stories from the 1 and 2 pages
    2. Print a single list of titles from two pages
     */
    private fun taskOne() {
        view?.showProgressBar()
        val args = arrayOf(0, 1)
        NetworkManager.getAlgoliaApi().getStories(args[0])
            .zipWith(NetworkManager.getAlgoliaApi().getStories(args[0]),
                BiFunction<NewsBean, NewsBean, List<String?>?> { data1, data2 ->
                    val listRes: MutableList<String?> = ArrayList()
                    data1.hits?.map { hit -> hit.title }?.let { titles1 ->
                        data2.hits?.map { hit -> hit.title }?.let { titles2 ->
                            if (titles1 is MutableList && titles2 is MutableList) {
                                titles1.addAll(titles2)
                                listRes.addAll(titles1)
                            }
                        }
                    }
                    listRes
                })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<String?>?> {

                override fun onSubscribe(d: Disposable) {
                    Log.d(TAG, "onSubscribe")
                    disposable = d
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "onError $e ")
                    view?.hideProgressBar()
                }

                override fun onSuccess(result: List<String?>) {
                    val stringBuilder = StringBuilder()
                    Log.d(TAG, "onSuccess ${result} ")
                    result.forEach { title ->
                        stringBuilder.append(title)
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                            stringBuilder.append(System.lineSeparator())
                        }
                    }
                    view?.hideProgressBar()
                    view?.setText(stringBuilder.toString())
                }
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