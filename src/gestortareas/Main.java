package gestortareas;

import actividad2colaspilas.EstructuraVaciaException;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final GestorTareas gestor = new GestorTareas();

    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println(" SISTEMA DE GESTIÓN DE TAREAS");
        System.out.println("======================================");

        boolean salir = false;

        while (!salir) {

            mostrarMenuPrincipal();

            int opcion = leerEntero("Selecciona una opción: ");

            switch (opcion) {

                case 1 -> menuUrgentes();

                case 2 -> menuProgramadas();

                case 3 -> menuPorDepartamento();

                case 4 -> gestor.mostrarTodasLasTareasPendientes();

                case 0 -> {
                    salir = true;
                    System.out.println("¡Hasta luego!");
                }

                default -> System.out.println("Opción inválida. Intenta de nuevo.");
            }
        }

        sc.close();
    }

    private static void mostrarMenuPrincipal() {

        System.out.println("\n===== MENÚ PRINCIPAL =====");
        System.out.println("1. Tareas urgentes (Pila)");
        System.out.println("2. Tareas programadas (Cola de prioridad)");
        System.out.println("3. Tareas por departamento (Lista)");
        System.out.println("4. Ver todas las tareas pendientes");
        System.out.println("0. Salir");
    }

    // ========================= PILA =========================

    private static void menuUrgentes() {

        boolean volver = false;

        while (!volver) {

            System.out.println("\n--- TAREAS URGENTES (Pila) ---");
            System.out.println("1. Agregar tarea urgente (push)");
            System.out.println("2. Atender siguiente tarea urgente (pop)");
            System.out.println("3. Ver la tarea más urgente sin atenderla (peek)");
            System.out.println("4. Ver todas las tareas urgentes");
            System.out.println("0. Volver al menú principal");

            int opcion = leerEntero("Selecciona una opción: ");

            switch (opcion) {

                case 1 -> {
                    Tarea t = leerDatosTarea();
                    gestor.agregarUrgente(t);
                    System.out.println("Tarea urgente agregada: " + t);
                }

                case 2 -> {

                    try {
                        Tarea atendida = gestor.atenderUrgente();
                        System.out.println("Tarea atendida: " + atendida);
                    } catch (EstructuraVaciaException e) {
                        System.out.println(e.getMessage());
                    }
                }

                case 3 -> {

                    try {
                        System.out.println(
                                "Próxima tarea urgente: "
                                + gestor.verProximaUrgente()
                        );
                    } catch (EstructuraVaciaException e) {
                        System.out.println(e.getMessage());
                    }
                }

                case 4 -> System.out.println(
                        "Tareas urgentes: " + gestor.mostrarUrgentes()
                );

                case 0 -> volver = true;

                default -> System.out.println("Opción inválida.");
            }
        }
    }

    // ========================= COLA =========================

    private static void menuProgramadas() {

        boolean volver = false;

        while (!volver) {

            System.out.println("\n--- TAREAS PROGRAMADAS (Cola de prioridad) ---");
            System.out.println("1. Agregar tarea programada (enqueue)");
            System.out.println("2. Atender siguiente tarea programada por urgencia (dequeue)");
            System.out.println("3. Ver la próxima tarea a atender (front)");
            System.out.println("4. Ver todas las tareas programadas");
            System.out.println("0. Volver al menú principal");

            int opcion = leerEntero("Selecciona una opción: ");

            switch (opcion) {

                case 1 -> {
                    Tarea t = leerDatosTarea();
                    gestor.agregarProgramada(t);
                    System.out.println("Tarea programada agregada: " + t);
                }

                case 2 -> {

                    try {
                        Tarea atendida = gestor.atenderProgramada();
                        System.out.println("Tarea atendida: " + atendida);
                    } catch (EstructuraVaciaException e) {
                        System.out.println(e.getMessage());
                    }
                }

                case 3 -> {

                    try {
                        System.out.println(
                                "Próxima tarea programada: "
                                + gestor.verProximaProgramada()
                        );
                    } catch (EstructuraVaciaException e) {
                        System.out.println(e.getMessage());
                    }
                }

                case 4 -> System.out.println(
                        "Tareas programadas: " + gestor.mostrarProgramadas()
                );

                case 0 -> volver = true;

                default -> System.out.println("Opción inválida.");
            }
        }
    }

    // ========================= LISTA =========================

    private static void menuPorDepartamento() {

        boolean volver = false;

        while (!volver) {

            System.out.println("\n--- TAREAS POR DEPARTAMENTO (Lista) ---");
            System.out.println("1. Agregar tarea (insert)");
            System.out.println("2. Eliminar tarea por id (delete)");
            System.out.println("3. Buscar tarea por id (find)");
            System.out.println("4. Ver todas, agrupadas por departamento");
            System.out.println("0. Volver al menú principal");

            int opcion = leerEntero("Selecciona una opción: ");

            switch (opcion) {

                case 1 -> {
                    Tarea t = leerDatosTarea();
                    gestor.agregarPorDepartamento(t);
                    System.out.println(
                            "Tarea agregada con id " + t.getId() + ": " + t
                    );
                }

                case 2 -> {

                    int id = leerEntero("Id de la tarea a eliminar: ");

                    boolean ok = gestor.eliminarPorDepartamento(id);

                    System.out.println(
                            ok
                                    ? "Tarea eliminada."
                                    : "No se encontró una tarea con ese id."
                    );
                }

                case 3 -> {

                    int id = leerEntero("Id de la tarea a buscar: ");

                    Tarea encontrada = gestor.buscarPorDepartamento(id);

                    System.out.println(
                            encontrada != null
                                    ? "Tarea encontrada: " + encontrada
                                    : "No se encontró una tarea con ese id."
                    );
                }

                case 4 -> gestor.mostrarPorDepartamento();

                case 0 -> volver = true;

                default -> System.out.println("Opción inválida.");
            }
        }
    }

    // ===================== UTILIDADES =====================

    private static Tarea leerDatosTarea() {

        System.out.print("Descripción: ");
        String descripcion = sc.nextLine();

        System.out.print("Departamento: ");
        String departamento = sc.nextLine();

        Tarea.Urgencia urgencia = leerUrgencia();

        return new Tarea(descripcion, departamento, urgencia);
    }

    /**
     * Lee la urgencia como TEXTO (Alta / Media / Baja), no como
     * número. Se valida contra las tres palabras permitidas, sin
     * distinguir mayúsculas/minúsculas ni acentos, y se vuelve a
     * pedir mientras la palabra no sea válida.
     */
    private static Tarea.Urgencia leerUrgencia() {

        while (true) {

            System.out.print("Urgencia (Alta / Media / Baja): ");

            String texto = sc.nextLine().trim();
            String normalizado = quitarAcentos(texto).toUpperCase();

            switch (normalizado) {
                case "ALTA":
                    return Tarea.Urgencia.ALTA;
                case "MEDIA":
                    return Tarea.Urgencia.MEDIA;
                case "BAJA":
                    return Tarea.Urgencia.BAJA;
                default:
                    System.out.println(
                            "Valor no válido. Escribe exactamente: Alta, Media o Baja."
                    );
            }
        }
    }

    private static String quitarAcentos(String texto) {

        return texto
                .replace("á", "a").replace("Á", "A")
                .replace("é", "e").replace("É", "E")
                .replace("í", "i").replace("Í", "I")
                .replace("ó", "o").replace("Ó", "O")
                .replace("ú", "u").replace("Ú", "U");
    }

    private static int leerEntero(String mensaje) {

        System.out.print(mensaje);

        while (!sc.hasNextInt()) {
            System.out.print("Por favor ingresa un número válido: ");
            sc.next();
        }

        int valor = sc.nextInt();
        sc.nextLine();

        return valor;
    }
}
