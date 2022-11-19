package es.unex.propuesta_proyecto;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ClasesAdapter extends RecyclerView.Adapter<ClasesAdapter.ViewHolder> {

    ArrayList<String> alClases;

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

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getBClase().setText(alClases.get(position));
    }

    @Override
    public int getItemCount() {
        return alClases.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        Button bClase;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bClase = itemView.findViewById(R.id.bClase);
        }

        public Button getBClase() {
            return bClase;
        }

    }

}
