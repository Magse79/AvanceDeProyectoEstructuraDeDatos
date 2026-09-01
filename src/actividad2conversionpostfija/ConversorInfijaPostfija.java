package actividad2conversionpostfija;

import actividad1estructuradedatos.SimplyLinkedList;
import actividad2colaspilas.Pila;

public final class ConversorInfijaPostfija {

    private ConversorInfijaPostfija() {
    }

    private static int pesoEntrada(char c) {

        return switch (c) {

            case '^' -> 4;

            case '*', '/' -> 2;

            case '+', '-' -> 1;

            case '(' -> 5;

            default -> -1;
        };
    }

    private static int pesoPila(char c) {

        return switch (c) {

            case '^' -> 3;

            case '*', '/' -> 2;

            case '+', '-' -> 1;

            case '(' -> 0;

            default -> -1;
        };
    }

    private static boolean esOperador(char c) {

        return c == '+'
                || c == '-'
                || c == '*'
                || c == '/'
                || c == '^';
    }

    public static SimplyLinkedList<Character> convertir(
            char[] expresionInfija) {

        Pila<Character> pilaOperadores =
                new Pila<>();

        SimplyLinkedList<Character> postfija =
                new SimplyLinkedList<>();

        for (char c : expresionInfija) {

            if (Character.isWhitespace(c)) {

                continue;
            }

            if (Character.isDigit(c)) {

                postfija.insertarFinal(c);

            } else if (c == '(') {

                pilaOperadores.push(c);

            } else if (c == ')') {

                while (
                        !pilaOperadores.estaVacia()
                        && pilaOperadores.peek() != '('
                ) {

                    postfija.insertarFinal(
                            pilaOperadores.pop()
                    );
                }

                if (!pilaOperadores.estaVacia()) {

                    pilaOperadores.pop();
                }

            } else if (esOperador(c)) {

                while (
                        !pilaOperadores.estaVacia()
                        && pesoEntrada(c)
                        <= pesoPila(
                                pilaOperadores.peek()
                        )
                ) {

                    postfija.insertarFinal(
                            pilaOperadores.pop()
                    );
                }

                pilaOperadores.push(c);

            } else {

                throw new IllegalArgumentException(
                        "Carácter no soportado en la expresión: '"
                        + c
                        + "'"
                );
            }
        }

        while (!pilaOperadores.estaVacia()) {

            postfija.insertarFinal(
                    pilaOperadores.pop()
            );
        }

        return postfija;
    }
}