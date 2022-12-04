import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args)
    {
        ArrayList<String> list = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        String menu;
        boolean done = false;
        boolean needsToBeSaved = false;
        String fileName = "";


        do {
            menu = display(in, list);
            switch(menu.toLowerCase())
            {
                case "q":
                    if (SafeInput.getYNConfirm(in, "Are you sure")) {
                        if (needsToBeSaved && SafeInput.getYNConfirm(in,"Do you want to save your file before quitting?")) {
                            saveCurrentFile(list, fileName);
                        }
                        done = true;
                    } else {
                        System.out.println("Loading...");
                    }
                    break;

                case "a":
                    add(in, list);
                    needsToBeSaved = true;
                    break;
                case "d":
                    if (list.size() ==0)
                    {
                        System.out.println("There is nothing in your list");
                        break;
                    }
                    delete(in, list);
                    needsToBeSaved = true;
                    break;
                case "v":
                    print(list);
                    break;
                case "c":
                    clearList(list);
                    needsToBeSaved = true;
                    break;
                case "o":
                    openListFile(in, list, needsToBeSaved);
                    break;
                case "s":
                    saveCurrentFile(list, fileName);
                    needsToBeSaved = false;
                    break;
            }
        }while(!done);

    }
    private static boolean quit()
    {
        Scanner in = new Scanner(System.in);
        return SafeInput.getYNConfirm(in, "Are you sure");
    }
    public static void add(Scanner in, ArrayList list)
    {
        String item = SafeInput.getNonZeroLength(in,"Enter your item");
        list.add(item);
    }

    public static void delete(Scanner in, ArrayList list)
    {
        int high = list.size();
        int num = SafeInput.getRangedInt(in, "Enter the order number of item that you want to delete", 1, high);
        list.remove(num-1);
    }

    public static void print(ArrayList list)
    {
        for (int i=0; i<list.size();i++)
        {
            System.out.println((i+1) + " " + list.get(i));
        }
        System.out.println();
    }

    private static String display(Scanner in, ArrayList list)
    {
        System.out.println("A – Add an item to the list");
        System.out.println("D – Delete an item from the list");
        System.out.println("O – Open a list file from disk");
        System.out.println("S – Save the current list file to disk");
        System.out.println("C – Clear removes all the elements from the current list");
        System.out.println("V – View (i.e. display) the list");
        System.out.println("Q – Quit the program");
        System.out.println();
        System.out.println("Current list: ");
        for (int i=0; i<list.size();i++)
        {
            System.out.println((i+1) + ". " + list.get(i));
        }
        System.out.println();
        return SafeInput.getRegExString(in,"Enter your choice","[AaCcDdOoSsVvQq]");
    }

    public static void clearList(ArrayList list)
    {
        list.clear();
    }

    private static String openListFile(Scanner in, ArrayList list, boolean needsToBeSaved) {
        String FileName="";
        if (needsToBeSaved)
        {
            boolean deleteListYN = SafeInput.getYNConfirm(in, "If you open a new list, make sure you save your current one. Are you sure?");
            if (!deleteListYN) {
                return "";
            }
        }

        Scanner inFile;
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        chooser.setFileFilter(filter);
        String line;

        Path target = new File(System.getProperty("user.dir")).toPath();
        target = target.resolve("src");
        chooser.setCurrentDirectory(target.toFile());

        try {
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                target = chooser.getSelectedFile().toPath();
                inFile = new Scanner(target);
                System.out.println("Opening File: " + target.getFileName());
                while (inFile.hasNextLine()) {
                    line = inFile.nextLine();
                    list.add(line);
                }
                inFile.close();
            } else { // user did not select a file
                System.out.println("You must select a file!");
            }
        } catch (IOException e) {
            System.out.println("IOException Error");
        }
        return target.toFile().toString();
    }

    public static void saveCurrentFile(ArrayList list, String fileName)
    {
        PrintWriter outFile;
        Path target = new File(System.getProperty("user.dir")).toPath();
        if (fileName.equals(""))
        {
            target = target.resolve("src\\list.txt");
        }else
        {
            target = target.resolve(fileName);
        }

        try
        {
            outFile = new PrintWriter(target.toString());
            for (int i = 0; i < list.size(); i++) {
                outFile.println(list.get(i));
            }
            outFile.close();
            System.out.printf("File \"%s\" saved!\n", target.getFileName());
        } catch (IOException e) {
            System.out.println("IOException Error");
        }
    }
}