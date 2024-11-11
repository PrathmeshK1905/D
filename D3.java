import java.util.*;

class Item {
    int value, weight;
    Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }
}

class FractionalKnapsack {
    static double getMaxValue(Item[] items, int capacity) {
        Arrays.sort(items, (a, b) -> Double.compare((double)b.value / b.weight, (double)a.value / a.weight));

        double totalValue = 0.0;
        for (Item item : items) {
            if (capacity >= item.weight) {
                totalValue += item.value;
                capacity -= item.weight;
            } else {
                totalValue += (double)item.value * capacity / item.weight;
                break;
            }
        }
        return totalValue;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of items: ");
        int n = sc.nextInt();
        Item[] items = new Item[n];

        System.out.println("Enter value and weight of each item:");
        for (int i = 0; i < n; i++) {
            System.out.print("Item " + (i + 1) + ": ");
            int value = sc.nextInt();
            int weight = sc.nextInt();
            items[i] = new Item(value, weight);
        }

        System.out.print("Enter knapsack capacity: ");
        int capacity = sc.nextInt();

        double maxValue = getMaxValue(items, capacity);
        System.out.println("Maximum value in the knapsack: " + maxValue);
        sc.close();
    }
}
