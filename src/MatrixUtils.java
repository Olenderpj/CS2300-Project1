import java.util.ArrayList;

abstract public class MatrixUtils {

    public static String buildNewRowWiseMatrix(int rows, ArrayList<Double> matrixData) {
        String data = "";

        for(int i = 0; i < rows; i++){
            ArrayList<Double> rowWiseData = findEveryNthElement(i, rows, matrixData);
            for (Double value: rowWiseData){
                data += value + " ";
            }
        }
        return data;
    }


    public static ArrayList<Double> findEveryNthElement(final int start, final int steps, final ArrayList matrix){
        ArrayList<Double> rowData= new ArrayList<>();

        for(int i = start; i < matrix.size(); i += steps){
            rowData.add(Double.parseDouble(matrix.get(i).toString()));
        }
        return rowData;
    }

    public static void printRowWiseMatrix(ArrayList<ArrayList<Double>> matrixRows){
        for (Object row : matrixRows){
            System.out.println(row);
        }
    }
}
