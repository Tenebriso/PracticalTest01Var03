package pracitcaltest01var03.eim.systems.cs.pub.ro.practicaltest01var03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class PracticalTest01Var03SecondaryActivity extends AppCompatActivity {
    TextView nume;
    TextView grupa;
    Button ok;
    Button cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var03_secondary);
        nume = (TextView) findViewById(R.id.nume);
        grupa = (TextView) findViewById(R.id.grupa);

        ok = (Button) findViewById(R.id.ok);
        ok.setOnClickListener(buttonClickListener);
        cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(buttonClickListener);
        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey("nume")) {
            nume.setText(intent.getStringExtra("nume"));
        }
        if (intent != null && intent.getExtras().containsKey("grupa")) {
            grupa.setText(intent.getStringExtra("grupa"));
        }
    }

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.ok:
                    setResult(RESULT_OK, null);
                    break;
                case R.id.cancel:
                    setResult(RESULT_CANCELED, null);
                    break;
            }
            finish();
        }
    }
}
