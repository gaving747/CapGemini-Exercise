//Gavin Garcia
//class for folder
package fileSystem;

import java.util.Map;
import java.util.TreeMap;

public class Folder extends DirectoryPiece {
    //holds everything
    private Map<String, DirectoryPiece> directory = new TreeMap<>();
    //constructor for new folder created
    public Folder(String name) {
        super(name);
    }
    //constructor for the root folder
    public Folder() {
        super("root");
    }

    //return directory
    public Map<String, DirectoryPiece> getChildren() {
        return directory;
    }

    //add a file/folder to directory
    public void addToDirectory(DirectoryPiece directoryPiece){
        directory.put(directoryPiece.getName(), directoryPiece);
    }

    //prints contents of a file, also prints the class type
    public void ls(){
        for (Map.Entry<String, DirectoryPiece> entry : directory.entrySet()) {
            System.out.println(entry.getKey() + "(" + entry.getValue().getClass().getSimpleName() + ")");
        }
    }

    //changes current directory and updates path
    public Folder cd(String input, Folder root, StringBuilder labeledPath){
        StringBuilder constantString = new StringBuilder(labeledPath);
        Folder constantValue = this;
        Folder tempFolder = this;
        boolean fileFound = false;
        int theSize = 0;
        if(input.equals("/")){
            //for when the user wants to go to the root file
            tempFolder = root;
            labeledPath.setLength(0);
            labeledPath.append("/");
            System.out.println("Taken back to root.");
            return tempFolder;
        }

        DirectoryPiece tempPiece;
        String[] path = input.split("/");
        for(String entry: path){

            if(entry.isEmpty()){
                continue;
            }
            tempPiece = tempFolder.getChildren().get(entry);
            if(tempPiece == null || fileFound){
                //this is for when a path segment is not within a file
                //or when a filename is not the last path
                labeledPath.setLength(0);
                labeledPath.append(constantString);
                System.out.println("Invalid path");
                return constantValue;
            }
            //made to ensure if a file path is typed, then it's the last
            if(tempPiece instanceof File file){
                fileFound = true;
                theSize = tempPiece.getSize();
            }
            //update filepath and directory
            else {
                labeledPath.append(entry + "/");
                tempFolder = (Folder) tempPiece;
            }
        }
        if(fileFound){
            System.out.println("Size of selected file: " + theSize);
            System.out.println("File successfully opened.");
            return tempFolder;
        }
        System.out.println("Directory successfully changed.");
        return tempFolder;
    }

    //prints total size of files in a directory
    public int size(){
        int currentSize = 0;
        for (DirectoryPiece entry : directory.values()) {

            //the types have to be checked, if it's a folder then call recursion
            //if it is a file then add it to the total
            if (entry instanceof Folder folder) {
                currentSize += folder.size();
            }
            else if (entry instanceof File file){
                currentSize += entry.getSize();
            }
        }
        return currentSize;
    }


}