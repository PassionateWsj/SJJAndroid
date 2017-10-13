package com.intfocus.sjjandroid.net;

import com.intfocus.sjjandroid.constant.API;
import com.intfocus.sjjandroid.data.response.BaseResult;
import com.intfocus.sjjandroid.data.response.info.ProfessionResult;
import com.intfocus.sjjandroid.data.response.login.LoginResult;
import com.intfocus.sjjandroid.data.response.login.RegisterResult;
import com.intfocus.sjjandroid.data.response.login.VerificationResult;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by CANC on 2017/7/31.
 */

public interface HttpService {

    /**
     * 获取注册验证码
     * <p>
     * GET
     * api/v1/register/verificationcode
     * <p>
     * keyname
     */
    @GET(API.API_GET_VERIFICATION_CODE)
    Observable<VerificationResult> getVerificationCode(@Query("mobile") String mobile);

    /**
     * 申请注册
     * <p>
     * GET
     * api/v1/register/user
     * <p>
     * keyname
     */
    @GET(API.API_REGISTER_USER)
    Observable<RegisterResult> register(@Query("mobile") String mobile, @Query("password") String password, @Query("verificationCode") String verificationCode);

    /**
     * 用户登录
     * <p>
     * GET
     * api/v1/login/user
     * <p>
     * keyname
     */
    @GET(API.API_LOGIN_USER)
    Observable<LoginResult> userLogin(@Query("mobile") String mobile, @Query("password") String password);

    /**
     * 获取职业列表
     * <p>
     * GET
     * api/v1/list/professions
     * <p>
     * keyname
     */
    @GET(API.API_GET_PROFESSIONS)
    Observable<ProfessionResult> getProfessions();

    /**
     * 关联职业列表
     * <p>
     * GET
     * api/v1/user/professions
     * <p>
     * keyname
     */
    @GET(API.API_BIND_PROFESSIONS)
    Observable<BaseResult> bindProfessions(@Query("userId") int userId, @Query("professionIds") int professionIds);


}
