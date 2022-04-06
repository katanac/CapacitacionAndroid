package com.infotrack.capacitacionandroidit.componentes.crudcanciones;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.infotrack.capacitacionandroidit.R;
import com.infotrack.capacitacionandroidit.componentes.crudcanciones.fabrica.AccionesTipoCrud;
import com.infotrack.capacitacionandroidit.componentes.crudcanciones.fabrica.TipoCrudFrabica;
import com.infotrack.capacitacionandroidit.datos.OrigenDatosFirebase;
import com.infotrack.capacitacionandroidit.modelos.Cancion;
import com.infotrack.capacitacionandroidit.traversales.enumeradores.TipoCrudEnum;

import java.io.Serializable;

public class CrudActividad extends AppCompatActivity implements CrudCallback {

    //region Atributos
    private TextInputEditText edNombreCancion;
    private TextInputEditText edNombreArtista;
    private TextInputEditText edNombreAlbum;
    private RadioGroup rgGeneroMusical;
    private Button btnRegistro;
    private RadioButton rbFolkMetal;
    private RadioButton rbAlternativo;
    private RadioButton rbMetal;
    private final OrigenDatosFirebase firebase;
    private DatabaseReference referencia;
    private TipoCrudEnum tipoCrudEnum;
    private AccionesTipoCrud accionesTipoCrud;
    private Cancion cancion;

    //endregion

    //region Constructor
    public CrudActividad() {
        this.firebase = new OrigenDatosFirebase("Musica");
        this.referencia = firebase.obtenerReferencia();
    }
    //endregion

    //region Intencion
    public static Intent obtenerIntencion(Context contexto, TipoCrudEnum tipoCrudEnum, Cancion cancion) {
        Intent intencion = new Intent(contexto, CrudActividad.class);
        intencion.putExtra("EXTRA_TIPO_CRUD", tipoCrudEnum);
        intencion.putExtra("EXTRA_CANCION", cancion);
        return intencion;
    }
    //endregion

    //region Sobrecargas
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindearVista();
        extras();
        ui();
        asignarListeners();

        this.referencia.child("Canciones");


    }

    //endregion

    //region Propios

    private void extras() {
        this.tipoCrudEnum = (TipoCrudEnum) getIntent().getSerializableExtra("EXTRA_TIPO_CRUD");
        this.accionesTipoCrud = new TipoCrudFrabica().crearTipoCrud(tipoCrudEnum.getValue());
        this.cancion = (Cancion) getIntent().getSerializableExtra("EXTRA_CANCION");
    }

    private void ui() {
        setTitle(accionesTipoCrud.obtenerTituloActividad());
        btnRegistro.setText(accionesTipoCrud.obtenerTextoBotonGuardar());
        asignarValores();
    }

    private void asignarValores() {
        if (accionesTipoCrud.iniciarControles()) {
            this.edNombreCancion.setText(cancion.getNombreCancion());
            this.edNombreArtista.setText(cancion.getNombreArtista());
            this.edNombreAlbum.setText(cancion.getNombreAlbum());
            this.rbFolkMetal.setChecked(cancion.getEsFolKMetal());
            this.rbAlternativo.setChecked(cancion.getEsAlternativo());
            this.rbMetal.setChecked(cancion.getEsMetal());
        }
    }

    private void bindearVista() {
        this.edNombreCancion = findViewById(R.id.edt_cancion);
        this.edNombreArtista = findViewById(R.id.edt_artista);
        this.edNombreAlbum = findViewById(R.id.edt_album);
        this.rgGeneroMusical = findViewById(R.id.radio_genero_musica);
        this.btnRegistro = findViewById(R.id.btn_registrar);
        this.rbFolkMetal = findViewById(R.id.rb_folk_metal);
        this.rbAlternativo = findViewById(R.id.rb_alternative);
        this.rbMetal = findViewById(R.id.rb_metal);
    }

    private void asignarListeners() {
        rgGeneroMusical.setOnCheckedChangeListener(listnerRgGeneros);
        btnRegistro.setOnClickListener(listnerBtnRegistro);
    }

    private void persistenciaTipoCrud() {
        this.accionesTipoCrud.persistirCancion(this);
    }


    private String obtenerinfomacionEditText(TextInputEditText editText) {
        return editText.getText() == null ? "" : editText.getText().toString();
    }

    private Cancion obtenerCancion() {
        Cancion cancionAgregada = new Cancion();
        cancionAgregada.setNombreCancion(obtenerinfomacionEditText(edNombreCancion));
        cancionAgregada.setNombreArtista(obtenerinfomacionEditText(edNombreArtista));
        cancionAgregada.setNombreAlbum(obtenerinfomacionEditText(edNombreAlbum));
        cancionAgregada.setEsAlternativo(rbAlternativo.isChecked());
        cancionAgregada.setEsFolKMetal(rbFolkMetal.isChecked());
        cancionAgregada.setEsMetal(rbMetal.isChecked());
        return cancionAgregada;
    }

    private void limpiarDatos() {
        edNombreAlbum.setText("");
        edNombreArtista.setText("");
        edNombreCancion.setText("");
        rbMetal.setChecked(false);
        rbFolkMetal.setChecked(false);
        rbAlternativo.setChecked(false);
    }

    private boolean validarDatosCancion() {
        boolean validado = true;

        if (obtenerinfomacionEditText(edNombreCancion).isEmpty()) {
            validado = false;
            edNombreCancion.setError("Obligatorio...");
        }


        if (obtenerinfomacionEditText(edNombreArtista).isEmpty()) {
            validado = false;
            edNombreArtista.setError("Obligatorio...");
        }

        return validado;
    }

    private void finalizacionActividad() {
        finish();
    }
    //endregion


    //region CallBack
    @Override
    public void registrarCancion() {
        if (validarDatosCancion()) {
            Cancion cancion = obtenerCancion();
            this.referencia.child("Canciones").child(cancion.getIdCancion()).setValue(cancion).addOnSuccessListener(listenerPersistenciaDatos);
        } else
            Toast.makeText(getApplicationContext(), "Por favor valide los campos de nombre y artista", Toast.LENGTH_LONG).show();


    }

    @Override
    public void editarCancion() {
        Toast.makeText(getBaseContext(), "edicion", Toast.LENGTH_SHORT).show();
    }
    //endregion

    //region listeners
    @SuppressLint("NonConstantResourceId")
    RadioGroup.OnCheckedChangeListener listnerRgGeneros = (radioGroup, rbtnSelecionado) -> {
        switch (rbtnSelecionado) {
            case R.id.rb_folk_metal:
                Toast.makeText(getApplicationContext(), "Folk", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rb_alternative:
                Toast.makeText(getApplicationContext(), "Alernativo", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rb_metal:
                Toast.makeText(getApplicationContext(), "Metal", Toast.LENGTH_SHORT).show();
                break;

        }
    };

    View.OnClickListener listnerBtnRegistro = view -> persistenciaTipoCrud();

    OnSuccessListener listenerPersistenciaDatos = o -> finalizacionActividad();

    //endregion

}