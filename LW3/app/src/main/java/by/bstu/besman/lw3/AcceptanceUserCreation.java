package by.bstu.besman.lw3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.Arrays;

public class AcceptanceUserCreation extends AppCompatActivity {

    String [] educations = { "начальное", "среднее", "высшее"};
    UserContext userContext;
    User user = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceptance_user_creation);

        Bundle arguments = getIntent().getExtras();
        userContext = UserContext.getInstance();

        if(arguments!=null){
            user = arguments.getParcelable(User.class.getSimpleName());
        }

        TableLayout user_info = (TableLayout) findViewById(R.id.user_info);
        int row_count = user_info.getChildCount();
        if (user == null) return;
        if ( Arrays.asList(educations).indexOf(user.getEducation()) == 2)
        {
            user_info.getChildAt(row_count - 1).setVisibility(View.VISIBLE);
        }
        else
        {
            user_info.getChildAt(row_count - 1).setVisibility(View.GONE);
        }

        setUserInfo(user);



    }

    protected void setUserInfo(User user){
        TextView userNameText = findViewById(R.id.name);
        TextView userSurnameText = findViewById(R.id.surname);
        TextView userAgeText = findViewById(R.id.age);

        TextView userCountryText = findViewById(R.id.country);
        TextView userCityText = findViewById(R.id.city);

        TextView userEducationText = findViewById(R.id.education);
        TextView userEd_DegreeText = findViewById(R.id.ed_degree);

        userNameText.setText(user.getName());
        userSurnameText.setText(user.getSurname());
        userAgeText.setText(String.valueOf(user.getAge()));

        userCountryText.setText(user.getCountry());
        userCityText.setText(user.getCity());

        userEducationText.setText(user.getEducation());
        userEd_DegreeText.setText(user.getEd_degree());

    }

    public void save(View view) {
        userContext.addUser(user);
        userContext.save(this);

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}