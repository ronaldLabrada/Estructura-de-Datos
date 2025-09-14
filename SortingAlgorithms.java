public class SortingAlgorithms
{
    public static void main(String[] args)
    {
      int[] arr1 ={1, 3, 5, 9, 0, 2, 12, 6, 4};
      System.out.print("Arreglo desordenado\n");
      printArray(arr1);
      int[] sorted1= bubbleSort(arr1);
      System.out.print("\nArreglo ordenado\n");
      printArray(sorted1);      

    }

    public static void printArray(int[] arr){
      for(int i = 0; i < arr.length; i++){
        System.out.print(arr[i]);
      }
    }

    public static int[] bubbleSort(int[] arr)
    {
      boolean swapped;
      for(int i = 0; i < arr.length; i++)
      {
        swapped = false;
        for(int j = arr.length-1; j > 1; j--)
        {
          if(arr[j] < arr[j-1])
          {
            int temp = arr[j-1];
            arr[j-1] = arr[j];
            arr[j] = temp;
            swapped = true;
          }
        }
        if(!swapped) break; // no sigue comparando
      }
      return arr;

    }

   
}