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

    //select table1.facility_id, lat, long from table1 join table2 where table1.facility_id = table2.facility_id;
    //select table1.facility_id, address, typology, description from table1 join table2 where table1.facility_id = table2.facility_id;
    //insert Query
    public long insertData (int facility_id, String address, String typology, String description)
    {
        db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Constants.FID, facility_id);
        contentValues.put(Constants.ADDRESS, address);
        contentValues.put(Constants.TYPOLOGY, typology);
        contentValues.put(Constants.DESCRIPTION, description);

        long id = db.insert(Constants.TABLE3_NAME, null, contentValues);
        return id;
    }

    public Cursor getData()
    {
        SQLiteDatabase db = helper.getWritableDatabase();

        String[] columns = {Constants.FID, Constants.ADDRESS, Constants.TYPOLOGY, Constants.DESCRIPTION};
        Cursor cursor = db.query(Constants.TABLE2_NAME, columns, null, null, null, null, null);
        return cursor;
    }


    public ArrayList<String> getSitesData()
    {
        //select plants from database of type 'herb'
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {Constants.FID, Constants.ADDRESS, Constants.TYPOLOGY, Constants.DESCRIPTION};

        String queryString = "SELECT table1.facility_id, address, typology, description FROM table1 join table2 WHERE table1.facility_id = table2.facility_id";
        String selection = "table1.facility_id = table2.facility_id";  //Constants.TYPE = 'type'
        Cursor cursor = db.query(Constants.TABLE1_NAME + "," + Constants.TABLE2_NAME, columns, selection, null, null, null, null);


//        StringBuffer buffer = new StringBuffer();
        ArrayList<String> mArrayList = new ArrayList<String>();
        while (cursor.moveToNext()) {

            int index0 = cursor.getColumnIndex(Constants.FID);
            int index1 = cursor.getColumnIndex(Constants.ADDRESS);
            int index2 = cursor.getColumnIndex(Constants.TYPOLOGY);
            int index3 = cursor.getColumnIndex(Constants.DESCRIPTION);

            String siteFID = cursor.getString(index0);
            String siteAddress = cursor.getString(index1);
            String siteTypology = cursor.getString(index2);
            String siteDescription = cursor.getString(index3);

//            buffer.append(siteaddress + " " + sitetypology + " " + sitedescription + "\n");

            String s = siteFID + "," + siteAddress + "," + siteTypology + "," + siteDescription;
            mArrayList.add(s);
        }
        return mArrayList;
    }

    public ArrayList<String> getSelectedData(String typology)
    {
        //select plants from database of type 'herb'
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {Constants.FID, Constants.ADDRESS, Constants.TYPOLOGY, Constants.DESCRIPTION};

        String selection = Constants.TYPOLOGY + "=" + "typology";  //Constants.TYPE = 'type'
        Cursor cursor = db.query(Constants.TABLE2_NAME, columns, selection, null, null, null, null);

//        StringBuffer buffer = new StringBuffer();
        ArrayList<String> mArrayList = new ArrayList<String>();
        while (cursor.moveToNext()) {

            int index0 = cursor.getColumnIndex(Constants.FID);
            int index1 = cursor.getColumnIndex(Constants.ADDRESS);
            int index2 = cursor.getColumnIndex(Constants.TYPOLOGY);
            int index3 = cursor.getColumnIndex(Constants.DESCRIPTION);

            String siteFID = cursor.getString(index0);
            String siteAddress = cursor.getString(index1);
            String siteTypology = cursor.getString(index2);
            String siteDescription = cursor.getString(index3);

//            buffer.append(siteaddress + " " + sitetypology + " " + sitedescription + "\n");

            String s = siteFID + "," + siteAddress + "," + siteTypology + "," + siteDescription;
            mArrayList.add(s);
        }
        return mArrayList;
    }

    public ArrayList<String> getAllTheData() {
        Cursor cursor = getData();

        int index0 = cursor.getColumnIndex(Constants.FID);
        int index1 = cursor.getColumnIndex(Constants.ADDRESS);
        int index2 = cursor.getColumnIndex(Constants.TYPOLOGY);
        int index3 = cursor.getColumnIndex(Constants.DESCRIPTION);

        ArrayList<String> mArrayList = new ArrayList<String>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String siteFID = cursor.getString(index0);
            String siteAddress = cursor.getString(index1);
            String siteTypology = cursor.getString(index2);
            String siteDescription = cursor.getString(index3);
            String s = siteFID + "," + siteAddress + "," + siteTypology + "," + siteDescription;
            mArrayList.add(s);
            cursor.moveToNext();
        }
        return mArrayList;
    }

    public int deleteRow(){
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] whereArgs = {"herb"};
        int count = db.delete(Constants.TABLE3_NAME, Constants.TYPOLOGY + "=?", whereArgs);
        return count;
    }

}
