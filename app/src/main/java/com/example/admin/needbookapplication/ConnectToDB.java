package com.example.admin.needbookapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConnectToDB {
    String result;
    public ArrayList<Subjects> arr = new ArrayList<Subjects>();

        public static final String KEY_ROWID = "_id";
        public static final String KEY_NAME = "sub_name";
        public static final String KEY_CELL = "sub_topic";

        private final String DATABASE_NAME = "NeedBookDB";
        private final String DATABASE_TABLEBBIO = "Biology";
        private final String DATABASE_TABLEBPHY = "Chemistry";
        private final String DATABASE_TABLEBCHEM = "Physics";
        private final String DATABASE_TABLEBMATHS = "Maths";
        private final String DATABASE_LOGIN = "login_table";

    private final int DATABASE_VERSION = 1;

        private DBHelper ourHelper;
        private final Context ourContext;
        private SQLiteDatabase ourDatabase;

        public ConnectToDB (Context context){
            ourContext = context;
        }

        private class DBHelper extends SQLiteOpenHelper {

            //Construct a new database
            public DBHelper (Context context){
                super(context,DATABASE_NAME,null,DATABASE_VERSION);
            }

            //Run only if the database is not created (i.e., ASA the table is created)
            @Override
            public void onCreate(SQLiteDatabase db) {

            /*CREATE TABLE ContactsTable (_id INTEGER PRIMARY KEY AUTOINCREMENT,
            person_name TEXT NOT NULL, _cell TEXT NOT NULL
             )*/
                String sqlCode;
                sqlCode = "CREATE TABLE "+ DATABASE_TABLEBBIO + " (" +
                        KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        KEY_NAME + " TEXT NOT NULL, " +
                        KEY_CELL + " TEXT NOT NULL);";
                db.execSQL(sqlCode);
                sqlCode = "CREATE TABLE "+ DATABASE_TABLEBCHEM + " (" +
                        KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        KEY_NAME + " TEXT NOT NULL, "+
                        KEY_CELL + " TEXT NOT NULL);";
                db.execSQL(sqlCode);
                sqlCode = "CREATE TABLE "+ DATABASE_TABLEBPHY + " (" +
                        KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        KEY_NAME + " TEXT NOT NULL, " +
                        KEY_CELL + " TEXT NOT NULL);";
                db.execSQL(sqlCode);
                sqlCode = "CREATE TABLE "+ DATABASE_TABLEBMATHS + " (" +
                        KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        KEY_NAME + " TEXT NOT NULL);";
                db.execSQL(sqlCode);
                sqlCode = "CREATE TABLE "+ DATABASE_LOGIN + " (username TEXT NOT NULL, password TEXT NOT NULL);";
                db.execSQL(sqlCode);
            }

            //Runs only when the database exists with database number lower than V
            //Runs when  the version is changed*
            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

                //Have to save the strcture before drop
                db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLEBBIO);
                onCreate(db);
                db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLEBCHEM);
                onCreate(db);
                db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLEBPHY);
                onCreate(db);
                db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLEBMATHS);
                onCreate(db);
                db.execSQL("DROP TABLE IF EXISTS " + DATABASE_LOGIN);
                onCreate(db);
            }
        }

        public ConnectToDB open() throws SQLException {

            ourHelper = new DBHelper(ourContext);
            ourDatabase = ourHelper.getWritableDatabase();
            return this;
        }

        public void close(){
            ourHelper.close();
        }

        //Inserting or Creating a log/record
        public long createEntry(String name,String cell, int subIndex){
            //Help us in storing key=> value pairs
            String DATABASE= ApplicationClass.subjects.get(subIndex).getSubName();
            try {
                String quer = "SELECT * FROM " + DATABASE + " WHERE " + KEY_CELL + "='" + cell + "';";
                Cursor c = ourDatabase.rawQuery(quer,null);
                arr.add(new Subjects("SAMPLE"));
                Boolean flag= false;
                int count = c.getCount();
                if(count == 0){
                    flag = true;
                }
                if (flag){
                    ContentValues cv = new ContentValues();
                    cv.put(KEY_NAME, name);
                    cv.put(KEY_CELL, cell);
                    //Returns the number of rows inserted
                    return ourDatabase.insert(DATABASE, null, cv);
                }
            }catch (SQLException e) {
                Toast.makeText(ourContext, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return 0;
        }

        //Fetch from DB
        public String getData( int index, String subtopic, int subIndex){
            String DATABASE = "";
            subtopic = ApplicationClass.subjects.get(subIndex).getSubName();
             switch (subIndex){
                 case 0: DATABASE = ApplicationClass.subBio.get(index).getSubName();
                 break;
                 case 1: DATABASE = ApplicationClass.subPhy.get(index).getSubName();
                 break;
                 case 2: DATABASE = ApplicationClass.subChem.get(index).getSubName();
             }

            //Indicating the columns which we are gonna retrieve
//            String [] columns = new String[] {KEY_ROWID,KEY_NAME,KEY_CELL};
//            Cursor c = ourDatabase.query(DATABASE_TABLEBBIO,columns,null,null,null,null,null);

            String rawQuery = "SELECT * FROM " + subtopic + " WHERE " + KEY_NAME + "='" + DATABASE + "'";


//            String rawQuery = "SELECT * FROM " + subtopic;
            Cursor c  = ourDatabase.rawQuery(rawQuery,null);

            result = "";
            //Should get the index of the column
            int iName = c.getColumnIndex(KEY_NAME);
            int iCell = c.getColumnIndex(KEY_CELL);
            //Cursor pointing to first , after last is null
            for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
                String temp = c.getString(iCell);
                arr.add(new Subjects(temp));
                result = result + c.getString(iCell) + "\n\n";
            }
            c.close();
            return result;
        }

        public ArrayList<Subjects> returnString(){
            return arr;
        }


        public void InsertLoginID(String username, String password){
            try {
                String quer = "SELECT * FROM " + DATABASE_LOGIN + " WHERE " + "username" + "='" + username + "' AND password='" + password + "';";
                Cursor c = ourDatabase.rawQuery(quer,null);
                Boolean flag= false;
                int count = c.getCount();
                if(count == 0){
                    flag = true;
                }
                if (flag){
                    ContentValues cv = new ContentValues();
                    cv.put("username", username);
                    cv.put("password", password);
                    //Returns the number of rows inserted
                    ourDatabase.insert(DATABASE_LOGIN, null, cv);
                }
            }catch (SQLException e) {
                Toast.makeText(ourContext, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        public int checkLogin(String username, String password){
            String quer = "SELECT * FROM " + DATABASE_LOGIN + " WHERE " + "username" + "='" + username + "' AND password='" + password + "';";
            Cursor c = ourDatabase.rawQuery(quer,null);
            Boolean flag= false;
            int count = c.getCount();
            if(count == 1){
                flag = true;
            }
            if(flag){
                return 1;
            }else {
                return 0;
            }
        }
//        public long updateEntry(String rowId, String name, String cell){
//
//            ContentValues cv = new ContentValues();
//            cv.put(KEY_NAME,name);
//            cv.put(KEY_CELL,cell);
//
//            return ourDatabase.update(DATABASE_TABLE, cv, KEY_ROWID + "=?", new String[]{rowId});
//        }
}
