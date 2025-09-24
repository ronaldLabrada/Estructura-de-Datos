public class Recursion {

    static long fibo[] = new long[100];

    public static void main(String [] args){
        int [] nums = {1,3,4,7,2,8,12,7,4,3,9,4,0,23};
        int [] f = {4,8,12,20,47,49};
        
        System.out.printf("Hay %d numeros pares Metodo iterativo", conteo_pares_inter(nums));
        System.out.printf("\nHay %d numeros pares Metodo recursivo", conteo_pares_rec2(nums,0));
        System.out.printf("\nEl factorial de 5 es: %d", factorial_rec(5));

        for (int i = 0; i < f.length; i++) {
            int num_fibo = f[i];
            
            // Medir tiempo del método iterativo
            long inicioIterativo = System.nanoTime();
            long resultadoIterativo = fibo_Iterativo(num_fibo);
            long finIterativo = System.nanoTime();
            long tiempoIterativo = finIterativo - inicioIterativo;
            
            // Medir tiempo del método recursivo (solo para valores pequeños)
            long tiempoRecursivo = 0;
            long resultadoRecursivo = 0;
            if (num_fibo <= 200) { // Limitar para evitar tiempos muy largos
                long inicioRecursivo = System.nanoTime();
                resultadoRecursivo = fibonacci_rec(num_fibo);
                long finRecursivo = System.nanoTime();
                tiempoRecursivo = finRecursivo - inicioRecursivo;
            }
            
            System.out.printf("\n\n--- Fibonacci para n = %d ---", num_fibo);
            System.out.printf("\nIterativo: %d (Tiempo: %d ns | %.3f ms)", 
                             resultadoIterativo, tiempoIterativo, tiempoIterativo / 1_000_000.0);
            
            if (num_fibo <= 200) {
                System.out.printf("\nRecursivo: %d (Tiempo: %d ns | %.3f ms)", 
                                 resultadoRecursivo, tiempoRecursivo, tiempoRecursivo / 1_000_000.0);
                
                // Comparar eficiencia
                if (tiempoRecursivo > 0) {
                    double diferencia = (double) tiempoRecursivo / tiempoIterativo;
                    /* diferencia es la variabale que me indica cuantas veces es mas rapido el metodo iterativo que el recursivo calculdo 
                    como el tiempo del recursivo dividido por el tiempo del iterativo dando como resultado un numero en nanosegundos que indica cuantas veces es mas
                     rapido que el recursivo */
                    System.out.printf("\nEl recursivo es %.1f veces más lento que el iterativo ", diferencia);
                }
            }
        }
    }
    /* este metodo cuenta la cantidad de numeros pares en un arreglo de enteros tanto en su version iterativa como en su version recursiva */
    //version iterativa
    static int conteo_pares_inter(int[] arr){
        int conteo = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] % 2 == 0){
                conteo++;
            }
        }
        return conteo;
    }
    
    //version recursiva   
    static int conteo_pares_rec2(int[] arr, int ini){
        int par = 0;
        //CASO BASE
        if(ini == arr.length){
            return 0;
        } 
        //evalucion de cada subproblema       
        if(arr[ini] % 2 == 0){
                par = 1;
            } else{
                par = 0;
            } 
        //consolidacion de resultados y particion del problema
        return par + conteo_pares_rec2(arr, ini + 1);
    }

    /* este metodo calcula el factorial de un numero n de forma recursiva usando la definicion matematica de factorial 
    pero no es eficiente para n muy grandes debido a la profundidad de la pila de llamadas */
    static int factorial_rec(int n){
        //CASO BASE
        if(n <= 1 || n == 0){
            return 1;
        }else{//particion del problema, solucion de subproblema y consolidacion de resultados
            return n * factorial_rec(n - 1);
        }
    }
    /* este metodo calcula el factorial de un numero n de forma recursiva usando la definicion matematica de factorial
    pero no es eficiente para n muy grandes debido a la profundidad de la pila de llamadas, la complejidad temporal es O(2^n) esto se debe a que
    cada llamada a fibonacci_rec genera dos llamadas recursivas adicionales, lo que lleva a una explosion exponencial en el numero de llamadas a medida que n aumenta */
    static int fibonacci_rec(int n){
        //CASO BASE
        if(n == 1 || n == 0){
            return 1;
        }else{//particion del problema, solucion de subproblema y consolidacion de resultados
            return fibonacci_rec(n - 1) + fibonacci_rec(n - 2);
        }
    }
    /* este metodo calcula el n-esimo termino de la secuencia de Fibonacci de forma iterativa usando un arreglo para
    almacenar los valores previamente calculados de la secuencia y asi evitar recalcularlos pero consume mas memoria, la compeljidad
    temporal y espacial es O(n) debido a que se itera una vez a traves de los numeros hasta n y se almacena cada valor en un arreglo */
    static long fibo_Iterativo(int n)
    {
        /* inicializar los dos primeros términos de la secuencia para usarlos en el cálculo de los términos siguientes y asi evitar recalcularlos como condicion base */
        fibo[0] = 1;
        fibo[1] = 1;
        //int fiboN = 1;
        /* caso base, los dos primeros términos de la secuencia son  1 valores ya inicializados permitira evitar 
        recalcularlos y asi evitar llamadas recursivas innecesarias */
        if(n == 0 || n == 1) /*  n <= 1   */
        {
            return 1;
        }
        else
        {
            /* calcular términos de la secuencia hasta n de forma iterativa permitiendo evitar la sobrecarga de llamadas recursivas y
             asi mejorar la eficiencia debido a que cada término se calcula una sola vez */
            for(int i = 2; i <= n; i++)
            {
                 fibo[i] = fibo[i-1] + fibo[i-2];
            }
        }
        // retornar término n de la secuencia almacenado en el arreglo fibo y asi mejorar la eficiencia evitando recalcularlo
        return fibo[n];
    }    
}

    
