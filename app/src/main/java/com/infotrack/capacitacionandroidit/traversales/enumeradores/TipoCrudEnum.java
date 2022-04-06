package com.infotrack.capacitacionandroidit.traversales.enumeradores;

public enum TipoCrudEnum {

    //region Tipos
    REGISTRO(0),
    EDICION(1);
    //endregion

    //region Atributos
    private final int idTipo;
    //endregion

    //region Constructor
    TipoCrudEnum(int id) {
        this.idTipo = id;
    }
    //endregion

    //region Propiedades
    public int getValue() {
        return idTipo;
    }
    //endregion
}
