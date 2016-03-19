package il.ac.huji.todolist;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Roei Avrahami on 08/03/2016.
 */
public class MyAdapter extends ArrayAdapter<Pair<String,Date>> {
    Context context;
    ArrayList<Pair<String,Date>> tasks;
    LayoutInflater inflater = null;
    public MyAdapter(Context context, int resource, ArrayList<Pair<String,Date>> tasksArr) {
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
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        TodoTitle.setText(tasks.get(position).first);
        TodoDate.setText(sdf.format(tasks.get(position).second));
        if(GregorianCalendar.getInstance().getTime().after(tasks.get(position).second))
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
