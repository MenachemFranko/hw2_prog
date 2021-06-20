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
            if(storageItem.getName() == item.getName())
                return false;
        this.content.add(item);
        item.setLocation(this);
        }
    }

    void setSize() {
        size=0;
        for(StorageItem storageItem : content){
            size += storageItem.getSize();//unsure if need some kind of casting here
        }
        super.setSize(size);

    }

    @Override
    public int getSize() {
        return super.getSize();
    }
}
