package il.ac.huji.todolist;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Roei Avrahami on 08/03/2016.
 */
public class TodoListManagerActivity extends AppCompatActivity {
    ArrayList<Task> tasks = new ArrayList<>();
    ListView list;
    MyAdapter adapter;
    DBHelper dbhelper;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list_manager);
        list = (ListView)findViewById(R.id.lstTodoItems);
        dbhelper = new DBHelper(this);
        tasks = dbhelper.getAllTasks();
        adapter = new MyAdapter(this, android.R.layout.simple_list_item_1, tasks);
        list.setAdapter(adapter);
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final int pos = position;
                final String taskName = tasks.get(pos).getName();
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
                alertDialogBuilder.setTitle(taskName)
                        .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dbhelper.deleteTask(tasks.get(pos));
                                tasks.remove(pos);
                                adapter.notifyDataSetChanged();
                            }
                        });
                if (taskName.toLowerCase().contains("call"))
                {
                    int i = 0;
                    while (!Character.isDigit(taskName.charAt(i))) i++;
                    int j = i;
                    do {
                        j++;
                        if (taskName.length() == j)
                        {
                            break;
                        }
                    } while (Character.isDigit(taskName.charAt(j)));
                    final String phoneNum = taskName.substring(i, j);
                    alertDialogBuilder.setPositiveButton("CALL " + phoneNum, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int i = 0;
                            while (!Character.isDigit(taskName.charAt(i))) i++;
                            int j = i;
                            while (!Character.isDigit(taskName.charAt(j))) j++;
                            Intent dial = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" +
                                    phoneNum));
                            startActivity(dial);
                        }
                    });
                }
                alertDialogBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alertDialogBuilder.show();
                return false;
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                Intent addTask = new Intent(this, AddNewTodoItemActivity.class);
                startActivityForResult(addTask, 1);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_CANCELED)
            {

            }
            if (resultCode == Activity.RESULT_OK)
            {
                Task taskToAdd = new Task(data.getExtras().getString("name"), (Date)data.getExtras().get("date"));
                tasks.add(taskToAdd);
                dbhelper.addTask(taskToAdd);
                adapter.notifyDataSetChanged();
            }
        }
    }
}
