package es.unex.propuesta_proyecto.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import es.unex.propuesta_proyecto.R;
import es.unex.propuesta_proyecto.api.AppExecutors;
import es.unex.propuesta_proyecto.dao.AppDatabaseArmas;
import es.unex.propuesta_proyecto.dao.AppDatabaseClases;
import es.unex.propuesta_proyecto.dao.AppDatabaseUsuarios;
import es.unex.propuesta_proyecto.model.Armas;
import es.unex.propuesta_proyecto.model.Clases;
import es.unex.propuesta_proyecto.model.Repo;
import es.unex.propuesta_proyecto.model.Usuarios;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Repo> mDataset;
    private static String usuarioGlobal;
    private static String claseGlobal;

    public interface OnListInteractionListener{
        public void onListInteraction(String url);
    }

    public OnListInteractionListener mListener;


    public void usuarioActivo(String usuario){
        usuarioGlobal = usuario;
    }

    public void claseActiva(String clase){
        claseGlobal = clase;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView tvNombre;
        ImageView ivArma;
        ProgressBar pbPrecisionArma;
        ProgressBar pbDanoArma;
        ProgressBar pbAlcanceArma;
        ProgressBar pbCadenciaArma;
        ProgressBar pbMovilidadArma;
        ProgressBar pbControlArma;
        Context context;

        public Repo mItem;

        public MyViewHolder(View v) {
            super(v);
            context = itemView.getContext();
            tvNombre = itemView.findViewById(R.id.tvNombreArmaItem);
            ivArma = itemView.findViewById(R.id.ivArma);
            pbPrecisionArma = itemView.findViewById(R.id.pbPrecisionArma);
            pbDanoArma = itemView.findViewById(R.id.pbDañoArma);
            pbAlcanceArma = itemView.findViewById(R.id.pbAlcanceArma);
            pbCadenciaArma = itemView.findViewById(R.id.pbCadenciaArma);
            pbMovilidadArma = itemView.findViewById(R.id.pbMovilidadArma);
            pbControlArma = itemView.findViewById(R.id.pbControlArma);

            tvNombre.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            Intent navegarADetalles = new Intent(context,DetalleClaseActivity.class);
                            navegarADetalles.putExtra("className",claseGlobal);
                            // usuarioGlobal es el usuario que está loggeado en este instante.
                            Armas armasUsuario = AppDatabaseArmas.getInstance(context).daoJuego().obtenerArmaUsuario(tvNombre.getText().toString(),usuarioGlobal);
                             List<Clases> clasesUsuario = AppDatabaseClases.getInstance(context).daoClases().obtenerClasesUsuario(usuarioGlobal);
                             if(clasesUsuario != null){
                                 for(int i = 0; i < clasesUsuario.size(); i++){
                                     int claseActual = clasesUsuario.get(i).getId(); // Recupera la id de la clase actual.
                                     if(clasesUsuario.get(i).getNombre().equals(claseGlobal)){ // compara todas las clases hasta encontrar en la que está
                                         if(armasUsuario != null){ //  tiene ese arma el usuario se actualiza, si no se inserta.
                                             int armaActual = armasUsuario.getId();
                                             AppDatabaseArmas.getInstance(context).daoJuego().actualizarArma(tvNombre.getText().toString(),"","",pbPrecisionArma.getProgress(),pbDanoArma.getProgress(),pbAlcanceArma.getProgress(),pbCadenciaArma.getProgress(),pbMovilidadArma.getProgress(),pbControlArma.getProgress(),armaActual,claseActual);
                                         } else {
                                             Armas insertarArma = new Armas(tvNombre.getText().toString(),"","",pbPrecisionArma.getProgress(),pbDanoArma.getProgress(),pbAlcanceArma.getProgress(),pbCadenciaArma.getProgress(),pbMovilidadArma.getProgress(),pbControlArma.getProgress(),"",usuarioGlobal,claseActual, true);
                                             AppDatabaseArmas.getInstance(context).daoJuego().insertarArmas(insertarArma);
                                         }
                                     }
                                 }
                             }
                             context.startActivity(navegarADetalles);
                        }
                    });
                }
            });

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<Repo> myDataset, OnListInteractionListener listener) {
        mDataset = myDataset;
        mListener = listener;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view_armas, parent, false);

        return new MyAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        holder.tvNombre.setText(mDataset.get(position).getName());
       // holder.ivArma.setImageURI(alArmas.get(position).getImagen());
        holder.pbPrecisionArma.setProgress(Integer.parseInt(mDataset.get(position).getAccuracy())/3);
        holder.pbDanoArma.setProgress(Integer.parseInt(mDataset.get(position).getDamage())/3);
        holder.pbAlcanceArma.setProgress(Integer.parseInt(mDataset.get(position).getRange())/3);
        holder.pbCadenciaArma.setProgress(Integer.parseInt(mDataset.get(position).getFireRate())/3);
        holder.pbMovilidadArma.setProgress(Integer.parseInt(mDataset.get(position).getMobility())/3);
        holder.pbControlArma.setProgress(Integer.parseInt(mDataset.get(position).getControl())/3);

    }

    @Override
    public int getItemCount() { return mDataset.size();}

    public void swap(List<Repo> dataset){
        mDataset.add(dataset.get(0));
        notifyDataSetChanged();
    }
}
