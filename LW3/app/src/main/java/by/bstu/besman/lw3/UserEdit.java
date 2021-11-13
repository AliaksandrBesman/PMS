package by.bstu.besman.lw3;

import android.app.Activity;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

public class UserEdit extends AppCompatActivity {

    String [] educations = { "начальное", "среднее", "высшее"};
    String [] ed_degree = { "бакалавр", "магистр", "аспирант"};
    User user;
    UserContext userContext;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit);

        configEducation();
        configImage();

        Bundle arguments = getIntent().getExtras();
        userContext = UserContext.getInstance();

        if(arguments!=null){
            user = arguments.getParcelable(User.class.getSimpleName());
        }

        setUserInfo(user);
    }

    private void configEducation()
    {
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
    }


    public void loadImage( ) {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/* video/*");
        if (intent.resolveActivity(getPackageManager()) != null) {
            loadImageForUser.launch(intent);
        }
    }

    protected  void configImage(){
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

        Spinner spinner_education = (Spinner) findViewById(R.id.education);
        Spinner spinner_ed_degree = (Spinner) findViewById(R.id.ed_degree);

        TextView userPhoneNumberext = findViewById(R.id.phoneNumber);
        TextView userEmailText = findViewById(R.id.email);
        TextView userWebSiteText = findViewById(R.id.webSite);

        userNameText.setText(user.getName());
        userSurnameText.setText(user.getSurname());
        userAgeText.setText(String.valueOf(user.getAge()));

        userCountryText.setText(user.getCountry());
        userCityText.setText(user.getCity());

        spinner_education.setSelection(Arrays.asList(educations).indexOf(user.getEducation()));
        if ( Arrays.asList(educations).indexOf(user.getEducation()) == 2)
        {
            spinner_ed_degree.setSelection(Arrays.asList(ed_degree).indexOf(user.getEd_degree()));
            spinner_ed_degree.setVisibility(View.VISIBLE);
        }
        else
        {
            spinner_ed_degree.setVisibility(View.GONE);
        }

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
        userContext.save(this);

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    public void saveImage()
    {
        ImageView user_image = (ImageView)findViewById(R.id.user_image);
        Drawable drawable = user_image.getDrawable();

        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();

        ContextWrapper wrapper = new ContextWrapper(getApplicationContext());

        File file = wrapper.getDir("Images",MODE_PRIVATE);

        file = new File(file, "UniqueFileName"+".jpg");

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