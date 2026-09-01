package actividad2colaspilas;

import actividad1estructuradedatos.LinkedList;
import actividad1estructuradedatos.SimplyLinkedList;

public class Cola<T> {

    private final LinkedList<T> lista;

    public Cola() {
        lista = new SimplyLinkedList<>();
    }

    public void enqueue(T dato) {
        lista.insertarFinal(dato);
    }

    public T dequeue() {

        if (estaVacia()) {
            throw new EstructuraVaciaException(
                    "No se puede hacer dequeue: la cola está vacía."
            );
        }

        return lista.eliminarInicio();
    }

    public T peek() {

        if (estaVacia()) {
            throw new EstructuraVaciaException(
                    "No se puede hacer peek: la cola está vacía."
            );
        }

        return lista.obtenerInicio();
    }

    /**
     * Alias de peek(), con el nombre que usa esta nueva actividad
     * (consultar el frente de la cola sin retirarlo). Se agrega
     * sin quitar peek(), que ya usan GestorSistemaOperativo y
     * TestGestorSO de la Actividad 2.
     */
    public T front() {
        return peek();
    }

    public boolean estaVacia() {
        return lista.estaVacia();
    }

    public int tamano() {
        return lista.tamano();
    }

    public String mostrar() {
        return lista.mostrar();
    }
}