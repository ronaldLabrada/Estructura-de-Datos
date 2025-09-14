import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

class Persona implements Comparable<Persona> {
    int id;
    int edad;
    String nombre;
    LocalDate fechaNacimiento;
    
    public Persona(int id, int edad, String nombre, LocalDate fechaNacimiento) {
        this.id = id;
        this.edad = edad;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public int getId() { return id; }
    public int getEdad() { return edad; }
    public String getNombre() { return nombre; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }

    /* aqui se usa el metodo compareTo para comparar las fechas de nacimiento de dos personas y determinar su orden,
    este metodo es crucial para el funcionamiento del algoritmo de ordenamiento merge sort
    y para la búsqueda binaria, ya que ambos algoritmos dependen de la capacidad de comparar objetos
    en este caso, las fechas de nacimiento de las personas */

    @Override
    public int compareTo(Persona otra) {
        return this.fechaNacimiento.compareTo(otra.fechaNacimiento);
    }

    /* Metodo toString para representar la persona como una cadena de texto y facilitar su impresión 
    funciona para mostrar el nombre y la fecha de nacimiento de la persona en un formato legible
    cuando se imprime un objeto Persona, este método se llama automáticamente
    y devuelve una cadena que incluye el nombre y la fecha de nacimiento formateada */

    @Override
    public String toString() {
        return edad + nombre + " (" + fechaNacimiento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ")";
    }
}

public class ComparaEdadPersona {    
    public static void main(String[] args) {
        /* El bloque try (Scanner scanner = new Scanner(System.in)) { ... } asegura que el scanner se cierre automáticamente.
        No necesitas llamar a scanner.close() manualmente. 
        Esto es más seguro y se recomienda para gestionar recursos como flujos y scanners. */
        try (Scanner scanner = new Scanner(System.in)) {
            // Scanner scanner = new Scanner(System.in);
        // Crear arreglo de personas con fechas completas
            Persona[] personas = {
                new Persona(1, 5 , "Isaac", LocalDate.of(1990, 5, 15)),
                new Persona(2, 15, "Martin", LocalDate.of(1985, 12, 3)),
                new Persona(3, 20, "Nahiara", LocalDate.of(1992, 8, 20)),
                new Persona(4, 12, "Ethan", LocalDate.of(1988, 3, 10)),
                new Persona(5, 33, "Laura", LocalDate.of(1990, 2, 28)) // Mismo año, diferente mes
            };
            Persona[] personas1 = {
                new Persona(1, 5 , "Matias", LocalDate.of(2004, 5, 15)),
                new Persona(2, 15, "Luis", LocalDate.of(1975, 12, 3)),
                new Persona(3, 20, "Luisa", LocalDate.of(1992, 8, 20)),
                new Persona(4, 12, "Marcos", LocalDate.of(1989, 3, 10)),
                new Persona(5, 33, "Andres", LocalDate.of(1990, 2, 28)) // Mismo año, diferente mes
            };            
      
            System.out.println("Arreglo desordenado:");
            imprimirArreglo(personas);
            /* aqui es donde se ejecuta el ordenamiento por merge sort de las fechas de nacimiento de las personas y es almacenado en el mismo arreglo personas
            es necesario usar el mismo arreglo porque el metodo merge sort no devuelve un nuevo arreglo ordenado, sino que modifica el arreglo original. para ser uso es necesario pasar
            los indices de inicio (posicion inicial del arreglo a ordenar) y el indice final (hasta que posicion final se quiere ordenar)  */
            mergeSort(personas, 0, personas.length - 1); 

            System.out.println("\n*** Arreglo ordenado por fecha de nacimiento usando merge sort: ***");
            imprimirArreglo(personas);            

            System.out.println("\nArreglo desordenado:");
            imprimirArreglo(personas1);
            /* aqui es donde se ejecuta el ordenamiento por burbuja de las fechas de nacimiento de las personas
            y se almacena en un nuevo arreglo llamado ArregloPorBurbuja, es necesario crear un nuevo arreglo porque
            el metodo burbuja devuelve un nuevo arreglo ordenado y no modifica el arreglo original */
            Persona [] ArregloPorBurbuja =  burbuja(personas1);
           
            System.out.println("\n*** Arreglo ordenado por fecha de nacimiento usando bubble sort: ***");
            imprimirArreglo(ArregloPorBurbuja);
            
            // Solicitar fecha al usuario
            System.out.print("\nIngrese una fecha para realizar la busqueda binaria en arreglo ordenado por merge sort (formato dd/MM/yyyy): ");
            String fechaUsuarioStr = scanner.nextLine();
        
            try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    /* como la fecha es ingresada por el usuario en formato String, la parseamos al formato LocalDate y la
                    almacenamos en la variable fechaUsuario */                
                    LocalDate fechaUsuario = LocalDate.parse(fechaUsuarioStr, formatter);
                    /*System.out.println("Fecha capturada: " + fechaUsuario);
                    System.out.println("Día: " + fechaUsuario.getDayOfMonth());
                    System.out.println("Mes: " + fechaUsuario.getMonth());
                    System.out.println("Año: " + fechaUsuario.getYear()); */
                    System.out.println("\nfecha a buscar: " + fechaUsuario.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    //aqui ees donde se ejecuta búsqueda binaria por medio de la fecha ingresada por el usuario en formato LocalDate
                    int resultado = busquedaBinaria(personas, fechaUsuario);
            
                    if (resultado != -1) {
                    System.out.println("Encontrado en la posición: " + (resultado + 1) + " del arreglo ordenado por merge sort");
                    System.out.println("Persona: " + personas[resultado].getNombre() + " - " + personas[resultado].getFechaNacimiento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    } else {
                    System.out.println("usario no encontrado en el arreglo oredenado");
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Formato de fecha inválido. Use dd/MM/yyyy");
                    }
        }             
        
        /* Tomar fecha de la tercera persona para buscar
        LocalDate fechaBuscada1 = personas[2].getFechaNacimiento();
        Fecha específica para buscar ingresada por codigo
        LocalDate fechaBuscada = LocalDate.of(1992, 8, 20); Fecha específica para buscar */
    }
    
    //Metodo de ordenamiento por Merge Sort
    public static void mergeSort(Persona[] personas, int inicio, int fin) {
        if (inicio < fin) {
            int medio = (inicio + fin) / 2;
            mergeSort(personas, inicio, medio);
            mergeSort(personas, medio + 1, fin);
            merge(personas, inicio, medio, fin);
        }
    }

    public static Persona[] burbuja(Persona[] arr) {
        int n = arr.length;        
        Persona[] ordenado = arr.clone(); // Copia del arreglo original

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                //compara por fecha de nacimiento
                if (ordenado[j].getFechaNacimiento().isAfter(ordenado[j + 1].getFechaNacimiento())) {
                    // Intercambio de valores
                    Persona temp = ordenado[j];
                    ordenado[j] = ordenado[j + 1];
                    ordenado[j + 1] = temp;
                }
            }
        }
        return ordenado;
    }
    
    private static void merge(Persona[] personas, int inicio, int medio, int fin) {
        int n1 = medio - inicio + 1;
        int n2 = fin - medio;
        
        Persona[] izquierda = new Persona[n1];
        Persona[] derecha = new Persona[n2];
        
        for (int i = 0; i < n1; i++) {
            izquierda[i] = personas[inicio + i];
        }
        for (int j = 0; j < n2; j++) {
            derecha[j] = personas[medio + 1 + j];
        }
        
        int i = 0, j = 0, k = inicio;
        
        while (i < n1 && j < n2) {
            if (izquierda[i].compareTo(derecha[j]) <= 0) {
                personas[k] = izquierda[i];
                i++;
            } else {
                personas[k] = derecha[j];
                j++;
            }
            k++;
        }
        
        while (i < n1) {
            personas[k] = izquierda[i];
            i++;
            k++;
        }
        
        while (j < n2) {
            personas[k] = derecha[j];
            j++;
            k++;
        }
    }
    
    /* este es el metodo de busqueda binaria que busca la fecha ingresada por el usuario en el arreglo de personas ordenado por merge sort */
    public static int busquedaBinaria(Persona[] personas, LocalDate fechaBuscada) {
        int iz = 0;
        int der = personas.length - 1;
        
        while (iz <= der) {
            int medio = iz + (der - iz) / 2;
            LocalDate fechaMedio = personas[medio].getFechaNacimiento();
            
            int comparacion = fechaMedio.compareTo(fechaBuscada);
            
            if (comparacion == 0) {
                return medio;
            }
            
            if (comparacion < 0) {
                iz = medio + 1;
            } else {
                der = medio - 1;
            }
        }
        
        return -1;
    }
    
    /* este metodo imprime el arreglo de personas con su id, nombre y fecha de nacimiento formateada */
    public static void imprimirArreglo(Persona[] arr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (Persona persona : arr) {
            System.out.println( persona.getId() + " - " + persona.getEdad() + " - " + persona.getNombre() + " - " + 
                             persona.getFechaNacimiento().format(formatter));
        }
    }
}

