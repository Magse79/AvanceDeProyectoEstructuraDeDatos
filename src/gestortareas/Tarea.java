package gestortareas;

public class Tarea {

    public enum Urgencia {
        ALTA, MEDIA, BAJA
    }

    private static int contador = 1;

    private final int id;
    private final String descripcion;
    private final String departamento;
    private final Urgencia urgencia;

    public Tarea(String descripcion, String departamento, Urgencia urgencia) {
        this.id = contador++;
        this.descripcion = descripcion;
        this.departamento = departamento;
        this.urgencia = urgencia;
    }

    // Constructor privado: solo para crear una "llave de búsqueda"
    // por id (equals compara únicamente por id), sin consumir
    // un folio nuevo del contador.
    private Tarea(int id) {
        this.id = id;
        this.descripcion = null;
        this.departamento = null;
        this.urgencia = null;
    }

    public static Tarea llaveBusqueda(int id) {
        return new Tarea(id);
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getDepartamento() {
        return departamento;
    }

    public Urgencia getUrgencia() {
        return urgencia;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Tarea)) {
            return false;
        }

        return id == ((Tarea) obj).id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public String toString() {

        return "#" + id
                + " [" + urgencia + "] "
                + descripcion
                + " (Depto: " + departamento + ")";
    }
}
