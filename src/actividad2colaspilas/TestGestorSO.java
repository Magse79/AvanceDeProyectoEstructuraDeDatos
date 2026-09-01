package actividad2colaspilas;

public class TestGestorSO {

    private static int pruebasOk = 0;
    private static int pruebasTotal = 0;

    public static void main(String[] args) {

        probarPila();

        probarCola();

        probarGestorSistemaOperativo();

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

    private static void probarPila() {

        System.out.println(
                "=== PRUEBAS: Pila<Integer> ==="
        );

        Pila<Integer> pila = new Pila<>();

        verificar(
                "pila nueva está vacía",
                pila.estaVacia()
        );

        pila.push(1);
        pila.push(2);
        pila.push(3);

        System.out.println(
                "Estado tras push 1,2,3: "
                + pila.mostrar()
        );

        verificar(
                "tamaño tras 3 push = 3",
                pila.tamano() == 3
        );

        verificar(
                "peek devuelve el último insertado (3)",
                pila.peek() == 3
        );

        verificar(
                "peek no modifica el tamaño",
                pila.tamano() == 3
        );

        int r1 = pila.pop();

        verificar(
                "pop devuelve 3 (LIFO)",
                r1 == 3
        );

        int r2 = pila.pop();

        verificar(
                "pop devuelve 2",
                r2 == 2
        );

        verificar(
                "tamaño tras 2 pops = 1",
                pila.tamano() == 1
        );

        pila.pop();

        verificar(
                "pila vacía tras retirar todo",
                pila.estaVacia()
        );

        boolean lanzoExcepcion = false;

        try {

            pila.pop();

        } catch (EstructuraVaciaException e) {

            lanzoExcepcion = true;

            System.out.println(
                    "Excepción esperada al hacer pop en vacío: "
                    + e.getMessage()
            );
        }

        verificar(
                "pop en pila vacía lanza EstructuraVaciaException",
                lanzoExcepcion
        );

        boolean lanzoExcepcionPeek = false;

        try {

            pila.peek();

        } catch (EstructuraVaciaException e) {

            lanzoExcepcionPeek = true;
        }

        verificar(
                "peek en pila vacía lanza EstructuraVaciaException",
                lanzoExcepcionPeek
        );
    }

    private static void probarCola() {

        System.out.println(
                "\n=== PRUEBAS: Cola<String> ==="
        );

        Cola<String> cola = new Cola<>();

        verificar(
                "cola nueva está vacía",
                cola.estaVacia()
        );

        cola.enqueue("A");
        cola.enqueue("B");
        cola.enqueue("C");

        System.out.println(
                "Estado tras enqueue A,B,C: "
                + cola.mostrar()
        );

        verificar(
                "tamaño tras 3 enqueue = 3",
                cola.tamano() == 3
        );

        verificar(
                "peek devuelve el primero insertado (A)",
                cola.peek().equals("A")
        );

        verificar(
                "peek no modifica el tamaño",
                cola.tamano() == 3
        );

        String r1 = cola.dequeue();

        verificar(
                "dequeue devuelve A (FIFO)",
                r1.equals("A")
        );

        String r2 = cola.dequeue();

        verificar(
                "dequeue devuelve B",
                r2.equals("B")
        );

        verificar(
                "tamaño tras 2 dequeue = 1",
                cola.tamano() == 1
        );

        cola.dequeue();

        verificar(
                "cola vacía tras retirar todo",
                cola.estaVacia()
        );

        boolean lanzoExcepcion = false;

        try {

            cola.dequeue();

        } catch (EstructuraVaciaException e) {

            lanzoExcepcion = true;

            System.out.println(
                    "Excepción esperada al hacer dequeue en vacío: "
                    + e.getMessage()
            );
        }

        verificar(
                "dequeue en cola vacía lanza EstructuraVaciaException",
                lanzoExcepcion
        );
    }

    private static void probarGestorSistemaOperativo() {

        System.out.println(
                "\n=== PRUEBAS: GestorSistemaOperativo ==="
        );

        GestorSistemaOperativo so =
                new GestorSistemaOperativo();

        Proceso p1 =
                new Proceso(
                        1,
                        "explorer.exe",
                        5
                );

        Proceso p2 =
                new Proceso(
                        2,
                        "navegador.exe",
                        8
                );

        Proceso p3 =
                new Proceso(
                        3,
                        "editor.exe",
                        3
                );

        so.ingresarProceso(p1);

        so.ingresarProceso(p2);

        so.ingresarProceso(p3);

        so.mostrarEstado();

        verificar(
                "3 procesos en espera tras ingresar 3",
                so.procesosEnEspera() == 3
        );

        verificar(
                "el siguiente en ejecutarse es p1 (FCFS)",
                p1.equals(
                        so.verSiguienteProceso()
                )
        );

        Proceso ejecutado1 =
                so.ejecutarSiguiente();

        verificar(
                "se ejecutó p1 primero",
                p1.equals(ejecutado1)
        );

        verificar(
                "quedan 2 procesos en espera",
                so.procesosEnEspera() == 2
        );

        verificar(
                "historial tiene 1 proceso",
                so.procesosEnHistorial() == 1
        );

        verificar(
                "tiempo total de CPU = 5",
                so.getTiempoTotalCPU() == 5
        );

        Proceso ejecutado2 =
                so.ejecutarSiguiente();

        verificar(
                "se ejecutó p2 en segundo lugar",
                p2.equals(ejecutado2)
        );

        verificar(
                "tiempo total de CPU = 13",
                so.getTiempoTotalCPU() == 13
        );

        verificar(
                "el último ejecutado es p2",
                p2.equals(
                        so.verUltimoEjecutado()
                )
        );

        so.mostrarEstado();

        Proceso deshecho =
                so.deshacerUltimaEjecucion();

        verificar(
                "se deshizo la ejecución de p2",
                p2.equals(deshecho)
        );

        verificar(
                "p2 volvió a la cola de listos",
                so.procesosEnEspera() == 2
        );

        verificar(
                "tiempo total de CPU vuelve a 5",
                so.getTiempoTotalCPU() == 5
        );

        verificar(
                "historial tiene a p1",
                p1.equals(
                        so.verUltimoEjecutado()
                )
        );

        so.mostrarEstado();

        so.ejecutarSiguiente();

        so.ejecutarSiguiente();

        verificar(
                "cola de listos queda vacía",
                so.procesosEnEspera() == 0
        );

        Proceso sinProceso =
                so.ejecutarSiguiente();

        verificar(
                "ejecutar con cola vacía devuelve null",
                sinProceso == null
        );

        so.mostrarEstado();
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