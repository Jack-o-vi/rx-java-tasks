package com.zeienko.rxjavatutorialapp.ui.fragments.task11

import android.util.Log
import com.zeienko.rxjavatutorialapp.data.net.manager.NetworkManager
import com.zeienko.rxjavatutorialapp.data.net.models.story.NewsBean
import com.zeienko.rxjavatutorialapp.data.net.models.user.TaskElevenData
import com.zeienko.rxjavatutorialapp.data.net.models.user.UserBean
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.zipWith
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
            .toObservable()
            .publish { published ->
                published.flatMapSingle { NetworkManager.getAlgoliaApi().getUsers(it.hits!![3].author) }
                    .zipWith(published) { t1: UserBean, t2: NewsBean ->
                        TaskElevenData(
                            t1.username!!,
                            t1.karma!!.toLong(),
                            t2.hits!![3].title!!
                        )
                    }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<TaskElevenData> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: TaskElevenData) {
                    Log.e("11TAG", t.toString())
                }

                override fun onError(e: Throwable) {
                    Log.e("11TAG", e.message)
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