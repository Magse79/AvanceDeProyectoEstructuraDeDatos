package actividad2colaspilas;

public class Main {
    public static void main(String[] args) {
        GestorSistemaOperativo gestor = new GestorSistemaOperativo();

        gestor.ingresarProceso(new Proceso(1, "Navegador", 5));
        gestor.ingresarProceso(new Proceso(2, "Editor", 3));

        gestor.ejecutarSiguiente();
        gestor.mostrarEstado();
    }
}