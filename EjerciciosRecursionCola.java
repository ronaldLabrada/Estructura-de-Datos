public class EjerciciosRecursionCola {

    public static void main(String [] args){
        
        int [] nums = {1,2,3,4,5,12,7,8,9,10};
        ; 
        System.out.printf("\nEl factorial de 5 es: %d (usando metodo recursivo cola)", factorial(5));
        System.out.println("\nEl numero fibonacci de 7 es: " + fibonacci(7) + " (usando metodo recursivo cola)");
        System.out.printf("la suma es: %d (usando metodo recursivo cola)", sum(nums, nums.length));
        System.out.printf("\nel valor de 2 elevado a la 3 es: %.2f (usando metodo recursivo cola)", potencia(2, 3));
        System.out.printf("\nel valor maximo del arreglo nums es: %d (usando metodo recursivo cola)", maximo(nums));

    }
    /////////////////////////
    public static long factorial(int n) {
        if (n < 0) {
            System.out.println("el factorial no puede ser negativo: " + n );
        }
        return factorialTailRec(n, 1); // Inicia con acumulador = 1
    }
    private static long factorialTailRec(int n, long acumulador) {
        // Caso base: devuelve el acumulador
        if (n == 0 || n == 1) {
            return acumulador;
        }        
        // Llamada recursiva - ES la última operación
        return factorialTailRec(n - 1, n * acumulador);
    }
    ////////////////////////////////////
    public static long fibonacci(int n) {
        if (n < 0) {
            System.out.println("n no puede ser negativo: " + n);
        }
        
        // Casos base
        if (n == 0) return 0;
        if (n == 1) return 1;
        
        // Inicia la recursión con los dos primeros valores
        return fibonacciTR(n, 1, 0, 1);
    }
    private static long fibonacciTR(int n, int current, long prev2, long prev1) {
        // Cuando llegamos a la posición n
        if (current == n) {
            return prev1;
        }
        
        // Llamada tail recursive
        // prev1 se convierte en el nuevo prev2
        // prev1 + prev2 se convierte en el nuevo prev1
        return fibonacciTR(n, current + 1, prev1, prev1 + prev2);
    }
    /////////////////////////////////////
    static int sum (int arr[], int size){
        return sum(arr, size, 0);
    }

    private static int sum (int arr[], int size, int acum){
        if (size == 0) {
            return acum;
        } else {
            return sum(arr, size - 1, acum + arr[size - 1]);
        }
    }
    /////////////////////////////////////
    public static double potencia(double base, int exponente) {
        if (exponente < 0) {
            return 1 / potenciaPositiva(base, -exponente, 1);
        }
        return potenciaPositiva(base, exponente, 1);
    }
    private static double potenciaPositiva(double base, int exponente, double acumulador) {
        // Caso base: cuando el exponente llega a 0
        if (exponente == 0) {
            return acumulador;
        }        
        // Llamada tail recursive
        return potenciaPositiva(base, exponente - 1, base * acumulador);
    }
    /////////////////////////////////////
     public static int maximo(int[] arreglo) {
        if (arreglo == null || arreglo.length == 0) {
            throw new IllegalArgumentException("El arreglo no puede ser null o vacío");
        }        
        // Inicia la recursión con el primer elemento como máximo actual
        return maximoRecursivo(arreglo, 1, arreglo[0]);
    }
    private static int maximoRecursivo(int[] arreglo, int indice, int maxActual) {
        // Caso base: hemos recorrido todo el arreglo
        if (indice >= arreglo.length) {
            return maxActual;
        }
        
        // Actualizar el máximo actual si encontramos un valor mayor
        if (arreglo[indice] > maxActual) {
            maxActual = arreglo[indice];
        }        
        // Llamada recursiva al siguiente índice
        return maximoRecursivo(arreglo, indice + 1, maxActual);
    }
    
}