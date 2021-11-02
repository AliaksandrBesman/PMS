package by.bstu.besman.lw3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class UserEducationInfo extends AppCompatActivity {

    String [] educations = { "начальное", "среднее", "высшее"};
    String [] ed_degree = { "бакалавр", "магистр", "аспирант"};
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_education_info);

        Spinner spinner_education = (Spinner) findViewById(R.id.education);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, educations);
        spinner_education.setAdapter(adapter);

        Spinner spinner_ed_degree = (Spinner) findViewById(R.id.ed_degree);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ed_degree);
        spinner_ed_degree.setAdapter(adapter);

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Получаем выбранный объект
                String item = (String)parent.getItemAtPosition(position);
                if ( position == 2)
                {
                    spinner_ed_degree.setVisibility(View.VISIBLE);
                }
                else{
                    spinner_ed_degree.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinner_ed_degree.setVisibility(View.GONE);
            }
        };
        spinner_education.setOnItemSelectedListener(itemSelectedListener);

        Bundle arguments = getIntent().getExtras();
        TextView preview = findViewById(R.id.preview);

        user = (User) arguments.getSerializable(User.class.getSimpleName());
        preview.setText(user.getSurname() + " " + user.getName() + "\n" +
                        user.getCountry() + " " + user.getCity());
    }

    public void next(View view) {
        Intent intent = new Intent(this, AcceptanceUserCreation.class );

        Spinner spinner_education = (Spinner) findViewById(R.id.education);
        Spinner spinner_ed_degree = (Spinner) findViewById(R.id.ed_degree);

        String userEducation = (String) spinner_education.getSelectedItem();
        String userEd_Degree = null;
        if (spinner_education.getSelectedItemPosition() == 2)
            userEd_Degree = (String) spinner_ed_degree.getSelectedItem();
        user.setEducation(userEducation);
        user.setEd_degree(userEd_Degree);

        intent.putExtra(User.class.getSimpleName(), (Parcelable) user);
        startActivity(intent);
    }
}