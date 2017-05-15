package com.wx.myproject.apptest.http;

import com.wx.myproject.apptest.http.bean.BaseEntity;
import com.wx.myproject.apptest.http.bean.UserInfo;
import com.wx.myproject.apptest.http.bean.VideoUrl;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by wwq on 2017/5/15.
 */

public interface RetrofitService {

    @POST("account/login")
    Observable<BaseEntity<UserInfo>> login(
            @Field("userId") String userId,
            @Field("password") String password
    );

    @GET("video/getUrl")
    Observable<BaseEntity<VideoUrl>> getVideoUrl(
            @Query("id") long id
    );

    @FormUrlEncoded
    @POST("user/addVideo")
    Observable<BaseEntity<Boolean>> addVideo(
            @FieldMap Map<String, Object> map
    );
}
