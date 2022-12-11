//Class used for testing
public class Test{
    public static void main(String[] args){
        long startTime = System.nanoTime();
        Filters filters = new Filters(3, 5, -1.0, 1.0);
        System.out.println(filters.toString());
        long endTime = System.nanoTime();
        double duration = (double)((double)(endTime - startTime)/1000000.0)/1000.0;  //divide by 1000000 to get milliseconds.
        System.out.println(duration);
    } 
}