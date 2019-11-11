package gupta.mohit.androidchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText mDisplayName;
    private TextInputEditText mEmail;
    private TextInputEditText mPassword;
    private Button mCreateBtn;

    private Toolbar mToolbar;

    protected AlertDialog.Builder mRegProgress;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mToolbar = (Toolbar)findViewById(R.id.register_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Create Account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRegProgress = new AlertDialog.Builder(this);

        mAuth = FirebaseAuth.getInstance();

        mDisplayName = (TextInputEditText)findViewById(R.id.reg_display_name);
        mEmail = (TextInputEditText)findViewById(R.id.login_email);
        mPassword = (TextInputEditText)findViewById(R.id.login_password);
        mCreateBtn = (Button)findViewById(R.id.login_btn);

        mCreateBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                String display_name = mDisplayName.getEditableText().toString();
                String email = mEmail.getEditableText().toString();
                String password = mPassword.getEditableText().toString();

                if(!TextUtils.isEmpty(display_name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
                    register_user(display_name, email, password);

                }


            }
        });

    }

    private void register_user(String display_name, String email, String password){
       mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
           @Override
           public void onComplete(@NonNull Task<AuthResult> task) {
               if(task.isSuccessful()){
                    Intent mainIntent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(mainIntent);
                    finish();
               }
               else
               {
                    Toast.makeText(RegisterActivity.this, "There was some error loggin in!", Toast.LENGTH_LONG).show();
               }
           }
       });
    }
}
