package com.example.bang.retrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bang.retrofitdemo.service.IGetDataFromService;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_main_request)
    Button mBtnMainRequest;
    @BindView(R.id.tv_main_request)
    TextView mTvMainRequest;
    @BindView(R.id.activity_main)
    LinearLayout mActivityMain;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_main_request)
    public void onClick() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://demo.aidpet.cn:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IGetDataFromService iGetDataFromService = retrofit.create(IGetDataFromService.class);
        Map<String, String> map = new HashMap<>();
        map.put("verifyCode", "123456");
        map.put("appType", "0");
        map.put("mobileId", "18682551685");
        map.put("loginType", "2");
        Call<ResponseBody> call = iGetDataFromService.post("/zwServer/zwLogin.action", map);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    Log.d(TAG, "onResponse: isSuccessful==" + response.isSuccessful());
                    Log.d(TAG, "onResponse: body==" + response.body().string());
                    Log.d(TAG, "onResponse: errorBody==" + response.errorBody());
                    Log.d(TAG, "onResponse: " + response.message());
                    Log.d(TAG, "onResponse: " + response.code());
                } catch (Exception e) {
                    Log.d(TAG, "onResponse:___________ " +e.toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.toString());
            }
        });
    }


}
