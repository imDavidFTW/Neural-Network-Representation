//This class will be used for creating the filters used for convolution
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.lang.Math;
public class Filters{
    protected int depth;
    protected int m;
    protected ArrayList<double[][]> appliedFilters;
    public Filters(int m, int depth, double lowerBound, double upperBound){
        appliedFilters = new ArrayList<>();
        this.m = m;
        this.depth = depth;
        for(int d = 0; d < depth; d++){
            double[][] filter = new double[m][m];
            double[] randoms = ThreadLocalRandom.current().doubles(m*m, lowerBound, upperBound).toArray();
            for(int i = 0; i < m; i++){
                for(int j = i * m; j < i * m + m; j++){
                    filter[i][j%m] = Math.round(randoms[j]);
                }
            }
            appliedFilters.add(filter);
        }
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("[");
        for(int i = 0; i < this.depth; i++)
        {
            s.append("[");
            for(int j = 0; j < this.m; j++)
            {
                s.append("[");
                for(int k = 0; k < this.m; k++){
                    if(k == this.m - 1){
                        s.append(this.appliedFilters.get(i)[j][k]);   
                    }
                    else
                        s.append(this.appliedFilters.get(i)[j][k] + ", ");
                }
                if(j == this.m - 1)
                    s.append("]");
                else
                    s.append("], ");
                if (this.m - 1 != j){
                    s.append(System.getProperty("line.separator"));
                }
            }
            if(i == this.depth - 1)
                s.append("]");
            else
                s.append("], ");
            if(this.depth - 1 != i)
            {
                s.append(System.getProperty("line.separator"));
                s.append(System.getProperty("line.separator"));
            }
        }
        s.append("]");
        return s.toString();
    }
}

