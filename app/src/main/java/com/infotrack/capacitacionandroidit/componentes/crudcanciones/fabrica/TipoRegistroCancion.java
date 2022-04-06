package com.infotrack.capacitacionandroidit.componentes.crudcanciones.fabrica;

import com.infotrack.capacitacionandroidit.R;
import com.infotrack.capacitacionandroidit.componentes.crudcanciones.CrudCallback;

public class TipoRegistroCancion implements AccionesTipoCrud {

    @Override
    public int obtenerTituloActividad() {
        return R.string.titulo_registro_de_canciones;
    }

    @Override
    public boolean iniciarControles() {
        return false;
    }

    @Override
    public String obtenerTextoBotonGuardar() {
        return "GUARDAR";
    }

    @Override
    public int obtenerImagenTipoCrud() {
        return 0;
    }

    @Override
    public void persistirCancion(CrudCallback callback) {
        callback.registrarCancion();
    }
}
