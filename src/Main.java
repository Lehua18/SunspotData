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
                    Scanner scan2 = new Scanner(System.in);
                    System.out.println("Please type the type of data you have entered (daily or monthly)");
                    type = scan2.nextLine();
                    if(type.equalsIgnoreCase("daily")){
                        int year = Integer.parseInt(line.substring(0, 4));
                        int month = Integer.parseInt(line.substring(5, 7));
                        int day = Integer.parseInt(line.substring(8, 10));
                        double yrAsDec = Double.parseDouble(line.substring(11, 19));
                        int sunspotNum = Integer.parseInt(line.substring(21, 24));
                        double standDev = Double.parseDouble(line.substring(25, 30));
                        int numObs = Integer.parseInt(line.substring(31, 35));
                        String provInd = line.substring(36);
                    }else {
                        int year = Integer.parseInt(line.substring(0, 4));
                        int month = Integer.parseInt(line.substring(5, 7));
                        double yrAsDec = Double.parseDouble(line.substring(8, 16));
                        int sunspotNum = Integer.parseInt(line.substring(18, 23));
                        double standDev = Double.parseDouble(line.substring(24, 29));
                        int numObs = Integer.parseInt(line.substring(31, 35));
                        String provInd = line.substring(36);

                    }
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
                    int numObs = Integer.parseInt(line.substring(53,56));
                    int nNumObs = Integer.parseInt(line.substring(57,60));
                    int sNumObs = Integer.parseInt(line.substring(61,64));
                    String provInd = line.substring(65);
                    type = "hemDaily";
                }else if(length == 72){
                    int year = Integer.parseInt(line.substring(0,4));
                    int month = Integer.parseInt(line.substring(5,7));
                    double yrAsDec = Double.parseDouble(line.substring(8,16));
                    int sunspotNum = Integer.parseInt(line.substring(18,23));
                    int nSunspotNum = Integer.parseInt(line.substring(24,29));
                    int sSunspotNum = Integer.parseInt(line.substring(30,35));
                    double standDev = Double.parseDouble(line.substring(37,42));
                    double nStandDev = Double.parseDouble(line.substring(43,48));
                    double sStandDev = Double.parseDouble(line.substring(49,54));
                    int numObs = Integer.parseInt(line.substring(56,60));
                    int nNumObs = Integer.parseInt(line.substring(61,65));
                    int sNumObs = Integer.parseInt(line.substring(66,70));
                    String provInd = line.substring(71);
                    type = "hemMonthly";

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