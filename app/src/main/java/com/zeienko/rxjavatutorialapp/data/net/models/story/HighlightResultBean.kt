package com.chisw.data.net.model.story

import com.zeienko.rxjavatutorialapp.data.net.models.story.AuthorBean
import com.zeienko.rxjavatutorialapp.data.net.models.story.TitleBean
import com.zeienko.rxjavatutorialapp.data.net.models.story.UrlBean

data class HighlightResultBean(val title: TitleBean? = null,
                               val url: UrlBean? = null,
                               val author: AuthorBean? = null)