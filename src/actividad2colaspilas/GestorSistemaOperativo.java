package actividad2colaspilas;

public class GestorSistemaOperativo {

    private final Cola<Proceso> colaListos;
    private final Pila<Proceso> pilaHistorial;
    private int tiempoTotalCPU;

    public GestorSistemaOperativo() {
        this.colaListos = new Cola<>();
        this.pilaHistorial = new Pila<>();
        this.tiempoTotalCPU = 0;
    }

    public void ingresarProceso(Proceso p) {

        colaListos.enqueue(p);

        System.out.println(
                "[INGRESO] "
                + p
                + " entró a la cola de listos."
        );
    }

    public Proceso ejecutarSiguiente() {

        if (colaListos.estaVacia()) {

            System.out.println(
                    "[CPU] No hay procesos en espera."
            );

            return null;
        }

        Proceso p = colaListos.dequeue();

        tiempoTotalCPU += p.getRafaga();

        pilaHistorial.push(p);

        System.out.println(
                "[CPU] Ejecutando "
                + p
                + " -> completado. Tiempo total de CPU: "
                + tiempoTotalCPU
        );

        return p;
    }

    public Proceso deshacerUltimaEjecucion() {

        if (pilaHistorial.estaVacia()) {

            System.out.println(
                    "[UNDO] No hay procesos en el historial."
            );

            return null;
        }

        Proceso p = pilaHistorial.pop();

        tiempoTotalCPU -= p.getRafaga();

        colaListos.enqueue(p);

        System.out.println(
                "[UNDO] "
                + p
                + " regresó a la cola de listos."
        );

        return p;
    }

    public Proceso verSiguienteProceso() {

        if (colaListos.estaVacia()) {
            return null;
        }

        return colaListos.peek();
    }

    public Proceso verUltimoEjecutado() {

        if (pilaHistorial.estaVacia()) {
            return null;
        }

        return pilaHistorial.peek();
    }

    public void mostrarEstado() {

        System.out.println(
                "Cola de listos:    "
                + colaListos.mostrar()
        );

        System.out.println(
                "Historial (pila):  "
                + pilaHistorial.mostrar()
        );

        System.out.println(
                "Tiempo total CPU:  "
                + tiempoTotalCPU
        );
    }

    public int getTiempoTotalCPU() {
        return tiempoTotalCPU;
    }

    public int procesosEnEspera() {
        return colaListos.tamano();
    }

    public int procesosEnHistorial() {
        return pilaHistorial.tamano();
    }
}