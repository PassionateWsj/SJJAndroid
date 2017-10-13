package com.intfocus.sjjandroid.login.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.intfocus.sjjandroid.R
import com.intfocus.sjjandroid.data.response.BaseResult
import com.intfocus.sjjandroid.data.response.info.ProfessionResult
import com.intfocus.sjjandroid.listener.NoDoubleClickListener
import com.intfocus.sjjandroid.login.ChooseCareerActivity
import com.intfocus.sjjandroid.main.MainActivity
import com.intfocus.sjjandroid.net.ApiException
import com.intfocus.sjjandroid.net.CodeHandledSubscriber
import com.intfocus.sjjandroid.net.RetrofitUtil
import com.intfoucs.sjjandroid.ToastUtils

/**
 * ****************************************************
 * author: jameswong
 * created on: 17/10/09 上午10:46
 * e-mail: PassionateWsj@outlook.com
 * name:
 * desc:
 * ****************************************************
 */
class ProfessionAdapter(private val ctx: Context, private val userId: Int) : RecyclerView.Adapter<ProfessionAdapter.ViewHolder>() {
    companion object {
        private val TAG = "hjjzz"
    }

    var mData: List<ProfessionResult.DataBean>? = null

    override fun getItemCount(): Int {
        return if (mData == null) 0 else mData!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val inflate = LayoutInflater.from(ctx).inflate(R.layout.item_profession_list, parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        if (mData != null) {
            Glide.with(ctx)
                    .load(mData!![position].profession_icon)
                    .into(holder!!.ivProfession)
            holder.ivProfession.setOnClickListener(object : NoDoubleClickListener() {
                override fun onNoDoubleClick(v: View?) {
                    Log.i(TAG, "$userId 点击：" + mData!![position].profession_name + " " + mData!![position].profession_id)
                    RetrofitUtil.getHttpService().bindProfessions(userId, mData!![position].profession_id)
                            .compose(RetrofitUtil.CommonOptions<BaseResult>())
                            .subscribe(object : CodeHandledSubscriber<BaseResult>() {
                                override fun onError(apiException: ApiException?) {
                                    ToastUtils.show(ctx, apiException!!.displayMessage)
                                }

                                override fun onBusinessNext(data: BaseResult?) {
                                    ToastUtils.show(ctx, data!!.msg!!)
                                    ctx.startActivity(Intent(ctx, MainActivity::class.java))
                                    (ctx as ChooseCareerActivity).finish()
                                }

                                override fun onCompleted() {
                                }

                            })
                }
            })
        }
    }

    fun setData(data: List<ProfessionResult.DataBean>) {
        mData = data
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val ivProfession = itemView!!.findViewById(R.id.iv_profession) as ImageView
    }
}