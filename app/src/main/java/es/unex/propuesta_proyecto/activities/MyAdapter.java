package es.unex.propuesta_proyecto.activities;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import es.unex.propuesta_proyecto.R;
import es.unex.propuesta_proyecto.api.AppExecutors;
import es.unex.propuesta_proyecto.dao.AppDataBase;
import es.unex.propuesta_proyecto.model.Armas;
import es.unex.propuesta_proyecto.model.Clases;
import es.unex.propuesta_proyecto.model.RepoArmas;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<RepoArmas> mDataset;
    private static String usuarioGlobal;
    private static String claseGlobal;
    private static int armaPrincipal;

    public interface OnListInteractionListener {
        public void onListInteraction(String url);
    }

    public OnListInteractionListener mListener;


    public void usuarioActivo(String usuario) {
        usuarioGlobal = usuario;
    }

    public void claseActiva(String clase) {
        claseGlobal = clase;
    }

    public void pasarIdArma(int principal) {
        this.armaPrincipal = principal;
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvNombre;
        ImageView ivArma;
        ProgressBar pbPrecisionArma;
        ProgressBar pbDanoArma;
        ProgressBar pbAlcanceArma;
        ProgressBar pbCadenciaArma;
        ProgressBar pbMovilidadArma;
        ProgressBar pbControlArma;
        Context context;

        public RepoArmas mItem;

        public MyViewHolder(View v) {
            super(v);
            context = itemView.getContext();
            tvNombre = itemView.findViewById(R.id.tvNombreArmaItem);
            ivArma = itemView.findViewById(R.id.ivArmaItem);
            pbPrecisionArma = itemView.findViewById(R.id.pbPrecisionArma);
            pbDanoArma = itemView.findViewById(R.id.pbDañoArma);
            pbAlcanceArma = itemView.findViewById(R.id.pbAlcanceArma);
            pbCadenciaArma = itemView.findViewById(R.id.pbCadenciaArma);
            pbMovilidadArma = itemView.findViewById(R.id.pbMovilidadArma);
            pbControlArma = itemView.findViewById(R.id.pbControlArma);

            tvNombre.setOnClickListener(v1 -> AppExecutors.getInstance().diskIO().execute(() -> {
                Intent navegarADetalles = new Intent(context, DetalleClaseActivity.class);
                navegarADetalles.putExtra("className", claseGlobal);
                Armas armasUsuario = AppDataBase.getInstance(context).daoJuego().obtenerArmaUsuario(tvNombre.getText().toString(), usuarioGlobal);
                List<Clases> clasesUsuario = AppDataBase.getInstance(context).daoClases().obtenerClasesUsuario(usuarioGlobal);
                if (clasesUsuario != null) {
                    for (int i = 0; i < clasesUsuario.size(); i++) {
                        int claseActual = clasesUsuario.get(i).getId();
                        if (clasesUsuario.get(i).getNombre().equals(claseGlobal)) {
                                Armas insertarArma = new Armas();
                                AppDataBase.getInstance(context).daoJuego().borrarArmasUsuario(claseActual, usuarioGlobal, armaPrincipal);
                                if (armaPrincipal == 1) {
                                    insertarArma = new Armas(tvNombre.getText().toString(), "", "", pbPrecisionArma.getProgress(), pbDanoArma.getProgress(), pbAlcanceArma.getProgress(), pbCadenciaArma.getProgress(), pbMovilidadArma.getProgress(), pbControlArma.getProgress(), "", usuarioGlobal, claseActual, 1);
                                    AppExecutors.getInstance().mainThread().execute(() -> pasarURLimg(tvNombre.getText().toString(), ivArma));
                                } else {
                                    if (armaPrincipal == 0) {
                                        insertarArma = new Armas(tvNombre.getText().toString(), "", "Base", pbPrecisionArma.getProgress(), pbDanoArma.getProgress(), pbAlcanceArma.getProgress(), pbCadenciaArma.getProgress(), pbMovilidadArma.getProgress(), pbControlArma.getProgress(), "", usuarioGlobal, claseActual, 0);
                                        AppExecutors.getInstance().mainThread().execute(() -> pasarURLimg(tvNombre.getText().toString(), ivArma));
                                    }
                                }
                                AppDataBase.getInstance(context).daoJuego().insertarArmas(insertarArma);
                            }
                        }
                    }
                context.startActivity(navegarADetalles);
            }));

        }
    }

    public static void pasarURLimg(String nombreArma, ImageView ivArma){
        switch (nombreArma){
            case "Ak-47":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_ak-47.webp").into(ivArma);
                break;
            case "Aug":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_aug.webp").into(ivArma);
                break;
            case "Fn Scar L7":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_fn-scar-17.webp").into(ivArma);
                break;
            case "M4a1":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_m4a1.webp").into(ivArma);
                break;
            case "725":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_725.webp").into(ivArma);
                break;
            case "Model 680":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_model-680.webp").into(ivArma);
                break;
            case "R9-O":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_r9-0-shotgun.webp").into(ivArma);
                break;
            case "Origin 12 Shotgun":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_origin-12-shotgun.webp").into(ivArma);
                break;
            case "Dragunov":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_dragunov.webp").into(ivArma);
                break;
            case "Ebr-14":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_ebr-14.webp").into(ivArma);
                break;
            case "Hdr":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_hdr.webp").into(ivArma);
                break;
            case "Kar98k":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_kar98k.webp").into(ivArma);
                break;
            case "M634":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_mg34.webp").into(ivArma);
                break;
            case "M91":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_m91.webp").into(ivArma);
                break;
            case "Pkm":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_pkm.webp").into(ivArma);
                break;
            case "Mp5":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_mp5.webp").into(ivArma);
                break;
            case "Mp7":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_mp7.webp").into(ivArma);
                break;
            case "P90":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_p90.webp").into(ivArma);
                break;
            case "Uzi":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_uzi.webp").into(ivArma);
                break;
            case "1911":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_1911.webp").into(ivArma);
                break;
            case "X16":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_x16.webp").into(ivArma);
                break;
            case ".50 GS":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_50-gs.webp").into(ivArma);
                break;
            case "Combat Knife":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_combat-knife.webp").into(ivArma);
                break;
            case "Riot Shield":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_riot-shield.webp").into(ivArma);
                break;
            default:
                break;
        }
    }

    public MyAdapter(List<RepoArmas> myDataset, OnListInteractionListener listener) {
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
        holder.pbPrecisionArma.setProgress(Integer.parseInt(mDataset.get(position).getAccuracy()));
        holder.pbDanoArma.setProgress(Integer.parseInt(mDataset.get(position).getDamage()));
        holder.pbAlcanceArma.setProgress(Integer.parseInt(mDataset.get(position).getRange()));
        holder.pbCadenciaArma.setProgress(Integer.parseInt(mDataset.get(position).getFireRate()));
        holder.pbMovilidadArma.setProgress(Integer.parseInt(mDataset.get(position).getMobility()));
        holder.pbControlArma.setProgress(Integer.parseInt(mDataset.get(position).getControl()));
    }


    @Override
    public int getItemCount() { return mDataset.size();}


    public void swap(List<RepoArmas> dataset){
        mDataset.add(dataset.get(0));
        notifyDataSetChanged();
    }
}
