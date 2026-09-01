package gestortareas;

import actividad2colaspilas.EstructuraVaciaException;

public class TestGestorTareas {

    private static int pruebasOk = 0;
    private static int pruebasTotal = 0;

    public static void main(String[] args) {

        probarColaPrioridad();

        probarUrgentes();

        probarProgramadas();

        probarPorDepartamento();

        probarReporteGeneral();

        System.out.println(
                "\n======================================"
        );

        System.out.println(
                "RESULTADO: "
                + pruebasOk
                + "/"
                + pruebasTotal
                + " pruebas superadas"
        );

        System.out.println(
                "======================================"
        );
    }

    // =================== COLA DE PRIORIDAD (aislada) ===================

    private static void probarColaPrioridad() {

        System.out.println(
                "=== PRUEBAS: ColaPrioridad<Integer> (menor número = mayor prioridad) ==="
        );

        ColaPrioridad<Integer> cp =
                new ColaPrioridad<>((a, b) -> a - b);

        verificar(
                "cola de prioridad nueva está vacía",
                cp.estaVacia()
        );

        cp.enqueue(5);
        cp.enqueue(1);
        cp.enqueue(3);
        cp.enqueue(1);

        System.out.println(
                "Estado tras enqueue 5,1,3,1: "
                + cp.mostrar()
        );

        verificar(
                "tamaño tras 4 enqueue = 4",
                cp.tamano() == 4
        );

        verificar(
                "front devuelve el de mayor prioridad (1, el primero de los dos 1)",
                cp.front() == 1
        );

        int r1 = cp.dequeue();

        verificar(
                "dequeue devuelve el primer 1 agregado",
                r1 == 1
        );

        int r2 = cp.dequeue();

        verificar(
                "dequeue devuelve el segundo 1 (mismo valor, respeta orden de llegada)",
                r2 == 1
        );

        int r3 = cp.dequeue();

        verificar(
                "dequeue devuelve 3",
                r3 == 3
        );

        int r4 = cp.dequeue();

        verificar(
                "dequeue devuelve 5 al final",
                r4 == 5
        );

        verificar(
                "cola de prioridad vacía tras retirar todo",
                cp.estaVacia()
        );

        boolean lanzoExcepcion = false;

        try {

            cp.dequeue();

        } catch (EstructuraVaciaException e) {

            lanzoExcepcion = true;

            System.out.println(
                    "Excepción esperada al hacer dequeue en cola de prioridad vacía: "
                    + e.getMessage()
            );
        }

        verificar(
                "dequeue en cola de prioridad vacía lanza EstructuraVaciaException",
                lanzoExcepcion
        );
    }

    // ===================== PILA: URGENTES =====================

    private static void probarUrgentes() {

        System.out.println(
                "=== PRUEBAS: Tareas urgentes (Pila) ==="
        );

        GestorTareas gestor = new GestorTareas();

        verificar(
                "sin tareas urgentes al inicio",
                !gestor.hayUrgentes()
        );

        Tarea t1 = new Tarea("Reiniciar servidor", "TI", Tarea.Urgencia.ALTA);
        Tarea t2 = new Tarea("Atender cliente molesto", "Ventas", Tarea.Urgencia.ALTA);
        Tarea t3 = new Tarea("Revisar fuga de agua", "Mantenimiento", Tarea.Urgencia.ALTA);

        gestor.agregarUrgente(t1);
        gestor.agregarUrgente(t2);
        gestor.agregarUrgente(t3);

        System.out.println(
                "Estado tras agregar 3 urgentes: "
                + gestor.mostrarUrgentes()
        );

        verificar(
                "hay urgentes tras agregar 3",
                gestor.hayUrgentes()
        );

        verificar(
                "verProximaUrgente devuelve la última agregada (t3, LIFO)",
                t3.equals(gestor.verProximaUrgente())
        );

        Tarea atendida1 = gestor.atenderUrgente();

        verificar(
                "atenderUrgente devuelve t3 (LIFO)",
                t3.equals(atendida1)
        );

        Tarea atendida2 = gestor.atenderUrgente();

        verificar(
                "atenderUrgente devuelve t2 en segundo lugar",
                t2.equals(atendida2)
        );

        gestor.atenderUrgente();

        verificar(
                "no quedan urgentes tras atender las 3",
                !gestor.hayUrgentes()
        );

        boolean lanzoExcepcion = false;

        try {

            gestor.atenderUrgente();

        } catch (EstructuraVaciaException e) {

            lanzoExcepcion = true;

            System.out.println(
                    "Excepción esperada al atender urgente en pila vacía: "
                    + e.getMessage()
            );
        }

        verificar(
                "atenderUrgente en pila vacía lanza EstructuraVaciaException",
                lanzoExcepcion
        );
    }

    // =================== COLA: PROGRAMADAS ====================

    private static void probarProgramadas() {

        System.out.println(
                "\n=== PRUEBAS: Tareas programadas (Cola de prioridad) ==="
        );

        GestorTareas gestor = new GestorTareas();

        verificar(
                "sin tareas programadas al inicio",
                !gestor.hayProgramadas()
        );

        // Se agregan fuera de orden de urgencia a propósito, para
        // comprobar que la cola de prioridad reordena por urgencia
        // y no por orden de llegada.
        Tarea media1 = new Tarea("Backup semanal", "TI", Tarea.Urgencia.MEDIA);
        Tarea baja1 = new Tarea("Mantenimiento de aires", "Mantenimiento", Tarea.Urgencia.BAJA);
        Tarea alta1 = new Tarea("Falla en facturación", "Finanzas", Tarea.Urgencia.ALTA);
        Tarea media2 = new Tarea("Reporte de ventas", "Ventas", Tarea.Urgencia.MEDIA);

        gestor.agregarProgramada(media1);
        gestor.agregarProgramada(baja1);
        gestor.agregarProgramada(alta1);
        gestor.agregarProgramada(media2);

        System.out.println(
                "Estado tras agregar media1, baja1, alta1, media2: "
                + gestor.mostrarProgramadas()
        );

        verificar(
                "verProximaProgramada devuelve la de mayor urgencia (alta1), no la primera en llegar",
                alta1.equals(gestor.verProximaProgramada())
        );

        Tarea atendida1 = gestor.atenderProgramada();

        verificar(
                "atenderProgramada devuelve alta1 primero (mayor urgencia)",
                alta1.equals(atendida1)
        );

        Tarea atendida2 = gestor.atenderProgramada();

        verificar(
                "atenderProgramada devuelve media1 en segundo lugar (llegó antes que media2, misma urgencia)",
                media1.equals(atendida2)
        );

        Tarea atendida3 = gestor.atenderProgramada();

        verificar(
                "atenderProgramada devuelve media2 en tercer lugar",
                media2.equals(atendida3)
        );

        Tarea atendida4 = gestor.atenderProgramada();

        verificar(
                "atenderProgramada devuelve baja1 al final (menor urgencia)",
                baja1.equals(atendida4)
        );

        verificar(
                "no quedan programadas tras atender las 4",
                !gestor.hayProgramadas()
        );

        boolean lanzoExcepcion = false;

        try {

            gestor.atenderProgramada();

        } catch (EstructuraVaciaException e) {

            lanzoExcepcion = true;

            System.out.println(
                    "Excepción esperada al atender programada en cola vacía: "
                    + e.getMessage()
            );
        }

        verificar(
                "atenderProgramada en cola de prioridad vacía lanza EstructuraVaciaException",
                lanzoExcepcion
        );
    }

    // =================== LISTA: DEPARTAMENTO ===================

    private static void probarPorDepartamento() {

        System.out.println(
                "\n=== PRUEBAS: Tareas por departamento (Lista) ==="
        );

        GestorTareas gestor = new GestorTareas();

        verificar(
                "sin tareas por departamento al inicio",
                !gestor.hayPorDepartamento()
        );

        Tarea t1 = new Tarea("Actualizar antivirus", "TI", Tarea.Urgencia.MEDIA);
        Tarea t2 = new Tarea("Cerrar trimestre", "Ventas", Tarea.Urgencia.ALTA);
        Tarea t3 = new Tarea("Revisar contratos", "TI", Tarea.Urgencia.BAJA);

        gestor.agregarPorDepartamento(t1);
        gestor.agregarPorDepartamento(t2);
        gestor.agregarPorDepartamento(t3);

        verificar(
                "hay tareas por departamento tras agregar 3",
                gestor.hayPorDepartamento()
        );

        Tarea encontrada = gestor.buscarPorDepartamento(t2.getId());

        verificar(
                "buscarPorDepartamento encuentra t2 por su id",
                t2.equals(encontrada)
        );

        Tarea noEncontrada = gestor.buscarPorDepartamento(9999);

        verificar(
                "buscarPorDepartamento con id inexistente devuelve null",
                noEncontrada == null
        );

        System.out.println("Vista agrupada por departamento:");
        gestor.mostrarPorDepartamento();

        boolean eliminada = gestor.eliminarPorDepartamento(t1.getId());

        verificar(
                "eliminarPorDepartamento elimina t1 correctamente",
                eliminada
        );

        verificar(
                "t1 ya no se encuentra tras eliminarla",
                gestor.buscarPorDepartamento(t1.getId()) == null
        );

        boolean eliminacionInexistente = gestor.eliminarPorDepartamento(9999);

        verificar(
                "eliminarPorDepartamento con id inexistente devuelve false",
                !eliminacionInexistente
        );

        verificar(
                "siguen quedando tareas por departamento (t2 y t3)",
                gestor.hayPorDepartamento()
        );
    }

    // ===================== REPORTE GENERAL =====================

    private static void probarReporteGeneral() {

        System.out.println(
                "\n=== PRUEBAS: Reporte general (mostrarTodasLasTareasPendientes) ==="
        );

        GestorTareas gestor = new GestorTareas();

        gestor.agregarUrgente(
                new Tarea("Falla eléctrica", "Mantenimiento", Tarea.Urgencia.ALTA)
        );

        gestor.agregarProgramada(
                new Tarea("Capacitación mensual", "RH", Tarea.Urgencia.MEDIA)
        );

        gestor.agregarPorDepartamento(
                new Tarea("Actualizar organigrama", "RH", Tarea.Urgencia.BAJA)
        );

        // No debe lanzar ninguna excepción al combinar las tres
        // estructuras (pila, cola y lista) en un mismo reporte.
        gestor.mostrarTodasLasTareasPendientes();

        verificar(
                "el reporte general se genera sin errores con datos en las 3 estructuras",
                true
        );
    }

    private static void verificar(
            String descripcion,
            boolean condicion) {

        pruebasTotal++;

        if (condicion) {

            pruebasOk++;

            System.out.println(
                    "  [OK]   "
                    + descripcion
            );

        } else {

            System.out.println(
                    "  [FAIL] "
                    + descripcion
            );
        }
    }
}
