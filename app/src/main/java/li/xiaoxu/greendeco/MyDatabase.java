package li.xiaoxu.greendeco;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Button;


import java.util.ArrayList;

public class MyDatabase {

    private SQLiteDatabase db;
    private Context context;
    private final MyHelper helper;

    Button btn_nav_home;
    Button btn_nav_map;

    public MyDatabase (Context c) {
        context = c;
        helper = new MyHelper(context);
    }
    //select table1.facility_id, lat, long from table1 join table2 where table1.facility_id = table2.facility_id;
    //select table1.facility_id, address, typology, description from table1 join table2 where table1.facility_id = table2.facility_id;
    //insert Query

    public ArrayList<String> getSitesData()
    {
        //select plants from database of type 'herb'
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {Constants.FID, Constants.ADDRESS, Constants.TYPOLOGY, Constants.DESCRIPTION};

        String queryString = "SELECT table2.address, table2.typology, table2.description FROM table1 join table2 WHERE table1.facility_id = table2.facility_id";
        String[] selection = {"table2.address","table2.typology","table2.description","table1.facility_id = table2.facility_id"};  //Constants.TYPE = 'type'
        Cursor cursor = db.rawQuery(queryString, null); //TODO

//        StringBuffer buffer = new StringBuffer();
        ArrayList<String> mArrayList = new ArrayList<String>();
        while (cursor.moveToNext()) {
            int index1 = cursor.getColumnIndex(Constants.ADDRESS);
            int index2 = cursor.getColumnIndex(Constants.TYPOLOGY);
            int index3 = cursor.getColumnIndex(Constants.DESCRIPTION);

            String siteAddress = cursor.getString(index1);
            String siteTypology = cursor.getString(index2);
            String siteDescription = cursor.getString(index3);

//            buffer.append(siteaddress + " " + sitetypology + " " + sitedescription + "\n");

            String s = siteAddress + "," + siteTypology + "," + siteDescription;
            mArrayList.add(s);
        }
        return mArrayList;
    }

    public ArrayList<String> getMapData()
    {
        //select facility's gps coordinates from database where it has descriptions
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {Constants.FID, Constants.ADDRESS, Constants.TYPOLOGY, Constants.DESCRIPTION};

        String queryString = "SELECT table1.facility_id, lat, long FROM table1 JOIN table2 WHERE table1.facility_id = table2.facility_id;";
        String[] selection = {"table1.facility_id","lat","long"};  //
        Cursor cursor = db.rawQuery(queryString, selection);

//        StringBuffer buffer = new StringBuffer();
        ArrayList<String> mArrayList = new ArrayList<String>();
        while (cursor.moveToNext()) {

            int index0 = cursor.getColumnIndex(Constants.FID);
            int index1 = cursor.getColumnIndex(Constants.LAT);
            int index2 = cursor.getColumnIndex(Constants.LONG);

            String siteFID = cursor.getString(index0);
            String siteLatitude = cursor.getString(index1);
            String siteLongitude = cursor.getString(index2);

//            buffer.append(siteaddress + " " + sitetypology + " " + sitedescription + "\n");

            String s = siteFID + "," + siteLatitude + "," + siteLongitude;
            mArrayList.add(s);
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
