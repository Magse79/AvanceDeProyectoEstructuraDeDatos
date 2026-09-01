package gestortareas;

import actividad1estructuradedatos.DoubleLinkedList;
import actividad1estructuradedatos.LinkedList;
import actividad1estructuradedatos.SimplyLinkedList;

public class ListaTareas {

    private final LinkedList<Tarea> lista;

    public ListaTareas() {
        this.lista = new DoubleLinkedList<>();
    }

    public void insertar(Tarea tarea) {
        lista.insertarFinal(tarea);
    }

    public boolean eliminar(int id) {
        return lista.eliminar(Tarea.llaveBusqueda(id));
    }

    public Tarea buscar(int id) {

        for (int i = 0; i < lista.tamano(); i++) {

            Tarea actual = lista.obtenerEn(i);

            if (actual.getId() == id) {
                return actual;
            }
        }

        return null;
    }

    public boolean estaVacia() {
        return lista.estaVacia();
    }

    public int tamano() {
        return lista.tamano();
    }

    public String mostrarTodas() {
        return lista.mostrar();
    }

    /**
     * Muestra las tareas agrupadas por departamento (orden de
     * primera aparición), demostrando el acceso aleatorio de la
     * lista mediante obtenerEn(indice).
     */
    public void mostrarAgrupadasPorDepartamento() {

        if (estaVacia()) {
            System.out.println("(sin tareas registradas por departamento)");
            return;
        }

        LinkedList<String> vistos = new SimplyLinkedList<>();

        for (int i = 0; i < lista.tamano(); i++) {

            Tarea actual = lista.obtenerEn(i);
            String depto = actual.getDepartamento();

            if (vistos.buscar(depto)) {
                continue;
            }

            vistos.insertarFinal(depto);

            System.out.println("\nDepartamento: " + depto);

            for (int j = 0; j < lista.tamano(); j++) {

                Tarea candidata = lista.obtenerEn(j);

                if (candidata.getDepartamento().equals(depto)) {
                    System.out.println("  - " + candidata);
                }
            }
        }
    }
}
