package com.intfocus.sjjandroid.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.intfocus.sjjandroid.R
import com.intfocus.sjjandroid.utils.ToastColor
import com.intfoucs.sjjandroid.ToastUtils

class ChooseCareerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_career)
    }

    fun clickCareer(view: View) {
        when (view.id) {
            R.id.iv_choose_career_student -> {
                ToastUtils.show(this, "学生",ToastColor.SUCCESS)
            }
            R.id.iv_choose_career_data_man -> {
                ToastUtils.show(this, "数据人",ToastColor.SUCCESS)
            }
            R.id.iv_choose_career_running -> {
                ToastUtils.show(this, "运营汪",ToastColor.SUCCESS)
            }

        }
    }
}
