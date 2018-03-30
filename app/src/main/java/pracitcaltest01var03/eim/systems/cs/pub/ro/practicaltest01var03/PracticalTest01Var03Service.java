package pracitcaltest01var03.eim.systems.cs.pub.ro.practicaltest01var03;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by student on 30.03.2018.
 */

public class PracticalTest01Var03Service extends Service {
    private ProcessingThread processingThread = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String nume = intent.getStringExtra("nume");
        String grupa = intent.getStringExtra("grupa");
        processingThread = new ProcessingThread(this, nume, grupa);
        processingThread.start();
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        processingThread.stopThread();
    }
}
