package com.zeienko.rxjavatutorialapp.ui.fragments.task10

import com.zeienko.rxjavatutorialapp.log
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject

class TaskTenPresenter : TaskTenContract.TaskTenPresenter {
    private var view: TaskTenContract.TaskTenView? = null

    /*
    Task 10
        Choose​ ​ the most suitable Subject for the next task
        1. Create Subject<Int>
        2. Post values 1,2,3
        3. Subscribe to it
        4. Post values 4, 5, 5, 6. In the cmd “3,4,5,6” should be printed
     */
    private fun taskTen() {
        val subj = BehaviorSubject.create<Int>()
        for (i in 1..6) {
            subj.onNext(i)
            if (i == 3) {
                subj.subscribe(object : Observer<Int> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(t: Int) {
                        "$t".log("duck")
                    }

                    override fun onError(e: Throwable) {

                    }
                })
            }
        }
    }

    override fun bind(view: TaskTenContract.TaskTenView) {
        this.view = view
        taskTen()
    }

    override fun unbind() {
        view = null
    }
}