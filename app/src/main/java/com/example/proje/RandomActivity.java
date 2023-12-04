package com.example.proje;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Random;

public class RandomActivity extends AppCompatActivity {
    EditText inputAdet, inputMin, inputMax;

    Button btnRandm;

    LinearLayout scrollViewContainer;

    ProgressBar[] progressBars;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        inputAdet = findViewById(R.id.input_adet);
        inputMin = findViewById(R.id.input_min);
        inputMax = findViewById(R.id.input_max);

        btnRandm =findViewById(R.id.btn_rndm);

        scrollViewContainer = findViewById(R.id.linear_layout_container);

        btnRandm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adet = Integer.parseInt(inputAdet.getText().toString());
                int min = Integer.parseInt(inputMin.getText().toString());
                int max = Integer.parseInt(inputMax.getText().toString());

                if(min >= max){
                    Toast.makeText(RandomActivity.this, "Minimum değer maksimum değerden büyük olamaz!", Toast.LENGTH_SHORT).show();
                    return;
                }

                scrollViewContainer.removeAllViews();

                progressBars = new ProgressBar[adet];

                for(int i = 0; i< adet; i++){
                    int randomValue = new Random().nextInt(max - min) + min;

                    float progressPercentage = ((float) randomValue / (max-min) *100);

                    ProgressBar progressBar = new ProgressBar(RandomActivity.this, null, android.R.attr.progressBarStyleHorizontal);
                    progressBar.setMax(max - min);
                    progressBar.setProgress(randomValue);

                    TextView textViewValue = new TextView((RandomActivity.this));
                    textViewValue.setText(String.format("%d (%f%%))", randomValue, progressPercentage));


                    LinearLayout linearLayout = new LinearLayout(RandomActivity.this);
                    linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                    linearLayout.addView(progressBar);
                    linearLayout.addView(textViewValue);


                    scrollViewContainer.addView(linearLayout);

                    progressBars[i] = progressBar;
                }
            }
        });

    }
}


