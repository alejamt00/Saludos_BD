package com.example.saludos_bd;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentoUserInfo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentoUserInfo extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentoUserInfo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Nombre.
     * @param param2 Edad
     * @return A new instance of fragment FragmentoEdad.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentoUserInfo newInstance(String param1, String param2) {
        FragmentoUserInfo fragment = new FragmentoUserInfo();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    /**
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     *
     * Una vez creada la vista de este gragmento, inicializa boton, imagen y texto según la edad y nombre
     * dados por los mParam1 y mParam2 retornados de fragmento main
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fragmento_user_info, container, false);

        TextView textSaludo = view.findViewById(R.id.textViewMsgSaludo);
        Button atrasBoton = view.findViewById(R.id.botonAtras);

        atrasBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        textSaludo.setText("Usuario  " + mParam1 + " creado con éxito!");

        // Inflate the layout for this fragment
        return view;
    }
}