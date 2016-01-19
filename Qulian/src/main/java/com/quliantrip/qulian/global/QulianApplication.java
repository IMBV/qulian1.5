package com.quliantrip.qulian.global;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.text.TextUtils;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.quliantrip.qulian.domain.LoginDataBean;
import com.quliantrip.qulian.domain.UserInfoBean;
import com.quliantrip.qulian.util.CommonHelp;

import java.util.Properties;
import java.util.UUID;

/**
 * 应用全局的变量
 */
public class QulianApplication extends Application {

    private static Handler mainHandler;
    private static RequestQueue queue;
    private static Context mContext;
    //登陆的用户信息
    private String userId;
    private boolean isLogin;
    private LoginDataBean user;

    private static QulianApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        queue = Volley.newRequestQueue(getApplicationContext());

        instance = this;
        mContext = this;
        mainHandler = new Handler();
        initImageLoader(getContext());
        initLogin();
    }

    public static QulianApplication getInstance() {
        return instance;
    }


    public boolean containsProperty(String key) {
        Properties props = getProperties();
        return props.containsKey(key);
    }

    public void setProperties(Properties ps) {
        AppConfig.getAppConfig(this).set(ps);
    }

    public Properties getProperties() {
        return AppConfig.getAppConfig(this).get();
    }

    public void setProperty(String key, String value) {
        AppConfig.getAppConfig(this).set(key, value);
    }


    public String getProperty(String key) {
        String res = AppConfig.getAppConfig(this).get(key);
        return res;
    }

    public void removeProperty(String... key) {
        AppConfig.getAppConfig(this).remove(key);
    }


    private void initLogin() {
        user = getLoginUser();
        if (null != user && user.getAuth_key() != null) {
            this.isLogin = true;
            userId = user.getAuth_key();
        } else {
            this.cleanLoginInfo();
        }
    }

    /**
     * 获取App唯一标识
     *
     * @return
     */
    public String getAppId() {
        String uniqueID = getProperty(AppConfig.CONF_APP_UNIQUEID);
        if (TextUtils.isEmpty(uniqueID)) {
            uniqueID = UUID.randomUUID().toString();
            setProperty(AppConfig.CONF_APP_UNIQUEID, uniqueID);
        }
        return uniqueID;
    }

    /**
     * 获取App安装包信息
     *
     * @return
     */
    public PackageInfo getPackageInfo() {
        PackageInfo info = null;
        try {
            info = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace(System.err);
        }
        if (info == null)
            info = new PackageInfo();
        return info;
    }


    /**
     * 更新用户信息
     *
     * @param user
     */
    @SuppressWarnings("serial")
    public void updateUserInfo(final LoginDataBean user) {
        setProperties(new Properties() {
            {
                setProperty("user.name", user.getUsername());
                setProperty("user.auth_key", user.getAuth_key());
                if (user.getMobile() != null)
                    setProperty("user.mobile", user.getMobile());
                if (user.getEmail() != null)
                    setProperty("user.email", user.getEmail());
            }
        });
    }

    public String getLoginUid() {
        return userId;
    }

    public boolean isLogin() {
        return this.isLogin;
    }


    /**
     * 用户注销
     */
    public void Logout() {
        cleanLoginInfo();
        this.isLogin = false;
        this.userId = "0";
    }

    /**
     * 保存登录信息
     */
    public void saveUserInfo(final LoginDataBean user) {
        this.userId = user.getAuth_key();
        this.isLogin = true;
        setProperties(new Properties() {
            {
                setProperty("user.name", user.getUsername());
                setProperty("user.auth_key", user.getAuth_key());
                if (user.getMobile() != null)
                    setProperty("user.mobile", user.getMobile());
                if (user.getEmail() != null)
                    setProperty("user.email", user.getEmail());
            }
        });
    }

    /**
     * 获得登录用户的信息
     *
     * @return
     */
    public LoginDataBean getLoginUser() {
        LoginDataBean user = new LoginDataBean();
        user.setUsername(getProperty("user.name"));
        user.setAuth_key(getProperty("user.auth_key"));
        if (user.getMobile() != null)
            user.setMobile(getProperty("user.mobile"));
        if (user.getEmail() != null)
            user.setEmail(getProperty("user.email"));
        return user;
    }

    /**
     * 清除登录信息
     */
    public void cleanLoginInfo() {
        removeProperty("user.name", "user.auth_key", "user.mobile", "user.email");
    }

    //这里是返回voley的消息的队列
    public static RequestQueue getRequestQueue() {
        return queue;
    }

    // 添加获取的方法进行调用
    public static Context getContext() {
        return mContext;
    }

    //获取应用的handle
    public static Handler getMainHandler() {
        return mainHandler;
    }

    //初始化imageLoader
    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(
                context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();// 不会在内存中缓存多个大小的图片
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());// 为了保证图片名称唯一
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        // 内存缓存大小默认是：app可用内存的1/8
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
        // ImageLoader.getInstance().init(
        // ImageLoaderConfiguration.createDefault(this));
    }
}
