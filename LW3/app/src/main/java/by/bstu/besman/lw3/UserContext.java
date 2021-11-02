package by.bstu.besman.lw3;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class UserContext {

    List<User> users;

    public List<User> getUsers(Context context){
        if(users ==null)
        {
        users = JSONHelper.importFromJSON(context);
        }
        return (users == null) ? new ArrayList<User>() : users;
    }

    private static UserContext instance;

    private UserContext()
    {

    }

    public static UserContext getInstance() {
        if (instance == null)
        {
            instance = new UserContext();
            return  instance;
        }else
        {
            return instance;
        }
    }

    public void addUser(User user)
    {
        if ( users == null)
            users = new ArrayList<User>();
        users.add(user);
    }

    public void save(Context context)
    {
        JSONHelper.exportToJSON(context,users);
    }


}
