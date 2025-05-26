package com.example.anew;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnRailFence, btnDiffieHellman, btnRSA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRailFence = findViewById(R.id.btnRailFence);
        btnDiffieHellman = findViewById(R.id.btnDiffieHellman);
        btnRSA = findViewById(R.id.btnRSA);

        btnRailFence.setOnClickListener(v -> startActivity(new Intent(this, RailFenceActivity.class)));
        btnDiffieHellman.setOnClickListener(v -> startActivity(new Intent(this, DiffieHellmanActivity.class)));
        btnRSA.setOnClickListener(v -> startActivity(new Intent(this, RSAActivity.class)));
    }
}
