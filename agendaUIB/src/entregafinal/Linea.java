/*
Clase Linea --> aglutina las declaraciones y funcionalidades para gestionar
objetos Lineas.
Un objeto Linea estará constituido por una secuencia de caracteres delimitado por
un salto de linea. Como máximo el número de caracteres que puede conformar un
objeto Linea será de 250 caracteres.
 */
package entregafinal;

public class Linea {

    //DECLARACIONES
    //Declaración atributo de clase constante entero que representa el final de
    //un fichero
    private static final char FINAL_LINEA = '\n';
    //declaración atributo de clase constante entero que representa el máximo
    //número de caracteres que puede contener un objetoLinea
    private static final int MAXIMO = 255;
    //declaración atributo de objeto variable array de caracteres que representa 
    //los caracteres de un objeto Linea 
    private char[] caracteres = new char[MAXIMO];
    //declaración atributo de objeto variable entero que represente el número
    //de caracteres de un objeto Linea
    private int numeroCaracteres;
    //declaración atributo de clase variable caracter que representa el último
    //caracter leido desde la secuencia de entrada por teclado
    private static char caracter;

    //MÉTODOS
    //Métodos Constructores
    public Linea() {
        numeroCaracteres = 0;
    }

    public Linea(String dato) {
        // DECARACIONES
        // declaración array de componentes char para almacenar los caracteres
        // contenidos en el String dado por parámetro
        char[] arrayDato = dato.toCharArray();
        if (arrayDato.length <= 255) {
            for (int indice = 0; indice < arrayDato.length; indice++) {
                caracteres[indice] = arrayDato[indice];
            }
            numeroCaracteres = arrayDato.length;
        }
    }

    //Métodos funcionales
    //método que lleva a cabo la verificación de si una objeto Linea ha sido
    //totalmente leido desde el teclado
    public static boolean hayLinea() throws Exception {
        //lectura primer caracter de la secuencia de entrada por teclado
        caracter = LT.readChar();
        //verificar final de linea
        return (caracter != FINAL_LINEA);
    }

    //método que lleva a cabo la lectura de una linea desde el teclado
    public void lectura() throws Exception {
        //inicialización de numeroCaracteres
        numeroCaracteres = 0;
        while (caracter != FINAL_LINEA) {
            //almacenar el caracter leido en el array caracteres del objeto Linea
            caracteres[numeroCaracteres] = caracter;
            //incrementar numeroCaracteres
            numeroCaracteres++;
            //lectura del siguiente caracter de la secuencia
            caracter = LT.readChar();
        }
    }

    //método que lleva a cabo la conversión de un objeto Linea a String para su
    //visualización en pantalla
    @Override
    public String toString() {
        String salida = "";
        for (int indice = 0; indice < numeroCaracteres; indice++) {
            salida = salida + caracteres[indice];
        }
        return salida;
    }

    //método que devuelve el número de caracteres que conforman un objeto Linea
    public int numeroCaracteres() {
        return numeroCaracteres;
    }

    //MÉTODO vacia QUE TIENE COMO OBJETIVO EL DE VERIFICAR SI UN OBJETO Linea 
    //ESTÁ VACIO O NO
    public boolean vacia() {
        return (numeroCaracteres == 0);
    }

    public boolean esPar() {
        return (numeroCaracteres % 2 == 0);
    }

    public void adicionString(String tmp) {
        // DECLARACIONES
        // declaración array de caracteres para almacenar el contenido del parámetro
        // tmp
        char[] local = tmp.toCharArray();

        // ACCIONES
        // bucle de adición al objeto Linea, caracter a caracter, de los caracteres
        // contenidos en el array local
        for (int indice = 0; indice < local.length; indice++) {
            // adición del caracter indice-ésima del array local en la componente
            // numeroCaracteres del array caracteres del objeto Linea
            caracteres[numeroCaracteres] = local[indice];
            // Incrementar el atributo númeroCaracteres del objeto Linea
            numeroCaracteres++;
        }
    }

    // método que lleva a cabo la adición de un caracter dado por parámetro
    // al objeto Linea
    public void adicionCaracter(int cod) {
        caracteres[numeroCaracteres] = (char) cod;
        numeroCaracteres++;
    }

    public void adicion(Linea linea) {
        for (int i = 0; i < linea.numeroCaracteres; i++) {
            caracteres[numeroCaracteres] = linea.caracteres[i];
            numeroCaracteres++;
        }
    }

    //método que lleva a cabo la obtención del caracter del objeto Linea en función
    //de la posición dada por parámetro
    public char obtenerCaracter(int pos) {
        return caracteres[pos];
    }

    //método que lleva a cabo la devolución de la posición dónde se encuentra
    //un caracter dado por parámetro dentro del objeto Linea a partir de una
    //posición también dada
    public int detectarCaracter(int pos, char car) {
        int indice = pos;
        while ((caracteres[indice] != car) && (indice < numeroCaracteres)) {
            indice++;
        }
        if (caracteres[indice] == car) {
            return indice;
        } else {
            return numeroCaracteres;
        }
        //return indice;
    }

    //método que lleva a cabo la devolución de un objeto Linea a partir de otro
    //objeto Linea, dado por parámetro, formado por los caracteres que se 
    //encuentran en las posiciones delimitadas por los límnites dados.
    public Linea extraccionSubLinea(int pos1, int pos2) {
        //para que pos1 y pos2 representen las posiciones en una linea teniendo en
        //cuenta que la primera posición de una linea será la número 1 tendremos
        //que al utilizarlos como acceso al array caracteres restarles 1 unidad
        Linea subLinea = new Linea();
        if (((pos1 < numeroCaracteres) && (pos2 < numeroCaracteres) && (pos1 <= pos2))) {
            for (int indice = pos1 - 1; indice <= pos2 - 1; indice++) {
                subLinea.caracteres[subLinea.numeroCaracteres] = caracteres[indice];
                subLinea.numeroCaracteres++;
            }
        }
        return subLinea;
    }

    // ...
    // método que lleva a cabo la sustitución de una sublinea por otra en el objeto Linea
    public void sustituirSublinea(Linea sublinea1, Linea sublinea2) {
        // Obtener el índice de la primera ocurrencia de sublinea1 en la línea actual
        int indiceInicio = detectarCaracter(0, sublinea1.obtenerCaracter(0));

        while (indiceInicio < numeroCaracteres) {
            // Verificar si la sublínea1 está presente en la línea actual a partir de indiceInicio
            boolean coincide = true;
            for (int i = 0; i < sublinea1.numeroCaracteres; i++) {
                if (obtenerCaracter(indiceInicio + i) != sublinea1.obtenerCaracter(i)) {
                    coincide = false;
                    break;
                }
            }

            if (coincide) {
                // Eliminar la sublínea1 de la línea actual
                for (int i = 0; i < sublinea1.numeroCaracteres; i++) {
                    for (int j = indiceInicio; j < numeroCaracteres - 1; j++) {
                        caracteres[j] = caracteres[j + 1];
                    }
                    numeroCaracteres--;
                }

                // Insertar la sublínea2 en la posición de la sublínea1
                for (int i = 0; i < sublinea2.numeroCaracteres; i++) {
                    for (int j = numeroCaracteres; j > indiceInicio + i; j--) {
                        caracteres[j] = caracteres[j - 1];
                    }
                    caracteres[indiceInicio + i] = sublinea2.obtenerCaracter(i);
                    numeroCaracteres++;
                }
            }

            // Buscar la siguiente ocurrencia de sublínea1 en la línea actual
            indiceInicio = detectarCaracter(indiceInicio + sublinea1.numeroCaracteres, sublinea1.obtenerCaracter(0));
        }
    }
    
    public boolean contiene(Linea sublinea1) throws Exception {
    // Verificar si la longitud de la sublínea a buscar es mayor que la longitud
    // de la línea actual
    if (sublinea1.numeroCaracteres > numeroCaracteres) {
        return false;
    }

    // Bucle para buscar la sublínea en la línea actual
    for (int i = 0; i <= numeroCaracteres - sublinea1.numeroCaracteres; i++) {
        boolean coincide = true;

        // Comprobar si la sublínea coincide a partir de la posición actual
        for (int j = 0; j < sublinea1.numeroCaracteres; j++) {
            if (caracteres[i + j] != sublinea1.obtenerCaracter(j)) {
                coincide = false;
                break;
            }
        }

        // Si la sublínea coincide, devolver true
        if (coincide) {
            return true;
        }
    }

    // Si no se encuentra ninguna coincidencia, devolver false
    return false;
}
    
    public boolean esIgual(Linea sublinea1) throws Exception {
        if (sublinea1.caracteres.length != caracteres.length) {
            return false;
        }
        
        for (int i = 0; i < sublinea1.caracteres.length; i++) {
            if (sublinea1.caracteres[i] != caracteres[i]) {
                return false;
            }
        }
        
        return true;
    }
}
