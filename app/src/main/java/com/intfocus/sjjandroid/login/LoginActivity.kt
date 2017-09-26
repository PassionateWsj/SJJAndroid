package com.intfocus.sjjandroid.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.intfocus.sjjandroid.R
import com.intfocus.sjjandroid.data.response.login.LoginResult
import com.intfocus.sjjandroid.data.response.login.RegisterResult
import com.intfocus.sjjandroid.data.response.login.VerificationResult
import com.intfocus.sjjandroid.net.ApiException
import com.intfocus.sjjandroid.net.CodeHandledSubscriber
import com.intfocus.sjjandroid.net.RetrofitUtil
import com.intfoucs.sjjandroid.BaseActivity
import com.intfoucs.sjjandroid.ToastUtils
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.item_login.*
import kotlinx.android.synthetic.main.item_register.view.*


/**
 * Created by liuruilin on 2017/9/23.
 */
class LoginActivity : BaseActivity() {
    private val TAG = "hjjzz"

    private lateinit var ctx: Context
    private var accountIsExist = true
    private var loginAllow = false
    private var registerAllow = false
    private var loginLayout: View? = null
    private var registerLayout: View? = null

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
            if (loginLayout == null) {
                loginLayout = LinearLayout.inflate(ctx, R.layout.item_login, null)
            }
            fl_login_content.addView(loginLayout, 0)
            et_login_mobile.addTextChangedListener(object : TextWatcher {
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    changeButtonStyle(et_login_password.text.toString(), p0.toString(), btn_login)
                }

                override fun afterTextChanged(p0: Editable?) {}

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            })
            et_login_password.addTextChangedListener(object : TextWatcher {
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    changeButtonStyle(et_login_mobile.text.toString(), p0.toString(), btn_login)
                }

                override fun afterTextChanged(p0: Editable?) {}

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            })
        } else {
            timer.cancel()
            registerLayout = LinearLayout.inflate(ctx, R.layout.item_register, null)
            fl_login_content.addView(registerLayout, 0)
            registerLayout!!.et_register_mobile.addTextChangedListener(object : TextWatcher {
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    changeButtonStyle(registerLayout!!.et_register_password.text.toString(), registerLayout!!.et_register_verification.text.toString(), p0.toString(), registerLayout!!.btn_register)
                }

                override fun afterTextChanged(p0: Editable?) {}

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            })
            registerLayout!!.et_register_password.addTextChangedListener(object : TextWatcher {
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    changeButtonStyle(registerLayout!!.et_register_mobile.text.toString(), registerLayout!!.et_register_verification.text.toString(), p0.toString(), registerLayout!!.btn_register)
                }

                override fun afterTextChanged(p0: Editable?) {
                    if (p0.toString().isNotEmpty()) {
                        registerLayout!!.cb_register_see_pwd.visibility = View.VISIBLE
                    } else {
                        registerLayout!!.cb_register_see_pwd.visibility = View.GONE
                    }
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            })
            registerLayout!!.et_register_verification.addTextChangedListener(object : TextWatcher {
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    changeButtonStyle(registerLayout!!.et_register_password.text.toString(), registerLayout!!.et_register_mobile.text.toString(), p0.toString(), registerLayout!!.btn_register)
                }

                override fun afterTextChanged(p0: Editable?) {}

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            })
            registerLayout!!.et_register_password.setOnFocusChangeListener { v, hasFocus ->
                if (registerLayout!!.et_register_password.text.isNotEmpty() && hasFocus) {
                    registerLayout!!.cb_register_see_pwd.visibility = View.VISIBLE
                } else {
                    registerLayout!!.cb_register_see_pwd.visibility = View.GONE
                }
            }
        }
        accountIsExist = !accountIsExist
    }

    fun changeButtonStyle(checkNull1: String, checkNull2: String, inputString: String, button: TextView) {
        if (!checkNull1.isNullOrEmpty() && !checkNull2.isNullOrEmpty() && !inputString.isNullOrEmpty()) {
            button.background = resources.getDrawable(R.drawable.background_login_verificatoin_code_botton_press)
            button.setTextColor(ContextCompat.getColor(this, R.color.co10))
            registerAllow = true
        } else {
            button.background = resources.getDrawable(R.drawable.background_login_verificatoin_code_botton)
            button.setTextColor(ContextCompat.getColor(this, R.color.co7))
            registerAllow = false
        }

    }

    fun changeButtonStyle(checkNull1: String, inputString: String, button: TextView) {
        if (!checkNull1.isNullOrEmpty() && !inputString.isNullOrEmpty()) {
            button.background = resources.getDrawable(R.drawable.background_login_verificatoin_code_botton_press)
            button.setTextColor(ContextCompat.getColor(this, R.color.co10))
            loginAllow = true
        } else {
            button.background = resources.getDrawable(R.drawable.background_login_verificatoin_code_botton)
            button.setTextColor(ContextCompat.getColor(this, R.color.co7))
            loginAllow = false
        }
    }

    private val timer = object : CountDownTimer(60000, 1000) {

        override fun onTick(millisUntilFinished: Long) {
            registerLayout!!.tv_get_verification.text = "(" + (millisUntilFinished / 1000) + ")后重试"
        }

        override fun onFinish() {
            registerLayout!!.tv_get_verification.isEnabled = true
            registerLayout!!.tv_get_verification.setTextColor(ContextCompat.getColor(this@LoginActivity, R.color.co7))
            registerLayout!!.tv_get_verification.background = resources.getDrawable(R.drawable.background_login_verificatoin_code_botton)
            registerLayout!!.tv_get_verification.text = "获取验证码"
        }
    }

    /**
     * 根据CheckBox选中情况更改EditText输入类型
     */
    private fun setEditTextInputTypeByCheckBox(mEditText: EditText, mCheckBox: CheckBox) {
        if (mCheckBox.isChecked) {
            mEditText.transformationMethod = HideReturnsTransformationMethod.getInstance()
        } else {
            mEditText.transformationMethod = PasswordTransformationMethod.getInstance()
        }
    }

    fun clickButton(v: View) {
        when {
            v.id == R.id.cb_register_see_pwd -> {
                setEditTextInputTypeByCheckBox(registerLayout!!.et_register_password, registerLayout!!.cb_register_see_pwd)
            }
            v.id == R.id.tv_get_verification -> {
                if (registerLayout!!.et_register_mobile.text.toString().matches(Regex("[1][34578]\\d{9}"))) {
                    // 获取验证码
                    RetrofitUtil.getHttpService().getVerificationCode(registerLayout!!.et_register_mobile.text.toString())
                            .compose(RetrofitUtil.CommonOptions<VerificationResult>())
                            .subscribe(object : CodeHandledSubscriber<VerificationResult>() {
                                override fun onBusinessNext(data: VerificationResult?) {
//                                    Log.i(TAG, "onBusinessNext:: " + data!!.data!!.verificationCode)
                                    ToastUtils.show(this@LoginActivity, data!!.data!!.verificationCode!!)

                                }

                                override fun onCompleted() {
                                    Log.i(TAG, "onCompleted")

                                }

                                override fun onError(apiException: ApiException?) {
                                    Log.i(TAG, "onError:: " + apiException!!.displayMessage)
                                }
                            })

                    registerLayout!!.tv_get_verification.setTextColor(ContextCompat.getColor(this@LoginActivity, R.color.co10))
                    registerLayout!!.tv_get_verification.background = resources.getDrawable(R.drawable.background_login_verificatoin_code_botton_press)
                    timer.start()
                } else {
                    registerLayout!!.tv_get_verification.text = "获取验证码"
                    registerLayout!!.tv_get_verification.setTextColor(ContextCompat.getColor(this, R.color.co7))
                    registerLayout!!.tv_get_verification.background = resources.getDrawable(R.drawable.background_login_verificatoin_code_botton)
                    ToastUtils.show(this, "手机号不合法")
                }
            }
            v.id == R.id.btn_login && loginAllow -> {
                if (et_login_mobile.text.toString().matches(Regex("[1][34578]\\d{9}"))) {
                    Log.i(TAG, "登录")
                    // todo 用户登录
                    RetrofitUtil.getHttpService()
                            .userLogin(et_login_mobile.text.toString(),
                                    et_login_password.toString())
                            .compose(RetrofitUtil.CommonOptions<LoginResult>())
                            .subscribe(object : CodeHandledSubscriber<LoginResult>() {
                                override fun onCompleted() {
                                    Log.i(TAG, "onCompleted")

                                }

                                override fun onError(apiException: ApiException?) {
                                    ToastUtils.show(this@LoginActivity, apiException!!.displayMessage)
                                }

                                override fun onBusinessNext(data: LoginResult?) {
//                                    ToastUtils.show(this@LoginActivity, "登录成功")
                                    if (data!!.data!![0].profession_ids == null) {
                                        // todo 用户职业为 null 则跳转职业选择界面
                                        startActivity(Intent(this@LoginActivity,ChooseCareerActivity::class.java))
                                    }
                                }

                            })
                } else {
                    ToastUtils.show(this, "手机号不合法")
                }
            }
            v.id == R.id.btn_register && registerAllow -> {
                Log.i(TAG, "注册")
                // todo 注册
                RetrofitUtil.getHttpService()
                        .register(registerLayout!!.et_register_mobile.text.toString(),
                                registerLayout!!.et_register_password.toString(),
                                registerLayout!!.et_register_verification.toString())
                        .compose(RetrofitUtil.CommonOptions<RegisterResult>())
                        .subscribe(object : CodeHandledSubscriber<RegisterResult>() {
                            override fun onCompleted() {
                                Log.i(TAG, "onCompleted")
                                switchStatus(View(ctx))
                            }

                            override fun onError(apiException: ApiException?) {
                                ToastUtils.show(this@LoginActivity, apiException!!.displayMessage)
                            }

                            override fun onBusinessNext(data: RegisterResult?) {
                                ToastUtils.show(this@LoginActivity, "注册成功")
                            }

                        })
            }
        }
    }

}
