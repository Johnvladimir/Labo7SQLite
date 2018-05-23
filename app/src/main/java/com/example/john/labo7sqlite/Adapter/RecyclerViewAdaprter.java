package com.example.john.labo7sqlite.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.john.labo7sqlite.Object.Personas;
import com.example.john.labo7sqlite.R;

import java.util.ArrayList;

public class RecyclerViewAdaprter extends RecyclerView.Adapter<RecyclerViewAdaprter.ViewHolderAdapter> {

    ArrayList<Personas> listaPersonas;

    public RecyclerViewAdaprter(ArrayList<Personas> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }

    @NonNull
    @Override
    public ViewHolderAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_personas,null,false);
        return new ViewHolderAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderAdapter holder, int position) {
        final Personas personas = listaPersonas.get(position);

        holder.carnet.setText(personas.getCarnet());
        holder.nombre.setText(personas.getNombre());
        holder.nota.setText(personas.getNota());
    }

    @Override
    public int getItemCount() {
        return listaPersonas.size();
    }

    public class ViewHolderAdapter extends RecyclerView.ViewHolder {

        TextView carnet,nombre,nota;

        public ViewHolderAdapter(View itemView) {
            super(itemView);
            carnet = itemView.findViewById(R.id.carnetRecyclerId);
            nombre = itemView.findViewById(R.id.nombreRecyclerId);
            nota = itemView.findViewById(R.id.notaRecyclerId);
        }
    }
}
