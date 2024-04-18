package com.example.harjoitustyo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText municipality;



    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });




        // Button for adding, when added ---> tablayout
        // Can be done with onClickListener
        // TODO Also a recyclerview in oncreate

        Button searchButton = findViewById(R.id.searchButton);


       // searchButton.setOnClickListener(listener);
    }

        public void searchMunicipality(View view) {
            municipality = findViewById(R.id.editMunicipality);
            // String municipalityString  = .getText().toString().

            // switch to tabActivity
            Intent intent = new Intent(this, TabActivity.class);
            startActivity(intent);

        }

}