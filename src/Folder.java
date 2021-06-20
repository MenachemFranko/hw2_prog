import java.util.ArrayList;

public class Folder extends StorageItem{
    ArrayList<StorageItem> content;

    public Folder(String name) {
        super(name);
        this.content = new ArrayList<StorageItem>();
    }
    public boolean addItem(StorageItem item){
        for(StorageItem storageItem : this.content){
            if(storageItem.getName().equals(item.getName()))
                return false;
        item.getLocation().remove(item);
        this.content.add(item);
        item.setLocation(this.content);
        }
        return true;
    }
    public void setSize() {
        size=0;
        for(StorageItem storageItem : content){
            size += storageItem.getSize();//unsure if need some kind of casting here
        }
        super.setSize(size);
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
                if(!(storageItem instanceof Folder) && path.length == 1)
                    return (File) storageItem;
            }
        }
        return null;
    }
    public File findFileAux(Folder folder, String [] path, int x){
        for(StorageItem storageItem : folder.content){
            if(path[x])
        }
    }
