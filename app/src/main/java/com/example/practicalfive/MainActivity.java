package com.example.practicalfive;

import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MUNICIPALITY =
            "com.example.practicalfive.MUNICIPALITY";

    private EditText municipalityInput;
    private final TextProcessor textProcessor = new TextProcessor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        municipalityInput = findViewById(R.id.municipality_input);
        municipalityInput.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_GO) {
                validateAndOpen();
                return true;
            }
            return false;
        });
    }

    private void validateAndOpen() {
        String raw = municipalityInput.getText().toString();
        String validName = textProcessor.processMunicipality(raw);

        if (validName == null) {
            if (raw.trim().isEmpty()) {
                municipalityInput.setError(getString(R.string.error_empty));
            } else {
                municipalityInput.setError(getString(R.string.error_invalid));
            }
            return;
        }

        Intent intent = new Intent(MainActivity.this, Municipality.class);
        intent.putExtra(EXTRA_MUNICIPALITY, validName);
        startActivity(intent);
    }
}
