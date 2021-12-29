package by.bstu.besman.lw6;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class UsersListFragment extends Fragment {


//    Project variables
    private MainActivity mainActivity;
    private ArrayAdapter<User> adapter;
    private EditText nameText, ageText;
    private List<User> users;
    private DatabaseAdapter db_adapter;
    ListView listView;

    static public int activePosition;

    interface OnFragmentSendDataListener {
        void onSendData(User user);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mainActivity = (MainActivity) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " должен реализовывать интерфейс OnFragmentInteractionListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_users_list, container, false);

        listView = (ListView) view.findViewById(R.id.usersList);

        db_adapter = new DatabaseAdapter(mainActivity);
        db_adapter.open();
        users = db_adapter.getUsers();

        adapter = new ArrayAdapter<>(mainActivity, android.R.layout.simple_list_item_1, users);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User currentUser = adapter.getItem(position);
                callUserDisplay(currentUser);
            }
        });

        registerForContextMenu(listView);
        db_adapter.close();
        // Inflate the layout for this fragment
        return view;
    }

    private  void callUserDisplay(User user)
    {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mainActivity.onSendData(user);
        }
        else{
            Intent intent = new Intent(mainActivity, UserDetailsActivity.class);
            intent.putExtra(User.class.getSimpleName(), (Parcelable) user);
            startActivity(intent);
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        db_adapter.open();
        users = db_adapter.getUsers();
        db_adapter.close();
        adapter = new ArrayAdapter<>(mainActivity, android.R.layout.simple_list_item_1, users);
        listView.setAdapter(adapter);
    }
}