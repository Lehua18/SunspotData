import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FileNotFoundException;
import java.util.ArrayList

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(uploadTxtFile());
            ArrayList<String[]> lineInfo = new ArrayList<>();
            do{

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