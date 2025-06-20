import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.ArrayList;

public class Main {
    private static int count;
    public static void main(String[] args) {
        ArrayList<DataPoint> allData = null;
        try {
            Scanner scan = new Scanner(uploadTxtFile());
            allData = new ArrayList<>();
            do {
                count = 0;
                ArrayList<String> dataPoint = new ArrayList<>();
                String line = scan.nextLine();
                int length = line.length();
                while(count< length){
                    dataPoint.add(getValuesFromTxt(line));
                }
                allData.add(new DataPoint(dataPoint));

            } while (scan.hasNext());
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            System.exit(1);
        }
        System.out.println(allData.getFirst());
        System.out.println("\n\n");
        System.out.println(allData.getLast());


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