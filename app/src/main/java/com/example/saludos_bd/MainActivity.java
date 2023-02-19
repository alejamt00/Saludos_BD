package com.example.saludos_bd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ft = getSupportFragmentManager().beginTransaction();
        FragmentoMain fragMain = FragmentoMain.newInstance();
        ft.replace(android.R.id.content, fragMain).addToBackStack(null) .commit();

    }
}