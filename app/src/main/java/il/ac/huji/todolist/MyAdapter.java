package il.ac.huji.todolist;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roei Avrahami on 08/03/2016.
 */
public class MyAdapter extends ArrayAdapter {
    public MyAdapter(Context context, int resource, ArrayList<String> missionsArr) {
        super(context, resource, missionsArr);
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent) {
        View view = super.getView(position,convertView,parent);
        view.setBackgroundColor((position%2 == 0)? Color.rgb(100,149,237) : Color.rgb(238,221,130));
        return view;
    }

}
