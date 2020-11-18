package li.xiaoxu.greendeco;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class SignupActivity extends AppCompatActivity {

    private Button button;

    EditText name, email, password;

    public static final String MyPROFILE = "MyProfile" ;
    public static final String Name = "nameKey";
    public static final String Email = "emailKey";
    public static final String Password = "passwordKey";

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name = (EditText)findViewById(R.id.editTextName);
        email = (EditText)findViewById(R.id.editTextEmail);
        password = (EditText)findViewById(R.id.editTextPassword);

        sharedpreferences = getSharedPreferences(MyPROFILE, Context.MODE_PRIVATE);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener((v)->{
            String n = name.getText().toString();
            String e = email.getText().toString();
            String p = password.getText().toString();

            SharedPreferences.Editor editor = sharedpreferences.edit();

            editor.putString(Name, n);
            editor.putString(Email, e);
            editor.putString(Password, p);
            editor.commit();
            Toast.makeText(SignupActivity.this,"Profile Saved", Toast.LENGTH_LONG).show();

            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        });

    }

}
