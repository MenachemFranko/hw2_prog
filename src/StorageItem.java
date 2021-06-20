import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

abstract class StorageItem {
    final static long LOW_BOUND = 1483228800000L;
    final static long HIGH_BOUND = 1640908800000L;
    private String name;
    private Date date;
    int size;
    private Folder location;// i think we need this for printing, or maybe location?
    public StorageItem(String name){
        this.name = name;
        makeData(Main.rnd.nextLong());
        this.size = 0;
    }
    public Date getDate(){return this.date;}
    public void setLocation(Folder folder){
        this.location = folder;
    }
    void makeData(long rndTime){
        if(rndTime<0){
            rndTime=-rndTime;
        }
        long boundedTimeInMs = (((rndTime - LOW_BOUND) % (HIGH_BOUND - LOW_BOUND) + LOW_BOUND));
        this.date = new Date(boundedTimeInMs);
    }
    void setSize(int size){
        this.size = size;
    }
    public abstract int getSize();

    String getName(){
        return this.name;
    }

    void printTree(SortingField field){
        sortFolder(field);
        printTree();
        }

    private void sortFolder(SortingField field) {// here we sort current Folder
        Comparator<StorageItem> comparator = switch (field) {
            case DATE -> (StorageItem firstFile, StorageItem secondFile) -> firstFile.getDate().compareTo(secondFile.getDate());
            case NAME -> (StorageItem firstFile, StorageItem secondFile) -> firstFile.getName().compareTo(secondFile.getName());
            case SIZE -> (StorageItem firstFile, StorageItem secondFile) -> firstFile.getSize() - secondFile.getSize();
        };
        this.location.content.sort(comparator);
        }


    }
    private void printTree(){// here we do the printing (using recursion and for loop?)

    }
}
