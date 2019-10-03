package br.com.intacta.Clinify;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.firebase.ui.auth.AuthUI;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import br.com.intacta.Clinify.beans.Cliente;
import br.com.intacta.Clinify.database.ClienteDatabase;
import br.com.intacta.Clinify.utils.Tools;

public class MainActivity extends AppCompatActivity {
    private Button btnCadastrar;
    private TextInputLayout edtNome, edtCpf, edtEmail, edtTelefone, edtDataNasc;
    private Activity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCadastrar = findViewById(R.id.btn_cadastrar);
        edtNome = findViewById(R.id.clientname);
        edtCpf = findViewById(R.id.cpffield);
        edtEmail = findViewById(R.id.emailfield);
        edtTelefone = findViewById(R.id.phonefield);
        edtDataNasc = findViewById(R.id.datefield);
        context = this;
        login();


        edtDataNasc.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    final DatePickerDialog pickerDialog = new DatePickerDialog(context);

                    pickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            Calendar myCalendar = Calendar.getInstance();
                            Date dia;
                            myCalendar.set(year, month, dayOfMonth);
                            dia = myCalendar.getTime();
                            edtDataNasc.getEditText().setText(Tools.formattomyday(dia));
                            pickerDialog.dismiss();
                        }
                    });
                    pickerDialog.show();
                }
            }
        });


    }
    public void login() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            buildlogin();

        }


    }
    protected void buildlogin() {
        List<AuthUI.IdpConfig> providers = Collections.singletonList(
                new AuthUI.IdpConfig.GoogleBuilder().build());
        context.startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder()
                .setLogo(R.mipmap.ic_launcher)
                .setAvailableProviders(providers)
                .setTheme(R.style.AppTheme)
                .build(), Tools.RC_SIGN_IN);
    }

    public void sendCliente(View view) {
        btnCadastrar.setEnabled(false);

        if (Tools.user() != null) {
            Cliente cliente = new Cliente();
            cliente.setNome(edtNome.getEditText().getText().toString());
            cliente.setCpf(edtCpf.getEditText().getText().toString());
            cliente.setTelefone(edtTelefone.getEditText().getText().toString());
            cliente.setEmail(edtEmail.getEditText().getText().toString());
            cliente.setDataNascimento(edtDataNasc.getEditText().getText().toString());

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Adicionando cliente");
            ClienteDatabase clientsdb = new ClienteDatabase(this);
            clientsdb.saveClient(cliente,progressDialog);

            btnCadastrar.setEnabled(true);
            openDashBoard();

        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this).setTitle("Desconectado!");
            builder.setMessage("Não é possível fazer isso se estiver desconectado");
            builder.setPositiveButton("Realizar login", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    List<AuthUI.IdpConfig> providers = Collections.singletonList(
                            new AuthUI.IdpConfig.GoogleBuilder().build());
                    context.startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder()
                            .setLogo(R.mipmap.ic_launcher)
                            .setAvailableProviders(providers)
                            .setTheme(R.style.AppTheme)
                            .build(), Tools.RC_SIGN_IN);
                }
            });
            builder.setNegativeButton("Cancelar", null);
            builder.show();
        }
    }
    private void openDashBoard(){
        Intent intent = new Intent(this, DashBoardActivity.class);
        this.startActivity(intent);

    }
}

