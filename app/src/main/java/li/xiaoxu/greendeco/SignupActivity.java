package li.xiaoxu.greendeco;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class SignupActivity extends AppCompatActivity {

    private Button button;
    private Button webBt;

    EditText name, email, password;
    static final int REQUEST_CODE_ENTRY = 0;
    public static final String MyPROFILE = "MyProfile" ;
    public static final String Name = "nameKey";
    public static final String Email = "emailKey";
    public static final String Password = "passwordKey";
    public static final String Position = "positionKey";

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name = (EditText)findViewById(R.id.editTextName);
        email = (EditText)findViewById(R.id.editTextEmail);
        password = (EditText)findViewById(R.id.editTextPassword);

        sharedpreferences = getSharedPreferences(MyPROFILE, Context.MODE_PRIVATE);

        //sign up button
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener((v)->{
            String n = name.getText().toString();
            String e = email.getText().toString();
            String p = password.getText().toString();

            SharedPreferences.Editor editor = sharedpreferences.edit();

            editor.putString(Name, n);
            editor.putString(Email, e);
            editor.putString(Password, p);
            editor.putInt(Position, 0);
            editor.commit();
            Toast.makeText(SignupActivity.this,"Profile Saved", Toast.LENGTH_LONG).show();

            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        });

        //website button
        webBt = (Button) findViewById(R.id.webButton);
        webBt.setOnClickListener((v)->{
            Intent i = new Intent();
            onActivityResult(REQUEST_CODE_ENTRY,RESULT_OK,i);
            finish();

        });
    }

    //Implicit Intent to redirect to CityStudio's website
    @Override
    protected void onActivityResult(int rqCode, int rsCode, Intent data){
        if(rqCode==REQUEST_CODE_ENTRY){
            if(rsCode==RESULT_OK){
                Uri webpage = Uri.parse("https://citystudiovancouver.com/");
                Intent webIntent = new Intent(Intent.ACTION_VIEW,webpage);
                startActivity(webIntent);
            }
        }
        super.onActivityResult(rqCode, rsCode, data);
    }
}
