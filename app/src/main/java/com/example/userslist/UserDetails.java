package com.example.userslist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class UserDetails extends AppCompatActivity {

    TextView name,email,phone,street,city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        name = (TextView)findViewById(R.id.txt_name);
        email = (TextView)findViewById(R.id.txt_email);
        phone = (TextView)findViewById(R.id.txt_phone);
        street = (TextView)findViewById(R.id.txt_street);
        city = (TextView)findViewById(R.id.txt_city);

        Bundle bundle = getIntent().getExtras();
        name.setText(bundle.getString("name"));
        email.setText(bundle.getString("email"));
        phone.setText(bundle.getString("phone"));
        street.setText(bundle.getString("street"));
        city.setText(bundle.getString("city"));
    }
}
