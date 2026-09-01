package actividad2conversionpostfija;

import actividad1estructuradedatos.SimplyLinkedList;

public class Main {

    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println("   CONVERSOR DE INFija A POSTFIJA");
        System.out.println("======================================");

        char[] expresion = {
            '2', '+', '3', '*', '(', '4', '-', '1', ')'
        };

        System.out.print("Expresión infija: ");

        for (char c : expresion) {
            System.out.print(c);
        }

        System.out.println();

        SimplyLinkedList<Character> resultado =
                ConversorInfijaPostfija.convertir(expresion);

        System.out.print("Expresión postfija: ");

        while (!resultado.estaVacia()) {
            System.out.print(resultado.eliminarInicio());
        }

        System.out.println();

        System.out.println("======================================");
    }
}