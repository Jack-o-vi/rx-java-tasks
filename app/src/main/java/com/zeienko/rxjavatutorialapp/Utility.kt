package com.zeienko.rxjavatutorialapp

import android.util.Log
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/*
    Task 6.1
        Create an extension to SingleSource that logs current thread. Should work
        similar to RxJava operators.
        Should print “Current thread: ​ thread name ​ ”
    Task 6.2
        Create an extension to SingleSource, ObservableSource and MaybeSource
        that subscribes on Schedulers.io() and observes on
        AndroidSchedulers.mainThread()
    Task 6.3
        Create a function that accepts disposable and adds it to a previously created
        CompositeDisposable.
       Example:
        safeSubscribe{
        observable.subscribeOn(...).observeOn(...).subscribe(...)
}
 */

fun String.log(tag: String) {
    Log.d(tag, this)
}

fun <T> SingleSource<T>.logObserver(tag: String = "duck"): SingleSource<T> {
    Log.d(tag, "Current thread: ${Thread.currentThread().name}")
    return this
}

fun <T> Single<T>.logObserver(tag: String = "duck"): Single<T> {
    Log.d(tag, "Current thread: ${Thread.currentThread().name}")
    return this
}

fun <T> Observable<T>.logObserver(tag: String = "duck"): Observable<T> {
    Log.d(tag, "Current thread: ${Thread.currentThread().name}")
    return this
}

fun <T> Single<T>.safeSubscribeOnAndObserveOn(): Single<T> {
    return this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}

fun <T> Observable<T>.safeSubscribeOnAndObserveOn(): Observable<T> {
    return this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}

fun <T> Maybe<T>.safeSubscribeOnAndObserveOn(): Maybe<T> {
    return this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}

fun CompositeDisposable.safeSubscribe(disposable: () -> Disposable) {
    this.add(disposable())
}