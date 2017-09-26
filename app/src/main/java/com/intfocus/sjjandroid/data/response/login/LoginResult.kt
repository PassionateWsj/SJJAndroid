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
    var data: List<DataBean>? = null

    class DataBean {
        /**
         * password : 123123
         * updated_at : 2017-09-17 23:39:09
         * name : hzl
         * mobile : 15278618270
         * description : asdasd
         * created_at : 2017-09-17 23:39:09
         * uuid : 7a0d5b14-dbc6-4f3f-b277-e72b9d4d5d56
         * profession_ids : null
         */

        var password: String? = null
        var updated_at: String? = null
        var name: String? = null
        var mobile: String? = null
        var description: String? = null
        var created_at: String? = null
        var uuid: String? = null
        var profession_ids: Any? = null
    }
}