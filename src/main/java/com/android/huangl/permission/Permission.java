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

import android.app.Activity;
import android.support.v4.app.Fragment;

/**
 * author：huangl on 2017-01-17 11:46
 * email：278168565@qq.com
 * <p>
 * des：Permission
 */
public class Permission {
    private        Object           object;
    private        int              REQUESTCODE;
    private        String[]         permissions;
    private static PermissionResult result;

    public Permission(Object object) {
        this.object = object;
    }

    public static Permission with(Activity activity) {
        return new Permission(activity);
    }

    public static Permission with(Fragment activity) {
        return new Permission(activity);
    }

    /**
     * add code
     */
    public Permission addRequestCode(int requestCode) {
        this.REQUESTCODE = requestCode;
        return this;
    }

    /**
     * add permissions
     */
    public Permission addPermissions(String... permission) {
        this.permissions = permission;
        return this;
    }

    /**
     * add response Interface
     */
    public Permission addInterface(PermissionResult result) {
        this.result = result;
        return this;
    }

    /**
     * submit
     */
    public void submit() {
        Util.judgePermissions(object,result, REQUESTCODE, permissions);
    }

    /**
     * consent
     **/
    public static void onRequestPermissionsResult(int requestCode) {
        requestResult(requestCode);
    }

    private static void requestResult(int requestCode) {
        result.result(requestCode);
    }

    /**
     * reject
     **/
    public static void onFaildRPermissionsResult(int requestCode) {
        FaildResult(requestCode);
    }

    private static void FaildResult(int requestCode) {
        result.faild(requestCode);
    }

}
