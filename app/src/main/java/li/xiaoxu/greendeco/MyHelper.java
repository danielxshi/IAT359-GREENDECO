package li.xiaoxu.greendeco;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import static li.xiaoxu.greendeco.Constants.DATABASE_NAME;
import static li.xiaoxu.greendeco.Constants.DATABASE_PATH;
import static li.xiaoxu.greendeco.Constants.TABLE1_NAME;


public class MyHelper extends SQLiteOpenHelper {

    private Context context;

    public MyHelper(Context context){
        super (context, copyDatabaseBeforeCreation(context, DATABASE_NAME), null, Constants.DATABASE_VERSION);
        this.context = context;
    }

   public static String copyDatabaseBeforeCreation (Context context, String name) {
       File databasePath = context.getDatabasePath(name);
       if (!databasePath.exists()) {
           AssetManager assets = context.getAssets();
           try (InputStream inputStream = assets.open(name); //source
                OutputStream outputStream = new FileOutputStream(databasePath)){ //output to device data folder
               //copy the database to device
               byte[] buffer = new byte[8192]; //variable starts with "m" means number variable
               int mLength;
               while ((mLength = inputStream.read(buffer)) > 0) {
                   outputStream.write(buffer, 0, mLength);
               }
           } catch (IOException e){
               throw new RuntimeException(e);
           }
       }
       return name;
   }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
