package entregafinal;

public class ContactoFicherosLectura {
    //ATRIBUTOS DE LA CLASE
    LineaFicherosLectura fichero=null;

    //MÉTODOS CONSTRUCTORES
    public ContactoFicherosLectura(String nombre) throws Exception{
        fichero= new LineaFicherosLectura(nombre);
    }
  
    //MÉTODOS FUNCIONALES
    public boolean hayContactos() throws Exception{
        return fichero.hayLineas();
    }

    public Contacto lectura() throws Exception {
    Linea linea = fichero.lectura();

    Contacto contacto = new Contacto();
    contacto = contacto.LineaToContacto(linea);  // Asigna el resultado de la conversión a Contacto

    return contacto;
}

  
  public void cierre() throws Exception {
      if(fichero!=null){
          fichero.cierre();
      }
  }
}
