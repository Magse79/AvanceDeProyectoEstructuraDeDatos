package actividad1estructuradedatos;

public interface LinkedList<T> {

    boolean estaVacia();

    int tamano();

    void insertarInicio(T dato);

    void insertarFinal(T dato);

    boolean eliminar(T dato);

    boolean buscar(T dato);

    String mostrar();

    T obtenerInicio();

    T eliminarInicio();

    /**
     * Acceso aleatorio: regresa el dato ubicado en la posición
     * indicada (0 = primer elemento). Se agrega para soportar
     * casos de uso donde no basta con acceder solo por los
     * extremos, como la consulta de tareas por departamento.
     */
    T obtenerEn(int indice);
}