package entregafinal;

public class ContactoFicherosEscritura {

    LineaFicherosEscritura fichero = null;

    public ContactoFicherosEscritura(String nombre) throws Exception {
        fichero = new LineaFicherosEscritura(nombre, true);
    }

    public void escritura(Contacto contacto) throws Exception {
        Linea linea = new Linea();
        linea = contacto.ContactoToLinea(contacto);
        fichero.escritura(linea);
    }

    public void cierre() throws Exception {
        if (fichero != null) {
            fichero.cierre();
        }
    }
}
