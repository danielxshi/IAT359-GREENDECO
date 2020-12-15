package li.xiaoxu.greendeco;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

        buttonSend.setOnClickListener(v -> {
            if(!email.getText().toString().isEmpty() && !subject.getText().toString().isEmpty() && !body.getText().toString().isEmpty()){
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{

                });

                intent.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
                intent.putExtra(Intent.EXTRA_TEXT, body.getText().toString());
                intent.setData(Uri.parse("mailto:"));
                startActivity(intent);
            }
        });
    }

}
