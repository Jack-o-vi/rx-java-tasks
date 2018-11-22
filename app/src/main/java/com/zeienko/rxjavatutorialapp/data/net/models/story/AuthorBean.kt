package com.zeienko.rxjavatutorialapp.data.net.models.story

data class AuthorBean(
    val value: String? = null,
    val matchLevel: String? = null,
    val matchedWords: MutableList<String>? = null
)