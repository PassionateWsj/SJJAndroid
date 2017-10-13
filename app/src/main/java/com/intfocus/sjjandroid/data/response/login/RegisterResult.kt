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

    private var data: DataBean? = null

    class DataBean {

        /**
         * password : 1
         * user_type : 0
         * uuser_avatar : http://img.nasin.org:88/wsc_pic/base/dc7539600a604f17952faff58e777ae2.jpg
         * user_id : 1293
         * user_mobile : 1
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
         var profession_id: Int =0
         var user_name: String? = null
         var profession_name: String? = null
         var profession_icon: String? = null

    }
}