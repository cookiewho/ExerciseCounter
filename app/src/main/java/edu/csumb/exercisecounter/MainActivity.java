package edu.csumb.exercisecounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String MAIN_ACTIVITY = "MainActivity";

    int number1;
    int number2;
    int number3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start = findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    EditText np1 = findViewById(R.id.inputRep);
                    number1 = Integer.parseInt(np1.getText().toString().trim());
                    EditText np2 = findViewById(R.id.inputSet);
                    number2 = Integer.parseInt(np2.getText().toString().trim());
                    EditText np3 = findViewById(R.id.secondsperrep);
                    number3 = Integer.parseInt(np3.getText().toString().trim());

                    Intent intent = new Intent(MainActivity.this, Counter.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("reps", number1);
                    bundle.putInt("sets", number2);
                    bundle.putInt("seconds", number3);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }catch (NumberFormatException nfe){
                    Log.d(MAIN_ACTIVITY,"Number Format Exception called");
                }
            }
        });

    }

}
