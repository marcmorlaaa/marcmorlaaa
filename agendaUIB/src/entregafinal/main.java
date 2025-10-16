package entregafinal;

import java.io.File;

public class main {

    //MÉTODO MAIN
    public static void main(String[] args) throws Exception {
        //INICIAMOS MENÚ
        new main().menu();
    }

    //MÉTODO MENÚ
    public void menu() throws Exception {
        boolean salir = false;
        System.out.println("");

        do {
            System.out.println("***********************************************************");
            System.out.println("MENÚ PRINCIPAL");
            System.out.println("***********************************************************");
            System.out.println("    1 Generar cartas");
            System.out.println("    2 Administrar agenda");
            System.out.println("    3 Administrar listas de distribución");
            System.out.println("    s Salir");
            System.out.println("***********************************************************");

            char opcion = leerTeclado();
            System.out.println("");

            switch (opcion) {
                case '1':
                    ContactoFicherosLectura fichero;
                    Contacto contacto = new Contacto();
                    fichero = new ContactoFicherosLectura("fitxers/agenda.txt");
                    if (fichero.hayContactos()) {
                        generarCartas();
                        fichero.cierre();
                    } else {
                        System.out.println("Necesitas al menos un contacto para generar cartas");
                        System.out.println("");
                        fichero.cierre();
                    }
                    break;
                case '2':
                    administrarAgenda();
                    break;
                case '3':
                    administrarListas();
                    break;
                case 's':
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida");
                    System.out.println("");
            }
        } while (!salir);
    }

    //MÉTODO GENERARCARTAS
    public void generarCartas() throws Exception {
        System.out.println("***********************************************************");
        System.out.println("GENERAR CARTES");
        System.out.println("***********************************************************");
        System.out.print("Escoge el nombre de la carta: ");
        String nombre1 = LT.readLine();
        File carpeta = new File("fitxers/generats/" + nombre1);
        if (carpeta.mkdir()) {
            System.out.print("Escoge el nombre de la plantilla: ");
            String nombre2 = LT.readLine();
            File plantilla = new File("fitxers/plantilles/" + nombre2 + ".html");
            if (plantilla.exists()) {
                Linea confirmaragenda = new Linea("agenda");
                System.out.print("Escoge el nombre de la lista de distribución o escribe \"agenda\" si quieres usar toda la agenda: ");
                Linea nombre3 = new Linea(LT.readLine());
                if (confirmaragenda.esIgual(nombre3)) {
                    int total = 0;
                    ContactoFicherosLectura fichero;
                    Contacto contacto = new Contacto();
                    fichero = new ContactoFicherosLectura("fitxers/agenda.txt");
                    LineaFicherosEscritura fichero2 = null;
                    while (fichero.hayContactos()) {
                        LineaFicherosLectura fichero3 = new LineaFicherosLectura("fitxers/plantilles/" + nombre2 + ".html");
                        Linea linea = new Linea();
                        contacto = fichero.lectura();
                        total++;
                        fichero2 = new LineaFicherosEscritura("fitxers/generats/" + nombre1 + "/" + contacto.getEmail().toString() + ".html");
                        while (fichero3.hayLineas()) {
                            linea = fichero3.lectura();
                            Linea sublineaSustituir = new Linea("#NOMBRE#");
                            Linea sublineaSustituta = contacto.getNombre();
                            linea.sustituirSublinea(sublineaSustituir, sublineaSustituta);
                            sublineaSustituir = new Linea("#APELLIDOS#");
                            sublineaSustituta = contacto.getApellido();
                            linea.sustituirSublinea(sublineaSustituir, sublineaSustituta);
                            sublineaSustituir = new Linea("#E-MAIL#");
                            sublineaSustituta = contacto.getEmail();
                            linea.sustituirSublinea(sublineaSustituir, sublineaSustituta);
                            sublineaSustituir = new Linea("#NUM-TEL#");
                            sublineaSustituta = contacto.getTel();
                            linea.sustituirSublinea(sublineaSustituir, sublineaSustituta);
                            fichero2.escritura(linea);
                        }
                        fichero2.cierre();
                        fichero3.cierre();
                    }
                    fichero.cierre();
                    if (total == 1) {
                        System.out.println("");
                        System.out.println("Se ha generado " + total + " carta personalizada en el directorio \"" + nombre1 + "\"");
                        System.out.println("");
                    } else {
                        System.out.println("");
                        System.out.println("Se han generado " + total + " cartas personalizadas en el directorio \"" + nombre1 + "\"");
                        System.out.println("");
                    }
                } else {
                    File lista = new File("fitxers/llistes/" + nombre3.toString() + ".txt");
                    if (lista.exists()) {
                        int total = 0;
                        LineaFicherosLectura fichero;
                        fichero = new LineaFicherosLectura("fitxers/llistes/" + nombre3.toString() + ".txt");
                        LineaFicherosEscritura fichero2 = null;
                        while (fichero.hayLineas()) {
                            LineaFicherosLectura fichero3 = new LineaFicherosLectura("fitxers/plantilles/" + nombre2 + ".html");
                            Linea linea = new Linea();
                            linea = fichero.lectura();
                            total++;
                            fichero2 = new LineaFicherosEscritura("fitxers/generats/" + nombre1 + "/" + linea.toString() + ".html");
                            ContactoFicherosLectura fichero4;
                            Contacto contacto = new Contacto();
                            Linea linea2 = new Linea();
                            fichero4 = new ContactoFicherosLectura("fitxers/agenda.txt");

                            while (fichero4.hayContactos()) {
                                contacto = fichero4.lectura();
                                linea2 = contacto.getEmail();
                                if (linea2.esIgual(linea)) {
                                    break;
                                }
                            }
                            fichero4.cierre();
                            while (fichero3.hayLineas()) {
                                linea = fichero3.lectura();
                                Linea sublineaSustituir = new Linea("#NOMBRE#");
                                Linea sublineaSustituta = contacto.getNombre();
                                linea.sustituirSublinea(sublineaSustituir, sublineaSustituta);
                                sublineaSustituir = new Linea("#APELLIDOS#");
                                sublineaSustituta = contacto.getApellido();
                                linea.sustituirSublinea(sublineaSustituir, sublineaSustituta);
                                sublineaSustituir = new Linea("#E-MAIL#");
                                sublineaSustituta = contacto.getEmail();
                                linea.sustituirSublinea(sublineaSustituir, sublineaSustituta);
                                sublineaSustituir = new Linea("#NUM-TEL#");
                                sublineaSustituta = contacto.getTel();
                                linea.sustituirSublinea(sublineaSustituir, sublineaSustituta);
                                fichero2.escritura(linea);
                            }
                            fichero2.cierre();
                            fichero3.cierre();
                        }
                        fichero.cierre();
                        if (total == 1) {
                            System.out.println("");
                            System.out.println("Se ha generado " + total + " carta personalizada en el directorio \"" + nombre1 + "\"");
                            System.out.println("");
                        } else {
                            System.out.println("");
                            System.out.println("Se han generado " + total + " cartas personalizadas en el directorio \"" + nombre1 + "\"");
                            System.out.println("");
                        }
                    } else {
                        System.out.println("");
                        System.out.println("Esta lista no existe");
                        System.out.println("");
                        carpeta.delete();
                    }
                }
            } else {
                System.out.println("");
                System.out.println("Esta plantilla no existe");
                System.out.println("");
                carpeta.delete();
            }
        } else {
            System.out.println("");
            System.out.println("Esta carta ya existe");
            System.out.println("");
        }
    }

    //MÉTODO ADMINISTRARAGENDA
    public void administrarAgenda() throws Exception {
        boolean salir = false;

        do {
            System.out.println("***********************************************************");
            System.out.println("ADMINISTRAR AGENDA");
            System.out.println("***********************************************************");
            System.out.println("    1 Añadir un contacto");
            System.out.println("    2 Consultar los datos de los contactos");
            System.out.println("    3 Eliminar un contacto");
            System.out.println("    s Salir al menú principal");
            System.out.println("***********************************************************");

            char opcion = leerTeclado();
            System.out.println("");

            switch (opcion) {
                case '1':
                    añadirContacto();
                    break;
                case '2':
                    consultarDatos();
                    break;
                case '3':
                    eliminarContacto();
                    break;
                case 's':
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida");
                    System.out.println("");
            }
        } while (!salir);
    }

    //MÉTODO AÑADIRCONTACTO
    public void añadirContacto() throws Exception {
        System.out.println("***********************************************************");
        System.out.println("AÑADIR UN CONTACTO");
        System.out.println("***********************************************************");
        Contacto contacto = Contacto.Crea();
        ContactoFicherosEscritura fichero;
        fichero = new ContactoFicherosEscritura("fitxers/agenda.txt");
        fichero.escritura(contacto);
        fichero.cierre();
        System.out.println("");
        System.out.println("Contacto añadido correctamente");
        System.out.println("");
    }

    //MÉTODO CONSULTARDATOS
    public void consultarDatos() throws Exception {
        boolean salir = false;

        do {
            System.out.println("***********************************************************");
            System.out.println("CONSULTAR CONTACTOS");
            System.out.println("***********************************************************");
            System.out.println("    1 Mostrar todos los contactos");
            System.out.println("    2 Mostrar contactos según parte del nombre o apellido");
            System.out.println("    3 Mostrar contactos según e-mail");
            System.out.println("    s Salir al menú de la agenda");
            System.out.println("***********************************************************");

            char opcion = leerTeclado();
            System.out.println("");

            switch (opcion) {
                case '1':
                    mostrarContactos();
                    break;
                case '2':
                    mostrarContactosNombreApellido();
                    break;
                case '3':
                    mostrarContactoEmail();
                    break;
                case 's':
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida");
                    System.out.println("");
            }
        } while (!salir);
    }

    //MÉTODO MOSTRARCONTACTOS
    public void mostrarContactos() throws Exception {
        System.out.println("***********************************************************");
        System.out.println("MOSTRAR CONTACTOS");
        System.out.println("***********************************************************");
        int total = 0;
        ContactoFicherosLectura fichero;
        Contacto contacto = new Contacto();
        fichero = new ContactoFicherosLectura("fitxers/agenda.txt");
        while (fichero.hayContactos()) {
            contacto = fichero.lectura();
            System.out.println(contacto.toString());
            total++;
        }

        if (total == 0) {
            System.out.println("No se han encontrado contactos");
        } else if (total == 1) {
            System.out.println("Se ha encontrado " + total + " contacto en total");
        } else {
            System.out.println("Se han encontrado " + total + " contactos en total");
        }

        System.out.println("");
        fichero.cierre();
    }

    //MÉTODO MOSTRARCONTACTOSNOMBREAPELLIDO
    public void mostrarContactosNombreApellido() throws Exception {
        System.out.println("***********************************************************");
        System.out.println("CONSULTAR CONTACTOS SEGÚN PARTE DEL NOMBRE O APELLIDO");
        System.out.println("***********************************************************");
        System.out.print("Introduce parte del nombre o apellido: ");
        Linea introducido = new Linea(LT.readLine());
        System.out.println("");
        int total = 0;
        ContactoFicherosLectura fichero;
        Contacto contacto = new Contacto();
        Linea linea = new Linea();
        Linea linea2 = new Linea();
        fichero = new ContactoFicherosLectura("fitxers/agenda.txt");

        while (fichero.hayContactos()) {
            contacto = fichero.lectura();
            linea = contacto.getNombre();
            linea2 = contacto.getApellido();
            if (linea.contiene(introducido) || linea2.contiene(introducido)) {
                System.out.println(contacto.toString());
                total++;
            }
        }

        if (total == 0) {
            System.out.println("No se han encontrado contactos");
        } else if (total == 1) {
            System.out.println("Se ha encontrado " + total + " contacto en total");
        } else {
            System.out.println("Se han encontrado " + total + " contactos en total");
        }

        System.out.println("");
        fichero.cierre();
    }

    //MÉTODO MOSTRARCONTACTOSEMAIL
    public void mostrarContactoEmail() throws Exception {
        System.out.println("***********************************************************");
        System.out.println("CONSULTAR CONTACTOS SEGÚN EMAIL");
        System.out.println("***********************************************************");
        System.out.print("Introduce el Email: ");
        Linea introducido = new Linea(LT.readLine());
        System.out.println("");
        int total = 0;
        ContactoFicherosLectura fichero;
        Contacto contacto = new Contacto();
        Linea linea = new Linea();
        fichero = new ContactoFicherosLectura("fitxers/agenda.txt");

        while (fichero.hayContactos()) {
            contacto = fichero.lectura();
            linea = contacto.getEmail();
            if (linea.esIgual(introducido)) {
                System.out.println(contacto.toString());
                total++;
            }
        }

        if (total == 0) {
            System.out.println("No se han encontrado contactos");
        } else if (total == 1) {
            System.out.println("Se ha encontrado " + total + " contacto en total");
        } else {
            System.out.println("Se han encontrado " + total + " contactos en total");
        }

        System.out.println("");
        fichero.cierre();
    }

    //MÉTODO ELIMINARCONTACTO
    public void eliminarContacto() throws Exception {
        System.out.println("***********************************************************");
        System.out.println("ELIMINAR CONTACTOS");
        System.out.println("***********************************************************");
        System.out.print("Introduce el Email del contacto a eliminar: ");
        Linea emailEliminar = new Linea(LT.readLine());
        Linea linea = new Linea();
        System.out.print("Escribe \"confirmar\" si de verdad quieres eliminar el contacto: ");
        Linea confirmacion = new Linea(LT.readLine());
        Linea si = new Linea("confirmar");
        boolean borrado = false;
        if (si.esIgual(confirmacion)) {
            ContactoFicherosLectura ficheroLectura = new ContactoFicherosLectura("fitxers/agenda.txt");
            ContactoFicherosEscritura ficheroEscritura = new ContactoFicherosEscritura("fitxers/agendacopia.txt");
            while (ficheroLectura.hayContactos()) {
                Contacto contacto = ficheroLectura.lectura();
                linea = contacto.getEmail();
                if (!linea.esIgual(emailEliminar)) {
                    // Si el email del contacto no coincide con el introducido, se escribe en el archivo de copia
                    ficheroEscritura.escritura(contacto);
                } else {
                    borrado = true;
                }
            }

            // Cerrar ambos archivos
            ficheroLectura.cierre();
            ficheroEscritura.cierre();

            if (borrado) {
                Linea linea2 = new Linea();
                File directorioListas = new File("fitxers/llistes/");
                File[] listas = directorioListas.listFiles();

                for (int i = 0; i < listas.length; i++) {
                    String nombreLista = listas[i].getName();
                    LineaFicherosLectura ficheroLectura2 = new LineaFicherosLectura("fitxers/llistes/" + nombreLista);
                    LineaFicherosEscritura ficheroEscritura2 = new LineaFicherosEscritura("fitxers/llistes/" + "copia" + nombreLista);
                    while (ficheroLectura2.hayLineas()) {
                        linea2 = ficheroLectura2.lectura();
                        if (!linea2.esIgual(emailEliminar)) {
                            linea2.adicionCaracter('\n');
                            ficheroEscritura2.escritura(linea2);
                        }
                    }

                    ficheroLectura2.cierre();
                    ficheroEscritura2.cierre();

                    File ficheroOriginal = new File("fitxers/llistes/" + nombreLista);
                    ficheroOriginal.delete();

                    // Renombrar el archivo de copia al nombre original
                    File ficheroCopia = new File("fitxers/llistes/" + "copia" + nombreLista);
                    ficheroCopia.renameTo(ficheroOriginal);
                }

                System.out.println("");
                System.out.println("Contacto eliminado");
                System.out.println("");
            } else {
                System.out.println("");
                System.out.println("No se ha encontrado al contacto");
                System.out.println("");
            }

            // Eliminar el archivo original
            File ficheroOriginal = new File("fitxers/agenda.txt");
            ficheroOriginal.delete();

            // Renombrar el archivo de copia al nombre original
            File ficheroCopia = new File("fitxers/agendacopia.txt");
            ficheroCopia.renameTo(ficheroOriginal);

        } else {

            System.out.println("");
            System.out.println("Operación cancelada");
            System.out.println("");
        }

    }

    //MÉTODO ADMINISTRARLISTAS
    public void administrarListas() throws Exception {
        boolean salir = false;

        do {
            System.out.println("***********************************************************");
            System.out.println("ADMINISTRAR LISTAS DE DISTRIBUCIÓN");
            System.out.println("***********************************************************");
            System.out.println("    1 Crea una lista de distribución");
            System.out.println("    2 Elimina una lista de distribución");
            System.out.println("    3 Selecciona una lista de distribución");
            System.out.println("    s Salir al menú principal");
            System.out.println("***********************************************************");

            char opcion = leerTeclado();
            System.out.println("");

            switch (opcion) {
                case '1':
                    crealista();
                    break;
                case '2':
                    eliminalista();
                    break;
                case '3':
                    seleccionalista();
                    break;
                case 's':
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida");
                    System.out.println("");
            }
        } while (!salir);
    }

    public void crealista() throws Exception {
        System.out.println("***********************************************************");
        System.out.println("CREAR LISTA DE DISTRIBUCIÓN");
        System.out.println("***********************************************************");
        System.out.print("Escoge el nombre de la lista: ");
        String nombre = LT.readLine();
        LineaFicherosEscritura fichero = new LineaFicherosEscritura("fitxers/llistes/" + nombre + ".txt");
        fichero.cierre();
        System.out.println("");
        System.out.println("Lista de distribución creada");
        System.out.println("");
    }

    public void eliminalista() throws Exception {
        System.out.println("***********************************************************");
        System.out.println("ELIMINAR LISTA DE DISTRIBUCIÓN");
        System.out.println("***********************************************************");
        System.out.print("Escoge el nombre de la lista: ");
        String nombre = LT.readLine();
        File lista = new File("fitxers/llistes/" + nombre + ".txt");

        if (lista.exists()) {
            lista.delete();
            System.out.println("");
            System.out.println("Lista de distribución eliminada");
            System.out.println("");
        } else {
            System.out.println("");
            System.out.println("Esa lista no existe");
            System.out.println("");
        }
    }

    public void seleccionalista() throws Exception {
        System.out.println("***********************************************************");
        System.out.println("SELECCIONAR LISTA DE DISTRIBUCIÓN");
        System.out.println("***********************************************************");
        System.out.print("Escoge el nombre de la lista: ");
        String nombre = LT.readLine();
        System.out.println("");
        File lista = new File("fitxers/llistes/" + nombre + ".txt");

        if (lista.exists()) {
            boolean salir = false;

            do {
                System.out.println("***********************************************************");
                System.out.println("LISTA DE DISTRIBUCIÓN (\"" + nombre + "\")");
                System.out.println("***********************************************************");
                System.out.println("    1 Añade un contacto");
                System.out.println("    2 Elimina un contacto");
                System.out.println("    s Salir al menú principal");
                System.out.println("***********************************************************");

                char opcion = leerTeclado();
                System.out.println("");

                switch (opcion) {
                    case '1':
                        System.out.println("***********************************************************");
                        System.out.println("AÑADIR CONTACTO A LISTA DE DISTRIBUCIÓN (\"" + nombre + "\")");
                        System.out.println("***********************************************************");
                        System.out.print("Introduce el Email del contacto a añadir: ");
                        Linea emailContacto = new Linea(LT.readLine());
                        boolean existe = false;
                        boolean existeya = false;
                        ContactoFicherosLectura fichero;
                        Contacto contacto = new Contacto();
                        Linea linea = new Linea();
                        fichero = new ContactoFicherosLectura("fitxers/agenda.txt");
                        while (fichero.hayContactos()) {
                            contacto = fichero.lectura();
                            linea = contacto.getEmail();
                            if (linea.esIgual(emailContacto)) {
                                existe = true;
                                break;
                            }
                        }
                        fichero.cierre();

                        LineaFicherosLectura fichero3 = new LineaFicherosLectura("fitxers/llistes/" + nombre + ".txt");
                        while (fichero3.hayLineas()) {
                            linea = fichero3.lectura();
                            if (linea.esIgual(emailContacto)) {
                                existeya = true;
                                break;
                            }
                        }
                        fichero3.cierre();
                        fichero.cierre();

                        if (existe && !existeya) {
                            LineaFicherosEscritura fichero2 = new LineaFicherosEscritura("fitxers/llistes/" + nombre + ".txt", true);
                            emailContacto.adicionCaracter('\n');
                            fichero2.escritura(emailContacto);
                            System.out.println("");
                            System.out.println("Contacto añadido a lista de distribución");
                            System.out.println("");
                            fichero2.cierre();
                        } else if (!existe) {
                            System.out.println("");
                            System.out.println("No se ha encontrado al contacto");
                            System.out.println("");
                        } else {
                            System.out.println("");
                            System.out.println("Esta contacto ya está en la lista de distribución");
                            System.out.println("");
                        }

                        break;

                    case '2':
                        System.out.println("***********************************************************");
                        System.out.println("ELIMINAR CONTACTO DE LISTA DE DISTRIBUCIÓN (\"" + nombre + "\")");
                        System.out.println("***********************************************************");
                        System.out.print("Introduce el Email del contacto a añadir: ");
                        Linea emailContacto2 = new Linea(LT.readLine());
                        LineaFicherosLectura fichero4 = new LineaFicherosLectura("fitxers/llistes/" + nombre + ".txt");
                        LineaFicherosEscritura fichero5 = new LineaFicherosEscritura("fitxers/llistes/" + nombre + "copia" + ".txt");
                        Linea linea2 = new Linea();
                        boolean borrado = false;
                        while (fichero4.hayLineas()) {
                            linea2 = fichero4.lectura();
                            if (!linea2.esIgual(emailContacto2)) {
                                linea2.adicionCaracter('\n');
                                fichero5.escritura(linea2);
                            } else {
                                borrado = true;
                            }
                        }
                        fichero5.cierre();
                        fichero4.cierre();

                        if (borrado) {
                            System.out.println("");
                            System.out.println("Contacto eliminado");
                            System.out.println("");
                        } else {
                            System.out.println("");
                            System.out.println("No se ha encontrado al contacto");
                            System.out.println("");
                        }

                        // Eliminar el archivo original
                        File ficheroOriginal = new File("fitxers/llistes/" + nombre + ".txt");
                        ficheroOriginal.delete();

                        // Renombrar el archivo de copia al nombre original
                        File ficheroCopia = new File("fitxers/llistes/" + nombre + "copia" + ".txt");
                        ficheroCopia.renameTo(ficheroOriginal);

                        break;
                    case 's':
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción no válida");
                        System.out.println("");
                }
            } while (!salir);
        } else {
            System.out.println("Esa lista no existe");
            System.out.println("");
        }
    }

    //MÉTODO LEERTECLADO
    private char leerTeclado() throws Exception {
        char[] tecla = LT.readLineChar();
        if (tecla.length < 1 || tecla.length > 1) {
            return 'n';
        }
        return tecla[0];
    }
}
