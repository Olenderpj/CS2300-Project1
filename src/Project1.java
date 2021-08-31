/**
 * Name: PJ Olender
 * Class: CS 2300 Fall 2021
 * Due Date: 9/14/2021
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class Project1 {
    public static ArrayList<Matrix> matrices;
    public static Path filePath = Path.of("TestFiles/matrixFile.txt");

    public static void main(String[] args) throws FileNotFoundException {
        Logger logger = Logger.getAnonymousLogger();
        try {
            List<String> lines = Files.readAllLines(filePath);
            for(String line: lines){
                if (line.length() > 0) {
                    ArrayList<String> matrixArray = convertMatrixDataToArray(line);
                    Matrix matrix = buildMatrixObject(matrixArray);
                    matrices.add(matrix);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print(matrices);
    }

    public static ArrayList<String> convertMatrixDataToArray(final String matrixData){
        ArrayList<String> matrixDataArrayList = new ArrayList<>();
        String[] matrixDataArray = matrixData.split(" ");
        for (String data: matrixDataArray){
            if (data != ""){
                matrixDataArrayList.add(data);
            }
        }
        return matrixDataArrayList;
    }

    public static Matrix buildMatrixObject(final ArrayList<String> matrixData){
        Matrix matrix = new Matrix();
        matrix.setVariableName(matrixData.get(0))
                .setHeight(Integer.parseInt(matrixData.get(1)))
                .setWidth(Integer.parseInt(matrixData.get(2)))
                .setMatrixData(convertArraylistFromStringToInt(matrixData.subList(3, matrixData.size())));
        return matrix;
    }
    public static ArrayList<Integer> convertArraylistFromStringToInt(final List<String> matrixDataAsString){
        ArrayList<Integer> arrayListData = new ArrayList<>();

        for (String data: matrixDataAsString){
            arrayListData.add(Integer.parseInt(data));
        }
        return arrayListData;
    }
}
