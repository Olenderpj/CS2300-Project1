import java.util.ArrayList;

public class Matrix {
    private String variableName;
    private int height;
    private int width;
    private ArrayList matrixData;

    public String getVariableName() {
        return variableName;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public ArrayList getMatrixData() {
        return matrixData;
    }

    public Matrix setVariableName(String variableName) {
        this.variableName = variableName;
        return this;
    }

    public Matrix setHeight(int height) {
        this.height = height;
        return this;
    }

    public Matrix setWidth(int width) {
        this.width = width;
        return this;
    }

    public Matrix setMatrixData(ArrayList matrixData) {
        this.matrixData = matrixData;
        return this;
    }

    @Override
    public String toString() {
        return "Matrix{" +
                "variableName='" + variableName + '\'' +
                ", height=" + height +
                ", width=" + width +
                ", matrixData=" + matrixData +
                '}';
    }
}
