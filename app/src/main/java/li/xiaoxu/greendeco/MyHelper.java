package li.xiaoxu.greendeco;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static li.xiaoxu.greendeco.Constants.DATABASE_NAME;
import static li.xiaoxu.greendeco.Constants.DATABASE_PATH;


public class MyHelper extends SQLiteOpenHelper {

    private Context context;

    public MyHelper(Context context){
        super (context, DATABASE_NAME, null, Constants.DATABASE_VERSION);
        this.context = context;
    }

    /*
    Code from StackOverFlow
    https://stackoverflow.com/questions/22627215/how-to-put-database-and-read-database-from-assets-folder-android-which-are-creat/22627776
    createDatabase()
    checkDatabase()
    copyDatabase()
     */

    //Check database already exist or not
    private boolean checkDatabase() {
        boolean checkDB = false;
        try {
            String myPath = DATABASE_PATH + DATABASE_NAME;
            File dbFile = new File(myPath);
            checkDB = dbFile.exists();
        }
        catch(SQLiteException e) {
        }
        return checkDB;
    }

    public void createDatabase() throws Exception {
        boolean dbExist = checkDatabase();
        if(dbExist) {
            Log.v("DB Exists", "db exists");
            // By calling this method here onUpgrade will be called on a
            // writeable database, but only if the version number has been
            // bumped
            //onUpgrade(myDataBase, DATABASE_VERSION_old, DATABASE_VERSION);
        }

        boolean dbExist2 = checkDatabase();
        if(!dbExist2) {
            this.getReadableDatabase();
            try
            {
                this.close();
                copyDataBase();
            }
            catch (Exception e)
            {
                throw new Error("Error copying database");
            }
        }
    }
    //Copies your database from your local assets-folder to the just created empty database in the system folder
    private void copyDataBase() throws IOException {
        InputStream mInput = context.getAssets().open(DATABASE_NAME);
        String outFileName = DATABASE_PATH + DATABASE_NAME;
        OutputStream mOutput = new FileOutputStream(outFileName);
        byte[] mBuffer = new byte[2024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0) {
            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    //delete database
    public void deleteDatabase()
    {
        File file = new File(DATABASE_PATH + DATABASE_NAME);
        if(file.exists())
        {
            file.delete();
            System.out.println("delete database file.");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            createDatabase();
            Toast.makeText(context, "onCreate() called", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(context, "exception onCreate() db", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            Log.v("Database Upgrade", "Database version higher than old.");
            Toast.makeText(context, "onUpgrade called", Toast.LENGTH_LONG).show();
            deleteDatabase();
        }
    }
}
