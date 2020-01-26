package com.developersancho.remote.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class License(

    @SerializedName("key")
    var key: String? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("node_id")
    var nodeId: String? = null,

    @SerializedName("spdx_id")
    var spdxId: String? = null,

    @SerializedName("url")
    var url: String? = null

) : Serializable