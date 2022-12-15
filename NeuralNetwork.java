//The network which will take an input that will be a dataset of images
//Then will perform forward propegation with random weights through the various layers
//Then calculate the error of the output and perform backward propegation
//to calculate the new weight values
//The result of the weights at the end of training will 
//be the neural network used for testing 
public class NeuralNetwork {
    
    public Neuron[] Layer(int numOfNeurons, int prevLayerSize){
        Neuron[] layer = new Neuron[numOfNeurons + 1];
        for(int i = 0; i < numOfNeurons; i++){
            layer[i] = new Neuron(prevLayerSize + 1);
        }
        layer[numOfNeurons] = new Neuron(numOfNeurons);
        double bias = layer[numOfNeurons].weights[0];
        for(int i = 1; i < layer[numOfNeurons].weights.length; i++){
            layer[numOfNeurons].weights[i] = bias;
        }
        layer[numOfNeurons].output = 1;
        return layer;
    }
    public Neuron[][] hiddenLayer(int numOfLayers, int[] neuronsPerLayer){
        Neuron[][] layers = new Neuron[numOfLayers][neuronsPerLayer.length];
        layers[0] = Layer(neuronsPerLayer[0], 0);
        for(int i = 1; i < numOfLayers; i++){
            layers[i] = Layer(neuronsPerLayer[i], neuronsPerLayer[i - 1]);
        }
        return layers;
    }
    
    public static Neuron[] booleanMatrixToInputLayer(Matrix<Boolean> m){
        Neuron[] inputLayer = new Neuron[m.getRows()*m.getColumns()];
        int counter = 0;
        for(int i = 0; i < m.getRows(); i++){
            for(int j = 0; j < m.getColumns(); j++){
                inputLayer[counter++] = new Neuron(0, m.get(i, j) ? 1 : 0);
            }
        }
        return inputLayer;
    }

}
