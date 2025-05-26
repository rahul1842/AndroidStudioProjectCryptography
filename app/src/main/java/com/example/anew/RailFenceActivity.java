package com.example.anew;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class RailFenceActivity extends AppCompatActivity {

    EditText railKeyInput, railMessageInput;
    Button encryptRailBtn;
    TextView encryptedRailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rail_fence);

        railKeyInput = findViewById(R.id.railKeyInput);
        railMessageInput = findViewById(R.id.railMessageInput);
        encryptRailBtn = findViewById(R.id.encryptRailBtn);
        encryptedRailText = findViewById(R.id.encryptedRailText);

        encryptRailBtn.setOnClickListener(v -> {
            int key = Integer.parseInt(railKeyInput.getText().toString());
            String message = railMessageInput.getText().toString();
            String encrypted = railFenceEncrypt(message, key);
            encryptedRailText.setText("Encrypted Message: " + encrypted);
        });
    }

    public String railFenceEncrypt(String message, int key) {
        char[] msg = message.toCharArray();
        char[][] rail = new char[key][message.length()];
        boolean directionDown = false;
        int row = 0, col = 0;

        for (int i = 0; i < message.length(); i++) {
            rail[row][col++] = msg[i];
            if (row == 0 || row == key - 1) {
                directionDown = !directionDown;
            }
            row = directionDown ? row + 1 : row - 1;
        }

        StringBuilder encryptedMessage = new StringBuilder();
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < message.length(); j++) {
                if (rail[i][j] != 0) {
                    encryptedMessage.append(rail[i][j]);
                }
            }
        }

        return encryptedMessage.toString();
    }
}
