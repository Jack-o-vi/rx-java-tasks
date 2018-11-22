package com.zeienko.rxjavatutorialapp.data.net.api

import com.zeienko.rxjavatutorialapp.data.net.models.story.NewsBean
import com.zeienko.rxjavatutorialapp.data.net.models.user.UserBean
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AlgoliaApi {

    @GET("api/v1/users/{username}")
    fun getUsers(@Path("username") username: String?): Single<UserBean>

    @GET("api/v1/search_by_date?tags=story")
    fun getStories(@Query("page") page: Int?): Single<NewsBean>

    @GET("api/v1/search_by_date?tags=story")
    fun getStoriesInString(@Query("page") page: Int?): Single<ResponseBody>
}