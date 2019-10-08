package br.com.intacta.Clinify.database;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.CountDownTimer;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import br.com.intacta.Clinify.beans.Cliente;
import br.com.intacta.Clinify.utils.Tools;

public class ClienteDatabase {
    private Context context;

    public ClienteDatabase(Context context) {
        this.context = context;
    }
    public void saveClient(Cliente cliente, final ProgressDialog progressDialog){
        progressDialog.show();
        DatabaseReference clientdb = FirebaseDatabase.getInstance().getReference(Tools.patientsPath).child(Tools.userPath).child(Tools.user().getUid());
        clientdb.setValue(cliente).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    progressDialog.setMessage("Paciente adicionado com sucesso!");
                }else{
                    progressDialog.setMessage("Erro ao adicionar paciente " + task.getException().getMessage());
                }

                CountDownTimer timer = new CountDownTimer(1000,100) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        progressDialog.dismiss();
                    }
                }.start();
            }
        });

    }
}
