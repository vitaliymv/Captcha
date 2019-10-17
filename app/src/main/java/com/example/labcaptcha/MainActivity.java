package com.example.labcaptcha;



import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView im;
    Button btn;
    Button send;
    EditText ed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        im = findViewById(R.id.imageView1);
        btn = findViewById(R.id.button1);
        ed =  findViewById(R.id.editText);
        send = findViewById(R.id.button2);

        btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                final Random random = new Random();
                String r = String.valueOf(random.nextInt(2) + 1);
                if(r.equals("1")){
                    Captcha c = new TextCaptcha(300, 100, 4, TextCaptcha.TextOptions.NUMBERS_AND_LETTERS);
                    im.setImageBitmap(c.image);
                    im.setLayoutParams(new LinearLayout.LayoutParams(c.width *2, c.height *2));
                } else if(r.equals("2")){
                    Captcha c = new MathCaptcha(300, 100, MathCaptcha.MathOptions.PLUS_MINUS_MULTIPLY);
                    im.setImageBitmap(c.image);
                    im.setLayoutParams(new LinearLayout.LayoutParams(c.width *2, c.height *2));
                }

            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String data = ed.getText().toString();
                if (data.equals(Captcha.answer)){
                    Toast.makeText(MainActivity.this, "Капча введена правильно", Toast.LENGTH_SHORT).show();
                } else if(!data.equals(Captcha.answer)){
                    Toast.makeText(MainActivity.this, "Капча введена неправильно", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
