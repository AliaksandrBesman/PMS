package by.bstu.besman.lw3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.Arrays;

public class UserInfo extends AppCompatActivity {

    String [] educations = { "начальное", "среднее", "высшее"};
    UserContext userContext;
    User user = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

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
            user_info.getChildAt(row_count-3 - 1).setVisibility(View.VISIBLE);
        }
        else
        {
            user_info.getChildAt(row_count-3 - 1).setVisibility(View.GONE);
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

        TextView userPhoneNumberext = findViewById(R.id.phoneNumber);
        TextView userEmailText = findViewById(R.id.email);
        TextView userWebSiteText = findViewById(R.id.webSite);
        TextView userWebSiteLabelText = findViewById(R.id.webSiteLabel);



        userNameText.setText(user.getName());
        userSurnameText.setText(user.getSurname());
        userAgeText.setText(String.valueOf(user.getAge()));

        userCountryText.setText(user.getCountry());
        userCityText.setText(user.getCity());

        userEducationText.setText(user.getEducation());
        userEd_DegreeText.setText(user.getEd_degree());

        userPhoneNumberext.setText(user.getPhoneNumber());
        userEmailText.setText(user.getEmail());
        userWebSiteText.setText(user.getWebSite());

        userPhoneNumberext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + user.getPhoneNumber()));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        userEmailText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("*/*");
                intent.putExtra(Intent.EXTRA_EMAIL, user.getEmail());
                intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
                intent.putExtra(Intent.EXTRA_STREAM, "Hello");

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        userWebSiteText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri webpage = Uri.parse(user.getWebSite());
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(intent);
            }
        });

        ImageView user_image = (ImageView) findViewById(R.id.user_image);
        if (user.getUri() == null)
        {
            user_image.setImageResource(R.mipmap.avatar);
        }
        else
        {
            File auxFile = new File(user.getUri());
            user_image.setImageURI(Uri.parse(auxFile.getAbsolutePath()));
        }


    }
}