import java.util.ArrayList;

public class Folder extends StorageItem {
    /*
     * folder is a storage item which may be containing more items (has content list)
     */
    ArrayList<StorageItem> content;

    public Folder(String name) {
        /*
         * the instructor of folder. calls the instructor of storage item
         * and initialize the contain list of the folder
         * @param: name: the name of the folder
         */
        super(name);
        this.content = new ArrayList<>();
    }

    public boolean addItem(StorageItem item) {
        /*
         * adding a new item to the folder container. removes the item from the previous location.
         * return true if there is no item with the same name in this folder and false if there is an existing
         * item with the same name here (and doesn't add the item)
         * @param: item: the item to add
         */
        for (StorageItem storageItem : this.content) {
            if (storageItem.getName().equals(item.getName()))
                return false;
        }
        item.getLocation().remove(item);
        this.content.add(item);
        item.setLocation(this.content);
        return true;
    }

    public void setSize() {
        /*
         * recursive method that calculate the folder size
         */
        size = 0;
        for (StorageItem storageItem : content) {
            size += storageItem.getSize();//unsure if need some kind of casting here
        }
        this.setSize(size);
    }

    @Override
    public int getSize() {
        /*
         * returns the folder size
         */
        this.setSize();
        return this.size;
    }

    public File findFile(String receivedPath) {
        /*
         * receive a path to a file and return the file if exist and the path is legal. otherwise return null.
         * use the recursive function findFileAux
         * @param: receivedPath: the path to the file
         */
        String[] path = receivedPath.split("/");
        for (StorageItem storageItem : this.content) {
            if (storageItem.getName().equals(path[0])) {
                if (storageItem instanceof Folder)
                    return findFileAux((Folder) storageItem, path, 1);
                else if (path.length == 1)
                    return (File) storageItem;
            }
        }
        return null;
    }

    public File findFileAux(Folder folder, String[] path, int howDeep) {
        /*
         * recursive function which receives the current folder in the recursive call, path to a file,
         * and how deep the recursive is, and returns the file if exists and the path is legal,
         * otherwise return null.
         * @param: folder: the current folder in the recursive call
         * @param: path: the path to the file
         * @param: howDeep: how deep the recursive is now
         */
        for (StorageItem storageItem : folder.content) {
            if (storageItem.getName().equals(path[howDeep])) {
                if (storageItem instanceof Folder)
                    return findFileAux((Folder) storageItem, path, howDeep + 1);
                else if (path.length == howDeep + 1)
                    return (File) storageItem;
            }
        }
        return null;
    }
}
