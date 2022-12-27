import java.util.concurrent.ThreadLocalRandom;
public class Neuron {
    public double input;
    public double output;
    public double[] weights;
    public Neuron(int numOfWeights){
            this.weights = ThreadLocalRandom.current().doubles(numOfWeights, -3.0, 3.0).toArray();
            this.input = 0;
            this.output = 0;
    }

    //Overloaded constructor for the input layer
    public Neuron(int numOfWeights, int output){
        this.weights = ThreadLocalRandom.current().doubles(numOfWeights, -3.0, 3.0).toArray();
        this.input = 0;
        this.output = output;
    }
    
}
