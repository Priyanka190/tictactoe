package com.exampl.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Feedback extends AppCompatActivity {
    private RatingBar rate;
    private EditText feed;
    private MaterialButton btn;
    private TextView text;
    private String ratePoint ="";
    DatabaseReference databaseReference;
    FirebaseAuth mAuth;
    private String info1;
    private DatabaseReference userIdRef;
    private static final String TAG = "feedback";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        rate=findViewById(R.id.ratingBar);
        Intent i=getIntent();
        info1=i.getStringExtra("info");
        Log.d(TAG,"line 40 "+info1);
        databaseReference= FirebaseDatabase.getInstance().getReference().child(info1);
        mAuth=FirebaseAuth.getInstance();
        feed=findViewById(R.id.f);
        btn=findViewById(R.id.sendfeedback);
        text=findViewById(R.id.textviewrate);
        rate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if(rating==0)
                {
                    text.setText("Very Dissatisfied");
                    ratePoint =Float.toString(rating);
                }
                else if(rating==1)
                {
                    text.setText("OK");
                    ratePoint =Float.toString(rating);
                }
                else if(rating==2 || rating==3)
                {
                    text.setText("Very Good");
                    ratePoint =Float.toString(rating);
                }
                else if(rating==4)
                {
                    text.setText("Satisfied");
                    ratePoint =Float.toString(rating);
                }
                else if(rating==5)
                {
                    text.setText("Very Satisfied");
                    ratePoint =Float.toString(rating);
                }
                else
                {

                }
            }
        });
        // Write a message to the database
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String comments= feed.getText().toString();
                userIdRef=databaseReference.child(mAuth.getCurrentUser().getUid());
                userIdRef.child("comments:").setValue(comments);

            }
        });
    }

}