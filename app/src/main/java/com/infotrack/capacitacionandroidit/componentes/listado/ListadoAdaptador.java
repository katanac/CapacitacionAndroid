package com.infotrack.capacitacionandroidit.componentes.listado;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.infotrack.capacitacionandroidit.R;
import com.infotrack.capacitacionandroidit.modelos.Cancion;

import java.util.List;

public class ListadoAdaptador extends RecyclerView.Adapter<ListadoAdaptador.ViewHolder> {

    //region Atributos
    private final List<Cancion> listadoCanciones;
    private final listenerOnClickCard callback;
    //endregion

    //region Constructor
    public ListadoAdaptador(List<Cancion> listadoCanciones, listenerOnClickCard callback) {
        this.listadoCanciones = listadoCanciones;
        this.callback = callback;
    }
    //endregion

    //region Sobrecarga
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cancion, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindeoVista(position);
    }

    @Override
    public int getItemCount() {
        return listadoCanciones.size();
    }
    //endregion

    //region ViewHolder
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNombre;
        TextView tvArtista;
        TextView tvAlbum;
        ImageView imgFolk;
        ImageView imgAlternativo;
        ImageView imgMetal;
        ImageButton btnEliminar;
        ImageButton btnEditar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tv_nombre);
            tvArtista = itemView.findViewById(R.id.tv_nombre_artista);
            tvAlbum = itemView.findViewById(R.id.tv_nombre_album);
            imgFolk = itemView.findViewById(R.id.img_folk_metal);
            imgAlternativo = itemView.findViewById(R.id.img_alternativo);
            imgMetal = itemView.findViewById(R.id.img_metal);
            btnEliminar = itemView.findViewById(R.id.btn_eliminar);
            btnEditar = itemView.findViewById(R.id.btn_editar);
        }

        void bindeoVista(int posicion) {
            Cancion cancionItem = listadoCanciones.get(posicion);
            tvNombre.setText(cancionItem.getNombreCancion());
            tvArtista.setText(cancionItem.getNombreArtista());
            tvAlbum.setText(cancionItem.getNombreAlbum());
            imgMetal.setVisibility(cancionItem.getEsMetal() ? View.VISIBLE : View.INVISIBLE);
            imgFolk.setVisibility(cancionItem.getEsFolKMetal() ? View.VISIBLE : View.GONE);
            imgAlternativo.setVisibility(cancionItem.getEsAlternativo() ? View.VISIBLE : View.GONE);
            btnEditar.setOnClickListener(view -> callback.edicionCancion(posicion));
            btnEliminar.setOnClickListener(view -> callback.eliminarCancion(posicion));

        }
    }
//endregion

    //region Listener
    interface listenerOnClickCard {

        void edicionCancion(int posicion);

        void eliminarCancion(int posicion);
    }
//endregion
}
