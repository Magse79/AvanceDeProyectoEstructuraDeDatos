package actividad1estructuradedatos;

public class Contactos {

    private final LinkedList<DataTypeExamples.Persona> personas;

    public Contactos(
            LinkedList<DataTypeExamples.Persona> listaBase) {

        this.personas = listaBase;
    }

    public void agregar(DataTypeExamples.Persona persona) {
        personas.insertarFinal(persona);
    }

    public boolean eliminar(
            String nombre,
            String telefono) {

        return personas.eliminar(
                new DataTypeExamples.ContactoPersonal(
                        nombre,
                        "",
                        telefono,
                        ""
                )
        );
    }

    public boolean buscar(
            String nombre,
            String telefono) {

        return personas.buscar(
                new DataTypeExamples.ContactoPersonal(
                        nombre,
                        "",
                        telefono,
                        ""
                )
        );
    }

    public int totalContactos() {
        return personas.tamano();
    }

    public boolean estaVacio() {
        return personas.estaVacia();
    }

    public String mostrar() {
        return personas.mostrar();
    }
}