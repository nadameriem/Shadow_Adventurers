package Game;

public class Item {
    private String name;
    private String type; // "Health", "Attack", or "Special"
    private int value;

    public Item(String name, String type, int value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return name + " (Type: " + type + ", Value: " + value + ")";
    }
}
