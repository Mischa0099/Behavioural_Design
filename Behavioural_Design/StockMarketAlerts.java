import java.util.*;

interface Observer {
    void update(String stock, double price);
}

class Trader implements Observer {
    private String name;
    public Trader(String name) { this.name = name; }
    public void update(String stock, double price) {
        System.out.println(name + " notified: " + stock + " is now $" + price);
    }
}

class StockMarket {
    private Map<String, Double> stocks = new HashMap<>();
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer o) { observers.add(o); }

    public void updatePrice(String stock, double price) {
        stocks.put(stock, price);
        for (Observer o : observers) o.update(stock, price);
    }
}

public class StockMarketAlerts {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StockMarket market = new StockMarket();

        // Add traders dynamically
        System.out.print("Enter number of traders: ");
        int t = sc.nextInt();
        sc.nextLine(); // consume newline

        for (int i = 1; i <= t; i++) {
            System.out.print("Enter trader name: ");
            String name = sc.nextLine();
            market.addObserver(new Trader(name));
        }

        // Update stock prices dynamically
        System.out.println("\nEnter stock updates (symbol price), type 'exit' to stop:");
        while (true) {
            System.out.print("Update: ");
            String input = sc.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) break;

            String[] parts = input.split(" ");
            if (parts.length == 2) {
                String stock = parts[0].toUpperCase();
                try {
                    double price = Double.parseDouble(parts[1]);
                    market.updatePrice(stock, price);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid price format. Try again.");
                }
            } else {
                System.out.println("Invalid input. Format: SYMBOL PRICE");
            }
        }

        sc.close();
        System.out.println("Stock updates ended.");
    }
}
