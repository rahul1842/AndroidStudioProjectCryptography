package com.example.anew;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RSAActivity extends AppCompatActivity {

    private EditText pField, qField;
    private Button generateKeysButton;
    private TextView encryptionKeyView, decryptionKeyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rsa);

        pField = findViewById(R.id.pField);
        qField = findViewById(R.id.qField);
        generateKeysButton = findViewById(R.id.generateKeysButton);
        encryptionKeyView = findViewById(R.id.encryptionKeyView);
        decryptionKeyView = findViewById(R.id.decryptionKeyView);

        generateKeysButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    long p = Long.parseLong(pField.getText().toString());
                    long q = Long.parseLong(qField.getText().toString());

                    long n = p * q;
                    long phi = (p - 1) * (q - 1);

                    // Choose public exponent e
                    long e = 65537;  // Common choice for e

                    // Compute private exponent d
                    long d = modInverse(e, phi);

                    // Display public and private keys
                    encryptionKeyView.setText("Encryption Key (e, n): (" + e + ", " + n + ")");
                    decryptionKeyView.setText("Decryption Key (d, n): (" + d + ", " + n + ")");
                } catch (Exception e) {
                    encryptionKeyView.setText("Invalid input");
                    decryptionKeyView.setText("");
                }
            }
        });
    }

    // Extended Euclidean Algorithm for finding modular inverse
    private long modInverse(long a, long m) {
        long m0 = m, x0 = 0, x1 = 1;
        if (m == 1) return 0;

        while (a > 1) {
            long q = a / m;
            long t = m;

            m = a % m;
            a = t;
            t = x0;

            x0 = x1 - q * x0;
            x1 = t;
        }

        if (x1 < 0) x1 += m0;

        return x1;
    }
}
