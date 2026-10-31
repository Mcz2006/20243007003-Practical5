package com.example.practicalfive;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class Municipality extends AppCompatActivity {

    private final TextProcessor textProcessor = new TextProcessor();
    private String municipalityName;
    private String websiteUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipality);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        String passed = getIntent().getStringExtra(MainActivity.EXTRA_MUNICIPALITY);
        municipalityName = textProcessor.processMunicipality(passed);

        if (municipalityName == null) {
            // Missing or invalid Intent data: fail safely instead of crashing.
            Toast.makeText(this, R.string.error_invalid, Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        int colorRes;
        int imageRes;
        int arrayIndex;

        switch (municipalityName) {
            case "Beijing":
                colorRes = R.color.municipality_beijing;
                imageRes = R.drawable.beijing;
                arrayIndex = 0;
                websiteUrl = "https://english.beijing.gov.cn/";
                break;
            case "Shanghai":
                colorRes = R.color.municipality_shanghai;
                imageRes = R.drawable.shanghai;
                arrayIndex = 1;
                websiteUrl = "https://english.shanghai.gov.cn/";
                break;
            case "Chongqing":
                colorRes = R.color.municipality_chongqing;
                imageRes = R.drawable.chongqing;
                arrayIndex = 2;
                websiteUrl = "https://english.cq.gov.cn/";
                break;
            case "Tianjin":
                colorRes = R.color.municipality_tianjin;
                imageRes = R.drawable.tianjin;
                arrayIndex = 3;
                websiteUrl = "https://en.tj.gov.cn/";
                break;
            default:
                Toast.makeText(this, R.string.error_invalid, Toast.LENGTH_SHORT).show();
                finish();
                return;
        }

        int themeColor = ContextCompat.getColor(this, colorRes);
        String[] summaries =
                getResources().getStringArray(R.array.municipalities_array);

        TextView nameView = findViewById(R.id.municipality_name);
        nameView.setText(municipalityName);
        nameView.setBackgroundColor(themeColor);
        nameView.setTextColor(
                ContextCompat.getColor(this, R.color.color_text_light));

        ImageView imageView = findViewById(R.id.municipality_image);
        imageView.setImageResource(imageRes);

        TextView infoView = findViewById(R.id.municipality_info);
        infoView.setText(summaries[arrayIndex]);
        infoView.setTextColor(themeColor);

        Button visitButton = findViewById(R.id.visit_site_button);
        visitButton.setBackgroundColor(themeColor);
        visitButton.setTextColor(
                ContextCompat.getColor(this, R.color.color_text_light));
        visitButton.setOnClickListener(this::openWebsite);
    }

    private void openWebsite(View view) {
        // Implicit Intent: the system chooses a suitable app, usually a browser.
        Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(websiteUrl));
        try {
            startActivity(webIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, R.string.error_no_browser, Toast.LENGTH_SHORT)
                    .show();
        }
    }
}
