package li.xiaoxu.greendeco;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;


public class Settings extends AppCompatActivity implements SensorEventListener {
    private SensorManager mySensorManager;
    private Sensor myLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        mySensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        myLight = mySensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

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

            lightEditText.setText("Light: " + vals[0]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

//    private Button.OnClickListener myListener = new Button.OnClickListener() {
//
//        @Override
//        public void onClick(View view) {
//
//        }
//    };
}
