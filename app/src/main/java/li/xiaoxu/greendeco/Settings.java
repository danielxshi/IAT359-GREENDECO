package li.xiaoxu.greendeco;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Settings extends AppCompatActivity implements SensorEventListener {

    private SensorManager mySensorManager;
    private Sensor myLight;
    private Button button;
    private Button button2;

    EditText name, email;

    public static final String MyPROFILE = "MyProfile" ;
    public static final String Name = "nameKey";
    public static final String Email = "emailKey";

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

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

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener((v)->{
            String n = name.getText().toString();
            String e = email.getText().toString();

            SharedPreferences.Editor editor = sharedpreferences.edit();

            editor.putString(Name, n);
            editor.putString(Email, e);
            editor.commit();
            Toast.makeText(Settings.this,"Saved", Toast.LENGTH_LONG).show();

            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        });

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener((v)->{

            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.clear();

            Intent i = new Intent(this, SignupActivity.class);
            startActivity(i);

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

    @Override
    public void onSensorChanged(SensorEvent event) {
        int type = event.sensor.getType();

        if(type == Sensor.TYPE_LIGHT) {

            float[] vals = event.values;

            EditText lightEditText;

            lightEditText = findViewById(R.id.lightEditText);

            lightEditText.setText("Light Sensor: " + vals[0]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
