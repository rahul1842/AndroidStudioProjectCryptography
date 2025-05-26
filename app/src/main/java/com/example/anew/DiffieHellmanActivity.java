package com.example.anew;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DiffieHellmanActivity extends AppCompatActivity {

    private EditText pField, gField, privateAField, privateBField;
    private Button calculateButton;
    private TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diffie_hellman);

        pField = findViewById(R.id.pField);
        gField = findViewById(R.id.gField);
        privateAField = findViewById(R.id.privateA);
        privateBField = findViewById(R.id.privateB);
        calculateButton = findViewById(R.id.calculateButton);
        resultView = findViewById(R.id.resultView);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    long p = Long.parseLong(pField.getText().toString());
                    long g = Long.parseLong(gField.getText().toString());
                    long privateA = Long.parseLong(privateAField.getText().toString());
                    long privateB = Long.parseLong(privateBField.getText().toString());

                    long publicA = modExp(g, privateA, p);
                    long publicB = modExp(g, privateB, p);

                    long sharedSecretA = modExp(publicB, privateA, p);
                    long sharedSecretB = modExp(publicA, privateB, p);

                    resultView.setText("Public A: " + publicA + "\n" +
                            "Public B: " + publicB + "\n" +
                            "Shared Secret A: " + sharedSecretA + "\n" +
                            "Shared Secret B: " + sharedSecretB);
                } catch (Exception e) {
                    resultView.setText("Invalid input");
                }
            }
        });
    }

    // Modulus Exponentiation function (g^a mod p)
    private long modExp(long base, long exp, long mod) {
        long result = 1;
        while (exp > 0) {
            if (exp % 2 == 1) {
                result = (result * base) % mod;
            }
            base = (base * base) % mod;
            exp /= 2;
        }
        return result;
    }
}
