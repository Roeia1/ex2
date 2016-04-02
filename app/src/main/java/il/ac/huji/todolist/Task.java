package il.ac.huji.todolist;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Roei on 4/2/2016.
 */
public class Task {
    private final String name;
    private final Date date;
    private int id;

    public Task(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    public Task(String name, String date) {
        Date dateToPut;
        this.name = name;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            dateToPut = sdf.parse(date);
        } catch (java.text.ParseException e) {
            dateToPut = Calendar.getInstance().getTime();
        }
        this.date = dateToPut;
    }

    public Task(String id, String name, String date) {
        this(name, date);
        this.id = Integer.parseInt(id);
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public String getDateStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
