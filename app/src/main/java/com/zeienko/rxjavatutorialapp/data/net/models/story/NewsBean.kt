package com.zeienko.rxjavatutorialapp.data.net.models.story

data class NewsBean(
    val hits: MutableList<HitBean>? = null,
    val page: Int? = null,
    val nbHits: Int? = null,
    val nbPages: Int? = null,
    val hitsPerPage: Int? = null,
    val processingTimeMS: Int? = null,
    val query: String? = null,
    val params: String? = null
)