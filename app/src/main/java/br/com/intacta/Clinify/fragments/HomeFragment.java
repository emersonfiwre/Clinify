package br.com.intacta.Clinify.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import br.com.intacta.Clinify.R;

public class HomeFragment extends Fragment {
    private AppBarLayout appbar;
    private Toolbar toolbar;
    private TabLayout tabs;
    private RecyclerView compromisserecycler;
    private TextView addcompromisse;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        return view;
    }
    private void initView(View v) {
        compromisserecycler = v.findViewById(R.id.compromisserecycler);
        //Carregar();
        addcompromisse = (TextView) v.findViewById(R.id.addcompromisse);
        addcompromisse.setVisibility(View.GONE);
        addcompromisse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Alerts alerts = new Alerts(getActivity());
                //alerts.CompromissoAlert();
            }
        });

    }

    private void addTab(String title) {
        tabs.addTab(tabs.newTab().setText(title));

    }


}
