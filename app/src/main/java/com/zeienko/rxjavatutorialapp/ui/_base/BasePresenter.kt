package com.zeienko.rxjavatutorialapp.ui._base

interface BasePresenter<T> {
    fun bind(view: T)
    fun unbind()
}