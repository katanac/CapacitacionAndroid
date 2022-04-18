package com.infotrack.capacitacionandroidit.componentes.listado;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.infotrack.capacitacionandroidit.R;
import com.infotrack.capacitacionandroidit.componentes.crudcanciones.CrudActividad;
import com.infotrack.capacitacionandroidit.datos.OrigenDatosFirebase;
import com.infotrack.capacitacionandroidit.modelos.Cancion;
import com.infotrack.capacitacionandroidit.traversales.enumeradores.TipoCrudEnum;

import java.util.LinkedList;
import java.util.List;

public class ListadoActividad extends AppCompatActivity implements ListadoAdaptador.listenerOnClickCard {


    //region Atributos
    private final OrigenDatosFirebase firebase;
    private DatabaseReference referencia;
    private RecyclerView recyclerCanciones;
    private FloatingActionButton fbAgregar;
    private ListadoAdaptador adapatador;
    private List<Cancion> listaCanciones;
    //endregion

    //region Constructor
    public ListadoActividad() {
        this.firebase = new OrigenDatosFirebase("Musica");
        this.referencia = firebase.obtenerReferencia();
        this.listaCanciones = new LinkedList<>();
    }
    //endregion


    //region Intencion
    public static Intent obtenerIntencion(Context context) {
        return new Intent(context, ListadoActividad.class);
    }
    //endregion


    //region Sobrecargas
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listado_canciones);

        this.recyclerCanciones = findViewById(R.id.recycler_canciones);
        this.fbAgregar = findViewById(R.id.fab_agregar_canciones);

        this.adapatador = new ListadoAdaptador(listaCanciones, this);
        this.recyclerCanciones.setAdapter(adapatador);
        this.recyclerCanciones.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        this.fbAgregar.setOnClickListener(listenerBtonAgregar);

        this.referencia.child("Canciones").addValueEventListener(listenerConsultaCanciones);
    }
    //endregion

    //region Propios
    @SuppressLint("NotifyDataSetChanged")
    private void pintarListado(List<Cancion> listadoCanciones) {
        this.listaCanciones.clear();
        this.listaCanciones.addAll(listadoCanciones);
        this.adapatador.notifyDataSetChanged();
    }

    private void navegarRegistroCanciones() {
        getApplicationContext().startActivity(CrudActividad.obtenerIntencion(getApplicationContext(), TipoCrudEnum.REGISTRO, new Cancion()));
    }


    private void navegarEdicionCanciones(int posicion) {
        getApplicationContext().startActivity(CrudActividad.obtenerIntencion(getApplicationContext(), TipoCrudEnum.EDICION, listaCanciones.get(posicion)));
    }


    private void eliminacionOk() {
        Toast.makeText(this, "Se elimino correctamente la cancion", Toast.LENGTH_LONG).show();
    }

    //endregion

    //region Callback Adaptador
    @Override
    public void edicionCancion(int posicion) {
        navegarEdicionCanciones(posicion);
    }

    @Override
    public void eliminarCancion(int posicion) {
        this.referencia.child("Canciones").child(this.listaCanciones.get(posicion).getIdCancion()).removeValue().addOnSuccessListener(listenerEliminacionCancion);

    }
    //endregion

    //region Listeners
    ValueEventListener listenerConsultaCanciones = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            List<Cancion> listaCancionesConsulta = new LinkedList<>();

            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                Cancion cancion = dataSnapshot.getValue(Cancion.class);
                if (cancion != null) listaCancionesConsulta.add(cancion);
            }

            pintarListado(listaCancionesConsulta);

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            Toast.makeText(getApplicationContext(), "Ocurrio un error al realizar la consulta", Toast.LENGTH_LONG).show();
        }
    };

    View.OnClickListener listenerBtonAgregar = view -> navegarRegistroCanciones();


    OnSuccessListener listenerEliminacionCancion = o -> eliminacionOk();

    //endregion


}
