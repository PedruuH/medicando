package com.medicando.activities.introducao;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.medicando.R;
import com.medicando.activities.primarias.MainActivity;
import com.medicando.helper.SolicitarPermissao;
import com.medicando.helper.UsuarioPreferences;
import com.medicando.helper.VerificaStatusInternet;
import com.medicando.model.Usuario;
import com.medicando.util.UtilMedicando;
import com.medicando.util.UtilUsuario;
import com.rengwuxian.materialedittext.MaterialEditText;

public class CadastroActivity extends AppCompatActivity {

    /*Solicitando Permissões*/
    private String[] permission = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };

    /*Declarando as variáveis*/
    private Usuario usuario;
    private MaterialEditText editNome, editEmail, editSenha;
    private Button btnCadastrar;

    //atributo da classe.
    private AlertDialog alerta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        getSupportActionBar().hide();

        /*Recupero os itens do Layout da Activity*/
        editEmail = findViewById(R.id.editEmailCadastro);
        editSenha = findViewById(R.id.editSenhaCadastro);
        editNome = findViewById(R.id.editNomeCadastro);
        btnCadastrar = findViewById(R.id.btnCadastrar);

        /*Solicitando as permissões necessarias*/
        SolicitarPermissao.validarPermissoes(permission, this, 1);

        /*Setando o OnClick*/
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = editNome.getText().toString();
                String email = editEmail.getText().toString();
                String senha = editSenha.getText().toString();
                if (!nome.isEmpty()){
                    if(!email.isEmpty()){
                        if(!senha.isEmpty()){
                            if(VerificaStatusInternet.isOnline(CadastroActivity.this)){
                                usuario = new Usuario(nome,email, senha);
                                cadastrarUsuarios();
                            }
                        } else {
                            UtilMedicando.showToastShort(CadastroActivity.this, "Digite a Senha!");
                        }
                    } else {
                        UtilMedicando.showToastShort(CadastroActivity.this, "Digite o Email!");
                    }
                } else {
                    UtilMedicando.showToastShort(CadastroActivity.this, "Digite o Nome");
                }

            }
        });
    }

    private void cadastrarUsuarios() {
//        UtilUsuario.InsertUsuarioAsyncTask task =
//                        new UtilUsuario.InsertUsuarioAsyncTask(CadastroActivity.this, usuario);
//        task.execute();

        Context context = CadastroActivity.this;

        /*Progress Bar passada para poder inicializar*/
        ProgressDialog progressDialog;

        progressDialog = new ProgressDialog(context);

        progressDialog.setMessage("Cadastrando, por favor espere...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        if(progressDialog != null && progressDialog.isShowing()){
            progressDialog.dismiss();
            UsuarioPreferences up = new UsuarioPreferences(context);
            up.salvarIdUsuarioPreferences(1);
            Intent intent = new Intent(context, MainActivity.class);
            UtilMedicando.showToastSucess(context, "Cadastro efetuado com sucesso!");
            context.startActivity(intent);
        }
    }

    public void mostrarTermosUso(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater li = getLayoutInflater();
        @SuppressLint("InflateParams") View v = li.inflate(R.layout.layout_termos_uso_dialog, null);

        v.findViewById(R.id.buttonConfirmarTermosDeUso).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alerta.dismiss();
            }
        });

        builder.setView(v);
        alerta = builder.create();
        alerta.show();
    }
}
