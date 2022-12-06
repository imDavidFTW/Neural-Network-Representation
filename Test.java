//Class used for testing
public class Test{
    public static void main(String[] args){
        long startTime = System.nanoTime();
        Filters filters = new Filters(11, 96, -1.0, 3.0);
        for(int i = 0; i < filters.depth; i++)
        {
            for(int j = 0; j < filters.m; j++)
            {
                for(int k = 0; k < filters.m; k++)
                    System.out.print(filters.appliedFilters.get(i)[j][k] + "  ");
            }
            System.out.println("  ");
            System.out.println("  ");
            System.out.println(i);
            System.out.println("  ");
            System.out.println("  ");   
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime)/1000000;  //divide by 1000000 to get milliseconds.
        System.out.println(duration);
    } 
}
