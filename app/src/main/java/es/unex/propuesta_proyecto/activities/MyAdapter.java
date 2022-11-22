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
import java.util.List;

import es.unex.propuesta_proyecto.R;
import es.unex.propuesta_proyecto.model.Repo;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Repo> mDataset;

    public interface OnListInteractionListener{
        public void onListInteraction(String url);
    }

    public OnListInteractionListener mListener;

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
            tvNombre = itemView.findViewById(R.id.tvNombre);
            ivArma = itemView.findViewById(R.id.ivArma);
            pbPrecisionArma = itemView.findViewById(R.id.pbPrecisionArma);
            pbDanoArma = itemView.findViewById(R.id.pbDañoArma);
            pbAlcanceArma = itemView.findViewById(R.id.pbAlcanceArma);
            pbCadenciaArma = itemView.findViewById(R.id.pbCadenciaArma);
            pbMovilidadArma = itemView.findViewById(R.id.pbMovilidadArma);
            pbControlArma = itemView.findViewById(R.id.pbControlArma);

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
        //holder.getIvArma().setImageURI(alArmas.get(position).getImagen());
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
