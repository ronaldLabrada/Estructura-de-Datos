public class RecursionCola {

    public static void main(String [] args){
        
        int [] nums = {1,2,3,4,5,6,7,8,9,10};
        System.out.printf("la suma es: %d (usando metodo recursivo cola)", sum(nums, nums.length));    

    }
    static int sum (int arr[], int size){
            if (size == 0) {
                return 0;
            } else {
                return arr[size - 1] + sum(arr, size - 1);
            }
        }

    static int sum (int arr[], int size, int acum){
        if (size == 0) {
            return acum;
        } else {
            return sum(arr, size - 1, acum + arr[size - 1]);
        }
    }
}