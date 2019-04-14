package com.example.funretrofit.retrofit;

import com.example.funretrofit.ResponseList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BaseApiService {
    @GET("echo?user_content_key=8oq-wOy89O9O-1nJy_aBBfyt-rHqlf57mw5jQRSDILYpC8BX3vF3QFThuKv7VUpauHCqFURPLPlrCOQc64BiXD_Cx9_ESm4Im5_BxDlH2jW0nuo2oDemN9CCS2h10ox_1xSncGQajx_ryfhECjZEnLk7bwoqaLkRq8oON4ADOB2WgLJL8cgHd-Z82xxBhxSNVTj1HkpqLmaLYkfZ3y50kZcdv3aNHr6K&lib=MbdoUtcCtYWaBW4ERzY6s9jyKjfTj9dU6")
    Call<List<ResponseList>> getData();
}
