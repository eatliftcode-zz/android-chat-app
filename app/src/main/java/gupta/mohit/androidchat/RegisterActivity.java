package gupta.mohit.androidchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText mDisplayName;
    private TextInputEditText mEmail;
    private TextInputEditText mPassword;
    private Button mCreateBtn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        mDisplayName = (TextInputEditText)findViewById(R.id.reg_display_name);
        mEmail = (TextInputEditText)findViewById(R.id.reg_email);
        mPassword = (TextInputEditText)findViewById(R.id.reg_password);
        mCreateBtn = (Button)findViewById(R.id.reg_create_button);

        mCreateBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                String display_name = mDisplayName.getEditableText().toString();
                String email = mEmail.getEditableText().toString();
                String password = mPassword.getEditableText().toString();

                register_user(display_name, email, password);

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
                    Toast.makeText(RegisterActivity.this, "You got some error", Toast.LENGTH_LONG).show();
               }
           }
       });
    }
}
