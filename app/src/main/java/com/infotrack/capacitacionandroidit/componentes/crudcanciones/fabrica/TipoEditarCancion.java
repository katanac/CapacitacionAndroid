package com.infotrack.capacitacionandroidit.componentes.crudcanciones.fabrica;

import com.infotrack.capacitacionandroidit.R;
import com.infotrack.capacitacionandroidit.componentes.crudcanciones.CrudCallback;

public class TipoEditarCancion implements AccionesTipoCrud {
    @Override
    public int obtenerTituloActividad() {
        return R.string.titulo_edicion_de_canciones;
    }

    @Override
    public boolean iniciarControles() {
        return true;
    }

    @Override
    public String obtenerTextoBotonGuardar() {
        return "EDITAR";
    }

    @Override
    public int obtenerImagenTipoCrud() {
        return 0;
    }

    @Override
    public void persistirCancion(CrudCallback callback) {
        callback.editarCancion();
    }
}
