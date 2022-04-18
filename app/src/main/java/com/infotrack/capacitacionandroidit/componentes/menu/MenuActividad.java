package com.infotrack.capacitacionandroidit.componentes.menu;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.infotrack.capacitacionandroidit.R;
import com.infotrack.capacitacionandroidit.componentes.listado.ListadoActividad;

public class MenuActividad extends AppCompatActivity {

    //region Atributos
    private Button btnCanciones;
    private Button btnArtistas;
    //endregion

    //region Sobrecargas
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        bindeoVista();
        ui();
    }
    //endregion

    //region Propios

    private void bindeoVista() {
        this.btnArtistas = findViewById(R.id.btn_menu_artistas);
        this.btnCanciones = findViewById(R.id.btn_menu_canciones);
    }

    private void ui() {
        this.btnArtistas.setOnClickListener(listenerBtnArtistas);
        this.btnCanciones.setOnClickListener(listenerBtnCanciones);

    }
    //endregion

    //region Listeners
    View.OnClickListener listenerBtnArtistas = view -> Toast.makeText(MenuActividad.this, "Artistas", Toast.LENGTH_SHORT).show();

    View.OnClickListener listenerBtnCanciones = view -> MenuActividad.this.startActivity(ListadoActividad.obtenerIntencion(MenuActividad.this));

    //endregion

}
