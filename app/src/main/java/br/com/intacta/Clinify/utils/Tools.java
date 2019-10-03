package br.com.intacta.Clinify.utils;

import android.annotation.SuppressLint;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tools {
    public static final int RC_SIGN_IN = 123;

    public static final String userPath = "Users";
    public static final String agenda = "Agenda";
    public static final String compromises = "Compromissos";
    public static final String patients = "Pacientes";
    public static final String notifications = "Notifications";

    public static String formattomyday(Date date) {
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(date);

    }

    public static FirebaseUser user() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        return user;
    }
}
