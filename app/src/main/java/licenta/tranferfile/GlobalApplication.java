package licenta.tranferfile;

import android.content.Context;

/**
 * Created by Madalina.Chis on 4/11/2016.
 */
public class GlobalApplication extends android.app.Application{
    private static Context GlobalContext;

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        if(GlobalApplication.GlobalContext == null){
            GlobalApplication.GlobalContext = getApplicationContext();
        }
    }

    public static Context getGlobalAppContext() {
        return GlobalApplication.GlobalContext;
    }
}
