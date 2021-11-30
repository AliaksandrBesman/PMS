package by.bstu.besman.lw3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<User> adapter;
    private EditText nameText, ageText;
    private List<User> users;
    UserContext userContext;
    ListView listView;

    private MainActivity instance;

    static public int activePosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
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

        registerForContextMenu(listView);
    }

    public void addUser( ) {
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

    private  void callUserEdit(User user)
    {
        Intent intent = new Intent(this, UserEdit.class);
        intent.putExtra(User.class.getSimpleName(), (Parcelable) user);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:
                addUser();
                return true;
            case R.id.bySurname:
                SortBySurname();
                return true;
            case R.id.byAge:
                SortByAge();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void SortBySurname()
    {
        users.sort(Comparator.comparing(User::getSurname));
        adapter.notifyDataSetChanged();
    }

    public void SortByAge()
    {
        users.sort(Comparator.comparing(User::getAge));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.edit:
                editItem(info.position); // метод, выполняющий действие при редактировании пункта меню
                return true;
            case R.id.display:
                displayItem(info.position); //метод, выполняющий действие при удалении пункта меню
                return true;
            case R.id.remove:
                Log.d("Main",users.size()+ " : " +userContext.getSize());
                removeItem(info.position);

            default:
                return super.onContextItemSelected(item);
        }
    }

    public void editItem(int position)
    {
        User currentUser = adapter.getItem(position);
        callUserEdit(currentUser);
    }

    public void displayItem(int position)
    {
        User currentUser = adapter.getItem(position);
        callUserDisplay(currentUser);
    }

    public void removeItem(int position)
    {
        activePosition = position;
        showDialog(1);
    }

    DialogInterface.OnClickListener myClickListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            if (which  == Dialog.BUTTON_POSITIVE)
            {
                users.remove(activePosition);
                Log.d("Main", users.size() + " : " + userContext.getSize());
                adapter.notifyDataSetChanged();
                userContext.save(instance);
            }
        }
    };

    @Nullable
    @Override
    protected Dialog onCreateDialog(int id, Bundle args) {
        AlertDialog.Builder al = new AlertDialog.Builder(this);
        // заголовок
        al.setTitle(R.string.removing);
        al.setMessage(R.string.remove_data);
        al.setIcon(android.R.drawable.ic_dialog_info);
        al.setPositiveButton(R.string.yes, myClickListener);
        al.setNegativeButton(R.string.no, myClickListener);
        al.setNeutralButton(R.string.cancel, myClickListener);
        return al.create();
    }

}