import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Matrix {
    private String variableName;
    private int rows;
    private int columns;
    private ArrayList<Integer> matrixData;
    private ArrayList<Double> multipliedMatrixData;
    private ArrayList matrixColumns = new ArrayList();
    private ArrayList matrixRows = new ArrayList();
    private Logger logger = Logger.getAnonymousLogger();

    public String getVariableName() {
        return variableName;
    }

    public int getRows() {
        return rows;
    }

    public int getWidth() {
        return columns;
    }

    public ArrayList getMatrixData() {
        return matrixData;
    }

    public Matrix setVariableName(String variableName) {
        this.variableName = variableName;
        return this;
    }

    public Matrix setRows(int rows) {
        this.rows = rows;
        return this;
    }

    public Matrix setColumns(int columns) {
        this.columns = columns;
        return this;
    }

    public Matrix setMatrixData(ArrayList matrixData) {
        this.matrixData = matrixData;
        return this;
    }

    public void buildColumnWiseMatrix(){
        int stopPoint = matrixData.size();
        int startIndex = 0;
        int endIndex = rows;

        while (endIndex <= stopPoint){
            List matrixColumn = matrixData.subList(startIndex, endIndex);
            matrixColumns.add(matrixColumn);
            startIndex += rows;
            endIndex += rows;
        }
    }

    public void buildRowWiseMatrix(){
        for (int i = 0; i < rows; i++){
            matrixRows.add(findEveryNthElement(i, rows, matrixData));
        }
    }

    private ArrayList<Integer> findEveryNthElement(final int start, final int steps, final ArrayList matrix){
        ArrayList<Integer> rowData= new ArrayList<>();

        for(int i = start; i < matrix.size(); i += steps){
            rowData.add(Integer.parseInt(matrix.get(i).toString()));
        }
        return rowData;
    }

    public void multiplyMatrix(double coefficient, ArrayList<Integer> matrix){
        ArrayList<Double> newMatrix = new ArrayList<>();

        for(Integer number: matrix){
            newMatrix.add(number * coefficient);
        }
        multipliedMatrixData = newMatrix;
    }

    public void printRowWiseMatrix(){
        for (Object row : matrixRows){
            System.out.println(row);
        }
    }

    @Override
    public String toString() {
        return "Matrix{" +
                "variableName='" + variableName + '\'' +
                ", height=" + rows +
                ", width=" + columns +
                ", matrixData=" + matrixData +
                '}';
    }
}
