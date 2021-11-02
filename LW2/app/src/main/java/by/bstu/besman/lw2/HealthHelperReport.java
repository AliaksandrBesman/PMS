package by.bstu.besman.lw2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

enum Lifestyle {
     sit(1.2f),
    activity(1.375f),
    avg_activity(1.55f),
    very_activity(1.725f),
    sportsman(1.9f);

    public final float value;
    Lifestyle(float v) {
        this.value = v;
    }
}

public class HealthHelperReport extends AppCompatActivity {

    final String[] lifestyles = { "Сидячий образ жизни", "Умеренная активность", "Средняя активность", "Активные люди", "Спортсмены"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_helper_report);

        Spinner spinner = (Spinner) findViewById(R.id.lifestyle);
        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lifestyles);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinner.setAdapter(adapter);
    }

    public void onSexRBClicked(View view) {
        // если переключатель отмечен
        boolean checked = ((RadioButton) view).isChecked();
        // Получаем нажатый переключатель
        switch(view.getId()) {
            case R.id.male:
                if (checked){
                    view.setBackgroundColor(Color.BLUE);
                    findViewById(R.id.female).setBackgroundColor(android.R.drawable.btn_default);

                }
                break;
            case R.id.female:
                if (checked){
                    view.setBackgroundColor(Color.RED);
                    findViewById(R.id.male).setBackgroundColor(android.R.drawable.btn_default);

                }
                break;
        }
    }

    public void calculate(View view) {
        boolean is_male = ((RadioButton) findViewById(R.id.male)).isChecked();
        float result = 0.0f;
        float bmr = 0.0f;
        if (is_male){
             bmr = 66.4730f + (13.7516f * Float.parseFloat(((EditText)findViewById(R.id.weight)).getText().toString())) +
                        (5.0033f * Float.parseFloat(((EditText)findViewById(R.id.height)).getText().toString())) -
                        (6.7550f * Float.parseFloat(((EditText)findViewById(R.id.age)).getText().toString()));


        }
        else{
             bmr = 65.0955f + (9.5634f * Float.parseFloat(((EditText)findViewById(R.id.weight)).getText().toString())) +
                       (1.8496f * Float.parseFloat(((EditText)findViewById(R.id.height)).getText().toString())) -
                       (4.6756f * Float.parseFloat(((EditText)findViewById(R.id.age)).getText().toString()));

        }
        int lifestype = ((Spinner)findViewById(R.id.lifestyle)).getSelectedItemPosition();
        if ( lifestype == 0)
            result = bmr * Lifestyle.sit.value;
        else if (lifestype == 1)
            result = bmr * Lifestyle.activity.value;
        else if (lifestype == 2)
            result = bmr * Lifestyle.avg_activity.value;
        else if (lifestype == 3)
            result = bmr * Lifestyle.very_activity.value;
        else if (lifestype == 4)
            result = bmr * Lifestyle.sportsman.value;

        Toast toast = Toast.makeText(this, String.format("%.2f",result)+" ккал/сут",Toast.LENGTH_LONG);
        toast.show();
    }
}