public class MergeSort {
    
    public static void main(String[] args) {
        MergeSort per = new MergeSort();
        per.comparar();
    }
    
    public void comparar() {
        // Crear arreglo de 4 personas
        Persona[] personas = {
            new Persona(1, "Isaac", 1990),
            new Persona(2, "Martin", 1985),
            new Persona(3, "Nahiara", 1992),
            new Persona(4, "Ethan", 1988)
        };

        System.out.println("Personas originales:");
        imprimirArreglo(personas);
        
        // Aplicar Merge Sort para ordenar por año de nacimiento
        mergeSort(personas, 0, personas.length - 1);
        
        System.out.println("\nPersonas ordenadas por año de nacimiento (ascendente):");
        imprimirArreglo(personas);

        // Tomar como referencia el año de la primera persona (ahora ordenada)
        int añoReferencia = personas[0].getAñoNacimiento();
        
        System.out.println("\nResultados de comparación:");
        System.out.println("Año de referencia: " + añoReferencia);
        
        for (int i = 0; i < personas.length; i++) {
            // Realizamos la comparación usando el método compareTo
            int resultado = personas[0].compareTo(personas[i]);
            System.out.println((i + 1) + ". " + personas[i].getNombre() + " = " + resultado);
        }
    }
    
    // Algoritmo Merge Sort para ordenar personas por año de nacimiento
    public void mergeSort(Persona[] personas, int inicio, int fin) {
        if (inicio < fin) {
            int medio = (inicio + fin) / 2;
            
            // Dividir y conquistar recursivamente
            mergeSort(personas, inicio, medio);
            mergeSort(personas, medio + 1, fin);
            
            // Combinar las mitades ordenadas
            merge(personas, inicio, medio, fin);
        }
    }
    
    private void merge(Persona[] personas, int inicio, int medio, int fin) {
        // Tamaños de los subarreglos
        int n1 = medio - inicio + 1;
        int n2 = fin - medio;
        
        // Arreglos temporales
        Persona[] izquierda = new Persona[n1];
        Persona[] derecha = new Persona[n2];
        
        // Copiar datos a arreglos temporales
        for (int i = 0; i < n1; i++) {
            izquierda[i] = personas[inicio + i];
        }
        for (int j = 0; j < n2; j++) {
            derecha[j] = personas[medio + 1 + j];
        }
        
        // Índices para los subarreglos
        int i = 0, j = 0;
        int k = inicio;
        
        // Combinar los arreglos temporales de forma ordenada
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
        
        // Copiar elementos restantes de izquierda (si hay)
        while (i < n1) {
            personas[k] = izquierda[i];
            i++;
            k++;
        }
        
        // Copiar elementos restantes de derecha (si hay)
        while (j < n2) {
            personas[k] = derecha[j];
            j++;
            k++;
        }
    }
    
    // Mostrar personas en el sistema
    public static void imprimirArreglo(Persona[] personas) {
        System.out.println("Personas en el sistema:");
        for(int i = 0; i < personas.length; i++) {
             System.out.println((i+1) + ". " + personas[i].getNombre() + 
                              " (Año de nacimiento: " + personas[i].getAñoNacimiento() + 
                              ", ID: " + personas[i].getId() + ")");
        }
    }
}

class Persona implements Comparable<Persona> {
    private int id;
    private String nombre;
    private int añoNacimiento;
    
    public Persona(int id, String nombre, int anioNacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.añoNacimiento = anioNacimiento;
    }
    
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public int getAñoNacimiento() { return añoNacimiento; }
    
    @Override
    public int compareTo(Persona otra) {
        // Comparar por año de nacimiento
        return Integer.compare(this.añoNacimiento, otra.añoNacimiento);
    }
}