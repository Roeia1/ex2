package il.ac.huji.todolist;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Created by Roei Avrahami on 08/03/2016.
 */
public class MyAdapter extends ArrayAdapter<Task> {
    Context context;
    ArrayList<Task> tasks;
    LayoutInflater inflater = null;
    public MyAdapter(Context context, int resource, ArrayList<Task> tasksArr) {
        super(context, resource, tasksArr);
        this.context = context;
        this.tasks = tasksArr;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (rowView == null)
        {
            rowView = inflater.inflate(R.layout.rowlayout,parent,false);
        }
        TextView TodoTitle = (TextView)rowView.findViewById(R.id.txtTodoTitle);
        TextView TodoDate = (TextView)rowView.findViewById(R.id.txtTodoDueDate);
        TodoTitle.setText(tasks.get(position).getName());
        TodoDate.setText(tasks.get(position).getDateStr());
        if(GregorianCalendar.getInstance().getTime().after(tasks.get(position).getDate()))
        {
            rowView.setBackgroundColor(Color.rgb(229,36,36));
        }
        else
        {
            rowView.setBackgroundColor(Color.rgb(255,255,255));
        }
        return rowView;
    }

}
