package com.medicando.activities.introducao;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ProgressBar;

import com.medicando.R;
import com.medicando.activities.primarias.MainActivity;
import com.medicando.helper.SolicitarPermissao;
import com.medicando.helper.UsuarioPreferences;
import com.medicando.model.Usuario;
import com.medicando.util.UtilMedicando;
import com.medicando.util.UtilUsuario;
import com.rengwuxian.materialedittext.MaterialEditText;

import static android.view.View.VISIBLE;

public class LoginActivity extends AppCompatActivity {
    /*Solicitando Permissões*/
    private String[] permission = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };

    private Usuario usuario;
    private MaterialEditText editEmail, editSenha;
    private Button btnLogin;
    public ProgressBar pbLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        /*Recupero os itens do Layout da Activity*/
        editEmail = findViewById(R.id.editEmailLogin);
        editSenha = findViewById(R.id.editSenhaLogin);
        btnLogin = findViewById(R.id.btnLogin);
        pbLogin = findViewById(R.id.progressBarLogin);

        /*Setando o progressBar como invisible*/
        pbLogin.setVisibility(View.INVISIBLE);

        /*Solicitando as permissões necessarias*/
        SolicitarPermissao.validarPermissoes(permission, this, 1);

        /*Adicionando o ClickListener*/
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editEmail.getText().toString();
                String senha = editSenha.getText().toString();
                if(!email.isEmpty()){
                    if(!senha.isEmpty()){
                        /*Fecha o KeyBoard ao cliclar em salvar*/
                        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        if (imm != null) {
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }
                        pbLogin.setVisibility(View.VISIBLE);
                        usuario = new Usuario(email,senha);
                        validarLogin();
                    } else {
                        UtilMedicando.showToastInfo(LoginActivity.this, "Digite a Senha!");
                    }
                } else {
                    UtilMedicando.showToastInfo(LoginActivity.this, "Digite o Email!");
                }
            }
        });
    }

    private void validarLogin() {

        Context context = LoginActivity.this;


        pbLogin.setVisibility(VISIBLE);

        if(usuario.getEmailUser().equals("teste@email.com") &&
                usuario.getSenhaUser().equals("senha123")) {
            UsuarioPreferences idUsuarioPreferences = new UsuarioPreferences(LoginActivity.this);
            idUsuarioPreferences.salvarIdUsuarioPreferences(1);
            idUsuarioPreferences.salvarBuscasPreferences(0);
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        } else {
            UtilMedicando.showToastError(context,
                    "Nenhum registro encontrado no momento!");
        }

        if(pbLogin != null && pbLogin.getVisibility() == VISIBLE){
            pbLogin.setVisibility(View.INVISIBLE);
        }

//        UtilUsuario.ValidarLoginAsyncTask asyncTask =
//                new UtilUsuario.ValidarLoginAsyncTask(LoginActivity.this, usuario, pbLogin);
//        asyncTask.execute();
    }
}
