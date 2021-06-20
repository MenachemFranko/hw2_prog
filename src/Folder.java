import java.util.ArrayList;
import java.util.List;

public class Folder extends StorageItem{
    List<StorageItem> content;

    public Folder(String name) {
        super(name);
        this.content = new ArrayList<StorageItem>();
    }
    public boolean addItem(StorageItem item){
        for(StorageItem storageItem : this.content){
            if(storageItem.getName().equals(item.getName()))
                return false;
        this.content.add(item);
        item.setLocation(this);
        }
        return true;
    }
    @Override
    public int getSize() {
        size=0;
        for(StorageItem storageItem : content){
            size += storageItem.getSize();//unsure if need some kind of casting here
        }
        super.setSize(size);
        return size;

    }
}
