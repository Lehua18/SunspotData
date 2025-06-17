import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(uploadTxtFile());
            ArrayList<Objects[]> lineInfo = new ArrayList<>();
            //order: year, month, day, yearasFraction, sunspot #, standard deviation; # observations, prov indicator
            do{
                String line = scan.nextLine();
                int length = line.length();
                String type = "";
                if(length == 37){
                 //daily and monthly = 37
                    int year = Integer.parseInt(line.substring(0,4));
                    int month = Integer.parseInt(line.substring(5,7));
                    int day = Integer.parseInt(line.substring(8,10));
                    double yrAsDec = Double.parseDouble(line.substring(11,19));
                    int sunspotNum = Integer.parseInt(line.substring(21,24));
                    double standDev = Double.parseDouble(line.substring(25,30));
                    int numObs = Integer.parseInt(line.substring(31,35));
                    String provInd = line.substring(36);
                    type = "daily";
                } else if (length == 28) {
                    double yrAsDec = Double.parseDouble(line.substring(0,7));
                    int sunspotNum = Integer.parseInt(line.substring(8,13));
                    double standDev = Double.parseDouble(line.substring(14,19));
                    String provInd = line.substring(27);
                    type = "yearly";
                } else if (length == 66) {
                    int year = Integer.parseInt(line.substring(0,4));
                    int month = Integer.parseInt(line.substring(5,7));
                    int day = Integer.parseInt(line.substring(8,10));
                    double yrAsDec = Double.parseDouble(line.substring(11,19));
                    int sunspotNum = Integer.parseInt(line.substring(21,24));
                    int nSunspotNum = Integer.parseInt(line.substring(25,28));
                    int sSunspotNum = Integer.parseInt(line.substring(29,32));
                    double standDev = Double.parseDouble(line.substring(34,39));
                    double nStandDev = Double.parseDouble(line.substring(40,45));
                    double sStandDev = Double.parseDouble(line.substring(45,51));
                    int numObs = Integer.parseInt(line.substring(31,35));
                    String provInd = line.substring(36);
                    type = "hemDaily";
                }else {

                }


            }while(scan.hasNext());
        }catch(FileNotFoundException e){
            System.out.println("File not found: "+e.getMessage());
            System.exit(1);
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
            return chooser.getSelectedFile();
        }else{
            System.out.println("Something went wrong: Code "+returnValue);
            return null;
        }
    }
}