package li.xiaoxu.greendeco.ui.settings;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import li.xiaoxu.greendeco.R;
import li.xiaoxu.greendeco.SignupActivity;

import static android.content.Context.SENSOR_SERVICE;


public class SettingsFragment extends Fragment implements SensorEventListener {

    static final int REQUEST_CODE_ENTRY = 0;
    private static final String TAG = "Settings";

    private SensorManager mySensorManager;
    private Sensor myLight;

    EditText name, email;

    public static final String MyPROFILE = "MyProfile" ;
    public static final String Name = "nameKey";
    public static final String Email = "emailKey";
    public static final String Position = "positionKey";

    SharedPreferences sharedpreferences;

    private RadioGroup radioGroup;

    boolean auto = false;
    int g = 0;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SettingsViewModel settingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
//        new ViewModelProvider(this).get(li.xiaoxu.greendeco.ui.settings.SettingsViewModel.class);
        //Inflate the layout for this fragment
        return inflater.inflate(R.layout.settings, container, false);
        }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mySensorManager = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);
        myLight = mySensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        name = (EditText)getView().findViewById(R.id.editTextName);
        email = (EditText)getView().findViewById(R.id.editTextEmail);

        sharedpreferences = getActivity().getSharedPreferences(MyPROFILE, Context.MODE_PRIVATE);
        String channel1 = (sharedpreferences.getString(Name, ""));
        name.setText(channel1);

        sharedpreferences = getActivity().getSharedPreferences(MyPROFILE, Context.MODE_PRIVATE);
        String channel2 = (sharedpreferences.getString(Email, ""));
        email.setText(channel2);

        sharedpreferences = getActivity().getSharedPreferences(MyPROFILE, Context.MODE_PRIVATE);
        g = (sharedpreferences.getInt(Position, 0));

        Button button = (Button) getView().findViewById(R.id.button);
        button.setOnClickListener((v)->{
            String n = name.getText().toString();
            String e = email.getText().toString();
            Integer gi = g;

            SharedPreferences.Editor editor = sharedpreferences.edit();

            editor.putString(Name, n);
            editor.putString(Email, e);
            editor.putInt(Position, gi);
            editor.commit();

        });

        Button button2 = (Button) getView().findViewById(R.id.button2);
        button2.setOnClickListener((v)->{

            SharedPreferences.Editor editor = sharedpreferences.edit();

            radioGroup.clearCheck();

            editor.clear();
            editor.putInt(Position, 0);
            editor.commit();

            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


            Intent i = new Intent(getActivity(), SignupActivity.class);
            startActivity(i);

        });


        radioGroup = (RadioGroup) getView().findViewById(R.id.radioGroup);

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


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            //implement this in class instead of creating new object
            //save this to sharedpreferences to maintain selection

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

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

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mySensorManager.registerListener(this, myLight, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
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

            if (auto) {
                if (vals[0] > 1000) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
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

}
