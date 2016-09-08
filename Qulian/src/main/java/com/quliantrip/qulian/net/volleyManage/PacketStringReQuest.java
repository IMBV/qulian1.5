package com.quliantrip.qulian.net.volleyManage;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.global.QulianApplication;

import org.json.JSONObject;

import java.util.Map;

public class PacketStringReQuest extends StringRequest {
    private Map map;//在请求体中的post请求

    //普通的不是选着界面跳转的问题
    public PacketStringReQuest(String url, BaseJson object, Map map){
        this(url,object,map,null);
    }

    //简单的get请求url后面加的是参数
    public PacketStringReQuest(String url, BaseJson object){
        this(url, object, new ResponseListenner.OnLoadFinishListener() {
            @Override
            public void onLoadFinish(int object) {

            }
        });
    }

    //带有界面条状的监听的界面
    public PacketStringReQuest(String url, BaseJson object, Map map, ResponseListenner.OnLoadFinishListener onLoadFinishListener) {
        super(Method.POST, url, new ResponseListenner(object, onLoadFinishListener), new ResponseErrorListener(onLoadFinishListener, object));
        this.map = map;
//        map.put("Authorization", "Bearer a313964a1b4a23c8b90370a11edb035d9d9334a9");
        QulianApplication.getRequestQueue().add(this);
    }

    public PacketStringReQuest(String url, BaseJson object, ResponseListenner.OnLoadFinishListener onLoadFinishListener) {
        super(url, new ResponseListenner(object, onLoadFinishListener), new ResponseErrorListener(onLoadFinishListener, object));
        QulianApplication.getRequestQueue().add(this);
    }

    //该方法是进行数post请求添加请求体
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
