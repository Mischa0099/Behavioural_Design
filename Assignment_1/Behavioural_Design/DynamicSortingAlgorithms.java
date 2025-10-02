package Assignment_1.Behavioural_Design;
import java.util.*;

interface SortStrategy {
    void sort(List<Integer> numbers);
}

class BubbleSort implements SortStrategy {
    public void sort(List<Integer> numbers) {
        for (int i = 0; i < numbers.size() - 1; i++) {
            for (int j = 0; j < numbers.size() - i - 1; j++) {
                if (numbers.get(j) > numbers.get(j + 1)) {
                    Collections.swap(numbers, j, j + 1);
                }
            }
        }
        System.out.println("Sorted using BubbleSort: " + numbers);
    }
}

class QuickSort implements SortStrategy {
    public void sort(List<Integer> numbers) {
        quickSort(numbers, 0, numbers.size() - 1);
        System.out.println("Sorted using QuickSort: " + numbers);
    }

    private void quickSort(List<Integer> list, int low, int high) {
        if (low < high) {
            int p = partition(list, low, high);
            quickSort(list, low, p - 1);
            quickSort(list, p + 1, high);
        }
    }

    private int partition(List<Integer> list, int low, int high) {
        int pivot = list.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (list.get(j) <= pivot) {
                i++;
                Collections.swap(list, i, j);
            }
        }
        Collections.swap(list, i + 1, high);
        return i + 1;
    }
}

class SortContext {
    private SortStrategy strategy;
    public void setStrategy(SortStrategy strategy) { this.strategy = strategy; }
    public void execute(List<Integer> numbers) { strategy.sort(numbers); }
}

public class DynamicSortingAlgorithms {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SortContext context = new SortContext();

        // Input numbers dynamically
        System.out.println("Enter numbers separated by spaces: ");
        String[] input = sc.nextLine().split(" ");
        List<Integer> data = new ArrayList<>();
        for (String s : input) {
            data.add(Integer.parseInt(s));
        }

        // Run BubbleSort
        context.setStrategy(new BubbleSort());
        context.execute(new ArrayList<>(data));

        // Run QuickSort
        context.setStrategy(new QuickSort());
        context.execute(new ArrayList<>(data));
    }
}
