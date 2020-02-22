package utils;

public class indexer {

    String name;
    String id;

    public indexer(){};

    public indexer(String name, String id)
    {
        this.name = name;
        this.id  = id;
    }

    public String getName() { return (this.name); }
    public String getID() {return (this.id); }
}
