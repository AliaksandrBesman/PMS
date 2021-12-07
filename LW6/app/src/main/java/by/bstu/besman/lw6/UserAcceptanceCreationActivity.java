package by.bstu.besman.lw6;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class UserAcceptanceCreationActivity extends AppCompatActivity {

    String [] educations = { "начальное", "среднее", "высшее"};
    UserContext userContext;
    User user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_acceptance_creation);

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

        ImageView user_image = (ImageView) findViewById(R.id.user_image);
        View.OnClickListener mCorkyListener = new View.OnClickListener() {
            public void onClick(View v) {
                loadImage();
            }
        };
        user_image.setOnClickListener(mCorkyListener);
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

        ImageView user_image = (ImageView) findViewById(R.id.user_image);
        if (user.getUri() == null)
        {
            user_image.setImageResource(R.mipmap.avatar);
        }
        else
        {
            user_image.setImageURI(Uri.parse(user.getUri()));
        }


    }

    public void save(View view) {
        saveImage();
        userContext.addUser(user);
        userContext.save(this);

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    ActivityResultLauncher<Intent> loadImageForUser =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result.getResultCode() == Activity.RESULT_OK) {
                                // There are no request codes
                                Intent data = result.getData();

                                ImageView user_image = (ImageView) findViewById(R.id.user_image);
                                user_image.setImageURI(data.getData());

                            }
                        }
                    });

    public void loadImage( ) {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/* video/*");
        if (intent.resolveActivity(getPackageManager()) != null) {
            loadImageForUser.launch(intent);
        }
    }

    public void saveImage()
    {
        ImageView user_image = (ImageView)findViewById(R.id.user_image);
        Drawable drawable = user_image.getDrawable();

        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();

        ContextWrapper wrapper = new ContextWrapper(getApplicationContext());

        File file = wrapper.getDir("Images",MODE_PRIVATE);

        SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
        Date date = new Date();
        String pathToFile = "UniqueFileName" + formatter.format(date);
        file = new File(file, pathToFile +".jpg");

        try{
            OutputStream stream = null;
            stream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
            stream.flush();
            stream.close();

        }catch (IOException e) // Catch the exception
        {
            e.printStackTrace();
        }
        user.setUri(file.getAbsolutePath());


    }
}