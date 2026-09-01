package actividad2colaspilas;

import actividad1estructuradedatos.LinkedList;
import actividad1estructuradedatos.SimplyLinkedList;

public class Pila<T> {

    private final LinkedList<T> lista;

    public Pila() {
        lista = new SimplyLinkedList<>();
    }

    public void push(T dato) {
        lista.insertarInicio(dato);
    }

    public T pop() {

        if (estaVacia()) {
            throw new EstructuraVaciaException(
                    "No se puede hacer pop: la pila está vacía."
            );
        }

        return lista.eliminarInicio();
    }

    public T peek() {

        if (estaVacia()) {
            throw new EstructuraVaciaException(
                    "No se puede hacer peek: la pila está vacía."
            );
        }

        return lista.obtenerInicio();
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