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
        makeData(Math.abs(Main.rnd.nextLong()));
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
        Comparator<StorageItem> comparator;
        switch (field) {
            case DATE:
                comparator = Comparator.comparing(StorageItem::getDate);
                break;
            case SIZE:
                comparator = Comparator.comparingInt(StorageItem::getSize);
                break;
            default:
                comparator =  Comparator.comparing(StorageItem::getName);
        }
        this.location.sort(comparator);
        this.sortTree(comparator);
        System.out.println(this.getName());
        if(this instanceof Folder)
            printTree(1,((Folder) this).content);
        }

    private void sortTree(Comparator<StorageItem> comparator) {
        if(this instanceof Folder){
            ((Folder) this).content.sort(comparator);
            for (StorageItem item : ((Folder) this).content)
                if (item instanceof Folder)
                    item.sortTree(comparator);
        }
    }
    private void printTree(int howDeep, ArrayList<StorageItem> folder){// here we do the printing (using recursion and for loop?)
        for (StorageItem currentItem : folder) {
            indent(howDeep);
            System.out.println(currentItem.getName());
            if (currentItem instanceof Folder) {
                currentItem.printTree(howDeep + 1, ((Folder) currentItem).content);
            }
        }

    }
    private void indent(int indent){
        for(int count = 0; count < indent; count++)
            System.out.print("|    ");
    }
}
