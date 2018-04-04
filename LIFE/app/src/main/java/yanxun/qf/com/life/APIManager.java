package yanxun.qf.com.life;

import android.util.Log;

/**
 * Created by Administrator on 2017/6/3.
 */

public class APIManager {
    public static final boolean isLog=true;
    public static final String BASEURL="http://api.mglife.me/";
    public static void log(String str){
        if (isLog){
            Log.i("xiaoji", str);
        }
    }
}
