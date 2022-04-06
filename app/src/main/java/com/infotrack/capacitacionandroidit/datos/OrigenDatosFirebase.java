package com.infotrack.capacitacionandroidit.datos;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OrigenDatosFirebase {

    //region atributos
    private final FirebaseDatabase database;
    private final String nodoRaiz;
    //endregion

    //region Constructor
    public OrigenDatosFirebase(String nodoRaiz) {
        this.database = FirebaseDatabase.getInstance();
        this.nodoRaiz = nodoRaiz;
    }
    //endregion

    //region Propios
    public DatabaseReference obtenerReferencia() {
        return database.getReference(nodoRaiz);
    }
    //endregion
}
