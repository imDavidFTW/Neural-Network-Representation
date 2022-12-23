//The network which will take an input that will be a dataset of images
//Then will perform forward propegation with random weights through the various layers
//Then calculate the error of the output and perform backward propegation
//to calculate the new weight values
//The result of the weights at the end of training will 
//be the neural network used for testing 
public class NeuralNetwork {
    public Layer[] layers;
    public double error;
    private Layer outputLayer;
    private double[] expectedResultsForInput = new double[] {0.01, 0.01, 0.01, 0.01, 0.01, 0.01, 0.01, 0.01, 0.01, 0.01};
    public NeuralNetwork(Layer[] layers){

    }

    private void ForwardPropegation(){

    }

    private void BackWardPropegation(){
        
    }

    private void inputNum(int n){
        this.expectedResultsForInput[n - 1] = 0.91;
    }

    public void err(Layer outputLayer){
        int length = outputLayer.layer.length;
        for(int i = 0; i < length; i++){
            error += 0.5*Math.pow((outputLayer.layer[i].output - this.expectedResultsForInput[i]), 2);
        }
    }
}
