package com.example.saludos_bd;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentoMain#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentoMain extends Fragment {

    BaseDatosHelper dbHelper;
    SQLiteDatabase dbW;
    SQLiteDatabase dbR;

    public FragmentoMain() {
        // Required empty public constructor
    }


    public static FragmentoMain newInstance() {
        FragmentoMain fragment = new FragmentoMain();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * Crea boton enviar y cuadros de texto de nombre y edad necesarios para acceder al fragmentoedad,
     * al que se accede cuando se da al botón con la info de nombre y edad dados
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        // obtenemos el boton
        Button btEnviar = view.findViewById(R.id.buttonSend);
        TextView tvName = view.findViewById(R.id.editTextName);
        TextView tvPass = view.findViewById(R.id.editTextPass);

        dbHelper = new BaseDatosHelper(getContext());
        dbW = dbHelper.getWritableDatabase();
        dbR = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();




        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor cursor = dbR.query(
                        EstructuraBBDD.TABLE_DATOS_PERSONALES,
                        EstructuraBBDD.projection,
                        EstructuraBBDD.selection,
                        EstructuraBBDD.selectionArgs,
                        null,
                        null,
                        null
                );

                Boolean enc = false;

                if(cursor.moveToFirst()){
                    enc = true;
                }

                if(!enc){
                    values.put(EstructuraBBDD.COLUMN_NAME, tvName.getText().toString());
                    values.put(EstructuraBBDD.COLUMN_PASS, tvPass.getText().toString());
                    long newRowId = dbW.insert(EstructuraBBDD.TABLE_DATOS_PERSONALES,null,values);
                    FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                    FragmentoUserInfo fe = FragmentoUserInfo.newInstance(tvName.getText().toString(), tvPass.getText().toString());
                    ft.replace(android.R.id.content, fe).addToBackStack(null).commit();
                } else {
                    Snackbar sb = Snackbar.make(getView().findViewById(R.id.layoutMain),"¡Ese usuario ya existe!", Snackbar.LENGTH_SHORT);
                    sb.show();
                }


            }
        });

        return view;
    }
}