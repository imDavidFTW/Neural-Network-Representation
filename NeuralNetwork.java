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
}
