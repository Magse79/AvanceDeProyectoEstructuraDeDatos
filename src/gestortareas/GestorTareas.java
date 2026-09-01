package gestortareas;

import actividad2colaspilas.Pila;

public class GestorTareas {

    // --- Pila (actividad2colaspilas): tareas urgentes ---
    private final Pila<Tarea> urgentes;

    // --- Cola de prioridad (gestortareas): tareas programadas,
    //     ordenadas por urgencia (ALTA sale antes que MEDIA y
    //     BAJA; entre igual urgencia se respeta el orden de
    //     llegada) ---
    private final ColaPrioridad<Tarea> programadas;

    // --- Lista (actividad1estructuradedatos): tareas por departamento ---
    private final ListaTareas porDepartamento;

    public GestorTareas() {
        this.urgentes = new Pila<>();
        this.programadas = new ColaPrioridad<>(
                (a, b) -> a.getUrgencia().ordinal() - b.getUrgencia().ordinal()
        );
        this.porDepartamento = new ListaTareas();
    }

    // ===================== PILA: URGENTES =====================

    public void agregarUrgente(Tarea tarea) {
        urgentes.push(tarea);
    }

    public Tarea atenderUrgente() {
        return urgentes.pop();
    }

    public Tarea verProximaUrgente() {
        return urgentes.peek();
    }

    public boolean hayUrgentes() {
        return !urgentes.estaVacia();
    }

    public String mostrarUrgentes() {
        return urgentes.mostrar();
    }

    // =================== COLA: PROGRAMADAS ====================

    public void agregarProgramada(Tarea tarea) {
        programadas.enqueue(tarea);
    }

    public Tarea atenderProgramada() {
        return programadas.dequeue();
    }

    public Tarea verProximaProgramada() {
        return programadas.front();
    }

    public boolean hayProgramadas() {
        return !programadas.estaVacia();
    }

    public String mostrarProgramadas() {
        return programadas.mostrar();
    }

    // =================== LISTA: DEPARTAMENTO ===================

    public void agregarPorDepartamento(Tarea tarea) {
        porDepartamento.insertar(tarea);
    }

    public boolean eliminarPorDepartamento(int id) {
        return porDepartamento.eliminar(id);
    }

    public Tarea buscarPorDepartamento(int id) {
        return porDepartamento.buscar(id);
    }

    public boolean hayPorDepartamento() {
        return !porDepartamento.estaVacia();
    }

    public void mostrarPorDepartamento() {
        porDepartamento.mostrarAgrupadasPorDepartamento();
    }

    // ===================== REPORTE GENERAL =====================

    public void mostrarTodasLasTareasPendientes() {

        System.out.println("\n======================================");
        System.out.println(" TODAS LAS TAREAS PENDIENTES");
        System.out.println("======================================");

        System.out.println("\n--- Urgentes (Pila: se atienden en este orden) ---");
        System.out.println(
                urgentes.estaVacia()
                        ? "(sin tareas urgentes)"
                        : urgentes.mostrar()
        );

        System.out.println("\n--- Programadas (Cola de prioridad: por urgencia) ---");
        System.out.println(
                programadas.estaVacia()
                        ? "(sin tareas programadas)"
                        : programadas.mostrar()
        );

        System.out.println("\n--- Por departamento (agrupadas) ---");
        mostrarPorDepartamento();

        System.out.println("\n======================================");
    }
}
