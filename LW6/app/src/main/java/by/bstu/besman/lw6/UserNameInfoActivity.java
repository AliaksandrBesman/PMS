package by.bstu.besman.lw6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class UserNameInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_name_info);
    }

    public void next(View view) {
        EditText userNameText = findViewById(R.id.name);
        EditText userSurnameText = findViewById(R.id.surname);
        EditText userAgeText = findViewById(R.id.age);

        String userName = userNameText.getText().toString();
        String userSurname= userSurnameText.getText().toString();
        int userAge = Integer.parseInt(userAgeText.getText().toString());

        Intent intent = new Intent(this,UserCountryInfoActivity.class);

        intent.putExtra("name",userName);
        intent.putExtra("surname",userSurname);
        intent.putExtra("age",userAge);

        startActivity(intent);

    }
}