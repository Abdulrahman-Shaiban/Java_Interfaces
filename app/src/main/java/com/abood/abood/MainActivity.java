package com.abood.abood;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    LinearLayout myLayout;
    final  Calendar myCalendar = Calendar.getInstance();
    int CAMERAS = 0;
    EditText  editText,editText1;
    ImageView imageView;
    Button button;
    TextView textView;
    RadioGroup radioGroup;
    RadioButton m_radio,f_radio;
    Button button1;
    Spinner spinner;
    char check = 'm';
    Bitmap selectedImage;

    ArrayList<String> Major = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        imageView = new ImageView(this);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(400, 400));
        imageView.setImageDrawable(getDrawable(R.drawable.ic_launcher_foreground));


        button = new Button(this);
        button.setText("Add Photo");
        button.setLayoutParams(new ViewGroup.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));


        editText = new EditText(this);
        editText.setLayoutParams(new ViewGroup.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        editText.setHint("Enter Your Name");
        editText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


        textView = new TextView(this);
        textView.setLayoutParams(new ViewGroup.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        textView.setText("Enter Your Gender : ");

        radioGroup = new RadioGroup(this);
        m_radio = new RadioButton(this);
        m_radio.setText("Male");
        f_radio = new RadioButton(this);
        f_radio.setText("Fmale");
        radioGroup.addView(m_radio);
        radioGroup.addView(f_radio);

        editText1 = new EditText(this);
        editText1.setLayoutParams(new ViewGroup.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        editText1.setHint("Enter Your Brith Date");
        editText1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        editText1.setClickable(true);
        editText1.setFocusable(false);

//        DatePicker datePicker = new DatePicker(this);
//        datePicker.setLayoutParams(new ViewGroup.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT,
//                500));


        Major.add("IS");
        Major.add("IT");
        Major.add("CS");


        ArrayAdapter<String> adapter = new ArrayAdapter(getApplicationContext(),  android.R.layout.simple_spinner_dropdown_item, Major);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);


        spinner = new Spinner(this);
        spinner.setLayoutParams(new ViewGroup.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        spinner.setAdapter(adapter);



        button1 = new Button(this);
        button1.setLayoutParams(new ViewGroup.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        button1.setText("Send");


        myLayout = new LinearLayout(this);
        LinearLayout.LayoutParams buttonParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);

        myLayout.setGravity(1);
        myLayout.setOrientation(LinearLayout.VERTICAL);
        myLayout.setWeightSum(7);


        myLayout.addView(imageView);
        myLayout.addView(button);
        myLayout.addView(editText);
        myLayout.addView(editText1);
        myLayout.addView(textView);
        myLayout.addView(radioGroup);
        myLayout.addView(spinner);
//        myLayout.addView(datePicker);
        myLayout.addView(button1);


        setContentView(myLayout);


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();

            }

        };

        editText1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(MainActivity.this, date,
                        myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePicture, CAMERAS);

            }
        });

        m_radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onRadioButtonClicked(view);

            }
        });

        m_radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onRadioButtonClicked(view);

            }
        });


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!editText.getText().toString().equals("")) {

                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    intent.putExtra("image", selectedImage);
                    intent.putExtra("name",editText.getText().toString());
                    intent.putExtra("brithday",editText1.getText().toString());
                    intent.putExtra("gender",check);
                    intent.putExtra("major",spinner.getSelectedItem().toString());
                    startActivity(intent);

                }else
                    Toast.makeText(MainActivity.this, "Enter Your Name And Gender", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case 0:

                if (resultCode == RESULT_OK && imageReturnedIntent != null) {


                    selectedImage = (Bitmap) imageReturnedIntent.getExtras().get("data");
                    imageView.setImageBitmap(selectedImage);


//                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
//                    intent.putExtra("image", selectedImage);
//                    startActivity(intent);
                }
                break;
        }
    }


    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.male_radio:
                if (checked)
                    check = 'm';
                break;
            case R.id.fmale_radio:
                if (checked)
                    check = 'f';
                break;

        }
    }


    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editText1.setText(sdf.format(myCalendar.getTime()));
    }

}
