import java.util.ArrayList;
import java.util.List;

public class Folder extends StorageItem{
    List<StorageItem> content;

    public Folder(String name) {
        super(name);
        this.content = new ArrayList<StorageItem>();

    }


    @Override
    void setSize() {

    }

    @Override
    public int getSize() {
        return 0;
    }
}
