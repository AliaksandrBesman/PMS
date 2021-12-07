package by.bstu.besman.lw6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements UsersListFragment.OnFragmentSendDataListener, UserDetailsFragment.OnFragmentCallIntentAboutContactInfo {

    private UsersListFragment fragmentListView;
    private UserDetailsFragment fragmentItem;
    FragmentTransaction ft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:
                addUser();
                return true;
//            case R.id.bySurname:
//                SortBySurname();
//                return true;
//            case R.id.byAge:
//                SortByAge();
//                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void addUser( ) {
        Intent intent = new Intent(this,UserNameInfoActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSendData(User user) {
        UserDetailsFragment fragment = (UserDetailsFragment) getSupportFragmentManager()
                .findFragmentById(R.id.detailFragment);
        if (fragment != null && fragment.isVisible())
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

    @Override
    protected void onResume() {
        super.onResume();

    }
    
}

