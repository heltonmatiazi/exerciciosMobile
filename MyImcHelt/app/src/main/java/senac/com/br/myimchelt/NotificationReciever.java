package senac.com.br.myimchelt;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import java.util.Random;

/**
 * Created by helton on 20/11/2017.
 */

public class NotificationReciever extends BroadcastReceiver {
    String[] motivation = {"a vida é uma caixinha de surpresas","believe in yourself","um dia o sol vai explodir e extinguir a vida na galáxia","pensar em cinco mensagens motivacionais é surpreendentemente difícil","Desisto"};
    int idx = new Random().nextInt(motivation.length);
    String rand = (motivation[idx]);
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent1 = new Intent(context,MainActivity.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,100,intent1,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context).
                setSmallIcon(R.drawable.imc).
                setContentIntent(pendingIntent).
                setContentText(rand).
                setContentTitle("notification").
                setAutoCancel(true);
        notificationManager.notify(100,builder.build());
    }
}
