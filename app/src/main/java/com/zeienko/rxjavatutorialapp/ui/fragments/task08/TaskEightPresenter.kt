package com.zeienko.rxjavatutorialapp.ui.fragments.task08

import android.util.Log
import com.zeienko.rxjavatutorialapp.data.net.manager.NetworkManager
import com.zeienko.rxjavatutorialapp.data.net.models.story.NewsBean
import com.zeienko.rxjavatutorialapp.logObserver
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors

class TaskEightPresenter : TaskEightContract.TaskEightPresenter {
    private var view: TaskEightContract.TaskEightView? = null


    /*
    Task 8
        1. Create 3 executors
        2. Load stories from page 0 in the thread 1
        3. Log thread
        4. Load stories from page 1 in the thread 2
        5. Log thread
        6. Subscribe in the thread 3
        7. Print a list of titles from two pages and the name of the thread
     */
    private fun taskEight() {
        val executor = Executors.newFixedThreadPool(2)
        NetworkManager.getAlgoliaApi().getStories(0)
            .subscribeOn(Schedulers.from(executor)).logObserver()
            .mergeWith(NetworkManager.getAlgoliaApi().getStories(1).subscribeOn(Schedulers.from(executor)))
            .toObservable()
            .logObserver()
            .observeOn(Schedulers.newThread())
            .subscribe(object : Observer<NewsBean> {
                override fun onComplete() {
                    view?.hideProgressBar()
                }

                override fun onSubscribe(d: Disposable) {
                    view?.showProgressBar()
                }

                override fun onNext(t: NewsBean) {
                    Log.d("duck", "OnNext() Current thread: ${Thread.currentThread().name}")
                    view?.safeSetText(t.toString())
                }

                override fun onError(e: Throwable) {
                    view?.hideProgressBar()
                }
            })
    }

    override fun bind(view: TaskEightContract.TaskEightView) {
        this.view = view
        taskEight()
    }

    override fun unbind() {
        view = null
    }
}