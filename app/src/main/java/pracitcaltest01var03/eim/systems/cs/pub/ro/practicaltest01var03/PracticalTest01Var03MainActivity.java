package pracitcaltest01var03.eim.systems.cs.pub.ro.practicaltest01var03;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class PracticalTest01Var03MainActivity extends AppCompatActivity {
    CheckBox buton_nume;
    CheckBox buton_grupa;
    EditText nume;
    EditText grupa;
    TextView afisare;
    Button buton_afisare;
    Button next;
    private final static int SECONDARY_ACTIVITY_REQUEST_CODE = 1;
    private IntentFilter intentFilter = new IntentFilter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var03_main);
        buton_nume = (CheckBox) findViewById(R.id.buton_nume);
        buton_grupa = (CheckBox) findViewById(R.id.buton_grupa);
        buton_afisare = (Button) findViewById(R.id.buton_display);
        buton_afisare.setOnClickListener(buttonClickListener);
        nume = (EditText) findViewById(R.id.edittext_nume);
        grupa = (EditText) findViewById(R.id.edittext_grupa);
        afisare = (TextView) findViewById(R.id.textview_afisare);
        next = (Button) findViewById(R.id.buton_next);
        next.setOnClickListener(buttonClickListener);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("afisare"))
                afisare.setText(savedInstanceState.getString("afisare"));
            if (savedInstanceState.containsKey("nume"))
                nume.setText(savedInstanceState.getString("nume"));
            if (savedInstanceState.containsKey("grupa"))
                grupa.setText(savedInstanceState.getString("grupa"));
        }
        intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.ACTION_STRING);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        if (nume.getText() != null)
            try {
                savedInstanceState.putString("nume", nume.getText().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        if (grupa.getText() != null)
            savedInstanceState.putString("grupa", grupa.getText().toString());
        if (afisare.getText() != null)
            savedInstanceState.putString("afisare", afisare.getText().toString());
    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String message = intent.getStringExtra(Constants.MESSAGE);
        if (message != null) {
            afisare.setText(afisare.getText().toString() + "\n" + message);
        }
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("afisare"))
                afisare.setText(savedInstanceState.getString("afisare"));
            if (savedInstanceState.containsKey("nume"))
                nume.setText(savedInstanceState.getString("nume"));
            if (savedInstanceState.containsKey("grupa"))
                grupa.setText(savedInstanceState.getString("grupa"));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == SECONDARY_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK)
                Toast.makeText(this, "OK ", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();

        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String num = null, gr = null;
            switch (view.getId()) {
                case R.id.buton_display:
                    if (buton_nume.isChecked() && buton_grupa.isChecked()) {
                        if (nume.getText() == null || grupa.getText() == null)
                            Toast.makeText(PracticalTest01Var03MainActivity.this, "Invalid data", Toast.LENGTH_LONG).show();
                        else
                            afisare.setText(nume.getText().toString() + " " + grupa.getText().toString());
                    }
                    else if (buton_nume.isChecked()) {
                        if (nume.getText() == null)
                            Toast.makeText(PracticalTest01Var03MainActivity.this, "Invalid nume", Toast.LENGTH_LONG).show();
                        else
                            afisare.setText(nume.getText().toString());
                    }
                    else if (buton_grupa.isChecked()) {
                        if (grupa.getText() == null)
                            Toast.makeText(PracticalTest01Var03MainActivity.this, "Invalid grupa", Toast.LENGTH_LONG).show();
                        else
                            afisare.setText(grupa.getText().toString());
                    } else {
                        if (nume.getText() != null && grupa.getText() != null) {
                            Intent intent = new Intent(getApplicationContext(), PracticalTest01Var03Service.class);
                            intent.putExtra("nume", nume.getText().toString());
                            intent.putExtra("grupa", grupa.getText().toString());
                            getApplicationContext().startService(intent);
                        }

                    }
                    /*
                    if (buton_nume.isChecked()) {
                        if (nume.getText() != null) {
                            num = nume.getText().toString();
                        } else {
                            Toast.makeText(PracticalTest01Var03MainActivity.this, "Invalid Name", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                    if (buton_grupa.isChecked()) {
                        if (grupa.getText() != null) {
                            gr = grupa.getText().toString();
                        } else {
                            Toast.makeText(PracticalTest01Var03MainActivity.this, "Invalid Group", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                    if (buton_nume.isChecked() && buton_grupa.isChecked()) {
                        if (num != null && gr != null)
                            afisare.setText(num + " " + gr);
                        else if (num == null)
                            Toast.makeText(PracticalTest01Var03MainActivity.this, "Invalid Name", Toast.LENGTH_LONG).show();
                        else if (gr == null)
                            Toast.makeText(PracticalTest01Var03MainActivity.this, "Invalid Group", Toast.LENGTH_LONG).show();
                    }
                        else if (buton_grupa.isChecked()) {
                        if (gr != null)
                            afisare.setText(gr);
                        else
                            Toast.makeText(PracticalTest01Var03MainActivity.this, "Invalid Group", Toast.LENGTH_LONG).show();
                    }
                    else if (buton_nume.isChecked())
                        afisare.setText(num);
                        */
                    break;
                case R.id.buton_next:
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var03SecondaryActivity.class);
                    if (afisare.getText() != null)
                        intent.putExtra("afisare", afisare.getText().toString());
                    if (nume.getText() != null)
                        intent.putExtra("nume", nume.getText().toString());
                    if (grupa.getText() != null)
                        intent.putExtra("grupa", grupa.getText().toString());
                    startActivityForResult(intent, SECONDARY_ACTIVITY_REQUEST_CODE);
                    break;
            }
        }
    }

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("[Message]", intent.getStringExtra("message"));
        }
    }


    }
