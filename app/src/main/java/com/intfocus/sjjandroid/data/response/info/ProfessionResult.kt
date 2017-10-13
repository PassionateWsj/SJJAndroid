package com.intfocus.sjjandroid.data.response.info

import com.intfocus.sjjandroid.data.response.BaseResult
import com.intfocus.sjjandroid.data.response.login.LoginResult

/**
 * ****************************************************
 * author: jameswong
 * created on: 17/10/09 上午10:05
 * e-mail: PassionateWsj@outlook.com
 * name:
 * desc:
 * ****************************************************
 */
class ProfessionResult : BaseResult() {
    var data: List<ProfessionResult.DataBean>? = null

    class DataBean {
        /**
         * profession_id : 19
         * profession_remark : 学生职业
         * profession_name : 学生
         * profession_icon : http://img.nasin.org:88/wsc_pic/base/dc7539600a604f17952faff58e777ae2.jpg
         * profession_order : null
         */

        var profession_id: Int = 0
        var profession_remark: String? = null
        var profession_name: String? = null
        var profession_icon: String? = null
        var profession_order: Any? = null
    }
}