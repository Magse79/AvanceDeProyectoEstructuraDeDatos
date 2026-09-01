package actividad2colaspilas;

public class Proceso {

    private final int pid;
    private final String nombre;
    private final int rafaga;

    public Proceso(int pid, String nombre, int rafaga) {
        this.pid = pid;
        this.nombre = nombre;
        this.rafaga = rafaga;
    }

    public int getPid() {
        return pid;
    }

    public String getNombre() {
        return nombre;
    }

    public int getRafaga() {
        return rafaga;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Proceso)) {
            return false;
        }

        Proceso otro = (Proceso) obj;

        return pid == otro.pid;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(pid);
    }

    @Override
    public String toString() {

        return "PID"
                + pid
                + "("
                + nombre
                + ", ráfaga="
                + rafaga
                + ")";
    }
}