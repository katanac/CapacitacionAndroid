package com.infotrack.capacitacionandroidit.componentes.crudcanciones.fabrica;

import com.infotrack.capacitacionandroidit.componentes.crudcanciones.CrudCallback;

public interface AccionesTipoCrud {

    int obtenerTituloActividad();

    boolean iniciarControles();

    String obtenerTextoBotonGuardar();

    int obtenerImagenTipoCrud();

    void persistirCancion(CrudCallback callback);
}

