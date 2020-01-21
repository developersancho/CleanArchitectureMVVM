package com.developersancho.remote.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Owner(

    @SerializedName("avatar_url")
    var avatarUrl: String? = null,

    @SerializedName("events_url")
    var eventsUrl: String? = null,

    @SerializedName("followers_url")
    var followersUrl: String? = null,

    @SerializedName("following_url")
    var followingUrl: String? = null,

    @SerializedName("gists_url")
    var gistsUrl: String? = null,

    @SerializedName("gravatar_id")
    var gravatarId: String? = null,

    @SerializedName("html_url")
    var htmlUrl: String? = null,

    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("login")
    var login: String? = null,

    @SerializedName("node_id")
    var nodeId: String? = null,

    @SerializedName("organizations_url")
    var organizationsUrl: String? = null,

    @SerializedName("received_events_url")
    var receivedEventsUrl: String? = null,

    @SerializedName("repos_url")
    var reposUrl: String? = null,

    @SerializedName("site_admin")
    var siteAdmin: Boolean = false,

    @SerializedName("starred_url")
    var starredUrl: String? = null,

    @SerializedName("subscriptions_url")
    var subscriptionsUrl: String? = null,

    @SerializedName("type")
    var type: String? = null,

    @SerializedName("url")
    var url: String? = null

) : Serializable