package actividad2conversionpostfija;

import actividad1estructuradedatos.SimplyLinkedList;

public class TestConversorPOstfijo {

    private static int pruebasOk = 0;
    private static int pruebasTotal = 0;

    public static void main(String[] args) {

        probar(
                "Prueba 1 - 2 + 3 * 4",

                new char[]{
                    '2', '+', '3', '*', '4'
                },

                new char[]{
                    '2', '3', '4', '*', '+'
                }
        );

        probar(
                "Prueba 2 - (2 + 3) * 4",

                new char[]{
                    '(', '2', '+', '3', ')',
                    '*', '4'
                },

                new char[]{
                    '2', '3', '+', '4', '*'
                }
        );

        probar(
                "Prueba 3 - 2 + 3 * (4 - 1)",

                new char[]{
                    '2', '+', '3', '*',
                    '(', '4', '-', '1', ')'
                },

                new char[]{
                    '2', '3', '4', '1',
                    '-', '*', '+'
                }
        );

        probar(
                "Prueba 4 - 8 - 2 + 1",

                new char[]{
                    '8', '-', '2', '+', '1'
                },

                new char[]{
                    '8', '2', '-', '1', '+'
                }
        );

        probar(
                "Prueba 5 - (2+3)*(4-1)",

                new char[]{
                    '(', '2', '+', '3', ')',
                    '*',
                    '(', '4', '-', '1', ')'
                },

                new char[]{
                    '2', '3', '+',
                    '4', '1', '-', '*'
                }
        );

        probar(
                "Prueba 6 - 2 ^ 3 * 4",

                new char[]{
                    '2', '^', '3', '*', '4'
                },

                new char[]{
                    '2', '3', '^', '4', '*'
                }
        );

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

    private static void probar(
            String descripcion,
            char[] infija,
            char[] postfijaEsperada) {

        pruebasTotal++;

        SimplyLinkedList<Character> resultado =
                ConversorInfijaPostfija.convertir(
                        infija
                );

        System.out.println(
                "\n" + descripcion
        );

        System.out.print(
                "Infija: "
        );

        mostrarArreglo(infija);

        System.out.print(
                "Postfija esperada: "
        );

        mostrarArreglo(postfijaEsperada);

        System.out.print(
                "Postfija obtenida: "
        );

        boolean correcta =
                mostrarYCompararLista(
                        resultado,
                        postfijaEsperada
                );

        if (correcta) {

            pruebasOk++;

            System.out.println(
                    "[OK]"
            );

        } else {

            System.out.println(
                    "[FAIL]"
            );
        }
    }

    private static void mostrarArreglo(
            char[] arreglo) {

        for (int i = 0;
                i < arreglo.length;
                i++) {

            System.out.print(
                    arreglo[i]
            );

            if (i < arreglo.length - 1) {

                System.out.print(
                        " -> "
                );
            }
        }

        System.out.println();
    }

    private static boolean mostrarYCompararLista(
            SimplyLinkedList<Character> lista,
            char[] esperado) {

        int cantidad =
                lista.tamano();

        boolean correcta =
                (cantidad == esperado.length);

        for (int i = 0;
                i < cantidad;
                i++) {

            Character dato =
                    lista.eliminarInicio();

            System.out.print(
                    dato
            );

            if (i < cantidad - 1) {

                System.out.print(
                        " -> "
                );
            }

            if (i >= esperado.length
                    || dato == null
                    || dato != esperado[i]) {

                correcta = false;
            }
        }

        System.out.println();

        return correcta;
    }
}