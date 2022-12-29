import java.util.concurrent.ThreadLocalRandom;
public class Layer {
    public int numOfNeurons;
    public int prevLayerSize;
    public Neuron[] layer;
    public double bias[];
    private double weights[][];//makes a matrix of all weights
    private double dweights[][];//makes a matrix of derivatives of all weights
    public Layer(int numOfNeurons, int prevLayerSize){
        this.numOfNeurons = numOfNeurons;
        this.prevLayerSize = prevLayerSize;
        this.layer = new Neuron[numOfNeurons];
        for(int i = 0; i < numOfNeurons; i++){
            this.layer[i] = new Neuron(prevLayerSize);
        }
        this.bias = ThreadLocalRandom.current().doubles(numOfNeurons, 0, 1).toArray();
    }

    public static Layer[] hiddenLayers(int[] neuronsPerLayer, Matrix<Boolean> m){
        int length = neuronsPerLayer.length;
        Layer[] layers = new Layer[length];
        layers[0] = new Layer(m);
        for(int i = 1; i < length; i++){
            if(i == 1)
                layers[i] = new Layer(neuronsPerLayer[i], neuronsPerLayer[i - 1] * neuronsPerLayer[i - 1]);
            else
                layers[i] = new Layer(neuronsPerLayer[i], neuronsPerLayer[i - 1]);
        }
        return layers;
    }

    public Layer(Matrix<Boolean> m){
        this.layer = new Neuron[m.getRows()*m.getColumns()];
        this.numOfNeurons = m.getRows()*m.getColumns();
        this.prevLayerSize = 0;
        this.bias = new double[0];
        int counter = 0;
        for(int i = 0; i < m.getRows(); i++){
            for(int j = 0; j < m.getColumns(); j++){
                this.layer[counter++] = new Neuron(0, m.get(i, j) ? 1 : 0);
            }
        }
    }
}

