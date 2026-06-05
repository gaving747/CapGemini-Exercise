//Gavin Garcia
//class for file
package fileSystem;

public class File extends DirectoryPiece {
    //constuctor
    public File(String name, int size) {
        super(name, size);
    }
    //constructor for if no info is given
    public File() {
        super("newFile", 0);
    }

}
