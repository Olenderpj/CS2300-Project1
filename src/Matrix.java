import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Matrix {
    private String variableName;
    private int rows;
    private int columns;
    private ArrayList<Double> matrixData;
    private ArrayList<Double> multipliedMatrixData;
    private ArrayList<ArrayList<Double>> matrixColumns = new ArrayList<>();
    private ArrayList<ArrayList<Double>> matrixRows = new ArrayList();

    private Logger logger = Logger.getAnonymousLogger();

    public ArrayList getMatrixColumns() {
        return matrixColumns;
    }

    public ArrayList getMatrixRows() {
        return matrixRows;
    }

    public String getVariableName() {
        return variableName;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public ArrayList getMatrixData() {
        return matrixData;
    }

    public ArrayList<Double> getMultipliedMatrixData() {
        return multipliedMatrixData;
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
            List<Double> sublist = matrixData.subList(startIndex, endIndex);
            ArrayList<Double> tempList = new ArrayList<>();
            for (Double value: sublist){
                tempList.add(value);
            }
            matrixColumns.add(tempList);
            startIndex += rows;
            endIndex += rows;
        }
    }

    public void buildRowWiseMatrix(){
        for (int i = 0; i < rows; i++){
            matrixRows.add(findEveryNthElement(i, rows, matrixData));
        }
    }

    private ArrayList<Double> findEveryNthElement(final int start, final int steps, final ArrayList matrix){
        ArrayList<Double> rowData= new ArrayList<>();

        for(int i = start; i < matrix.size(); i += steps){
            rowData.add(Double.parseDouble(matrix.get(i).toString()));
        }
        return rowData;
    }

    public void multiplyMatrix(double coefficient){
        ArrayList<Double> newMatrix = new ArrayList<>();
        for(Double number: matrixData){
            newMatrix.add(number * coefficient);
        }
        multipliedMatrixData = newMatrix;
    }

    public void printRowWiseMatrix(){
        for (Object row : matrixRows){
            System.out.println(row);
        }
    }

    public String getMatrixMetadata(){
        String metadata = "";
        metadata += variableName + " ";
        metadata += rows + " ";
        metadata += columns + " ";

        for (Double data : matrixData) {
           metadata += data.toString() + " ";
        }
        return metadata;
    }


    @Override
    public String toString(){
        return "Matrix{" +
                "variableName='" + variableName + '\'' +
                ", rows=" + rows +
                ", columns=" + columns +
                ", matrixData=" + matrixData +
                ", multipliedMatrixData=" + multipliedMatrixData +
                ", matrixColumns=" + matrixColumns +
                ", matrixRows=" + matrixRows +
                '}';
    }
}
