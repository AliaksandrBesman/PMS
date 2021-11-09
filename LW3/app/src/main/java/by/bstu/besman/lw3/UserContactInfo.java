package by.bstu.besman.lw3;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class UserContactInfo extends AppCompatActivity {

    String [] educations = { "начальное", "среднее", "высшее"};
    String [] ed_degree = { "бакалавр", "магистр", "аспирант"};
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_contact_info);

        Bundle arguments = getIntent().getExtras();
        user = (User) arguments.getSerializable(User.class.getSimpleName());
    }

    public void next(View view) {
        Intent intent = new Intent(this, AcceptanceUserCreation.class );

        EditText userPhoneText = findViewById(R.id.phoneNumber);
        EditText userEmailText = findViewById(R.id.email);
        EditText userWebSiteText = findViewById(R.id.webSite);

        String userPhone = userPhoneText.getText().toString();
        String userEmail= userEmailText.getText().toString();
        String userWebSite= userWebSiteText.getText().toString();

        user.setPhoneNumber(userPhone);
        user.setEmail(userEmail);
        user.setWebSite(userWebSite);

        intent.putExtra(User.class.getSimpleName(), (Parcelable) user);
        startActivity(intent);
    }
}