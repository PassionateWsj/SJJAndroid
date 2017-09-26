package com.intfocus.sjjandroid.login

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import com.intfocus.sjjandroid.R
import com.intfocus.sjjandroid.data.response.BaseResult
import com.intfocus.sjjandroid.data.response.login.VerificationResult
import com.intfocus.sjjandroid.net.ApiException
import com.intfocus.sjjandroid.net.CodeHandledSubscriber
import com.intfocus.sjjandroid.net.RetrofitUtil
import com.intfoucs.sjjandroid.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.item_login.*
import kotlinx.android.synthetic.main.item_register.*

/**
 * Created by liuruilin on 2017/9/23.
 */
class LoginActivity: BaseActivity() {
    private lateinit var ctx: Context
    private var accountIsExist = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ctx = this
        switchStatus(View(ctx))
    }

    /**
     * 登录/注册 切换
     */
    fun switchStatus(v: View) {
        fl_login_content.removeAllViews()
        if (accountIsExist) {
            accountIsExist = false
            var loginLayout = LinearLayout.inflate(ctx, R.layout.item_login, null)
            fl_login_content.addView(loginLayout, 0)
            et_login_mobile.addTextChangedListener(object : TextWatcher {
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (!et_login_password.text.isNullOrEmpty() && !p0.isNullOrEmpty()) {
                        btn_login.setBackgroundColor(resources.getColor(R.color.btn_active))
                    }
                    else {
                        btn_login.setBackgroundColor(resources.getColor(R.color.btn_not_active))
                    }
                }

                override fun afterTextChanged(p0: Editable?){}

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            })
            et_login_password.addTextChangedListener(object : TextWatcher {
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (!et_login_mobile.text.isNullOrEmpty() && !p0.isNullOrEmpty()) {
                        btn_login.setBackgroundColor(resources.getColor(R.color.btn_active))
                    }
                    else {
                        btn_login.setBackgroundColor(resources.getColor(R.color.btn_not_active))
                    }
                }

                override fun afterTextChanged(p0: Editable?){}

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            })
        }
        else {
            accountIsExist = true
            var registerLayout = LinearLayout.inflate(ctx, R.layout.item_register, null)
            fl_login_content.addView(registerLayout, 0)
            et_register_mobile.addTextChangedListener(object : TextWatcher {
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (!et_register_password.text.isNullOrEmpty() &&  !et_register_verification.text.isNullOrEmpty() && !p0.isNullOrEmpty()) {
                        btn_register.setBackgroundColor(resources.getColor(R.color.btn_active))
                    }
                    else {
                        btn_register.setBackgroundColor(resources.getColor(R.color.btn_not_active))
                    }
                }

                override fun afterTextChanged(p0: Editable?){}

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            })
            et_register_password.addTextChangedListener(object : TextWatcher {
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (!et_register_mobile.text.isNullOrEmpty() &&  !et_register_verification.text.isNullOrEmpty() && !p0.isNullOrEmpty()) {
                        btn_register.setBackgroundColor(resources.getColor(R.color.btn_active))
                    }
                    else {
                        btn_register.setBackgroundColor(resources.getColor(R.color.btn_not_active))
                    }
                }

                override fun afterTextChanged(p0: Editable?){}

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            })
            et_register_verification.addTextChangedListener(object : TextWatcher {
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (!et_register_password.text.isNullOrEmpty() &&  !et_register_mobile.text.isNullOrEmpty() && !p0.isNullOrEmpty()) {
                        btn_register.setBackgroundColor(resources.getColor(R.color.btn_active))
                    }
                    else {
                        btn_register.setBackgroundColor(resources.getColor(R.color.btn_not_active))
                    }
                }

                override fun afterTextChanged(p0: Editable?){}

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            })
        }
    }

    fun getVerification(v: View) {
        RetrofitUtil.getHttpService().register(et_register_mobile.text.toString())
                .compose(RetrofitUtil.CommonOptions<VerificationResult>())
                .subscribe(object : CodeHandledSubscriber<VerificationResult>() {
                    override fun onBusinessNext(data: VerificationResult?) {
                        Log.i("testlog", data!!.data!!.verificationCode)
                    }

                    override fun onCompleted() {
                        Log.i("testlog", "")

                    }

                    override fun onError(apiException: ApiException?) {
                        Log.i("testlog", apiException.toString())
                    }
                })
    }
}