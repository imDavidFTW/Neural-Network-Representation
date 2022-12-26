import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//class will be used to convert an input image to a matrix
//will also perform the convolutions on the image and the max/avg pooling 
//to extract important features from the image
public class Matrix<T> {
    T[][] matrix;
    int m;
    int n;

    public Matrix(int m, int n) {
        this.matrix = (T[][]) new Object[m][n];
        this.m = m;
        this.n = n;
    }

    public int getRows(){
        return m;
    }

    public int getColumns(){
        return n;
    }
    
    public T get(int i, int j) {
        return matrix[i][j];
    }

    public void set(int i, int j, T val) {
        this.matrix[i][j] = val;
    }

    public static Matrix<Boolean> ImageToBooleanMatrix(String pathname) {
        try {
            File pathToFile = new File(pathname);
            BufferedImage image = ImageIO.read(pathToFile);
            Matrix<Boolean> currMatrix = new Matrix<>(image.getHeight(), image.getWidth());
            for (int i = 0; i < image.getHeight(); i++) {
                for (int j = 0; j < image.getWidth(); j++) {
                    currMatrix.set(i, j, image.getRGB(j, i) < -9000000 ? false : true);
                }
            }
            return currMatrix;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return new Matrix<>(0, 0);
    }
}