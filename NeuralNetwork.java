//The network which will take an input that will be a dataset of images
//Then will perform forward propegation with random weights through the various layers
//Then calculate the error of the output and perform backward propegation
//to calculate the new weight values
//The result of the weights at the end of training will 
//be the neural network used for testing 
public class NeuralNetwork {
    
    public Neuron[] Layer(int numOfNeurons, int prevLayerSize){
        Neuron[] layer = new Neuron[numOfNeurons];
        for(int i = 0; i < numOfNeurons; i++){
            layer[i] = new Neuron(prevLayerSize);
        }
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
        

}
