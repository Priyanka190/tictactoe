package com.exampl.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import static android.content.ContentValues.TAG;

public class login extends AppCompatActivity implements View.OnClickListener {
    private TextView loginbtn;
    private FirebaseAuth mAuth;
    private LinearLayout signInlayout;
    private TextInputEditText inuseremail;
    private TextInputEditText inuserPassword;
    private TextView inforgetbtn;
    private ImageButton ingooglebtn;
    private ImageButton inphone;
    private ImageButton intweeter;
    private LinearLayout signuplayout;
    private TextInputEditText uvsername;
    private TextInputEditText useremail;
    private TextInputEditText userpaswword;
    private MaterialButton signupbtn;
    private TextView ugooglebtn;
    private TextView upsignin;
    private TextView insignup;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 9001;
    private EditText etPhone;
    private Button btnSend;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private TextView tvMobile;
    private TextView tvResendBtn;
    private EditText enterOtpText;
    private String backendOtp;
    private EditText etC4;
    private String phoneNo;
    private EditText etC5;
    private ConstraintLayout number;
    private ConstraintLayout otp;
    private EditText etC6;
    private Button btnVerify;
    private TextView resendotp;
    private TextView changeno;
    private TextInputEditText fuseremail;
    private TextView fftn;
    private Button signInf;
    private LinearLayout forgetlayout;
    private ImageButton googlebtn1;
    private ImageButton phone1;
    private ImageButton tweeter1;
    private TextView signInInUp;
    String phoneNumber;
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null) {
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            updateUI(currentUser);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signInlayout=findViewById(R.id.signInlayout);
        inuseremail=findViewById(R.id.inuseremail);
        inuserPassword=findViewById(R.id.inuserpassword);
        inforgetbtn=findViewById(R.id.forgatebutton);
        ingooglebtn=findViewById(R.id.googleloginbutton);
        inphone=findViewById(R.id.phone);
        intweeter=findViewById(R.id.tweet);
        loginbtn=findViewById(R.id.login);
        signuplayout=findViewById(R.id.signuplayout);
        uvsername=findViewById(R.id.uvsername);
        useremail=findViewById(R.id.useremail);
        userpaswword=findViewById(R.id.userpassword);
        signupbtn=findViewById(R.id.signupbutton);
        upsignin=findViewById(R.id.signInbuttonsignup);
        insignup=findViewById(R.id.signuplogin);
        etPhone = findViewById(R.id.etPhone);
        btnSend = findViewById(R.id.btnSend);
        tvMobile = findViewById(R.id.tvMobile);
        enterOtpText = findViewById(R.id.editTextTextPersonName);
        number = findViewById(R.id.number);
        otp = findViewById(R.id.otp);
        changeno=findViewById(R.id.change);
        resendotp=findViewById(R.id.tvResendBtn);
        btnVerify = findViewById(R.id.btnVerify);
        tvResendBtn = findViewById(R.id.tvResendBtn);
        fuseremail=findViewById(R.id.fuseremail);
        fftn=findViewById(R.id.ffbtn);
        forgetlayout=findViewById(R.id.flayout);
        tweeter1=findViewById(R.id.tweet1);
        phone1=findViewById(R.id.phone1);
        signInInUp=findViewById(R.id.signInbuttonsignup);
        googlebtn1=findViewById(R.id.googleloginbutton1);

        mAuth = FirebaseAuth.getInstance();
        insignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInlayout.setVisibility(View.GONE);
                signuplayout.setVisibility(View.VISIBLE);
            }
        });
        signupbtn.setOnClickListener(this);
        loginbtn.setOnClickListener(this::onClick);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        ingooglebtn.setOnClickListener(this::onClick);
        inphone.setOnClickListener(this::onClick);
        btnSend.setOnClickListener(this::onClick);
        btnVerify.setOnClickListener(this::onClick);
        changeno.setOnClickListener(this::onClick);
        resendotp.setOnClickListener(this::onClick);
        inforgetbtn.setOnClickListener(this::onClick);
        googlebtn1.setOnClickListener(this::onClick);
        phone1.setOnClickListener(this::onClick);
        tweeter1.setOnClickListener(this::onClick);
        signInInUp.setOnClickListener(this::onClick);
        fftn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = fuseremail.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplication(), "Enter your registered email id", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.sendPasswordResetEmail(email)

                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(login.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                                    forgetlayout.setVisibility(View.GONE);
                                    signInlayout.setVisibility(View.VISIBLE);
                                } else {
                                    Toast.makeText(login.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Toast.makeText(this, "Item 1 selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signupbutton:
                signupuser();
                break;
            case R.id.login:
                loginuser();
                break;
            case R.id.googleloginbutton:
                signIn();
                break;
            case R.id.googleloginbutton1:
                signIn();
                break;
            case R.id.btnSend:
                sendverification1();
                number.setVisibility(View.GONE);
                otp.setVisibility(View.VISIBLE);
                break;
            case R.id.phone:
                signInlayout.setVisibility(View.GONE);
                number.setVisibility(View.VISIBLE);
                break;
            case R.id.phone1:
                signuplayout.setVisibility(View.GONE);
                number.setVisibility(View.VISIBLE);
                break;
            case R.id.btnVerify:
                String enteredOtp=enterOtpText.getText().toString().trim();
                verifyVerificationCode(enteredOtp);
                break;
            case R.id.change:
                number.setVisibility(View.VISIBLE);
                otp.setVisibility(View.GONE);
                break;
            case R.id.tvResendBtn:
                String phoneNumber = etPhone.getText().toString().trim();
                resendVerificationCode(phoneNumber,mResendToken);
                break;
            case R.id.forgatebutton:
                signInlayout.setVisibility(View.GONE);
                forgetlayout.setVisibility(View.VISIBLE);
                break;
            case R.id.signInbuttonsignup:
                signuplayout.setVisibility(View.GONE);
                signInlayout.setVisibility(View.VISIBLE);
        }
    }

    private void resendVerificationCode(String phoneNumber,
                                        PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("91-%s"+phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .setForceResendingToken(token)     // ForceResendingToken from callbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void sendverification1() {
        phoneNumber = etPhone.getText().toString().trim();
        if (!phoneNumber.isEmpty()) {
            if (phoneNumber.length() == 10) {
                sendVerificationCode(phoneNumber);
                tvMobile.setText(String.format("91-%s",phoneNumber));
                number.setVisibility(View.GONE);
                otp.setVisibility(View.VISIBLE);
            } else {
                Toast.makeText(login.this, "Please Enter Correct Number", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(login.this, "Please Enter Valid Phone number", Toast.LENGTH_LONG).show();
        }
    }
    private void sendVerificationCode(String mobile) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+91"+mobile)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            //Getting the code sent by SMS
            String code = phoneAuthCredential.getSmsCode();

            //sometime the code is not detected automatically
            //in this case the code will be null
            //so user has to manually enter the code
            if (code != null) {
                enterOtpText.setText(code);
                //verifying the code
                verifyVerificationCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(login.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            mVerificationId = s;
            mResendToken = forceResendingToken;
        }
    };

    private void verifyVerificationCode(String otp) {
        Log.i("code",otp);
        //creating the credential
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, otp);

        //signing the user
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //verification successful we will start the profile activity
                            Intent intent = new Intent(login.this, MainActivity.class);
                            startActivity(intent);

                        } else {

                            //verification unsuccessful.. display an error message

                            String message = "Somthing is wrong, we will fix it soon...";

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                message = "Invalid code entered...";
                            }

                            Toast.makeText(login.this,message,Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


    private void signIn() {
        mGoogleSignInClient.signOut();
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                Log.w(TAG, "Google sign in failed", e);
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            Intent i=new Intent(login.this,MainActivity.class);
//                            i.putExtra("username",mAuth.getCurrentUser().getDisplayName());
                            startActivity(i);
                        } else {
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            updateUI(null);
                        }
                    }
                });
    }

    private void loginuser() {
        String email=inuseremail.getText().toString().trim();
        Log.i("line 86",email);
        String password=inuserPassword.getText().toString().trim();
        Log.i("line 88",password);
       mAuth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "signInWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                    Intent i=new Intent(login.this,MainActivity.class);
                    startActivity(i);
                } else {
                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                    Toast.makeText(login.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }
            }
        });
    }

    private void signupuser() {
            String email1=useremail.getText().toString().trim();
            String name1=uvsername.getText().toString().trim();
            String password1=userpaswword.getText().toString().trim();
            if(name1.isEmpty()) {
                uvsername.setError("full name is required");
                uvsername.requestFocus();
                return;
            }
            if(password1.isEmpty()) {
                userpaswword.setError("please enter password");
                userpaswword.requestFocus();
                return;
            }
            if(email1.isEmpty()) {
                useremail.setError("please enter password");
                useremail.requestFocus();
                return;
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(useremail.getText().toString()).matches()){
                useremail.setError("Please enter a Valid E-Mail Address!");
                useremail.requestFocus();
                return;
            }
            if(password1.length()<6) {
                userpaswword.setError("the password length should be 6 character");
                userpaswword.requestFocus();
                return;
            }
        mAuth.createUserWithEmailAndPassword(email1, password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_LONG).show();
                    Intent intent= new Intent(login.this, MainActivity.class);
                    startActivity(intent);
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Registration failed!!" + " Please try again later", Toast.LENGTH_LONG).show();
                    updateUI(null);
                }
            }
        });
    }
    private void updateUI(FirebaseUser user) {

    }
}