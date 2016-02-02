package com.quliantrip.qulian.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.global.SimpleBackPage;
import com.quliantrip.qulian.ui.activity.SimpleBackActivity;

public class UIHelper {
    //显示的fragment类,bundule表示fragment的argument的参数，用来进行传递参数
    public static void showMyLogin(Fragment fragment, int requestCode) {
        showSimpleBackForResult(fragment, requestCode, SimpleBackPage.MY_LOGIN);
    }

    public static void showMyLogin(Activity activity, int requestCode) {
        showSimpleBackForResult(activity, requestCode, SimpleBackPage.MY_LOGIN);
    }

    public static void showMeSetting(Fragment fragment, int requestCode) {
        showSimpleBackForResult(fragment, requestCode, SimpleBackPage.MY_SETTING);
    }

    public static void showCityChoose(Fragment fragment, int requestCode,Bundle bundle) {
        showSimpleBackForResult(fragment, requestCode, SimpleBackPage.CITY_CHOOSE,bundle);
    }

    public static void showIntegral(Context context, Bundle bundle) {
        showSimpleBack(context, SimpleBackPage.MY_INTEGRAL, bundle);
    }

    public static void showRegister(Context context, Bundle bundle) {
        showSimpleBack(context, SimpleBackPage.MY_REGISTER, bundle);
    }

    public static void showSearchBackPassword(Context context, Bundle bundle) {
        showSimpleBack(context, SimpleBackPage.MY_SEARCH_BACK_PASSWORD, bundle);
    }

    public static void showGoodOrder(Context context, Bundle bundle) {
        showSimpleBack(context, SimpleBackPage.GOOD_ORDER, bundle);
    }

    //提交玩法订单详情
    public static void showPlayMethodOrder(Context context, Bundle bundle) {
        showSimpleBack(context, SimpleBackPage.PLAY_METHOD_ORDER, bundle);
    }

    public static void showPayMethod(Context context, Bundle bundle) {
        showSimpleBack(context, SimpleBackPage.PAY_METHOD, bundle);
    }

    public static void showMyCommonInfo(Context context, Bundle bundle) {
        showSimpleBack(context, SimpleBackPage.MY_COMMON_INFO, bundle);
    }

    //关于我们
    public static void showAboutMe(Context context, Bundle bundle) {
        showSimpleBack(context, SimpleBackPage.OABOUT_ME, bundle);
    }

    public static void showMyCommonInfo(Context context, Fragment fragment, int requestCode, Bundle args) {
        showSimpleBackForResult(fragment, requestCode, SimpleBackPage.MY_COMMON_INFO, args);
        ((Activity) context).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
    }

    public static void showMyDisCount(Context context, Bundle bundle) {
        showSimpleBack(context, SimpleBackPage.MY_DISCOUNT, bundle);
    }

    public static void showOrderConfirm(Context context, Bundle bundle) {
        showSimpleBack(context, SimpleBackPage.ORDER_CONFIRM, bundle);
    }
    public static void showPlayMethodOrderConfirm(Context context, Bundle bundle) {
        showSimpleBack(context, SimpleBackPage.PLAY_METHOD_ORDER_CONFIRM, bundle);
    }

    public static void showChangePassword(Context context, Bundle bundle) {
        showSimpleBack(context, SimpleBackPage.MY_CHANGE_PASSWORD, bundle);
        ((Activity) context).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
    }

    public static void showUserProcel(Context context, Bundle bundle) {
        showSimpleBack(context, SimpleBackPage.ME_USER_PROTOCEL, bundle);
        ((Activity) context).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
    }

    public static void showMyOrderDetail(Context context, Bundle bundle) {
        showSimpleBack(context, SimpleBackPage.ME_GOOD_ORDER_DETAIL, bundle);
        ((Activity) context).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
    }

    public static void showAddLinkMan(Context context, Fragment fragment, int requestCode, Bundle args) {
        showSimpleBackForResult(fragment, requestCode, SimpleBackPage.MY_ADD_LINKMAN, args);
        ((Activity) context).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
    }


    //下面是有返回值得简单返回的activity的操作
    public static void showSimpleBackForResult(Fragment fragment, int requestCode, SimpleBackPage page) {
        Intent intent = new Intent(fragment.getActivity(), SimpleBackActivity.class);
        intent.putExtra(SimpleBackActivity.BUNDLE_KEY_PAGE, page.getValue());
        fragment.startActivityForResult(intent, requestCode);
    }

    public static void showSimpleBackForResult(Fragment fragment, int requestCode, SimpleBackPage page, Bundle args) {
        Intent intent = new Intent(fragment.getActivity(), SimpleBackActivity.class);
        intent.putExtra(SimpleBackActivity.BUNDLE_KEY_PAGE, page.getValue());
        intent.putExtra(SimpleBackActivity.BUNDLE_KEY_ARGS, args);
        fragment.startActivityForResult(intent, requestCode);
    }

    public static void showSimpleBackForResult(Activity context, int requestCode, SimpleBackPage page, Bundle args) {
        Intent intent = new Intent(context, SimpleBackActivity.class);
        intent.putExtra(SimpleBackActivity.BUNDLE_KEY_PAGE, page.getValue());
        intent.putExtra(SimpleBackActivity.BUNDLE_KEY_ARGS, args);
        context.startActivityForResult(intent, requestCode);
    }

    public static void showSimpleBackForResult(Activity context, int requestCode, SimpleBackPage page) {
        Intent intent = new Intent(context, SimpleBackActivity.class);
        intent.putExtra(SimpleBackActivity.BUNDLE_KEY_PAGE, page.getValue());
        context.startActivityForResult(intent, requestCode);
    }

    public static void showSimpleBack(Context context, SimpleBackPage page) {
        Intent intent = new Intent(context, SimpleBackActivity.class);
        intent.putExtra(SimpleBackActivity.BUNDLE_KEY_PAGE, page.getValue());
        context.startActivity(intent);
    }

    public static void showSimpleBack(Context context, SimpleBackPage page,
                                      Bundle args) {
        Intent intent = new Intent(context, SimpleBackActivity.class);
        intent.putExtra(SimpleBackActivity.BUNDLE_KEY_ARGS, args);
        intent.putExtra(SimpleBackActivity.BUNDLE_KEY_PAGE, page.getValue());
        context.startActivity(intent);
    }

    /**
     * 返回富文本要显示的文字
     */
    public static SpannableStringBuilder parseActiveReply(String name, String body) {
        Spanned span = Html.fromHtml(body.trim());
        SpannableStringBuilder sp = new SpannableStringBuilder(name + "：");
        sp.append(span);
        // 设置用户名字体加粗、高亮
        // sp.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0,
        // name.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        sp.setSpan(new ForegroundColorSpan(Color.parseColor("#576B95")), 0,
                name.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return sp;
    }

//打开activity的操作
//    public static void showTeamActiveDetail(Context contex, int teamId,
//            TeamActive active) {
//        Intent intent = new Intent(contex, DetailActivity.class);
//
//        Bundle bundle = new Bundle();
//        bundle.putSerializable(TeamActiveFragment.DYNAMIC_FRAGMENT_KEY, active);
//        bundle.putInt(TeamActiveFragment.DYNAMIC_FRAGMENT_TEAM_KEY, teamId);
//        bundle.putInt(DetailActivity.BUNDLE_KEY_DISPLAY_TYPE,
//                DetailActivity.DISPLAY_TEAM_TWEET_DETAIL);
//        intent.putExtras(bundle);
//        contex.startActivity(intent);
//    }

    //    /**
//     * 发送通知广播
//     *
//     * @param context
//     * @param notice
//     */
//    public static void sendBroadCast(Context context, Notice notice) {
//        if (!((AppContext) context.getApplicationContext()).isLogin()
//                || notice == null)
//            return;
//        Intent intent = new Intent(Constants.INTENT_ACTION_NOTICE);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("notice_bean", notice);
//        intent.putExtras(bundle);
//        context.sendBroadcast(intent);
//    }

//        /**
    //     * 发送App异常崩溃报告
    //     *
    //     * @param cont
    //     * @param crashReport
    //     */
//    public static void sendAppCrashReport(final Context context,
//            final String crashReport) {
//        CommonDialog dialog = new CommonDialog(context);
//
//        dialog.setTitle(R.string.app_error);
//        dialog.setMessage(R.string.app_error_message);
//        dialog.setPositiveButton(R.string.submit_report,
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                        // 发送异常报告
//                        TDevice.sendEmail(context, "OSCAndroid客户端耍脾气 - 症状诊断报告",
//                                crashReport, "apposchina@163.com");
//                        // 退出
//                        AppManager.getAppManager().AppExit(context);
//                    }
//                });
//        dialog.setNegativeButton(R.string.cancle,
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                        // 退出
//                        AppManager.getAppManager().AppExit(context);
//                    }
//                });
//        dialog.show();
//    }
//
//    public static void sendAppCrashReport(final Context context) {
//        CommonDialog dialog = new CommonDialog(context);
//        dialog.setTitle(R.string.app_error);
//        dialog.setMessage(R.string.app_error_message);
//        dialog.setNegativeButton(R.string.ok,
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        System.exit(-1);
//                    }
//                });
//        dialog.show();
//    }
}
