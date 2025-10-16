package entregafinal;

public class Contacto {

    private Linea Nombre;
    private Linea Apellido;
    private Linea Telefono;
    private Linea Email;

    //Creacion de un constructor contacto vacio
    public Contacto() {
    }

    public static Contacto Crea() {

        Contacto nuevo = new Contacto();

        System.out.print("INTRODUCIR EL CAMPO #NOMBRE#: ");
        nuevo.Nombre = new Linea(LT.readLine());
        System.out.print("INTRODUCIR EL CAMPO #APELLIDO#: ");
        nuevo.Apellido = new Linea(LT.readLine());
        System.out.print("INTRODUCIR EL CAMPO #E-MAIL#: ");
        nuevo.Email = new Linea(LT.readLine());
        System.out.print("INTRODUCIR EL CAMPO #NUM-TEL#: ");
        nuevo.Telefono = new Linea(LT.readLine());

        return nuevo;
    }

    @Override
    public String toString() {
        return "Nombre: " + Nombre.toString() + "\n" + "Apellidos: " + Apellido.toString() + "\n" + "E-mail: " + Email.toString() + "\n" + "Núm-tel: " + Telefono.toString() + "\n";

    }

    public Contacto LineaToContacto(Linea linea) {
        Contacto contacto = new Contacto();

        Linea Nombre = new Linea();
        Linea Apellido = new Linea();
        Linea Email = new Linea();
        Linea Telefono = new Linea();

        int posicion1 = linea.detectarCaracter(0, '#');

        for (int i = 0; i < posicion1; i++) {
            Nombre.adicionCaracter(linea.obtenerCaracter(i));
        }

        contacto.Nombre = Nombre;

        int posicion2 = linea.detectarCaracter(posicion1 + 1, '#');

        for (int indice = posicion1 + 1; indice < posicion2; indice++) {
            Apellido.adicionCaracter(linea.obtenerCaracter(indice));
        }

        contacto.Apellido = Apellido;

        int posicion3 = linea.detectarCaracter(posicion2 + 1, '#');

        for (int indice = posicion2 + 1; indice < posicion3; indice++) {
            Email.adicionCaracter(linea.obtenerCaracter(indice));
        }

        contacto.Email = Email;

        for (int indice = posicion3 + 1; indice < linea.numeroCaracteres(); indice++) {
            Telefono.adicionCaracter(linea.obtenerCaracter(indice));
        }

        contacto.Telefono = Telefono;

        return contacto;
    }

    public Linea ContactoToLinea(Contacto contacto) throws Exception {
        Linea linea = new Linea();

        //añadir el atributo nombre
        linea.adicion(contacto.Nombre);
        //añadir el separador
        linea.adicionString("#");
        //añadir el atributo apellido
        linea.adicion(contacto.Apellido);
        //añadir el separador
        linea.adicionString("#");
        //añadir el atributo Email
        linea.adicion(contacto.Email);
        //añadir el atributo separador
        linea.adicionString("#");
        //añadir el atributo teléfono
        linea.adicion(contacto.Telefono);
        linea.adicionCaracter('\n');

        return linea;
    }
    
    public Linea getEmail() {
        return Email;
    }
    
    public Linea getNombre() {
        return Nombre;
    }
    
    public Linea getApellido() {
        return Apellido;
    }
    
    public Linea getTel() {
        return Telefono;
    }
}
