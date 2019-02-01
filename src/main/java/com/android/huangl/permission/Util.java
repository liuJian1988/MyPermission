package com.android.huangl.permission;
//                  _ooOoo_
//                 o8888888o
//                 88" . "88
//                 (| -_- |)
//                 O\  =  /O
//              ____/`---'\____
//            .'  \\|     |//  `.
//           /  \\|||  :  |||//  \
//          /  _||||| -:- |||||-  \
//          |   | \\\  -  /// |   |
//          | \_|  ''\---/''  |   |
//           \  .-\__  `-`  ___/-. /
//         ___`. .'  /--.--\  `. . __
//      ."" '<  `.___\_<|>_/___.'  >'"".
//    | | :  `- \`.;`\ _ /`;.`/ - ` : | |
//    \  \ `-.   \_ __\ /__ _/   .-` /  /
//=====`-.____`-.___\_____/___.-`____.-'======
//                  `=---='
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//         佛祖保佑       永无BUG

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;

/**
 * author：huangl on 2017-01-17 14:00
 * email：278168565@qq.com
 * <p>
 * des：
 */
public class Util {
    /**
     * get SDK version
     */
    @TargetApi(Build.VERSION_CODES.M)
    public static boolean getAndroidOSVersion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            return true;
        else
            return false;
    }

    /**
     * Look for failed permissions
     *
     * @param activity
     * @param permissions
     */
    public static ArrayList<String> findNoPassPermissions(Activity activity, String[] permissions) {
        ArrayList<String> list = new ArrayList<>();
        for (String s : permissions) {
            if ((ContextCompat.checkSelfPermission(activity, s) != PackageManager.PERMISSION_GRANTED)) {
                list.add(s);
            }
        }
        return list;
    }

    public static Activity getActivity(Object object) {
        if (object instanceof Fragment) {
            return ((Fragment) object).getActivity();
        } else if (object instanceof Activity) {
            return (Activity) object;
        }
        return null;
    }

    /**
     * Judge authority
     */
    @TargetApi(Build.VERSION_CODES.M)
    public static void judgePermissions(Object object,PermissionResult result, int requestcode, String[] permissions) {
        if (Util.getAndroidOSVersion()) { //determine whether the SDK version is greater than 23, that is, whether it is a system with Android 6.0 or above
            ArrayList<String> request = findNoPassPermissions(Util.getActivity(object), permissions);
            if (request.size() > 0) {
                if (object instanceof Activity) {
                    ActivityCompat.requestPermissions((Activity) object, request.toArray(new String[request.size()]), requestcode);
                } else if (object instanceof Fragment) {
                    ActivityCompat.requestPermissions(((Fragment) object).getActivity(), request.toArray(new String[request.size()]), requestcode);
                }
            } else {
                result.result(requestcode);
            }
        } else {
            result.result(requestcode);
        }
    }
}
