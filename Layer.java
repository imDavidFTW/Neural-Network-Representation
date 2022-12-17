public class Layer {
    public int numOfNeurons;
    public int prevLayerSize;
    public Neuron[] layer;
    public double bias;
    public Layer(int numOfNeurons, int prevLayerSize){
        this.numOfNeurons = numOfNeurons;
        this.prevLayerSize = prevLayerSize;
        this.layer = new Neuron[numOfNeurons];
        for(int i = 0; i < numOfNeurons; i++){
            this.layer[i] = new Neuron(prevLayerSize);
        }
        this.bias = Math.random();
    }

    public static Layer[] hiddenLayers(int[] neuronsPerLayer, Matrix<Boolean> m){
        int length = neuronsPerLayer.length;
        Layer[] layers = new Layer[length];
        layers[0] = new Layer(m);
        for(int i = 1; i < length; i++){
            layers[i] = new Layer(neuronsPerLayer[i], neuronsPerLayer[i - 1]);
        }
        return layers;
    }

    public Layer(Matrix<Boolean> m){
        this.layer = new Neuron[m.getRows()*m.getColumns()];
        this.numOfNeurons = m.getRows()*m.getColumns();
        this.prevLayerSize = 0;
        this.bias = 0;
        int counter = 0;
        for(int i = 0; i < m.getRows(); i++){
            for(int j = 0; j < m.getColumns(); j++){
                this.layer[counter++] = new Neuron(0, m.get(i, j) ? 1 : 0);
            }
        }
    }
}

