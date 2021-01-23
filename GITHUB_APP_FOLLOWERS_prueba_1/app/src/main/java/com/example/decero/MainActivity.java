package com.example.decero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView text;
    private EditText respuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        respuesta =(EditText)findViewById(R.id.respuesta);



    }

    public void button (View view){

        String username = respuesta.getText().toString();
        respuesta.setText("");
        //Toast.makeText(this,"animo",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("username",username);
        startActivity(intent);

    }

}