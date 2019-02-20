package com.zeienko.rxjavatutorialapp.ui.fragments.task11

import android.util.Log
import com.zeienko.rxjavatutorialapp.data.net.manager.NetworkManager
import com.zeienko.rxjavatutorialapp.data.net.models.user.TaskElevenData
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class TaskElevenPresenter : TaskElevenContract.TaskElevenPresenter {

    private var view: TaskElevenContract.TaskElevenView? = null

    /*
    Task 11
1. Load stories from the page 0
2. Load 3rd story’s author info
3. Combine to the new model with fields: author name, karma, story title
4. Print the result
     */
    private fun taskEleven() {
        NetworkManager.getAlgoliaApi().getStories(0)
            .flatMap { bean ->
                NetworkManager.getAlgoliaApi().getUsers(bean.hits!![3].author)
                    .map { TaskElevenData(it.username!!, it.karma!!, bean.hits[3].title!!) }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<TaskElevenData> {
                override fun onSuccess(t: TaskElevenData) {
                    Log.e("duck", t.toString())
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                    Log.e("duck", e.message)
                }
            })
    }

    override fun bind(view: TaskElevenContract.TaskElevenView) {
        this.view = view
        taskEleven()
    }

    override fun unbind() {
        this.view = null
    }
}