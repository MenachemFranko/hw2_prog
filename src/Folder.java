import java.util.ArrayList;

public class Folder extends StorageItem{
    ArrayList<StorageItem> content;

    public Folder(String name) {
        super(name);
        this.content = new ArrayList<>();
    }
    public boolean addItem(StorageItem item){
        for(StorageItem storageItem : this.content) {
            if (storageItem.getName().equals(item.getName()))
                return false;
        }
        item.getLocation().remove(item);
        this.content.add(item);
        item.setLocation(this.content);
        return true;
    }
    public void setSize() {
        size=0;
        for(StorageItem storageItem : content){
            size += storageItem.getSize();//unsure if need some kind of casting here
        }
        this.setSize(size);
    }
    @Override
    public int getSize() {
        this.setSize();
        return this.size;
    }
    public File findFile(String receivedPath){
        String [] path = receivedPath.split("/");
        for(StorageItem storageItem : pc){
            if(storageItem.getName().equals(path[0])){
                if(storageItem instanceof Folder)
                    return findFileAux((Folder) storageItem, path, 1);
                else if (path.length == 1)
                    return (File) storageItem;
            }
        }
        return null;
    }
    public File findFileAux(Folder folder, String [] path, int x){
        for(StorageItem storageItem : folder.content){
            if(storageItem.getName().equals(path[x])){
                if(storageItem instanceof Folder)
                    return findFileAux((Folder) storageItem, path, x+1);
                else if (path.length == x+1)
                    return (File) storageItem;
            }
        }
        return null;
    }
    }
