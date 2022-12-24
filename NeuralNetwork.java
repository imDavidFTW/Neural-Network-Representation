//The network which will take an input that will be a dataset of images
//Then will perform forward propegation with random weights through the various layers
//Then calculate the error of the output and perform backward propegation
//to calculate the new weight values
//The result of the weights at the end of training will 
//be the neural network used for testing 
public class NeuralNetwork {
    public Layer[] layers;
    private double[] softmaxLayer = new double[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };// Use this to store the values obtained
                                                                               // from the softmax function
    // public double error;
    // private double[] expectedResultsForInput = new double[] {0.01, 0.01, 0.01,
    // 0.01, 0.01, 0.01, 0.01, 0.01, 0.01, 0.01};

    public NeuralNetwork(Layer[] layers) {

    }

    // For more clarification go to power point 7 I sent you
    private void ForwardPropegation() {
        // For the input of each neuron sum the weights times the outputs of the
        // previous neurons plus the bias
        // For the output of each neuron use the sigmoid activation function
        // 1/(1+e^(-x)) with x being the input value calculated
        // Then calculate your values for the double[] softmaxLayer using the softmax
        // function and the output values from last layer of the NN
        // Softmax makes a vector of probabilites
        for (int i = 1; i < layers.length; i++) {
            for (Neuron n : layers[i].layer) {
                Neuron[] prevNeuronLay = layers[i - 1].layer;
                for (int j = 0; j < prevNeuronLay.length; i++) {
                    n.input += prevNeuronLay[j].output * n.weights[j];
                }
                n.input += layers[i - 1].bias;
                // Calculating output from the input, using the Sigmoid Function
                n.output = 1 / (1 + Math.pow(Math.E, -1 * n.input));
            }
        }
        // Implementing Softmax function on last layer
        double sumExpOutputs = 0;
        Neuron[] lastNeuronLayer = layers[layers.length - 1].layer;
        for (Neuron n : lastNeuronLayer) {
            sumExpOutputs += Math.exp(n.input);
        }
        for (int i = 0; i < softmaxLayer.length; i++) {
            softmaxLayer[i] = Math.exp(lastNeuronLayer[i].output) / sumExpOutputs;
        }
    }

    /*
     * private void BackWardPropegation(){
     * 
     * }
     * 
     * private void inputNum(int n){
     * this.expectedResultsForInput[n - 1] = 0.91;
     * }
     * 
     * public void err(Layer outputLayer){
     * int length = outputLayer.layer.length;
     * for(int i = 0; i < length; i++){
     * error += 0.5*Math.pow((outputLayer.layer[i].output -
     * this.expectedResultsForInput[i]), 2);
     * }
     * }
     */
}
