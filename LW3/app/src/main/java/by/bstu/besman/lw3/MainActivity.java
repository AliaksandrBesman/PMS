package by.bstu.besman.lw3;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<User> adapter;
    private EditText nameText, ageText;
    private List<User> users;
    UserContext userContext;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list);

        userContext = UserContext.getInstance();
        users = userContext.getUsers(this);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, users);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User currentUser = adapter.getItem(position);
                callUserDisplay(currentUser);
            }
        });
    }

    public void addUser(View view) {
        Intent intent = new Intent(this,UserNameInfo.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        users = userContext.getUsers(this);
        adapter.notifyDataSetChanged();
    }

    private  void callUserDisplay(User user)
    {
        Intent intent = new Intent(this, UserInfo.class);
        intent.putExtra(User.class.getSimpleName(), (Parcelable) user);
        startActivity(intent);
    }
}