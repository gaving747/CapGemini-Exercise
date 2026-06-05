//Gavin Garcia
//This file aims to mimic a hierarchical file system
package fileSystem;
import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Scanner myObj = new Scanner(System.in);
        String[] commandArray = DirectoryPiece.getKnownCommands();
        Scanner path = new Scanner(System.in);
        StringBuilder labeledPath = new StringBuilder("/");

        //added test data
        //root folder

        Folder root = new Folder();
        Folder currentDirectory = root;

        File currentFile = new File("cFile", 9);
        currentDirectory.addToDirectory(currentFile);
        currentFile = new File("bGoofyFile", 8);
        currentDirectory.addToDirectory(currentFile);
        currentFile = new File("aGoofyFile", 7);
        currentDirectory.addToDirectory(currentFile);

        Folder otherFolder = new Folder("newFolder");
        currentDirectory.addToDirectory(otherFolder);
        //currentDirectory = otherFolder;
        currentDirectory = currentDirectory.cd("/newFolder", root, labeledPath);
        currentFile = new File("bOtherFile", 2);
        currentDirectory.addToDirectory(currentFile);
        currentFile = new File("aOtherFile", 1);
        currentDirectory.addToDirectory(currentFile);
        Folder thirdFolder = new Folder("thirdFolder");
        currentDirectory.addToDirectory(thirdFolder);
        currentFile = new File("lastFile", 12);
        //currentDirectory = thirdFolder;
        currentDirectory = currentDirectory.cd("/thirdFolder", root, labeledPath);
        currentDirectory.addToDirectory(currentFile);
        currentDirectory = currentDirectory.cd("/", root, labeledPath);
        currentFile = new File("qFile", 1);
        currentDirectory.addToDirectory(currentFile);


        while(true) {
            System.out.println("Enter the command you would like to perform from the following valid commands:");
            System.out.println("Current location:" + labeledPath);
            System.out.println("The valid commands are: " + Arrays.toString(commandArray) + " or type quit to end program.");

            String command = myObj.nextLine().strip();
            //choose one of either 3 commands
            if(Arrays.asList(commandArray).contains(command)) {
                if(command.equals(commandArray[0])){
                    System.out.println("Enter in a path to the desired folder:");
                    System.out.println("e.g. /folder/otherFolder/file");
                    String thePath = myObj.nextLine().strip();

                    currentDirectory = currentDirectory.cd(thePath, root, labeledPath);
                }
                else if(command.equals(commandArray[1])){
                    currentDirectory.ls();
                }
                else if(command.equals(commandArray[2])){
                    System.out.println(currentDirectory.size());
                }
            }
            else if (command.equals("quit")) {
                break;
            }
            else{
                System.out.println("Command not recognized.");
            }
        }
    }
}