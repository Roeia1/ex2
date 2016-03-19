package il.ac.huji.todolist;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AddNewTodoItemActivity extends AppCompatActivity {
    Intent returnIntent = new Intent();
    Button btnOK;
    Button btnCancel;
    DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_todo_item);
        btnOK = (Button)findViewById(R.id.btnOK);
        btnCancel = (Button)findViewById(R.id.btnCancel);
        datePicker = (DatePicker)findViewById(R.id.datePicker);
        btnOK.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (((TextView)findViewById(R.id.edtNewItem)).getText().toString().isEmpty())
                {
                    new AlertDialog.Builder(getApplicationContext())
                            .setMessage("Please enter the task name")
                            .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {}
                            })
                            .create().show();
                }
                else
                {
                    returnIntent.putExtra("name", ((TextView) findViewById(R.id.edtNewItem))
                            .getText().toString());
                    Calendar datePicked = GregorianCalendar.getInstance();
                    datePicked.set(((DatePicker) findViewById(R.id.datePicker)).getYear(),
                                    ((DatePicker) findViewById(R.id.datePicker)).getMonth(),
                                    ((DatePicker) findViewById(R.id.datePicker)).getDayOfMonth(),
                                    23, 59);
                    returnIntent.putExtra("date", datePicked.getTime());
                    setResult(RESULT_OK, returnIntent);
                    finish();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED, returnIntent);
                finish();
            }
        });
    }
}
