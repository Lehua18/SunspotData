import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.ArrayList;

public class Main {
    private static int count;
    public static void main(String[] args) {
        String inputFile = args[0];
        ArrayList<DataPoint> allData = null;
        try {
            //Create reader with uploaded file
            Scanner scan = new Scanner(new File(inputFile));
            allData = new ArrayList<>();
            do {
                count = 0;
                ArrayList<String> dataPoint = new ArrayList<>();
                String line = scan.nextLine();
                int length = line.length();

                //Adds individual data points to arraylist
                while(count< length){
                    dataPoint.add(getValuesFromTxt(line));
                }

                //Uses arraylist to construct a new Datapoint object that holds all relevant information related to the point, and adds point to overall list
                allData.add(new DataPoint(dataPoint));

            //Checks if txt file has another line
            } while (scan.hasNext());

        //Ends program if file is not uploaded
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            System.exit(1);
        }

        //Set x values to year as decimal values
        double[] x = new double[allData.size()];
        for(int i = 0; i<allData.size(); i++){
            x[i] = allData.get(i).getYrAsDec();
        }

        //Set y values to sunspot number values
        double[] y = new double[allData.size()];
        for (int j = 0; j<allData.size(); j++){
            y[j] = allData.get(j).getSunspotNum();
        }

        //Create a graph of year vs sunspot number
        try {
            Grapher grapher = new Grapher(x, y,args);
        }catch (InterruptedException e){
            System.out.println("Something went wrong: "+e);
        }
    }

    //upload txt file
    public static File uploadTxtFile(){
        //create JFileChooser
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Open File");

        //Filters files shown to only txt files
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt", "text");
        chooser.setFileFilter(filter);

        //Open chooser and get file
        int returnValue = chooser.showOpenDialog(new JFrame());
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            System.out.println(file.getName());
            return file;
        }else{
            System.out.println("Something went wrong: Code "+returnValue);
            return null;
        }
    }

    //Gets next value from txt line
    public static String getValuesFromTxt(String line) {
        boolean stop = false;
        StringBuilder data = new StringBuilder();
        while (!stop) {
            if (count >= line.length()){
                //failsafe
                stop = true;
            }else if (line.charAt(count) != ' ') {
                //Adds character to StringBuilder if the character is not a space.
                data.append(line.charAt(count));
            }else if(count == line.length()-1) {
                //If the provisional index column is blank, append a '.' to the StringBuilder so that the data is compiled correctly
                data.append('.');
                stop = true;
            }else if(line.charAt(count) == ' ' && !data.isEmpty()){
                //stops the function once the function reaches another space.
                stop = true;
            }
            count++;
        }
     //   System.out.println("DATA: "+data);
        return data.toString();
    }
}