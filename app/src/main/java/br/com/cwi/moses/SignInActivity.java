package br.com.cwi.moses;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class SignInActivity extends BaseActivity {

    private EditText txtName;
    private EditText txtEmail;
    private EditText txtPassword;
    private EditText txtConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        initializeComponents();
    }

    public void initializeComponents(){
        txtName = (EditText)findViewById(R.id.txtName);
        txtEmail = (EditText)findViewById(R.id.txtEmail);
        txtPassword = (EditText)findViewById(R.id.txtPassword);
        txtConfirm = (EditText)findViewById(R.id.txtConfirm);
    }

    public void singIn(String email, String senha){
        showLoader("Aguarde...");
        if(!email.isEmpty() && !senha.isEmpty()){
            mAuth.createUserWithEmailAndPassword(email, senha)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            hideLoader();
                            if (!task.isSuccessful()) {
                                if(task.getException().getMessage().indexOf("network error") != -1){
                                    showError("Verifique a internet");
                                }else{
                                    showError("Falha na authenticaçao");
                                }
                            } else {
                                Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }
                    });
        }
    }

    public void openLogin(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void signIn(View view){
        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();
        if(!email.isEmpty() && !password.isEmpty()){
            singIn(email, password);
        }
    }
}