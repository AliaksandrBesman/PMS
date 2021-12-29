package by.bstu.besman.lw6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class DatabaseAdapter {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public DatabaseAdapter(Context context){
        dbHelper = new DatabaseHelper(context.getApplicationContext());
    }

    public DatabaseAdapter open(){
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    private Cursor getAllEntries(){
        String[] columns = new String[] {DatabaseHelper.COLUMN_ID,

                                        DatabaseHelper.COLUMN_NAME,
                                        DatabaseHelper.COLUMN_SURNAME,
                                        DatabaseHelper.COLUMN_AGE,

                                        DatabaseHelper.COLUMN_country,
                                        DatabaseHelper.COLUMN_city,

                                        DatabaseHelper.COLUMN_education,
                                        DatabaseHelper.COLUMN_ed_degree,

                                        DatabaseHelper.COLUMN_uri,

                                        DatabaseHelper.COLUMN_phoneNumber,
                                        DatabaseHelper.COLUMN_email,
                                        DatabaseHelper.COLUMN_webSite,
        };
        return  database.query(DatabaseHelper.TABLE, columns, null, null, null, null, null);
    }

    public List<User> getUsers(){
        ArrayList<User> users = new ArrayList<>();
        Cursor cursor = getAllEntries();
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));

            String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME));
            String surname = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_SURNAME));
            int age = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_AGE));

            String country = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_country));
            String city = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_city));

            String education = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_education));
            String ed_degree = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ed_degree));

            String uri = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_uri));

            String phoneNumber = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_phoneNumber));
            String email = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_email));
            String webSite = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_webSite));


            users.add(new User(id,
                    name, surname,age,
                    country, city,
                    education,ed_degree,
                    uri,
                    phoneNumber,email,webSite ));
        }
        cursor.close();
        return  users;
    }

    public long getCount(){
        return DatabaseUtils.queryNumEntries(database, DatabaseHelper.TABLE);
    }

    public User getUser(int id){
        User user = null;
        String query = String.format("SELECT * FROM %s WHERE %s=?",DatabaseHelper.TABLE, DatabaseHelper.COLUMN_ID);
        Cursor cursor = database.rawQuery(query, new String[]{ String.valueOf(id)});
        if(cursor.moveToFirst()){

            int _id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));

            String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME));
            String surname = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_SURNAME));
            int age = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_AGE));

            String country = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_country));
            String city = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_city));

            String education = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_education));
            String ed_degree = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ed_degree));

            String uri = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_uri));

            String phoneNumber = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_phoneNumber));
            String email = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_email));
            String webSite = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_webSite));

            user = new User(_id,
                    name, surname,age,
                    country, city,
                    education,ed_degree,
                    uri,
                    phoneNumber,email,webSite);
        }
        cursor.close();
        return  user;
    }

    public int insert(User user){

        ContentValues cv = new ContentValues();

        cv.put(DatabaseHelper.COLUMN_NAME, user.getName());
        cv.put(DatabaseHelper.COLUMN_SURNAME, user.getSurname());
        cv.put(DatabaseHelper.COLUMN_AGE, user.getAge());

        cv.put(DatabaseHelper.COLUMN_country, user.getCountry());
        cv.put(DatabaseHelper.COLUMN_city, user.getCity());

        cv.put(DatabaseHelper.COLUMN_education, user.getEducation());
        cv.put(DatabaseHelper.COLUMN_ed_degree, user.getEd_degree());

        cv.put(DatabaseHelper.COLUMN_uri, user.getUri());

        cv.put(DatabaseHelper.COLUMN_phoneNumber, user.getPhoneNumber());
        cv.put(DatabaseHelper.COLUMN_email, user.getEmail());
        cv.put(DatabaseHelper.COLUMN_webSite, user.getWebSite());

        return  (int) database.insert(DatabaseHelper.TABLE, null, cv);
    }

    public long delete(long userId){

        String whereClause = "_id = ?";
        String[] whereArgs = new String[]{String.valueOf(userId)};
        return database.delete(DatabaseHelper.TABLE, whereClause, whereArgs);
    }

    public long update(User user){

        String whereClause = DatabaseHelper.COLUMN_ID + "=" + user.getId();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_NAME, user.getName());
        cv.put(DatabaseHelper.COLUMN_SURNAME, user.getSurname());
        cv.put(DatabaseHelper.COLUMN_AGE, user.getAge());

        cv.put(DatabaseHelper.COLUMN_country, user.getCountry());
        cv.put(DatabaseHelper.COLUMN_city, user.getCity());

        cv.put(DatabaseHelper.COLUMN_education, user.getEducation());
        cv.put(DatabaseHelper.COLUMN_ed_degree, user.getEd_degree());

        cv.put(DatabaseHelper.COLUMN_uri, user.getUri());

        cv.put(DatabaseHelper.COLUMN_phoneNumber, user.getPhoneNumber());
        cv.put(DatabaseHelper.COLUMN_email, user.getEmail());
        cv.put(DatabaseHelper.COLUMN_webSite, user.getWebSite());
        return database.update(DatabaseHelper.TABLE, cv, whereClause, null);
    }
}
