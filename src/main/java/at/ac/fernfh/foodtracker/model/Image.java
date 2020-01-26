package at.ac.fernfh.foodtracker.model;

public class Image {

    private String fileName;
    private String path;

    /**
     * Constructor
     *
     * @param fileName name of the file
     * @param path     file path
     */
    public Image(String fileName, String path) {
        this.fileName = fileName;
        this.path = path;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Image{" + "fileName='" + fileName + '\'' + ", path='" + path + '\'' + '}';
    }
}
