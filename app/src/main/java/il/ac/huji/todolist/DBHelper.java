package il.ac.huji.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;


/**
 * Created by Roei on 4/2/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "todo_db";
    private static final String TABLE_NAME = "todo";
    private static final String KEY_ID = "_id";
    private static final String KEY_NAME = "_name";
    private static final String KEY_DATE = "_date";

    private static final String[] COLUMNS = {KEY_ID,KEY_NAME,KEY_DATE};

    private int taskCounter = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TODO_TABLE = "CREATE TABLE todo ( " +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "_name TEXT, "+
                "_date TEXT )";

        db.execSQL(CREATE_TODO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS todo");

        this.onCreate(db);
    }

    public void addTask(Task task){
        task.setId(taskCounter);
        taskCounter++;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, task.getName());
        values.put(KEY_DATE, task.getDateStr());

        db.insert(TABLE_NAME, null, values);

        db.close();
    }

//    public Task getTask(int id){
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor =
//                db.query(TABLE_NAME,
//                        COLUMNS,
//                        " id = ?",
//                        new String[] { String.valueOf(id) },
//                        null,
//                        null,
//                        null,
//                        null);
//
//        if (cursor != null)
//            cursor.moveToFirst();
//
//        Task task = new Task(cursor.getString(1), cursor.getString(2));
//
//        db.close();
//
//        return task;
//    }

    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> tasks = new ArrayList<>();

        String query = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Task task = null;
        if (cursor.moveToFirst()) {
            do {
                tasks.add(new Task(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
            } while (cursor.moveToNext());
        }

        return tasks;
    }

    public void deleteTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME,
                KEY_ID + " = ?",
                new String[]{String.valueOf(task.getId())});

        db.close();
    }
}
