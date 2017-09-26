package com.intfocus.sjjandroid.data.response.login

import com.google.gson.annotations.SerializedName
import com.intfocus.sjjandroid.data.response.BaseResult

/**
 * Created by liuruilin on 2017/9/24.
 */

class VerificationResult: BaseResult() {
    @SerializedName("data")
    var data: Data? = null

    inner class Data {
        var verificationCode: String? = null
    }
}
