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
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

/**
 * Fragmento main
 * Si existe un usuario con el mismo nombre, muestra error; Si no, se va a
 * otro fragmento y después de insertar dicho nombre en la base de datos, procede a
 * actualizarlo concatenandole "_DAM"
 */
public class FragmentoMain extends Fragment {

    BaseDatosHelper dbHelper;
    SQLiteDatabase dbW;
    SQLiteDatabase dbR;

    /**
     * Constructor, sin uso
     */
    public FragmentoMain() {
        // Required empty public constructor
    }

    /**
     * Constructor, sin uso
     */
    public static FragmentoMain newInstance() {
        FragmentoMain fragment = new FragmentoMain();

        return fragment;
    }

    /**
     * Método onCreate
     *
     * @param savedInstanceState If the fragment is being re-created from
     * a previous saved state, this is the state.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * Crea boton enviar y cuadros de texto de nombre y pass necesarios para acceder al fragmentoUserInfo,
     * al que se accede cuando se da al botón con la info de nombre y pass dados solo si no existe
     * dicho nombre en la BD.
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
                        new String[]{tvName.getText().toString()},
                        null,
                        null,
                        EstructuraBBDD.sortOrder
                );

                Boolean enc = false;

                if(!cursor.isAfterLast()){
                    enc = true;
                    cursor.moveToNext();
                }

                if(!enc){
                    values.put(EstructuraBBDD.COLUMN_NAME, tvName.getText().toString());
                    values.put(EstructuraBBDD.COLUMN_PASS, tvPass.getText().toString());
                    long newRowId = dbW.insert(EstructuraBBDD.TABLE_DATOS_PERSONALES,null,values);
                    FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                    FragmentoUserInfo fe = FragmentoUserInfo.newInstance(tvName.getText().toString(), tvPass.getText().toString());
                    ft.replace(android.R.id.content, fe).addToBackStack(null).commit();
                } else {
                    Toast toast=Toast.makeText(getContext(),"¡Ese usuario ya existe!",Toast.LENGTH_SHORT);
                    toast.setMargin(50,50);
                    toast.show();
                }


            }
        });

        return view;
    }
}