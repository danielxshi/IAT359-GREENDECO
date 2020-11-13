package li.xiaoxu.greendeco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class MyDatabase {

    private SQLiteDatabase db;
    private Context context;
    private final MyHelper helper;

    public MyDatabase (Context c) {
        context = c;
        helper = new MyHelper(context);
    }

    //insert Query
    public long insertData (String address, String typology, String description)
    {
        db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Constants.ADDRESS, address);
        contentValues.put(Constants.TYPOLOGY, typology);
        contentValues.put(Constants.DESCRIPTION, description);

        long id = db.insert(Constants.TABLE_NAME, null, contentValues);
        return id;
    }

    public Cursor getData()
    {
        SQLiteDatabase db = helper.getWritableDatabase();

        String[] columns = {Constants.FID, Constants.ADDRESS, Constants.TYPOLOGY, Constants.DESCRIPTION};
        Cursor cursor = db.query(Constants.TABLE_NAME, columns, null, null, null, null, null);
        return cursor;
    }


    public ArrayList<String> getSelectedData(String type)
    {
        //select plants from database of type 'herb'
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {Constants.ADDRESS, Constants.TYPOLOGY, Constants.DESCRIPTION};

        String selection = Constants.TYPOLOGY + "='" + type + "'";  //Constants.TYPE = 'type'
        Cursor cursor = db.query(Constants.TABLE_NAME, columns, selection, null, null, null, null);

//        StringBuffer buffer = new StringBuffer();
        ArrayList<String> mArrayList = new ArrayList<String>();
        while (cursor.moveToNext()) {

            int index1 = cursor.getColumnIndex(Constants.ADDRESS);
            int index2 = cursor.getColumnIndex(Constants.TYPOLOGY);
            int index3 = cursor.getColumnIndex(Constants.DESCRIPTION);

            String siteaddress = cursor.getString(index1);
            String sitetypology = cursor.getString(index2);
            String sitedescription = cursor.getString(index3);

//            buffer.append(siteaddress + " " + sitetypology + " " + sitedescription + "\n");

            String s = siteaddress + "," + sitetypology + "," + sitedescription;
            mArrayList.add(s);
        }
        return mArrayList;
    }

    public ArrayList<String> getAllTheData() {
        Cursor cursor = getData();

        int index1 = cursor.getColumnIndex(Constants.ADDRESS);
        int index2 = cursor.getColumnIndex(Constants.TYPOLOGY);
        int index3 = cursor.getColumnIndex(Constants.DESCRIPTION);

        ArrayList<String> mArrayList = new ArrayList<String>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String siteaddress = cursor.getString(index1);
            String sitetypology = cursor.getString(index2);
            String sitedescription = cursor.getString(index3);
            String s = siteaddress + "," + sitetypology + "," + sitedescription;
            mArrayList.add(s);
            cursor.moveToNext();
        }
        return mArrayList;
    }

    public int deleteRow(){
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] whereArgs = {"herb"};
        int count = db.delete(Constants.TABLE_NAME, Constants.TYPOLOGY + "=?", whereArgs);
        return count;
    }

}
