public class File extends StorageItem {
    /*
    File is a storage item which its content is string. has an extension ant content.
     */
    String ext;
    String content;

    public File(String name, String ext) {
        /*
        receive name and extension, call the constructor of storage item and initialize
        the ext. and the content of this file
         */
        super(name);
        this.ext = ext;
        this.content = "";
    }

    void setSize() {
        /*
        set the size of the file (the length of the content)
         */
        super.setSize(this.content.length());
    }

    @Override
    public int getSize() {
        /*
        return the size of the file
         */
        return this.size;
    }

    @Override
    String getName() {
        /*
        return the name of the file (including the ext.)
         */
        return super.getName() + "." + this.ext;
    }

    void addContent(String contentToAdd) {
        /*
        add a new content to the file
        @param: contentToAdd: the string to add to the content
         */
        this.content = this.content + contentToAdd;
        this.setSize();
    }

    void printContent() {
        /*
        print the size, creation date and content of the file
         */
        String header = String.format("%s Size: %d Created: ", getName(), this.size);
        header += this.getDate();
        System.out.println(header);
        System.out.println(this.content);
    }
}
