package com.intfocus.sjjandroid.data.response.login

import com.intfocus.sjjandroid.data.response.BaseResult

/**
 * ****************************************************
 * author: jameswong
 * created on: 17/09/26 下午5:30
 * e-mail: PassionateWsj@outlook.com
 * name:
 * desc:
 * ****************************************************
 */
class LoginResult : BaseResult() {
    var data: DataBean? = null

    class DataBean {
        /**
         * password : 123123
         * user_type : 0
         * uuser_avatar : http://img.nasin.org:88/wsc_pic/base/dc7539600a604f17952faff58e777ae2.jpg
         * user_id : 1292
         * user_mobile : 152786182701
         * profession_id : null
         * user_name :
         * profession_name : null
         * profession_icon : null
         */

        var password: String? = null
        var user_type: String? = null
        var uuser_avatar: String? = null
        var user_id: Int = 0
        var user_mobile: String? = null
        var profession_id: Int = 0
        var user_name: String? = null
        var profession_name: String? = null
        var profession_icon: String? = null

    }
}