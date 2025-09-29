public class EjerciciosRecursion {
    public static void main(String [] args){
        int [] nums = {1,3,12,4,7,12,8,12};
        System.out.printf("El factorial de 5 es: %d", factorial_rec(5));
        System.out.println("\nEl numero fibonacci de 7 es: " + fibonacci_rec(7));
        System.out.println("La suma de los digitos de 12345 es: " + sumaDigitos(12345));
        System.out.println("El mayor comun divisor de -48 y -18 es: " + mcdEucllides(-48, -18));
        System.out.println("La cadena 'hola mundo' invertida es: " + invertirCadena("hola mundo"));
        System.out.println("La cadena 'anita lava la tina' es palindromo? " + palindromo("anita lava la tina"));
        System.out.println("La cadena 'anilina' es palindromo? " + palindromo("anilina"));
        System.out.println("la suma de los elementos del arreglo nums es: " + sumaArreglo(nums, nums.length));
        System.out.println("El numero 12 se repite " + numeroDeVeces(nums, nums.length, 12) + " veces en el arreglo nums");
        System.out.println("Numeros ascendentes hasta 5:");
        ascendente(5);
        System.out.println("Numeros descendente hasta 5:");
        descendente(5);
        System.out.println("Resolviendo Torres de Hanoi para 3 discos:");
        resolverHanoi(4, 'A', 'C', 'B'); // A,
    }       
    //metodo para calcular el factorial de un numero de forma recursiva
    static int factorial_rec(int n){
        if(n == 0){
            return 1; // caso base: el factorial de 0 es 1
        } else {
            return n * factorial_rec(n - 1); // caso recursivo
        }
    }
    static int fibonacci_rec(int n){
        //CASO BASE
        if(n == 1 || n == 0){
            return 1;
        }else{//particion del problema, solucion de subproblema y consolidacion de resultados
            return fibonacci_rec(n - 1) + fibonacci_rec(n - 2);
        }
    }
    static int sumaDigitos(int numero) {
        // Manejar números negativos convirtiéndolos a positivos
        if (numero < 0) {
            return sumaDigitos(-numero);
        }
        // Caso base: si el número es un solo dígito, retornar el número
        if (numero < 10) {
            return numero;
        }
        // Caso recursivo: último dígito (numero % 10) + suma del resto (numero / 10)
        return (numero % 10) + sumaDigitos(numero / 10);
    }
    static int mcdEucllides(int a, int b) {
        // Caso base: cuando b es 0, el MCD es a
        // el algoritmo de Euclides funciona realizando divisiones sucesivas y tomando el residuo, luego se repite el proceso con el divisor y el residuo hasta que el residuo sea cero.

        if (b == 0) {

            //usando la funcion Math.abs = valor absoluto; para asegurar que el MCD sea positivo
            return Math.abs(a); // MCD siempre es positivo
        }
        
        // Paso recursivo: MCD(a, b) = MCD(b, a mod b)
        return mcdEucllides(b, a % b);
    }
    static String invertirCadena(String cadena) {
        // Caso base: cadena vacía o de un solo carácter

        if (cadena == null || cadena.length() <= 1) {
            return cadena;
        }
        
        // Paso recursivo: último carácter + invertir(resto de la cadena)
        //con substring se obtiene la subcadena desde el inicio hasta el penultimo caracter (excluyendo el ultimo caracter)
        //con charAt se obtiene el ultimo caracter de la cadena
        //cadena.length() - 1 es la posicion del ultimo caracter
        return cadena.charAt(cadena.length() - 1) + 
               invertirCadena(cadena.substring(0, cadena.length() - 1));
    }

    static boolean palindromo(String cadena) {
        if (cadena == null) return false;
        
        // en la variable se almacena la cadena sin espacios y en minusculas usando la funcion replaceAll y toLowerCase
        //replaceAll("[^a-zA-Z0-9]", "") elimina todos los caracteres que no sean letras o numeros
        //toLowerCase() convierte la cadena a minusculas
        //es necesario normalizar la cadena para que la funcion recursiva funcione correctamente debido a que la comparacion de caracteres es sensible a mayusculas y minusculas y a espacios
        String normalizada = cadena.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        
        return esPalindromoRecursivo(normalizada);
    }
    
    static boolean esPalindromoRecursivo(String cadena) {
        // Caso base: si la cadena es vacía o tiene un solo carácter, es un palíndromo
        // Si la longitud de la cadena es 0 o 1, es un palíndromo
        // porque se lee igual de adelante hacia atrás y de atrás hacia adelante
        // Casos base
        
                if (cadena.length() <= 1) {
            return true;
        }
        
        // Comparar el primer y último carácter
        char primero = cadena.charAt(0);
        char ultimo = cadena.charAt(cadena.length() - 1);
        
        if (primero != ultimo) {
            return false;
        }
        
        // Llamada recursiva con el substring interior
        // ¡ESTA ES LA CLAVE! La operación principal (comparación) ocurre ANTES
        // y el resultado se propaga hacia arriba en la pila de llamadas
        String interior = cadena.substring(1, cadena.length() - 1);
        return esPalindromoRecursivo(interior);
    }
    static int sumaArreglo(int arr[], int size){
        if(size == 0){
            return 0;
        } else {
            return arr[size - 1] + sumaArreglo(arr, size - 1);
        }
    }
    static int numeroDeVeces(int arr[], int size, int num){
        if(size == 0){
            return 0;
        } else {
            if(arr[size - 1] == num){
                return 1 + numeroDeVeces(arr, size - 1, num);
            } else {
                return numeroDeVeces(arr, size - 1, num);
            }
        }
    }
    static void ascendente(int n){
        if(n < 0){
            System.out.println("El numero debe ser positivo");
            return;
        }
        if(n == 0){
            System.out.println(0);
        } else {
            ascendente(n - 1);
            System.out.println(n);
        }
    }
    static void descendente(int n){
        if(n < 0){
            System.out.println("El numero debe ser positivo");
            return;
        }
        if(n == 0){
            System.out.println(0);
        } else {
            System.out.println(n);
            descendente(n - 1);
        }
    }
    public static void resolverHanoi(int n, char origen, char destino, char auxiliar) {
        // Caso base: si solo hay un disco
        if (n == 1) {
            System.out.println("Mover disco 1 de " + origen + " a " + destino);
            return;
        }       
        // Paso 1: Mover n-1 discos de origen a auxiliar, usando destino como auxiliar
        resolverHanoi(n - 1, origen, auxiliar, destino);
        
        // Paso 2: Mover el disco más grande de origen a destino
        System.out.println("Mover disco " + n + " de " + origen + " a " + destino);
        
        // Paso 3: Mover los n-1 discos de auxiliar a destino, usando origen como auxiliar
        resolverHanoi(n - 1, auxiliar, destino, origen);
    }
}