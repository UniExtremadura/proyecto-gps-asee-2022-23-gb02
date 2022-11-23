package es.unex.propuesta_proyecto.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import es.unex.propuesta_proyecto.R;
import es.unex.propuesta_proyecto.model.Armas;
import es.unex.propuesta_proyecto.model.Repo;

public class ArmasAdapter extends RecyclerView.Adapter<ArmasAdapter.ViewHolder> {

    private ArrayList<Armas> alArmas;
    Context context;

    public ArmasAdapter(ArrayList<Armas> alArmas) {
        this.alArmas = alArmas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view_armas, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getTvNombre().setText(alArmas.get(position).getName());
        //holder.getIvArma().setImageURI(alArmas.get(position).getImagen());
        holder.getPbPrecisionArma().setProgress(alArmas.get(position).getAccuracy());
        holder.getPbDañoArma().setProgress(alArmas.get(position).getDamage());
        holder.getPbAlcanceArma().setProgress(alArmas.get(position).getRange());
        holder.getPbCadenciaArma().setProgress(alArmas.get(position).getFire_rate());
        holder.getPbMovilidadArma().setProgress(alArmas.get(position).getMobility());
        holder.getPbControlArma().setProgress(alArmas.get(position).getControl());
    }

    @Override
    public int getItemCount() {
        return alArmas.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNombre;
        ImageView ivArma;
        ProgressBar pbPrecisionArma;
        ProgressBar pbDañoArma;
        ProgressBar pbAlcanceArma;
        ProgressBar pbCadenciaArma;
        ProgressBar pbMovilidadArma;
        ProgressBar pbControlArma;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            tvNombre = itemView.findViewById(R.id.tvNombre);
            ivArma = itemView.findViewById(R.id.ivArma);
            pbPrecisionArma = itemView.findViewById(R.id.pbPrecisionArma);
            pbDañoArma = itemView.findViewById(R.id.pbDañoArma);
            pbAlcanceArma = itemView.findViewById(R.id.pbAlcanceArma);
            pbCadenciaArma = itemView.findViewById(R.id.pbCadenciaArma);
            pbMovilidadArma = itemView.findViewById(R.id.pbMovilidadArma);
            pbControlArma = itemView.findViewById(R.id.pbControlArma);
        }

        public TextView getTvNombre() {
            return tvNombre;
        }

        public void setTvNombre(TextView tvNombre) {
            this.tvNombre = tvNombre;
        }

        public ImageView getIvArma() {
            return ivArma;
        }

        public void setIvArma(ImageView ivArma) {
            this.ivArma = ivArma;
        }

        public ProgressBar getPbPrecisionArma() {
            return pbPrecisionArma;
        }

        public void setPbPrecisionArma(ProgressBar pbPrecisionArma) {
            this.pbPrecisionArma = pbPrecisionArma;
        }

        public ProgressBar getPbDañoArma() {
            return pbDañoArma;
        }

        public void setPbDañoArma(ProgressBar pbDañoArma) {
            this.pbDañoArma = pbDañoArma;
        }

        public ProgressBar getPbAlcanceArma() {
            return pbAlcanceArma;
        }

        public void setPbAlcanceArma(ProgressBar pbAlcanceArma) {
            this.pbAlcanceArma = pbAlcanceArma;
        }

        public ProgressBar getPbCadenciaArma() {
            return pbCadenciaArma;
        }

        public void setPbCadenciaArma(ProgressBar pbCadenciaArma) {
            this.pbCadenciaArma = pbCadenciaArma;
        }

        public ProgressBar getPbMovilidadArma() {
            return pbMovilidadArma;
        }

        public void setPbMovilidadArma(ProgressBar pbMovilidadArma) {
            this.pbMovilidadArma = pbMovilidadArma;
        }

        public ProgressBar getPbControlArma() {
            return pbControlArma;
        }

        public void setPbControlArma(ProgressBar pbControlArma) {
            this.pbControlArma = pbControlArma;
        }


    }//Fin class ViewHolder

}
