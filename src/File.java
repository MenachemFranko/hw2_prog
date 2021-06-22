public class File extends StorageItem {
    String ext;
    String content;

    public File(String name, String ext) {
        super(name);
        this.ext = ext;
        this.content = "";
    }

    void setSize() {
        super.setSize(this.content.length()); //make in mb??
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    String getName() {
        return super.getName() + "." + this.ext;
    }

    void addContent(String contentToAdd) {
        this.content = this.content + contentToAdd;
        this.setSize();
    }

    void printContent() {
        String header = String.format("%s Size: %d Created: ", getName(), this.size);
        header += this.getDate();
        System.out.println(header);
        System.out.println(this.content);
    }
}
