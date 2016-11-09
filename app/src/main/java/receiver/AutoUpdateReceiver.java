package receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import service.AutoUpdateService;

/**
 * Created by ex-zhongwencheng001 on 16/11/9.
 */
public class AutoUpdateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, AutoUpdateService.class);
        context.startService(i);
    }

}
