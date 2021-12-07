package by.bstu.besman.lw6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;

public class UserCountryInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_country_info);

        Bundle arguments = getIntent().getExtras();


        if(arguments!=null){
            String name = arguments.get("name").toString();
            String surname = arguments.getString("surname");
            int age = arguments.getInt("age");

            TextView preview = findViewById(R.id.preview);
            preview.setText(surname + " " + name + "пожалуйста, введите данные о месте проживания" );
        }
    }

    public void next(View view) {
        Intent intent = new Intent(this,UserEducationInfoActivity.class);

        User user = new User();
        Bundle arguments = getIntent().getExtras();

        user.setName(arguments.get("name").toString());
        user.setSurname(arguments.get("surname").toString());
        user.setAge(arguments.getInt("age"));

        EditText userCountryText = findViewById(R.id.country);
        EditText userCityText = findViewById(R.id.city);

        user.setCountry(userCountryText.getText().toString());
        user.setCity(userCityText.getText().toString());

        intent.putExtra(User.class.getSimpleName(), (Serializable) user);
        startActivity(intent);
    }

}