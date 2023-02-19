package com.example.saludos_bd;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

/**
 * Fragmento encargado de mostrar el nombre del usuario creado y su actualización.
 *
 */
public class FragmentoUserInfo extends Fragment {

    private String mParam1;
    private String mParam2;

    /**
     * Constructor del fragmento, obtiene el usuario y pass pasados por argumentos en su creacion
     * @param user
     * @param pass
     */
    public FragmentoUserInfo(String user, String pass) {
        this.mParam1 = user;
        this.mParam2 = pass;
    }

    /**
     * Constructor del fragmento, obtiene el usuario y pass pasados por argumentos en su creacion
     * @param param1
     * @param param2
     */
    public static FragmentoUserInfo newInstance(String param1, String param2) {
        FragmentoUserInfo fragment = new FragmentoUserInfo(param1,param2);

        return fragment;
    }

    /**
     * Método onCreate del fragmento
     * @param savedInstanceState If the fragment is being re-created from
     * a previous saved state, this is the state.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * Método onCreateView, encargado de t0do el funcionamiento del fragmento
     *
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fragmento_user_info, container, false);

        TextView textSaludo = view.findViewById(R.id.textViewMsgSaludo);
        Button atrasBoton = view.findViewById(R.id.botonAtras);
        BaseDatosHelper dbHelper = new BaseDatosHelper(getContext());

        String nombre = mParam1 + "_DAM";

        ContentValues values = new ContentValues();
        values.put(EstructuraBBDD.COLUMN_NAME, nombre);

        int count = dbHelper.getWritableDatabase().update(
                EstructuraBBDD.TABLE_DATOS_PERSONALES,
                values,
                EstructuraBBDD.selectionUpdate,
                new String[]{mParam1, mParam2}
                );


        atrasBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        textSaludo.setText("Usuario  " + mParam1 + " creado con éxito y actualizado a: " + nombre);


        // Inflate the layout for this fragment
        return view;
    }
}