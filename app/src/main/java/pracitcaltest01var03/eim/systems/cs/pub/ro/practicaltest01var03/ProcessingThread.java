package pracitcaltest01var03.eim.systems.cs.pub.ro.practicaltest01var03;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.lang.Thread;

/**
 * Created by student on 30.03.2018.
 */

public class ProcessingThread extends Thread {
    private Context context = null;
    private boolean isRunning = true;


    private String nume;
    private String grupa;


    public ProcessingThread(Context context, String num, String gr) {
        this.context = context;

        nume = num;
        grupa = gr;
    }

    @Override
    public void run() {
        Log.d("[ProcessingThread]", "Thread has started!");
        while (isRunning) {
            sendMessage();
            sleep();
        }
        Log.d("[ProcessingThread]", "Thread has stopped!");
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction(Constants.ACTION_STRING);
        intent.putExtra("message", nume + " " + grupa);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}
