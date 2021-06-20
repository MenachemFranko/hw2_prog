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


    @Override
    void setSize() {

    }

    @Override
    public int getSize() {
        return 0;
    }
}
