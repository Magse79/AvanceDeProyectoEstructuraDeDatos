package actividad1estructuradedatos;

public class DataTypeExamples {

    public abstract static class Persona {

        private String nombre;
        private String direccion;
        private String telefono;
        private String fechaNacimiento;

        public Persona(
                String nombre,
                String direccion,
                String telefono,
                String fechaNacimiento) {

            this.nombre = nombre;
            this.direccion = direccion;
            this.telefono = telefono;
            this.fechaNacimiento = fechaNacimiento;
        }

        public String getNombre() {
            return nombre;
        }

        public String getDireccion() {
            return direccion;
        }

        public String getTelefono() {
            return telefono;
        }

        public String getFechaNacimiento() {
            return fechaNacimiento;
        }

        public abstract String getTipoContacto();

        @Override
        public boolean equals(Object obj) {

            if (this == obj) {
                return true;
            }

            if (!(obj instanceof Persona)) {
                return false;
            }

            Persona otra = (Persona) obj;

            return nombre.equalsIgnoreCase(otra.nombre)
                    && telefono.equals(otra.telefono);
        }

        @Override
        public String toString() {

            return "[" + getTipoContacto() + "] "
                    + nombre + " | "
                    + direccion + " | "
                    + telefono
                    + " | Nac: " + fechaNacimiento;
        }
    }

    public static class ContactoPersonal extends Persona {

        public ContactoPersonal(
                String nombre,
                String direccion,
                String telefono,
                String fechaNacimiento) {

            super(nombre, direccion, telefono, fechaNacimiento);
        }

        @Override
        public String getTipoContacto() {
            return "Personal";
        }
    }

    public static class ContactoLaboral extends Persona {

        public ContactoLaboral(
                String nombre,
                String direccion,
                String telefono,
                String fechaNacimiento) {

            super(nombre, direccion, telefono, fechaNacimiento);
        }

        @Override
        public String getTipoContacto() {
            return "Laboral";
        }
    }

    public abstract static class Figura {

        public abstract double area();

        public abstract String getNombre();

        @Override
        public String toString() {
            return String.format(
                    "%s (área = %.2f)",
                    getNombre(),
                    area()
            );
        }
    }

    public static class Circulo extends Figura {

        private final double radio;

        public Circulo(double radio) {
            this.radio = radio;
        }

        @Override
        public double area() {
            return Math.PI * radio * radio;
        }

        @Override
        public String getNombre() {
            return "Círculo";
        }
    }

    public static class Rectangulo extends Figura {

        private final double base;
        private final double altura;

        public Rectangulo(double base, double altura) {
            this.base = base;
            this.altura = altura;
        }

        @Override
        public double area() {
            return base * altura;
        }

        @Override
        public String getNombre() {
            return "Rectángulo";
        }
    }

    public static void ejemploPrimitivos() {

        System.out.println(
                "\n--- Ejemplo con tipos PRIMITIVOS ---"
        );

        SimplyLinkedList<Integer> enteros =
                new SimplyLinkedList<>();

        enteros.insertarFinal(10);
        enteros.insertarFinal(20);
        enteros.insertarFinal(30);

        System.out.println(
                "Lista simple de enteros: "
                + enteros.mostrar()
        );

        DoubleLinkedList<Double> decimales =
                new DoubleLinkedList<>();

        decimales.insertarFinal(1.5);
        decimales.insertarFinal(2.75);
        decimales.insertarFinal(3.14);

        System.out.println(
                "Lista doble de decimales: "
                + decimales.mostrar()
        );

        SimplyLinkedList<Character> caracteres =
                new SimplyLinkedList<>();

        caracteres.insertarFinal('J');
        caracteres.insertarFinal('A');
        caracteres.insertarFinal('V');
        caracteres.insertarFinal('A');

        System.out.println(
                "Lista simple de caracteres: "
                + caracteres.mostrar()
        );
    }

    public static void ejemploComplejo() {

        System.out.println(
                "\n--- Ejemplo con tipo COMPLEJO Y ABSTRACTO (Persona) ---"
        );

        DoubleLinkedList<Persona> personas =
                new DoubleLinkedList<>();

        personas.insertarFinal(
                new ContactoPersonal(
                        "Ana Torres",
                        "Av. Universidad 100",
                        "8112345678",
                        "14/03/1998"
                )
        );

        personas.insertarFinal(
                new ContactoLaboral(
                        "Luis Gómez",
                        "Calle Reforma 45",
                        "8187654321",
                        "22/07/2001"
                )
        );

        System.out.println(
                "Lista de personas: "
                + personas.mostrar()
        );
    }

    public static void ejemploAbstracto() {

        System.out.println(
                "\n--- Ejemplo con tipo ABSTRACTO (Figura) ---"
        );

        SimplyLinkedList<Figura> figuras =
                new SimplyLinkedList<>();

        figuras.insertarFinal(new Circulo(4.0));
        figuras.insertarFinal(new Rectangulo(3.0, 5.0));

        System.out.println(
                "Lista de figuras: "
                + figuras.mostrar()
        );
    }

    public static void ejecutarTodos() {

        ejemploPrimitivos();
        ejemploComplejo();
        ejemploAbstracto();
    }
}