package li.xiaoxu.greendeco;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ContactForm extends Activity {

    EditText email;
    EditText subject;
    EditText body;
    Button buttonSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sites_email);


        email = findViewById(R.id.et_Email);
        subject = findViewById(R.id.et_Subject);
        body = findViewById(R.id.et_Message);

        buttonSend = findViewById(R.id.formSendBtn);

        subject.setText(getIntent().getStringExtra("typology"));

        buttonSend.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(!email.getText().toString().isEmpty() && !subject.getText().toString().isEmpty() && !body.getText().toString().isEmpty()){
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{

                    });

                    intent.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
                    intent.putExtra(Intent.EXTRA_TEXT, body.getText().toString());
                    intent.setData(Uri.parse("mailto:"));
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    } else {
                        Toast.makeText(ContactForm.this, "There is no application that supports this action", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ContactForm.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
