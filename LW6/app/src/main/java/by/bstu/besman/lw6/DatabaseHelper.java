package by.bstu.besman.lw6;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "userstore.db"; // название бд
    private static final int SCHEMA = 1; // версия базы данных
    static final String TABLE = "users"; // название таблицы в бд
    // названия столбцов
    public static final String COLUMN_ID = "_id";

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SURNAME = "surname";
    public static final String COLUMN_AGE = "age";

    public static final String COLUMN_country = "country";
    public static final String COLUMN_city = "city";

    public static final String COLUMN_education = "education";
    public static final String COLUMN_ed_degree = "ed_degree";

    public static final String COLUMN_uri= "uri";

    public static final String COLUMN_phoneNumber= "phoneNumber";
    public static final String COLUMN_email= "email";
    public static final String COLUMN_webSite= "webSite";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE users ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT, "
                + COLUMN_SURNAME + " TEXT, "
                + COLUMN_AGE + " INTEGER, "

                + COLUMN_country + " TEXT, "
                + COLUMN_city + " TEXT, "

                + COLUMN_education + " TEXT, "
                + COLUMN_ed_degree + " TEXT, "

                + COLUMN_uri + " TEXT, "

                + COLUMN_phoneNumber + " TEXT, "
                + COLUMN_email + " TEXT, "
                + COLUMN_webSite + " TEXT ) ;" );

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);
    }
}
