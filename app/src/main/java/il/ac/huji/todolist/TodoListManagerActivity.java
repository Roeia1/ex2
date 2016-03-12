package il.ac.huji.todolist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;

/**
 * Created by Roei Avrahami on 08/03/2016.
 */
public class TodoListManagerActivity extends AppCompatActivity {
    ArrayList<String> tasks = new ArrayList<String>();
    ListView list;
    MyAdapter adapter;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list_manager);
        list = (ListView) findViewById(R.id.lstTodoItems);
        adapter = new MyAdapter(this, android.R.layout.simple_list_item_1, tasks);
        list.setAdapter(adapter);
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                alertDialogBuilder.setTitle("Delete")
                        .setMessage("Are you sure you want to delete '" + tasks.get(pos) + "' ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                tasks.remove(pos);
                                adapter.notifyDataSetChanged();
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which){}
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
                if (((EditText) findViewById(R.id.edtNewItem)).getText().toString().isEmpty()) {
                    new AlertDialog.Builder(il.ac.huji.todolist.TodoListManagerActivity.this)
                            .setTitle("Invalid task").setMessage("Please Enter a task")
                            .setNeutralButton("Close", null).show();
                }
                else {
                    tasks.add(((EditText) findViewById(R.id.edtNewItem)).getText().toString());
                    adapter.notifyDataSetChanged();
                    ((EditText) findViewById(R.id.edtNewItem)).setText("");
                }
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
