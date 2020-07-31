package edu.csumb.exercisecounter;

import android.os.CountDownTimer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Counter extends AppCompatActivity {

    private static final String COUNTER_ACTIVITY = "Counter Activity";

    private ProgressBar timer;
    Button add_button;

    int inputSets;
    int inputReps;
    int[] modify = new int[2];

    TextView repsL;
    TextView setsL;

    MyCountDownTimer myCountDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

        Bundle intent= getIntent().getExtras();
        inputSets = intent.getInt("sets");
        inputReps = intent.getInt("reps");
        final int secondsinmilliseconds = intent.getInt("seconds") * 1000;
        modify[0] = inputSets;
        modify[1] = inputReps;

        //once we add timer option, set progress length here
        setsL = findViewById(R.id.setsleft);
        setsL.setText(getString(R.string.sets) + inputSets);
        repsL = findViewById(R.id.repsleft);
        repsL.setText(getString(R.string.reps) + inputReps);

        timer = findViewById(R.id.timer);
        timer.setMax((int) secondsinmilliseconds/10);



        add_button = findViewById(R.id.addOne);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(COUNTER_ACTIVITY, "onClick add one called");
                add_button.setEnabled(false);

                myCountDownTimer = new MyCountDownTimer(secondsinmilliseconds,10);
                myCountDownTimer.start();
            }
        });

    }

    public class MyCountDownTimer extends CountDownTimer{

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            int progress = (int) (millisUntilFinished/10);
            timer.setProgress(timer.getMax()-progress);
        }

        @Override
        public void onFinish() {
            modify[1] -= 1;
            if(modify[1] <= 0)
            {
                modify[0] -=1;
                if (modify[0] <=0)
                {
                    finish();//Make Alert button to congratulate on finishing the workout
                }
                modify[1] = inputReps;//resets reps counter
            }
            setsL.setText(getString(R.string.sets) + modify[0]);
            repsL.setText(getString(R.string.reps) + modify[1]);
            add_button.setEnabled(true);
        }
    }
}
