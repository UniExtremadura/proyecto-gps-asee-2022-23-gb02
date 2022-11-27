package es.unex.propuesta_proyecto.activities;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.unex.propuesta_proyecto.R;

/* Esta clase sirve para convertir las clases al recyclerView */

public class ClasesAdapter extends RecyclerView.Adapter<ClasesAdapter.ViewHolder> {

    private ArrayList<String> alClases;
    Context context;

    public ClasesAdapter(ArrayList<String> alClases) {
        this.alClases = alClases;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view_clases, null, false);
        return new ViewHolder(view);
    }

    /* Establece el texto */

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getbClase().setText(alClases.get(position));
    }

    /* Devuelve el numero de clases que hay en el ArrayList */

    @Override
    public int getItemCount() {
        return alClases.size();
    }

    /* Navega a la clase actual */

    public class ViewHolder extends RecyclerView.ViewHolder {

        Button bClase;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            bClase = itemView.findViewById(R.id.bClase);
            bClase.setOnClickListener(v -> {
                Intent actClase = new Intent(context, DetalleClaseActivity.class);;
                actClase.putExtra("className", bClase.getText());
                context.startActivity(actClase);
            });
        }

        /* Recupera el boton de la clase */

        public Button getbClase() {
            return bClase;
        }

    }



}
