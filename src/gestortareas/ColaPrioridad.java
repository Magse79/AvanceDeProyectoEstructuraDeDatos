package gestortareas;

import actividad1estructuradedatos.Node;
import actividad2colaspilas.EstructuraVaciaException;
import java.util.Comparator;

/**
 * Cola de prioridad implementada sobre nodos enlazados (sin
 * arreglos). A diferencia de una cola FIFO normal, el elemento
 * que sale primero (dequeue/front) no es el que llegó primero,
 * sino el de mayor prioridad según el comparador recibido.
 *
 * Entre elementos con la misma prioridad se conserva el orden
 * de llegada (FIFO dentro de cada bloque de prioridad), gracias
 * a que enqueue siempre inserta un nuevo elemento después de
 * los que ya tienen prioridad igual o mayor.
 */
public class ColaPrioridad<T> {

    private Node<T> frente;
    private Node<T> fin;
    private int tamano;
    private final Comparator<T> comparador;

    public ColaPrioridad(Comparator<T> comparador) {
        this.comparador = comparador;
        this.frente = null;
        this.fin = null;
        this.tamano = 0;
    }

    public void enqueue(T dato) {

        Node<T> nuevo = new Node<>(dato);

        if (estaVacia()) {
            frente = nuevo;
            fin = nuevo;
            tamano++;
            return;
        }

        // El nuevo dato tiene mayor prioridad que todo lo que hay:
        // se vuelve el nuevo frente.
        if (comparador.compare(dato, frente.getData()) < 0) {
            nuevo.setNext(frente);
            frente = nuevo;
            tamano++;
            return;
        }

        // Se recorre la lista mientras el nuevo dato no tenga
        // mayor prioridad que el siguiente nodo, para insertarlo
        // justo después del último elemento con prioridad igual
        // o mayor (esto conserva el orden de llegada entre
        // elementos con la misma prioridad).
        Node<T> actual = frente;

        while (actual.getNext() != null
                && comparador.compare(dato, actual.getNext().getData()) >= 0) {

            actual = actual.getNext();
        }

        nuevo.setNext(actual.getNext());
        actual.setNext(nuevo);

        if (nuevo.getNext() == null) {
            fin = nuevo;
        }

        tamano++;
    }

    public T dequeue() {

        if (estaVacia()) {
            throw new EstructuraVaciaException(
                    "No se puede hacer dequeue: la cola de prioridad está vacía."
            );
        }

        T dato = frente.getData();

        frente = frente.getNext();
        tamano--;

        if (frente == null) {
            fin = null;
        }

        return dato;
    }

    public T front() {

        if (estaVacia()) {
            throw new EstructuraVaciaException(
                    "No se puede hacer front: la cola de prioridad está vacía."
            );
        }

        return frente.getData();
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public int tamano() {
        return tamano;
    }

    public String mostrar() {

        StringBuilder sb = new StringBuilder("[ ");

        Node<T> actual = frente;

        while (actual != null) {

            sb.append(actual.getData());

            if (actual.getNext() != null) {
                sb.append(" -> ");
            }

            actual = actual.getNext();
        }

        sb.append(" ]");

        return sb.toString();
    }
}
