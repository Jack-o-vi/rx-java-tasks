package com.zeienko.rxjavatutorialapp.data.net.models.story

data class TitleBean(
    val value: String? = null,
    val matchLevel: String? = null,
    val matchedWords: MutableList<Any>? = null
)