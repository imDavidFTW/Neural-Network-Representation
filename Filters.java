//This class will be used for creating the filters used for convolution
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.lang.Math;
public class Filters{
    protected int depth;
    protected int m;
    protected ArrayList<double[][]> appliedFilters;
    public Filters(int m, int depth){
        appliedFilters = new ArrayList<>();
        this.m = m;
        this.depth = depth;
        for(int d = 0; d < depth; d++){
            double[][] filter = new double[m][m];
            double[] randoms = ThreadLocalRandom.current().doubles(m*m, -1.0, 5).toArray();
            for(int i = 0; i < m; i++){
                for(int j = i * m; j < i * m + m; j++){
                    filter[i][j%m] = Math.round(randoms[j]);
                }
            }
            appliedFilters.add(filter);
        }
    }
}

