package Asignment.Week1;

public class WarehouseInventoryBalancer {
    static void analyzeInventory(int[] sectionA, int[] sectionB) {
        int totalA = 0, totalB = 0;
        for (int val : sectionA)
            totalA += val;
        for (int val : sectionB)
            totalB += val;

        String status = (totalA == totalB) ? "Balanced" : "Not Balanced";

        int maxQty = sectionA[0];
        String maxSection = "Section A";
        int maxIndex = 0;

        for (int i = 0; i < sectionA.length; i++) {
            if (sectionA[i] > maxQty) {
                maxQty = sectionA[i];
                maxSection = "Section A";
                maxIndex = i;
            }
        }
        for (int i = 0; i < sectionB.length; i++) {
            if (sectionB[i] > maxQty) {
                maxQty = sectionB[i];
                maxSection = "Section B";
                maxIndex = i;
            }
        }

        System.out.printf(
                "Section A Total: %d | Section B Total: %d | Status: %s | Highest Quantity: %d (%s, Item %d)%n",
                totalA, totalB, status, maxQty, maxSection, maxIndex + 1);
    }

    public static void main(String[] args) {
        analyzeInventory(new int[] { 20, 15, 30 }, new int[] { 25, 10, 30 });
    }
}