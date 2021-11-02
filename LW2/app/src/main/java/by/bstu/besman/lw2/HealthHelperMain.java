package by.bstu.besman.lw2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class HealthHelperMain extends AppCompatActivity {

    final String previewName = "Добро пожаловать, ";
    final String previewStart = "Добро пожаловать";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_helper_main);

        EditText editText = (EditText)findViewById(R.id.name);

        editText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TextView textView = (TextView) findViewById(R.id.previewName);
                if (s.length() == 0)
                {
                    textView.setText(previewStart);
                }
                else
                {
                    textView.setText(previewName + s);
                }

            }
        });
    }

    public void startApp(View view) {
        Intent health_helper_report = new Intent(this, HealthHelperReport.class);
        startActivity(health_helper_report);
    }
}