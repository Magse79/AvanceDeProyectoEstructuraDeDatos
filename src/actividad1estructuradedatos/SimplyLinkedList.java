package actividad1estructuradedatos;

public class SimplyLinkedList<T> implements LinkedList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int tamano;

    @Override
    public boolean estaVacia() {
        return head == null;
    }

    @Override
    public int tamano() {
        return tamano;
    }

    @Override
    public void insertarFinal(T dato) {

        Node<T> nuevo = new Node<>(dato);

        if (estaVacia()) {
            head = nuevo;
            tail = nuevo;
        } else {
            tail.setNext(nuevo);
            tail = nuevo;
        }

        tamano++;
    }

    @Override
    public void insertarInicio(T dato) {

        Node<T> nuevo = new Node<>(dato);

        if (estaVacia()) {
            head = nuevo;
            tail = nuevo;
        } else {
            nuevo.setNext(head);
            head = nuevo;
        }

        tamano++;
    }

    @Override
    public boolean eliminar(T dato) {

        if (estaVacia()) {
            return false;
        }

        if (head.getData().equals(dato)) {

            head = head.getNext();

            if (head == null) {
                tail = null;
            }

            tamano--;
            return true;
        }

        Node<T> anterior = head;
        Node<T> actual = head.getNext();

        while (actual != null) {

            if (actual.getData().equals(dato)) {

                anterior.setNext(actual.getNext());

                if (actual == tail) {
                    tail = anterior;
                }

                tamano--;
                return true;
            }

            anterior = actual;
            actual = actual.getNext();
        }

        return false;
    }

    @Override
    public boolean buscar(T dato) {

        Node<T> actual = head;

        while (actual != null) {

            if (actual.getData().equals(dato)) {
                return true;
            }

            actual = actual.getNext();
        }

        return false;
    }

    @Override
    public T obtenerInicio() {
        return estaVacia() ? null : head.getData();
    }

    @Override
    public T eliminarInicio() {

        if (estaVacia()) {
            return null;
        }

        T dato = head.getData();

        head = head.getNext();

        if (head == null) {
            tail = null;
        }

        tamano--;

        return dato;
    }

    @Override
    public T obtenerEn(int indice) {

        if (indice < 0 || indice >= tamano) {
            throw new IndexOutOfBoundsException(
                    "Índice fuera de rango: " + indice);
        }

        Node<T> actual = head;

        for (int i = 0; i < indice; i++) {
            actual = actual.getNext();
        }

        return actual.getData();
    }

    @Override
    public String mostrar() {

        if (estaVacia()) {
            return "(vacía)";
        }

        StringBuilder sb = new StringBuilder("[ ");

        Node<T> actual = head;

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