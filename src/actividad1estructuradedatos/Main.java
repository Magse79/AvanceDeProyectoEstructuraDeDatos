package actividad1estructuradedatos;

import java.util.Scanner;

public class Main {

    private static final Scanner sc =
            new Scanner(System.in);

    private static LinkedList<Integer> listaEnteros;
    private static LinkedList<String> listaTexto;
    private static Contactos contactos;

    private static int tipoSeleccionado;
    private static String nombreTipoActual;

    public static void main(String[] args) {

        System.out.println(
                "======================================"
        );

        System.out.println(
                " LISTAS ENLAZADAS - PROGRAMA INTERACTIVO"
        );

        System.out.println(
                "======================================"
        );

        elegirTipoLista();
        recrearListas();

        boolean salir = false;

        while (!salir) {

            mostrarMenuPrincipal();

            int opcion =
                    leerEntero("Selecciona una opción: ");

            switch (opcion) {

                case 1 -> insertarElemento();

                case 2 -> eliminarElemento();

                case 3 -> buscarElemento();

                case 4 -> mostrarListas();

                case 5 -> DataTypeExamples.ejecutarTodos();

                case 6 -> gestionContactos();

                case 7 -> {
                    elegirTipoLista();
                    recrearListas();
                }

                case 0 -> {
                    salir = true;
                    System.out.println("¡Hasta luego!");
                }

                default ->
                    System.out.println(
                            "Opción inválida. Intenta de nuevo."
                    );
            }
        }

        sc.close();
    }

    private static void mostrarMenuPrincipal() {

        System.out.println(
                "\n--- MENÚ PRINCIPAL (tipo de lista actual: "
                + nombreTipoActual + ") ---"
        );

        System.out.println("1. Insertar elemento");
        System.out.println("2. Eliminar elemento");
        System.out.println("3. Buscar elemento");
        System.out.println("4. Mostrar listas");

        System.out.println(
                "5. Ejecutar ejemplos de tipos de datos "
                + "(primitivo/complejo/abstracto)"
        );

        System.out.println("6. Gestión de contactos");

        System.out.println(
                "7. Cambiar tipo de lista (simple/doble)"
        );

        System.out.println("0. Salir");
    }

    private static void elegirTipoLista() {

        System.out.println(
                "\nElige el tipo de lista a utilizar:"
        );

        System.out.println(
                "1. Simplemente enlazada"
        );

        System.out.println(
                "2. Doblemente enlazada"
        );

        int op = leerEntero("Opción: ");

        tipoSeleccionado =
                (op == 2) ? 2 : 1;

        nombreTipoActual =
                (tipoSeleccionado == 2)
                ? "DOBLE"
                : "SIMPLE";
    }

    private static <T> LinkedList<T> crearLista() {

        return switch (tipoSeleccionado) {

            case 2 ->
                new DoubleLinkedList<>();

            default ->
                new SimplyLinkedList<>();
        };
    }

    private static void recrearListas() {

        listaEnteros = crearLista();

        listaTexto = crearLista();

        contactos =
                new Contactos(crearLista());
    }

    private static void insertarElemento() {

        int tipoDato = elegirTipoDato();

        switch (tipoDato) {

            case 1 -> {

                int valor =
                        leerEntero(
                                "Valor entero a insertar: "
                        );

                listaEnteros.insertarFinal(valor);

                System.out.println(
                        "Insertado en la lista de enteros."
                );
            }

            case 2 -> {

                System.out.print(
                        "Texto a insertar: "
                );

                String valor = sc.nextLine();

                listaTexto.insertarFinal(valor);

                System.out.println(
                        "Insertado en la lista de texto."
                );
            }

            case 3 -> {

                DataTypeExamples.Persona p =
                        leerContacto();

                contactos.agregar(p);

                System.out.println(
                        "Contacto insertado."
                );
            }

            default ->
                System.out.println(
                        "Tipo de dato inválido."
                );
        }
    }

    private static void eliminarElemento() {

        int tipoDato = elegirTipoDato();

        boolean encontrado;

        switch (tipoDato) {

            case 1 -> {

                int valor =
                        leerEntero(
                                "Valor entero a eliminar: "
                        );

                encontrado =
                        listaEnteros.eliminar(valor);

                System.out.println(
                        encontrado
                        ? "Elemento eliminado."
                        : "No se encontró el elemento."
                );
            }

            case 2 -> {

                System.out.print(
                        "Texto a eliminar: "
                );

                String valor = sc.nextLine();

                encontrado =
                        listaTexto.eliminar(valor);

                System.out.println(
                        encontrado
                        ? "Elemento eliminado."
                        : "No se encontró el elemento."
                );
            }

            case 3 -> {

                System.out.print(
                        "Nombre del contacto a eliminar: "
                );

                String nombre = sc.nextLine();

                System.out.print(
                        "Teléfono del contacto a eliminar: "
                );

                String telefono = sc.nextLine();

                encontrado =
                        contactos.eliminar(
                                nombre,
                                telefono
                        );

                System.out.println(
                        encontrado
                        ? "Contacto eliminado."
                        : "No se encontró el contacto."
                );
            }

            default ->
                System.out.println(
                        "Tipo de dato inválido."
                );
        }
    }

    private static void buscarElemento() {

        int tipoDato = elegirTipoDato();

        boolean encontrado;

        switch (tipoDato) {

            case 1 -> {

                int valor =
                        leerEntero(
                                "Valor entero a buscar: "
                        );

                encontrado =
                        listaEnteros.buscar(valor);

                System.out.println(
                        encontrado
                        ? "El elemento SÍ está en la lista."
                        : "El elemento NO está en la lista."
                );
            }

            case 2 -> {

                System.out.print(
                        "Texto a buscar: "
                );

                String valor = sc.nextLine();

                encontrado =
                        listaTexto.buscar(valor);

                System.out.println(
                        encontrado
                        ? "El elemento SÍ está en la lista."
                        : "El elemento NO está en la lista."
                );
            }

            case 3 -> {

                System.out.print(
                        "Nombre del contacto a buscar: "
                );

                String nombre = sc.nextLine();

                System.out.print(
                        "Teléfono del contacto a buscar: "
                );

                String telefono = sc.nextLine();

                encontrado =
                        contactos.buscar(
                                nombre,
                                telefono
                        );

                System.out.println(
                        encontrado
                        ? "El contacto SÍ está en la lista."
                        : "El contacto NO está en la lista."
                );
            }

            default ->
                System.out.println(
                        "Tipo de dato inválido."
                );
        }
    }

    private static void mostrarListas() {

        System.out.println(
                "\nLista de enteros: "
                + listaEnteros.mostrar()
        );

        System.out.println(
                "Lista de texto:   "
                + listaTexto.mostrar()
        );

        System.out.println(
                "Contactos:        "
                + contactos.mostrar()
        );
    }

    private static void gestionContactos() {

        boolean volver = false;

        while (!volver) {

            System.out.println(
                    "\n--- GESTIÓN DE CONTACTOS ---"
            );

            System.out.println("1. Agregar contacto");
            System.out.println("2. Eliminar contacto");
            System.out.println("3. Buscar contacto");
            System.out.println("4. Mostrar todos los contactos");
            System.out.println("0. Volver al menú principal");

            int op =
                    leerEntero(
                            "Selecciona una opción: "
                    );

            switch (op) {

                case 1 -> {

                    DataTypeExamples.Persona p =
                            leerContacto();

                    contactos.agregar(p);

                    System.out.println(
                            "Contacto agregado con éxito."
                    );
                }

                case 2 -> {

                    System.out.print(
                            "Nombre del contacto a eliminar: "
                    );

                    String nombre = sc.nextLine();

                    System.out.print(
                            "Teléfono del contacto a eliminar: "
                    );

                    String telefono = sc.nextLine();

                    boolean ok =
                            contactos.eliminar(
                                    nombre,
                                    telefono
                            );

                    System.out.println(
                            ok
                            ? "Contacto eliminado."
                            : "No se encontró el contacto."
                    );
                }

                case 3 -> {

                    System.out.print(
                            "Nombre del contacto a buscar: "
                    );

                    String nombre = sc.nextLine();

                    System.out.print(
                            "Teléfono del contacto a buscar: "
                    );

                    String telefono = sc.nextLine();

                    boolean ok =
                            contactos.buscar(
                                    nombre,
                                    telefono
                            );

                    System.out.println(
                            ok
                            ? "El contacto SÍ está registrado."
                            : "El contacto NO está registrado."
                    );
                }

                case 4 ->
                    System.out.println(
                            "Contactos: "
                            + contactos.mostrar()
                    );

                case 0 ->
                    volver = true;

                default ->
                    System.out.println(
                            "Opción inválida."
                    );
            }
        }
    }

    private static DataTypeExamples.Persona leerContacto() {

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Dirección: ");
        String direccion = sc.nextLine();

        System.out.print("Teléfono: ");
        String telefono = sc.nextLine();

        System.out.print(
                "Fecha de nacimiento (dd/mm/aaaa): "
        );

        String fechaNacimiento =
                sc.nextLine();

        System.out.println(
                "Tipo de contacto:"
        );

        System.out.println("1. Personal");
        System.out.println("2. Laboral");

        int tipo =
                leerEntero("Opción: ");

        if (tipo == 2) {

            return new DataTypeExamples.ContactoLaboral(
                    nombre,
                    direccion,
                    telefono,
                    fechaNacimiento
            );
        }

        return new DataTypeExamples.ContactoPersonal(
                nombre,
                direccion,
                telefono,
                fechaNacimiento
        );
    }

    private static int elegirTipoDato() {

        System.out.println(
                "\n¿Con qué tipo de dato quieres trabajar?"
        );

        System.out.println(
                "1. Entero (primitivo)"
        );

        System.out.println(
                "2. Texto (String)"
        );

        System.out.println(
                "3. Contacto (complejo y abstracto)"
        );

        return leerEntero("Opción: ");
    }

    private static int leerEntero(String mensaje) {

        System.out.print(mensaje);

        while (!sc.hasNextInt()) {

            System.out.print(
                    "Por favor ingresa un número válido: "
            );

            sc.next();
        }

        int valor = sc.nextInt();

        sc.nextLine();

        return valor;
    }
}