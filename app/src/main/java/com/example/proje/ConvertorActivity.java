package com.example.proje;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ConvertorActivity extends AppCompatActivity {

    Button btn_decimal, btn_megabyte, btn_celcius;
    EditText input_decimal, input_megabyte, input_celcius;

    TextView result_decimal, result_megabyte, result_celcius;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convertor);


        //decimal
        btn_decimal = findViewById(R.id.decimal_btn);
        input_decimal = findViewById(R.id.decimal_input);
        result_decimal = findViewById(R.id.decimal_result);


        btn_decimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConvertToDecimal();
            }
        });


        //megabyte
        btn_megabyte = findViewById(R.id.megabyte_btn);
        input_megabyte = findViewById(R.id.megabyte_input);
        result_megabyte = findViewById(R.id.megabyte_result);

        btn_megabyte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConvertToByte();
            }
        });


        //celcius
        btn_celcius = findViewById(R.id.celcius_btn);
        input_celcius = findViewById(R.id.celcius_input);
        result_celcius = findViewById(R.id.celcius_result);

        btn_celcius.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConvertToCelcius();
            }
        });

    }



    private void ConvertToDecimal() {
        String inputDecimal = input_decimal.getText().toString();

        if(!inputDecimal.isEmpty()){
            try {
                int decimalNumber = Integer.parseInt(inputDecimal);

                String binary = convertToBinary(decimalNumber);
                String octal = convertToOctal(decimalNumber);
                String hexadecimal = convertToHexadecimal(decimalNumber);

                result_decimal.setText("İkilik: " + binary + "\nSekizlik: " + octal + "\nOnaltılık:" + hexadecimal);
            }catch (NumberFormatException e){
                result_decimal.setText("Geçersiz bir değer!");
            }
        }else{
            result_decimal.setText("Lütden bir değer giriniz!");
        }
    }

    private String convertToBinary(int decimalNumber){
        return Integer.toBinaryString(decimalNumber);
    }

    private String convertToOctal(int decimalNumber){
        return Integer.toOctalString(decimalNumber);
    }

    private String convertToHexadecimal(int decimalNumber){
        return Integer.toHexString(decimalNumber);
    }


    private void ConvertToByte() {
        String inputMegabyte = input_megabyte.getText().toString();

        if(!inputMegabyte.isEmpty()){
            try{
                double megaByteValue = Double.parseDouble(inputMegabyte);

                double kiloByte = convertToKiloByte(megaByteValue);
                double byteValue = convertToByte(megaByteValue);
                double kibiByte = convertToKibiByte(megaByteValue);
                double bitValue = convertToBit(megaByteValue);

                result_megabyte.setText("Kilo byte : " + kiloByte + "\nByte: " + byteValue + "\nKibi byte: " + kibiByte + "\nBit: " + bitValue);
            }catch (NumberFormatException e){
                result_megabyte.setText("Geçersiz değer!");
            }
        }else{
            result_megabyte.setText("Lütfen bir değer giriniz!");
        }
    }

    private double convertToKiloByte(double megaByteValue){
        return megaByteValue * 1024;
    }
    private double convertToByte(double megaByteValue){
        return megaByteValue * 1024 * 1024;
    }
    private double convertToKibiByte(double megaByteValue){
        return megaByteValue * 8 * 1024 * 1024;
    }
    private double convertToBit(double megaByteValue){
        return megaByteValue * 8  * 8 * 1024 * 1024;
    }

    private void ConvertToCelcius() {
        String inputCelsius = input_celcius.getText().toString();

        if (!inputCelsius.isEmpty()) {
            try {
                double celsiusTemperature = Double.parseDouble(inputCelsius);

                double fahrenheit = convertCelsiusToFahrenheit(celsiusTemperature);
                double kelvin = convertCelsiusToKelvin(celsiusTemperature);

                result_celcius.setText("Fahrenheit: " + fahrenheit + "\nKelvin: " + kelvin);
            } catch (NumberFormatException e) {
                result_celcius.setText("Geçersiz değer");
            }
        } else {
            result_celcius.setText("Lütfen bir sıcaklık değeri giriniz!");
        }
    }

    private double convertCelsiusToFahrenheit(double celsius) {
        return (celsius * 9/5) + 32;
    }

    private double convertCelsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }


}
