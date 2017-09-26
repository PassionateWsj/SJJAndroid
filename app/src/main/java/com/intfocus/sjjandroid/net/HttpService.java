package com.intfocus.sjjandroid.net;

import com.intfocus.sjjandroid.constant.API;
import com.intfocus.sjjandroid.data.response.BaseResult;
import com.intfocus.sjjandroid.data.response.login.VerificationResult;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by CANC on 2017/7/31.
 */

public interface HttpService {

    /**
     * 申请注册
     *
     * GET
     * /api/v1.1/config/info
     *
     * keyname
     */
    @GET(API.REGISTER)
    Observable<VerificationResult> register(@Query("mobile") String mobile);
}
