import java.util.Comparator;
import java.util.ArrayList;
import java.sql.Timestamp;

abstract class StorageItem {
    /*
    storage item may be a folder or a file. has a name, size, creation date, location.
     */
    static ArrayList<StorageItem> pc = new ArrayList<>();
    final static long LOW_BOUND = Timestamp.valueOf("2017-01-01 00:00:00").getTime();
    final static long HIGH_BOUND = Timestamp.valueOf("2021-12-31 23:59:59").getTime();
    private final String name;
    private Timestamp date;
    int size;
    private ArrayList<StorageItem> location;

    public StorageItem(String name) {
        /*
        the constructor of the storage item. receives the name for the new item and initialize it.
        in addition, initialize the creation date randomly, the size to zero and the location to the
        main location in the pc
        @param: name: the name of the item
         */
        this.name = name;
        makeData(Math.abs(Main.rnd.nextLong()));
        this.size = 0;
        this.location = pc;
        pc.add(this);
    }

    public void setLocation(ArrayList<StorageItem> location) {
        /*
        set the new location for the item
        @param: location: the new location
         */
        this.location = location;
    }

    public ArrayList<StorageItem> getLocation() {
        /*
        return the location of the item
         */
        return this.location;
    }

    void makeData(long rndTime) {
        /*
        receives a random number and cast it to date and time. initialize the creation date of the item.
        @param: rndTime: random num to cast
         */
        long boundedTimeInMs = ((rndTime) % (HIGH_BOUND - LOW_BOUND) + LOW_BOUND);
        this.date = new Timestamp(boundedTimeInMs);
    }

    void setSize(int size) {
        /*
        set the item size
        @param: the item size
         */
        this.size = size;
    }

    void printTree(SortingField field) {
        /*
        sort and print all files that stem from the obj using the field given.
        @param: field: the field we want the folders to be sorted by
         */
        Comparator<StorageItem> comparator;
        switch (field) {
            case DATE:
                comparator = Comparator.comparing(StorageItem::getDate);
                break;
            case SIZE:
                comparator = Comparator.comparingInt(StorageItem::getSize);
                break;
            default:
                comparator = Comparator.comparing(StorageItem::getName);
        }
        this.location.sort(comparator);
        this.sortTree(comparator);
        System.out.println(this.getName());
        if (this instanceof Folder)
            printTree(1, ((Folder) this).content);
    }

    private void sortTree(Comparator<StorageItem> comparator) {
        /*
        sort all subsequent folders using the comparator
        @param: comparator: the comparator to use to sort folders
         */
        if (this instanceof Folder) {
            ((Folder) this).content.sort(comparator);
            for (StorageItem item : ((Folder) this).content)
                if (item instanceof Folder)
                    item.sortTree(comparator);
        }
    }

    private void printTree(int howDeep, ArrayList<StorageItem> folder) {
        /*
        get a folder and print all items in that folder, including items within further folders
        @param: howDeep: current depth of the function, needed for printing purposes
        @param: folder: folder to be printed
         */
        for (StorageItem currentItem : folder) {
            indent(howDeep);
            System.out.println(currentItem.getName());
            if (currentItem instanceof Folder) {
                currentItem.printTree(howDeep + 1, ((Folder) currentItem).content);
            }
        }

    }

    private void indent(int indent) {
        /*
        indent the printing as required
        @param: indent: how many indents to print
         */
        for (int count = 0; count < indent; count++)
            System.out.print("|    ");
    }

    String getName() {
        /*
        return the name of the item
         */
        return this.name;
    }

    public abstract int getSize();

    public Timestamp getDate() {
        /*
        return the creation date of the item
         */
        return this.date;
    }
}
