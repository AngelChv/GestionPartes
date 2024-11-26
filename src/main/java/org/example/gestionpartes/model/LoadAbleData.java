package org.example.gestionpartes.model;

/**
 * Especifica que un controlador puede recibir datos de otro cuando se cambia de escena.
 */
public interface LoadAbleData<T> {
    void loadData(T dataSend);
}
