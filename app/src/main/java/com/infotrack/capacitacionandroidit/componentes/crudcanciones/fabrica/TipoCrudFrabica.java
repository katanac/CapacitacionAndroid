package com.infotrack.capacitacionandroidit.componentes.crudcanciones.fabrica;

import com.infotrack.capacitacionandroidit.traversales.enumeradores.TipoCrudEnum;

public class TipoCrudFrabica {

    public AccionesTipoCrud crearTipoCrud(int id) {
        return TipoCrudEnum.REGISTRO.getValue() == id ? new TipoRegistroCancion() : new TipoEditarCancion();
    }
}
