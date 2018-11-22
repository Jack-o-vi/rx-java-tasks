package com.zeienko.rxjavatutorialapp.data.net.models.user

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/*
{"id": 912833,
"username": "crunchiebones",
"about": "smth",
"karma": 613,
"created_at": "2016-03-17T00:13:12.000Z",
"avg": null,
"delay": null,
"submitted": 136,
"updated_at": "2018-10-17T11:37:36.000Z",
"submission_count": 85,
"comment_count": 16,
"created_at_i": 1458173592,
"objectID": "crunchiebones"}
 */

data class UserBean constructor(
    var id: Int? = null,
    var username: String? = null,
    var about: String? = null,
    var karma: Int? = null,
    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null,
    var avg: Any? = null,
    var delay: Any? = null,
    var submitted: Int? = null,
    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null,
    @SerializedName("submission_count")
    @Expose
    var submissionCount: Int? = null,
    @SerializedName("comment_count")
    @Expose
    var commentCount: Int? = null,
    @SerializedName("created_at_i")
    @Expose
    var createdAtI: Int? = null,
    var objectID: String? = null
)
