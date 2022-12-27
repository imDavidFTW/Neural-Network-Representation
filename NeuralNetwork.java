//The network which will take an input that will be a dataset of images
//Then will perform forward propegation with random weights through the various layers
//Then calculate the error of the output and perform backward propegation
//to calculate the new weight values
//The result of the weights at the end of training will 
//be the neural network used for testing
import java.util.ArrayList; 
public class NeuralNetwork {
    
    public Layer[] layers;
    public int networkSize;//gives how many layers the network has
    public double totalLoss;// Here we use loss because of the softMax function 
    private double[] expectedResultsForInput;
    private int cur;//the current index we are at in the batch. Ex. batch size is 15 images. At cur == 8 we have done 8/15 forward propegations. Once we hit 15 we do back propegation
    public double[][] Loss;//matrix containing the loss for each forward pass of a batch
    public double[][] CrossEntropyLoss_SoftMaxDerivativeMatrix;//Cross entropy loss derivative for the back propegation
    public ArrayList<Integer> testNums;
    public NeuralNetwork(Layer[] layers, int batchSize) {
        this.layers = layers;
        this.CrossEntropyLoss_SoftMaxDerivativeMatrix = new double[batchSize][layers[layers.length - 1].layer.length];
        this.Loss = new double[batchSize][layers[layers.length - 1].layer.length];
        this.networkSize = layers.length;
        this.testNums = new ArrayList<>();
        this.totalLoss = 0;
        this.cur = 0;
    }

    // For more clarification go to power point 7 I sent you
    private void ForwardPropegation(int batchSize, int inputVal, String path) {
        // For the input of each neuron sum the weights times the outputs of the
        // previous neurons plus the bias
        // For the output of each neuron use the ReLu activation function 
        // If the value is positive it remains the same if it is negative the output is 0
        // This was changed for speed and efficiency when calculating the derivatives in back propegation
        // Then calculate your values for the output values for last layer of the NN (Softmax makes a vector of probabilites)
        for (int i = 1; i < layers.length; i++) {
            for (Neuron n : layers[i].layer) {
                Neuron[] prevNeuronLay = layers[i - 1].layer;
                for (int j = 0; j < prevNeuronLay.length; j++) {
                    n.input += prevNeuronLay[j].output * n.weights[j];
                }
                n.input += layers[i - 1].bias;
                // Calculating output from the input, using the ReLu Function
                n.output = n.input >= 0 ? n.input : 0;
            }
        }
        // Implementing Softmax function on last layer
        double sumExpOutputs = 0;
        double max = 0;
        Neuron[] lastNeuronLayer = layers[layers.length - 1].layer;
        for (Neuron n : lastNeuronLayer) {
            if(n.input > max)
                max = n.input;
        }
        for (Neuron n : lastNeuronLayer) {
            double shrink = n.input - max;//make the value smaller
            sumExpOutputs += Math.exp(shrink);// we need the max in order to subtract it from all our inputs for the softmax function 
                                                    //this prevents us from having the issue of getting a number too large for calculating the softmax
                                                    //this was resulting in us getting infinity values so we were getting a NaN error in later calculations                                 
        }
        int l = lastNeuronLayer.length;
        for (int i = 0; i < l; i++) {
            double shrink = lastNeuronLayer[i].input - max;//make the value smaller
            lastNeuronLayer[i].output = Math.exp(shrink) / sumExpOutputs;
        }
        this.expectedResultsForInput = new double[] {0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0};
        this.expectedResultsForInput[inputVal] = 1.0;
        Loss[cur] = loss(this.layers[layers.length - 1]);
        testNums.add(inputVal);
        softMaxCrossEntropyDer(batchSize, cur);
    }

    private void BackWardPropegation(){
        
     }
  
     public double[] loss(Layer outputLayer){
        int length = outputLayer.layer.length;
        double[] Loss = new double[length];
        for(int i = 0; i < length; i++){
            if(outputLayer.layer[i].output == 0){//in a case the output is 0
                totalLoss += -Math.log(0.0000001) * this.expectedResultsForInput[i];
                Loss[i] = -Math.log(0.0000001) * this.expectedResultsForInput[i];
            }
            else{
                totalLoss += -Math.log(outputLayer.layer[i].output) * this.expectedResultsForInput[i];
                Loss[i] = -Math.log(outputLayer.layer[i].output) * this.expectedResultsForInput[i];
            }
        }
        return Loss;
    }

    public void softMaxCrossEntropyDer(int batchSize, int cur){
        int len1 = layers.length - 1;
        int len2 = layers[len1].layer.length;
        for(int j = 0; j < len2; j++)
        {   
            this.CrossEntropyLoss_SoftMaxDerivativeMatrix[cur][j] = (layers[len1].layer[j].output - expectedResultsForInput[j]) / batchSize;
        }
        this.cur+=1;
    }

    public void trainNetwork(int networkSize, String path, int batchSize){
        for(int i = 0; i < batchSize; i++)
            this.ForwardPropegation(networkSize, 0, path);
        this.totalLoss = this.totalLoss / batchSize;
    }

    public static void main(String[] args){
        int[] NPL = new int[]{28, 1008, 1008, 456, 10};
        Matrix<Boolean> m = Matrix.ImageToBooleanMatrix("C:\\Users\\xdryn\\OneDrive\\Documents\\Desktop\\OOP\\Neural-Network-Representation\\DigitDataset\\0\\image9001.png");
        Layer[] layers = Layer.hiddenLayers(NPL, m);
        NeuralNetwork n = new NeuralNetwork(layers, 3);
        n.trainNetwork(5, "C:\\Users\\xdryn\\OneDrive\\Documents\\Desktop\\OOP\\Neural-Network-Representation\\DigitDataset\\0\\image9001.png", 3);
        for(int i = 0; i < n.CrossEntropyLoss_SoftMaxDerivativeMatrix.length; i++){
            for(int j = 0; j < n.CrossEntropyLoss_SoftMaxDerivativeMatrix[i].length; j++){
                //System.out.println(n.layers[layers.length - 1].layer[j].input);
            }
            System.out.println();
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(n.totalLoss);
    }
}
