
import java.util.concurrent.ThreadLocalRandom;
public class Neuron {
    public double input;
    public double output;
    public double[] weights;
    public Neuron(int numOfWeights){
            this.weights = ThreadLocalRandom.current().doubles(numOfWeights, 0, 1).toArray();
            this.input = 0;
            this.output = 0;
    }  
}
