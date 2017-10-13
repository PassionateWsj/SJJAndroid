package com.intfocus.sjjandroid.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.intfocus.sjjandroid.R
import com.intfocus.sjjandroid.data.response.info.ProfessionResult
import com.intfocus.sjjandroid.login.adapter.ProfessionAdapter
import com.intfocus.sjjandroid.net.ApiException
import com.intfocus.sjjandroid.net.CodeHandledSubscriber
import com.intfocus.sjjandroid.net.RetrofitUtil
import kotlinx.android.synthetic.main.activity_choose_career.*

class ChooseCareerActivity : AppCompatActivity() {

    private var adapter: ProfessionAdapter? = null
    private var userId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_career)

        initData()
        initAdapter()
        loadData()
    }

    private fun initData() {
        userId = intent.getIntExtra("userId", 0)
    }

    private fun initAdapter() {
        adapter = ProfessionAdapter(this,userId)
        rv_choose_career.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_choose_career.adapter = adapter
    }

    private fun loadData() {

        RetrofitUtil.getHttpService().professions
                .compose(RetrofitUtil.CommonOptions<ProfessionResult>())
                .subscribe(object : CodeHandledSubscriber<ProfessionResult>() {
                    override fun onError(apiException: ApiException?) {
                    }

                    override fun onBusinessNext(data: ProfessionResult?) {
//                        initAdapter(data)
                        adapter!!.setData(data!!.data!!)
                    }

                    override fun onCompleted() {
                    }

                })
    }

}
