package by.bstu.besman.lw6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class UserDetailsActivity extends AppCompatActivity implements UserDetailsFragment.OnFragmentCallIntentAboutContactInfo {

    User user = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        Bundle arguments = getIntent().getExtras();


        if(arguments!=null){
            user = arguments.getParcelable(User.class.getSimpleName());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onResume() {
        super.onResume();
        UserDetailsFragment fragment = (UserDetailsFragment) getSupportFragmentManager()
                .findFragmentById(R.id.usersDetailsFragment);
        if (fragment != null)
            fragment.displaySelectedItem(user);
    }

    @Override
    public void callEmail(User user) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, user.getEmail());
        intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
        intent.putExtra(Intent.EXTRA_STREAM, "Hello");

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @Override
    public void callPhone(User user) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + user.getPhoneNumber()));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @Override
    public void callBrowser(User user) {
        Uri webpage = Uri.parse(user.getWebSite());
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(intent);
    }
}