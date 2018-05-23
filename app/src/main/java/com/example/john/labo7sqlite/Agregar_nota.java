package com.example.john.labo7sqlite;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.john.labo7sqlite.Entidades.DBHelper;
import com.example.john.labo7sqlite.Object.Personas;

public class Agregar_nota extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText Carnet, Nombre, Nota;
    private Button Buscar, Modificar, Eliminar, Limpiar;

    private OnFragmentInteractionListener mListener;

    public Agregar_nota() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Agregar_nota newInstance(String param1, String param2) {
        Agregar_nota fragment = new Agregar_nota();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_agregar_nota, container, false);
        Carnet = view.findViewById(R.id.carnetId);
        Nombre = view.findViewById(R.id.nombreId);
        Nota = view.findViewById(R.id.notaId);
        Buscar = view.findViewById(R.id.Buscar);
        Modificar = view.findViewById(R.id.Modificar);
        Eliminar = view.findViewById(R.id.Eliminar);
        Limpiar = view.findViewById(R.id.Limpiar);

        Buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Personas p = DBHelper.myDB.findUser(Carnet.getText().toString());
                if (p == null) {
                    Log.d("findUser", "No se encontro usuario");
                    limpiar();
                }else{
                    Nombre.setText(p.getNombre());
                    Nota.setText(p.getNota());
                }
            }

            private void limpiar() {
                Carnet.setText("");
                Nombre.setText("");
                Nota.setText("");
            }
        });

        Modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper.myDB.editUser(new Personas(
                        Carnet.getText().toString(),
                        Nombre.getText().toString(),
                        Nota.getText().toString()));
            }
        });

        Eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper.myDB.deleteUser(Carnet.getText().toString());
            }
        });

        Limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Carnet.setText("");
                Nombre.setText("");
                Nota.setText("");
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
