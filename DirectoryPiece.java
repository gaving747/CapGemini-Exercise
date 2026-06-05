//Gavin Garcia
//Abstract class used by both File and Folder classes
package fileSystem;

public abstract class DirectoryPiece {
    private String name;
    private int size;
    private static String[] knownCommands = {"cd", "ls", "size"};

    //constructor for a file
    public DirectoryPiece(String name, int size) {
        this.name = name;
        this.size = size;
    }

    //constructor for a folder
    public DirectoryPiece(String name) {
        this.name = name;
    }

    //get the name
    public String getName() {
        return name;
    }
    //get the size
    public int getSize(){
        return size;
    }
    //get the known commands
    public static String[] getKnownCommands(){
        return knownCommands;
    }

}
