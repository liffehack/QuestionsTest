package com.example.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Begin extends AppCompatActivity {
Button btn;
EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);
        btn=(Button) findViewById(R.id.button4);
        name=(EditText) findViewById(R.id.editText);
        btn.setOnClickListener(v -> {
            Intent intent = new Intent(Begin.this, MainActivity.class);
            String Your=name.getText().toString();
            intent.putExtra("YourName", Your);
            startActivity(intent);
            finish();
        });
    }
}
