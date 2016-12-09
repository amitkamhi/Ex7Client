package com.example.kamhi.ex7client;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String ACTION_REGISTER = "com.Ex7.Register";
    public static final int REGISTER_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttnOptions(View view){
        switch(view.getId()){
            case R.id.buttonCall: {
                String number = ((EditText) findViewById(R.id.editTextCall)).getText().toString();
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:" + number));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(i);
                }
                break;
            }
                case R.id.buttonSurf: {
                String url = ((EditText) findViewById(R.id.editTextSurf)).getText().toString();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + url));
                startActivity(intent);
                break;

            }
            case R.id.buttonEmail: {
                String recipent = ((EditText) findViewById(R.id.editTextEmail)).getText().toString();
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{recipent});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Hello from Ex7Client");
                intent.putExtra(Intent.EXTRA_TEXT, "This is my email from Ex7Client program");
                intent.setType("plain/text");
                startActivity(intent);
                break;
            }
            case R.id.buttonRegister: {
                Intent intent = new Intent(ACTION_REGISTER);
                startActivityForResult(intent, REGISTER_REQUEST);
                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REGISTER_REQUEST && resultCode == RESULT_OK){

            String firstName = data.getExtras().getString("First Name");
            String lastName = data.getExtras().getString("Last Name");
            String gender = data.getExtras().getString("Gender");

            EditText register = ((EditText) findViewById(R.id.editTextRegister));
            register.setText("Welcome " + gender + firstName + " " + lastName);
        }
    }
}
