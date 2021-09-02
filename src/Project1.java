/**
 * Name: PJ Olender
 * Class: CS 2300 Fall 2021
 * Due Date: 9/14/2021
 * Project 1, matrix calculations
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class Project1 {
    public static ArrayList<Matrix> matrices = new ArrayList<>();
    public static Path filePath = Path.of("TestFiles/matrixFile.txt");
    public static Logger logger = Logger.getAnonymousLogger();

    public static void main(String[] args) throws IOException {

        try {
            List<String> lines = Files.readAllLines(filePath);
            for(String line: lines){
                if (line.length() > 0) {
                    ArrayList<String> matrixArray = convertMatrixDataToArray(line);
                    Matrix matrix = buildMatrixObject(matrixArray);
                    matrices.add(matrix);
                    matrix.buildColumnWiseMatrix();
                    matrix.buildRowWiseMatrix();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        printFormattedMatrix("First Matrix", matrices.get(0).getMatrixRows());
        printFormattedMatrix("Second Matrix", matrices.get(1).getMatrixRows());

        createNewTextFile("FirstMatrix");
        writeToFile("FirstMatrix.txt", matrices.get(0));
        createNewTextFile("SecondMatrix");
        writeToFile("SecondMatrix.txt", matrices.get(1));

        // Calculate the resulting matrix of "C = 10.5 * B - 8.0 * A"
        ArrayList matrixC = calculateNewMatrixData(matrices.get(0), 10.5, matrices.get(1), 8.0);
        String finalMatrixData = "C 6 5 " + MatrixUtils.buildNewRowWiseMatrix(matrices.get(0).getRows(), matrixC);

        // Create a new matrix object for matrix C and build the matrices in the object
        ArrayList<String> finalMatrix = convertMatrixDataToArray(finalMatrixData);
        Matrix matrix = buildMatrixObject(finalMatrix);
        matrices.add(matrix);
        matrix.buildRowWiseMatrix();
        matrix.buildColumnWiseMatrix();

        // create and write to new files for the calculated and transposed matrices.
        createNewTextFile("CalcMatrix");
        writeToFile("CalcMatrix.txt", matrices.get(2));
        createNewTextFile("transposedMatrix");
        writeToFile("transposedMatrix.txt",matrices.get(2).getVariableName(), matrices.get(2).getRows(), matrices.get(2).getColumns(), matrices.get(2).getMatrixRows());

        printFormattedMatrix("Row Major Matrix: (Original Matrix 2)", matrices.get(1).getMatrixColumns());
        printFormattedMatrix("Column Major Matrix: (Transposed Matrix 2)", matrices.get(1).getMatrixRows());

    }

    public static void printFormattedMatrix(final String matrixName, final ArrayList<ArrayList<Double>> matrix){
        System.out.println(matrixName);
        for (ArrayList array: matrix){
            System.out.println(array);
        }
        System.out.println();
    }
    public static void createNewTextFile(final String fileName){
        try{
            File newFile = new File(fileName + ".txt");
            if (newFile.createNewFile()) {
                logger.info(LoggingUtils.formatLogMessage(fileName, "New File Created"));
            } else {
                logger.warning(LoggingUtils.formatLogMessage(fileName, "File Already Exists and was overwritten with new data"));
            }
        } catch(IOException e){
                logger.severe(LoggingUtils.formatLogMessage(fileName, "Something went wrong when creating the file"));
            }
    }

    public static void writeToFile(final String fileName, Matrix matrix){
        try{
            FileWriter fileWriter= new FileWriter(fileName);
            fileWriter.write(matrix.getMatrixMetadata());
            fileWriter.close();
            logger.info(LoggingUtils.formatLogMessage(fileName, "Successfully Wrote Data to File"));

        } catch (IOException e) {
            logger.warning(LoggingUtils.formatLogMessage(fileName, "Something went wrong when writing to the file")
            );
            e.printStackTrace();
        }
    }

    public static void writeToFile(final String fileName, final String matrixName,  final int matrixRows, final int matrixColumns,
                                   final ArrayList<ArrayList<Double>> matrixData){
        try{
            String matrixDataToWriteToFile = matrixName + " " + matrixColumns + " " + matrixRows + " ";
            for (ArrayList<Double> array: matrixData){
                for (Double doubleValue: array){
                    matrixDataToWriteToFile += String.valueOf(doubleValue) + " ";
                }
            }
            FileWriter fileWriter= new FileWriter(fileName);
            fileWriter.write(matrixDataToWriteToFile);
            fileWriter.close();
            logger.info(LoggingUtils.formatLogMessage(fileName, "Successfully Wrote Data to File"));

        } catch (IOException e) {
            logger.warning(LoggingUtils.formatLogMessage(fileName, "Something went wrong when writing to the file")
            );
            e.printStackTrace();
        }
    }


    public static ArrayList<String> convertMatrixDataToArray(final String matrixData){
        ArrayList<String> matrixDataArrayList = new ArrayList<>();
        String[] matrixDataArray = matrixData.split(" ");
        for (String data: matrixDataArray){
            if (!Objects.equals(data, "")){
                matrixDataArrayList.add(data);
            }
        }
        return matrixDataArrayList;
    }

    public static Matrix buildMatrixObject(final ArrayList<String> matrixData){
        Matrix matrix = new Matrix();
        matrix.setVariableName(matrixData.get(0))
                .setRows(Integer.parseInt(matrixData.get(1)))
                .setColumns(Integer.parseInt(matrixData.get(2)))
                .setMatrixData(convertArrayListFromStringToDouble(matrixData.subList(3, matrixData.size())));
        return matrix;
    }

    public static ArrayList<Double> convertArrayListFromStringToDouble(final List<String> matrixDataAsString){
        ArrayList<Double> arrayListData = new ArrayList<>();

        for (String data: matrixDataAsString){
            arrayListData.add(Double.parseDouble(data));
        }
        return arrayListData;
    }

    // Calculate the "C" matrix from the assignment and take the two variables as coefficients
    public static ArrayList<Double> calculateNewMatrixData(Matrix matrixA, Double firstCoefficient, Matrix matrixB, Double secondCoefficient){
        ArrayList<Double> newMatrixData = new ArrayList<>();
        matrixA.multiplyMatrix(secondCoefficient);
        matrixB.multiplyMatrix(firstCoefficient);

        if (matrixA.getColumns() == matrixB.getColumns() && matrixA.getRows() == matrixB.getRows()){
            for (int i = 0; i < matrixA.getMatrixData().size(); i++){
                double result = matrixB.getMultipliedMatrixData().get(i) - matrixA.getMultipliedMatrixData().get(i);
                newMatrixData.add(result);
            }
        } else {
            logger.info("Columns and rows don't match, Unable to perform new matrix calculations: " +
                    "\n Columns: " + matrixA.getColumns() + "!=" + matrixB.getColumns() +
                    "\n Rows: " + matrixA.getRows() + "!=" + matrixB.getRows());
        }
        return newMatrixData;
    }
}
