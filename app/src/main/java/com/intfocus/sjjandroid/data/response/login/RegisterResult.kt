package com.intfocus.sjjandroid.data.response.login

import com.intfocus.sjjandroid.data.response.BaseResult

/**
 * ****************************************************
 * author: jameswong
 * created on: 17/09/26 下午5:05
 * e-mail: PassionateWsj@outlook.com
 * name:
 * desc:
 * ****************************************************
 */
class RegisterResult : BaseResult() {

    /**
     * msg : 请求数据成功!
     * code : 0
     * data : [{"password":"123123","updated_at":"2017-09-26 16:53:32","name":null,"mobile":"13111111111","description":null,"created_at":"2017-09-26 16:53:32","uuid":"a589a128-d846-42de-93d2-4887f1d5f0d5","profession_ids":null}]
     */

    private var data: List<DataBean>? = null

    class DataBean {
        /**
         * password : 123123
         * updated_at : 2017-09-26 16:53:32
         * name : null
         * mobile : 13111111111
         * description : null
         * created_at : 2017-09-26 16:53:32
         * uuid : a589a128-d846-42de-93d2-4887f1d5f0d5
         * profession_ids : null
         */

        var password: String? = null
        var updated_at: String? = null
        var name: Any? = null
        var mobile: String? = null
        var description: Any? = null
        var created_at: String? = null
        var uuid: String? = null
        var profession_ids: Any? = null
    }
}