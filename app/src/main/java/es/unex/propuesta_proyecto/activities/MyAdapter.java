package es.unex.propuesta_proyecto.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import es.unex.propuesta_proyecto.R;
import es.unex.propuesta_proyecto.api.AppExecutors;
import es.unex.propuesta_proyecto.dao.AppDatabaseArmas;
import es.unex.propuesta_proyecto.dao.AppDatabaseClases;
import es.unex.propuesta_proyecto.model.Armas;
import es.unex.propuesta_proyecto.model.Clases;
import es.unex.propuesta_proyecto.model.Repo;

/* Esta clase permite meter las armas en el recyclerView de la clase ArmasPrincipalesActivity y ArmasSecundariasActivity */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Repo> mDataset;
    private static String usuarioGlobal;
    private static String claseGlobal;
    private static int armaIdGlobal;
    private static String weaponGlobal;

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

    public void pasarIdArma(int armaId) {
        this.armaIdGlobal = armaId;
    }

    public void pasarWeapon(String weapon) {
        this.weaponGlobal = weapon;
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
            ivArma = itemView.findViewById(R.id.ivArmaItem);
            pbPrecisionArma = itemView.findViewById(R.id.pbPrecisionArma);
            pbDanoArma = itemView.findViewById(R.id.pbDañoArma);
            pbAlcanceArma = itemView.findViewById(R.id.pbAlcanceArma);
            pbCadenciaArma = itemView.findViewById(R.id.pbCadenciaArma);
            pbMovilidadArma = itemView.findViewById(R.id.pbMovilidadArma);
            pbControlArma = itemView.findViewById(R.id.pbControlArma);

            /* Este método se encarga de cambiar las armas principales y secundarias. Una vez que el usuario pulse en el nombre del arma (Dentro de la lista mostrada por la API en Armas
            Principales (Activity) o Armas Secundarias (Activity), le conducirá a la pantalla de DetalleClaseActivity, donde le intercambiará el arma anterior por el arma seleccionado */

            tvNombre.setOnClickListener(v1 -> AppExecutors.getInstance().diskIO().execute(() -> {
                Intent navegarADetalles = new Intent(context, DetalleClaseActivity.class);
                navegarADetalles.putExtra("className", claseGlobal); // Pasamos la clase al detallesActivity a través del intent
                // usuarioGlobal es el usuario que está loggeado en este instante.
                Armas armasUsuario = AppDatabaseArmas.getInstance(context).daoJuego().obtenerArmaUsuario(tvNombre.getText().toString(), usuarioGlobal); // Se coge el arma actual del usuario
                List<Clases> clasesUsuario = AppDatabaseClases.getInstance(context).daoClases().obtenerClasesUsuario(usuarioGlobal); // Lista de clases del usuario
                if (clasesUsuario != null) {
                    for (int i = 0; i < clasesUsuario.size(); i++) {
                        int claseActual = clasesUsuario.get(i).getId(); // Recupera la id de la clase actual.
                        if (clasesUsuario.get(i).getNombre().equals(claseGlobal)) { // compara todas las clases hasta encontrar en la que está
                            if (armasUsuario != null) { //  tiene ese arma el usuario se actualiza, si no se inserta.
                                int armaActual = armasUsuario.getId();
                                //Se ejecuta en el hilo principal porque realiza cambios en la pantalla en tiempo de ejecución
                                AppExecutors.getInstance().mainThread().execute(() -> pasarURLimg(weaponGlobal, ivArma));
                                AppDatabaseArmas.getInstance(context).daoJuego().actualizarArma(tvNombre.getText().toString(), "", "", pbPrecisionArma.getProgress(), pbDanoArma.getProgress(), pbAlcanceArma.getProgress(), pbCadenciaArma.getProgress(), pbMovilidadArma.getProgress(), pbControlArma.getProgress(), armaActual, claseActual, armasUsuario.getPrincipal(), weaponGlobal);
                            } else {
                                Armas insertarArma = new Armas();
                                if (armaIdGlobal == 1) {
                                    insertarArma = new Armas(tvNombre.getText().toString(), "", "", pbPrecisionArma.getProgress(), pbDanoArma.getProgress(), pbAlcanceArma.getProgress(), pbCadenciaArma.getProgress(), pbMovilidadArma.getProgress(), pbControlArma.getProgress(), "", usuarioGlobal, claseActual, 1, weaponGlobal);
                                    AppExecutors.getInstance().mainThread().execute(() -> pasarURLimg(weaponGlobal, ivArma));
                                    insertarArma.setWeapon(weaponGlobal);
                                } else {
                                    if (armaIdGlobal == 0) {
                                        insertarArma = new Armas(tvNombre.getText().toString(), "", "", pbPrecisionArma.getProgress(), pbDanoArma.getProgress(), pbAlcanceArma.getProgress(), pbCadenciaArma.getProgress(), pbMovilidadArma.getProgress(), pbControlArma.getProgress(), "", usuarioGlobal, claseActual, 0, weaponGlobal);
                                        AppExecutors.getInstance().mainThread().execute(() -> pasarURLimg(weaponGlobal, ivArma));
                                        insertarArma.setWeapon(weaponGlobal);
                                    }
                                }
                                AppDatabaseArmas.getInstance(context).daoJuego().insertarArmas(insertarArma);
                            }
                        }
                    }
                }
                context.startActivity(navegarADetalles);
            }));

        }
    }
    //Dependiendo del nombre del arma recibido como parámetro muestra una imagen u otra en función del enlace con las funciones de Picasso
    //sobre el objeto ImageView recibido por parámetro

    public static void pasarURLimg(String nombreArma, ImageView ivArma){
        switch (nombreArma){
            case "ak-47":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_ak-47.webp").into(ivArma);
                break;
            case "aug":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_aug.webp").into(ivArma);
                break;
            case "fn-scar-17":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_fn-scar-17.webp").into(ivArma);
                break;
            case "m4a1":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_m4a1.webp").into(ivArma);
                break;
            case "725":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_725.webp").into(ivArma);
                break;
            case "model-680":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_model-680.webp").into(ivArma);
                break;
            case "r9-0-shotgun":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_r9-0-shotgun.webp").into(ivArma);
                break;
            case "origin-12-shotgun":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_origin-12-shotgun.webp").into(ivArma);
                break;
            case "dragunov":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_dragunov.webp").into(ivArma);
                break;
            case "ebr-14":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_ebr-14.webp").into(ivArma);
                break;
            case "hdr":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_hdr.webp").into(ivArma);
                break;
            case "kar98k":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_kar98k.webp").into(ivArma);
                break;
            case "mg34":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_mg34.webp").into(ivArma);
                break;
            case "m91":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_m91.webp").into(ivArma);
                break;
            case "pkm":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_pkm.webp").into(ivArma);
                break;
            case "mp5":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_mp5.webp").into(ivArma);
                break;
            case "mp7":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_mp7.webp").into(ivArma);
                break;
            case "p90":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_p90.webp").into(ivArma);
                break;
            case "uzi":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_uzi.webp").into(ivArma);
                break;
            case "1911":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_1911.webp").into(ivArma);
                break;
            case "x16":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_x16.webp").into(ivArma);
                break;
            case "50-gs":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_50-gs.webp").into(ivArma);
                break;
            case "combat-knife":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_combat-knife.webp").into(ivArma);
                break;
            case "riot-shield":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_riot-shield.webp").into(ivArma);
                break;
            default:
                break;
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

    // Modificación de atributos del Dataset de la api.

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

    // Devuelve el tamaño de mDataset

    @Override
    public int getItemCount() { return mDataset.size();}

    // Se le pasa el repositorio por parametros y lo añade a mDataset, almacenando los datos de la api.

    public void swap(List<Repo> dataset){
        mDataset.add(dataset.get(0));
        notifyDataSetChanged();
    }
}
