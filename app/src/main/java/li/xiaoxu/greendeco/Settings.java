package li.xiaoxu.greendeco;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import li.xiaoxu.greendeco.ui.maps.MapsFragment;


public class Settings extends AppCompatActivity implements SensorEventListener {

//    testing
    private static final String TAG = "MyActivity";

    private boolean setDarkTheme = false;

    Button btn_nav_home;
    Button btn_nav_map;
    static final int REQUEST_CODE_ENTRY = 0;

    private SensorManager mySensorManager;
    private Sensor myLight;
    private Button button, button2;

    EditText name, email;

    MediaPlayer mp;
    Context context;

    public static final String MyPROFILE = "MyProfile" ;
    public static final String Name = "nameKey";
    public static final String Email = "emailKey";
    public static final String Position = "positionKey";

    SharedPreferences sharedpreferences;

    private RadioGroup radioGroup;

    boolean auto = false;
    int g = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        context = this;
        mp = MediaPlayer.create(context, R.raw.sound);

        //Implicit Intent to GI Website
        btn_nav_home = (Button) findViewById(R.id.btn_nav_home);
        btn_nav_home.setOnClickListener((v)->{
            finish();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        });

        //Implicit Intent to GI Website
        btn_nav_map = (Button) findViewById(R.id.btn_nav_map);
        btn_nav_map.setOnClickListener((v)->{
            finish();
            Intent i = new Intent(this, MapsFragment.class);
            startActivity(i);
        });

        //Implicit Intent to GI Website
        btn_nav_map = (Button) findViewById(R.id.btn_nav_sites);
        btn_nav_map.setOnClickListener((v)->{
            finish();
            Intent i = new Intent(this, SitesActivity.class);
            startActivity(i);
        });

        //Implicit Intent to settings
        btn_nav_map = (Button) findViewById(R.id.btn_nav_set);
        btn_nav_map.setOnClickListener((v)->{
            finish();
            Intent i = new Intent(this, Settings.class);
            startActivity(i);
        });

        mySensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        myLight = mySensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        name = (EditText)findViewById(R.id.editTextName);
        email = (EditText)findViewById(R.id.editTextEmail);

        sharedpreferences = getSharedPreferences(MyPROFILE, Context.MODE_PRIVATE);
        String channel1 = (sharedpreferences.getString(Name, ""));
        name.setText(channel1);

        sharedpreferences = getSharedPreferences(MyPROFILE, Context.MODE_PRIVATE);
        String channel2 = (sharedpreferences.getString(Email, ""));
        email.setText(channel2);

        sharedpreferences = getSharedPreferences(MyPROFILE, Context.MODE_PRIVATE);
        g = (sharedpreferences.getInt(Position, 0));

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener((v)->{

            try {
                if (mp.isPlaying()) {
                    mp.stop();
                    mp.release();
                    mp = MediaPlayer.create(context, R.raw.sound);
                } mp.start();
            } catch(Exception e) { e.printStackTrace(); }

            String n = name.getText().toString();
            String e = email.getText().toString();
            Integer gi = g;

            SharedPreferences.Editor editor = sharedpreferences.edit();

            editor.putString(Name, n);
            editor.putString(Email, e);
            editor.putInt(Position, gi);
            editor.commit();

        });

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener((v)->{

            SharedPreferences.Editor editor = sharedpreferences.edit();

            radioGroup.clearCheck();

            editor.clear();
            editor.putInt(Position, 0);
            editor.commit();

            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


            Intent i = new Intent(this, SignupActivity.class);
            startActivity(i);

        });


        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        if (g == 0) {
            auto = false;
            radioGroup.check(R.id.radioButton1);
        }

        if (g == 1) {
            auto = false;
            radioGroup.check(R.id.radioButton2);
        }

        if (g == 2) {
            auto = true;
            radioGroup.check(R.id.radioButton3);
        }


        //implement this in class instead of creating new object
//save this to sharedpreferences to maintain selection
        radioGroup.setOnCheckedChangeListener((RadioGroup.OnCheckedChangeListener) (group, checkedId) -> {

            int radioButtonID = group.getCheckedRadioButtonId();
            View radioButton = group.findViewById(radioButtonID);
            int position = group.indexOfChild(radioButton);

            RadioButton rb = (RadioButton) group.findViewById(checkedId);
            if (null != rb && position == 0) {
                auto = false;
                Log.i(TAG, "position should be 0" + position);

                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putInt(Position, position);
                editor.commit();
            }

            if (null != rb && position == 1) {
                Log.i(TAG, "position should be 1" + position);

                auto = false;
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putInt(Position, position);
                editor.commit();
            }

            if (null != rb && position == 2) {
                auto = true;

                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putInt(Position, position);
                editor.commit();
            }

        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        mySensorManager.registerListener(this, myLight, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        mySensorManager.unregisterListener(this);
        super.onPause();
    }

    //Only called when the sensor is altered
    @Override
    public void onSensorChanged(SensorEvent event) {
//        Log.i(TAG, "Auto is " + auto);

        int type = event.sensor.getType();

        if(type == Sensor.TYPE_LIGHT) {

            float[] vals = event.values;

//            Log.i(TAG, "Light value in auto" + vals[0]);
//
//            EditText lightEditText;
//
//            lightEditText = findViewById(R.id.lightEditText);
//
//            lightEditText.setText("Light Sensor: " + vals[0]);

            if (auto == true) {
                if (vals[0] > 1000) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    setDarkTheme = true;
                }
                else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public boolean isSetDarkTheme() {
        return setDarkTheme;
    }

    public void setSetDarkTheme(boolean setDarkTheme) {
        this.setDarkTheme = setDarkTheme;
    }

}
