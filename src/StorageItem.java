import java.util.Comparator;
import java.util.ArrayList;
import java.util.Date;

abstract class StorageItem {
    static ArrayList<StorageItem> pc = new ArrayList<>();
    final static long LOW_BOUND = 1483228800000L;
    final static long HIGH_BOUND = 1640908800000L;
    private final String name;
    private Date date;
    int size;
    private ArrayList<StorageItem> location;               // i think we need this for printing, or maybe location?
    public StorageItem(String name){
        this.name = name;
        makeData(Main.rnd.nextLong());
        this.size = 0;
        this.location = pc;
        pc.add(this);
    }
    public Date getDate(){return this.date;}
    public void setLocation(ArrayList<StorageItem> location){
        this.location = location;
    }
    public ArrayList<StorageItem> getLocation(){return this.location;}
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
        Comparator<StorageItem> comparator = switch (field) {
            case DATE -> Comparator.comparing(StorageItem::getDate);
            case NAME -> Comparator.comparing(StorageItem::getName);
            case SIZE -> Comparator.comparingInt(StorageItem::getSize);
        };
        sortTree(comparator);
        printTree(1);
        }

    private void sortTree(Comparator<StorageItem> comparator) {
        this.location.sort(comparator);
        for( StorageItem item : this.location){
            if(item instanceof Folder)
                item.sortTree(comparator);
        }


    }
    private void printTree(int howDeep){// here we do the printing (using recursion and for loop?)
        for(int index = this.location.indexOf(this); index<this.location.size();index++){
            StorageItem currentItem=this.location.get(index);
            System.out.println(currentItem.getName());
            indent(howDeep);
            if(currentItem instanceof Folder){
                currentItem.printTree(howDeep+1);
            }
        }

    }
    private void indent(int indent){
        for(int count = 0; count < indent; count++)
            System.out.print("|");
    }
}
