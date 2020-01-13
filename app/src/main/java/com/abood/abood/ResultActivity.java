package com.abood.abood;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView,textView1,textView2,textView3;
    char c = 'm';

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        imageView = findViewById(R.id.profile_image);
        textView = findViewById(R.id.name);
        textView1 = findViewById(R.id.brithdate);
        textView2 = findViewById(R.id.gender);
        textView3 = findViewById(R.id.major);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Bitmap image = (Bitmap) extras.get("image");
            if (image != null) {
                imageView.setImageBitmap(image);
            }
        }

        String name = getIntent().getExtras().getString("name");
        String brithdate = getIntent().getExtras().getString("brithday");
        char gender = getIntent().getCharExtra("gender",c);
        String major = getIntent().getExtras().getString("major");

        textView.setText(name);
        textView1.setText(brithdate);

        if (gender == 'm') {
            textView2.setText("Male");

        } else if(gender == 'f') {
            textView2.setText("Fmale");
        }
        textView3.setText(major);


    }
}
