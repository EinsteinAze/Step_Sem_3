class Item {
    String itemName;
    int stock;

    Item(String itemName, int stock) {
        this.itemName = itemName;
        this.stock = stock;
    }

    void restock(int stock) {
        this.stock = this.stock + stock;
    }
}

public class week4a2 {
    public static void main(String[] args) {
        String[] itemNames = {"Samosa", "Tea Powder", "Bread", "Biscuit Packs"};
        int[] stockLevels = {15, 40, 8, 25};

        Item[] items = new Item[itemNames.length];
        for (int i = 0; i < itemNames.length; i++) {
            items[i] = new Item(itemNames[i], stockLevels[i]);
        }

        for (Item item : items) {
            item.restock(20);
            System.out.println(item.itemName + " | Final Stock: " + item.stock);
        }
    }
}
