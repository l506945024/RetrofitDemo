package com.example.bang.retrofitdemo.service;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by bang on 2017/2/21.
 */

public interface IGetDataFromService {
    @FormUrlEncoded
    @POST()
    Call<ResponseBody> post(@Url String url, @FieldMap Map<String, String> params);
}
