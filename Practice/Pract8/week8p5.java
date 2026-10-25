import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

interface PaymentMethod {
    String name();

    boolean processPayment(double amount);
}

class CreditCardPayment implements PaymentMethod {
    private final double creditLimit;

    CreditCardPayment(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    @Override
    public String name() {
        return "Credit Card";
    }

    @Override
    public boolean processPayment(double amount) {
        return amount <= creditLimit;
    }
}

class PayPalPayment implements PaymentMethod {
    private final double walletBalance;

    PayPalPayment(double walletBalance) {
        this.walletBalance = walletBalance;
    }

    @Override
    public String name() {
        return "PayPal";
    }

    @Override
    public boolean processPayment(double amount) {
        return amount <= walletBalance;
    }
}

class BankTransferPayment implements PaymentMethod {
    private final double accountBalance;

    BankTransferPayment(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Override
    public String name() {
        return "Bank Transfer";
    }

    @Override
    public boolean processPayment(double amount) {
        return amount <= accountBalance;
    }
}

class Product {
    private final String name;
    private final double price;

    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    String getName() {
        return name;
    }

    double getPrice() {
        return price;
    }
}

class OrderItem {
    private final Product product;
    private final int quantity;

    OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    double subtotal() {
        return product.getPrice() * quantity;
    }
}

class Customer {
    private final String name;

    Customer(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }
}

enum OrderStatus {
    PENDING("Pending"),
    PAID("Paid");

    private final String label;

    OrderStatus(String label) {
        this.label = label;
    }

    String getLabel() {
        return label;
    }
}

class Order {
    private final Customer customer;
    private final List<OrderItem> items = new ArrayList<>();
    private OrderStatus status = OrderStatus.PENDING;

    Order(Customer customer) {
        this.customer = customer;
        System.out.println("Order created for Customer " + customer.getName() + ".");
    }

    String label() {
        return "Order " + customer.getName();
    }

    void addItem(Product product, int quantity) {
        if (quantity <= 0) {
            System.out.println("Quantity must be positive.");
            return;
        }
        items.add(new OrderItem(product, quantity));
    }

    List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    boolean isEmpty() {
        return items.isEmpty();
    }

    double total() {
        double sum = 0;
        for (OrderItem item : items) {
            sum += item.subtotal();
        }
        return sum;
    }

    OrderStatus getStatus() {
        return status;
    }

    void markPaid() {
        status = OrderStatus.PAID;
    }
}

class PaymentProcessor {
    boolean pay(Order order, PaymentMethod method) {
        if (order.isEmpty()) {
            System.out.println("Cannot process payment for an empty order.");
            return false;
        }
        if (order.getStatus() == OrderStatus.PAID) {
            System.out.println(order.label() + " is already paid.");
            return false;
        }
        System.out.println("Payment initiated via " + method.name() + " for " + order.label() + ".");
        boolean success = method.processPayment(order.total());
        if (success) {
            order.markPaid();
            System.out.println("Payment for " + order.label() + " successful. Order status: "
                    + order.getStatus().getLabel() + ".");
        } else {
            System.out.println("Payment for " + order.label() + " failed. Order status: "
                    + order.getStatus().getLabel() + ".");
        }
        return success;
    }
}

public class week8p5 {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();

        Product a = new Product("Product A", 250.0);
        Product b = new Product("Product B", 400.0);
        Product c = new Product("Product C", 900.0);

        Customer x = new Customer("X");
        Order orderX = new Order(x);
        orderX.addItem(a, 2);
        orderX.addItem(b, 1);
        processor.pay(orderX, new CreditCardPayment(5000.0));

        Customer y = new Customer("Y");
        Order orderY = new Order(y);
        processor.pay(orderY, new CreditCardPayment(5000.0));

        Customer z = new Customer("Z");
        Order orderZ = new Order(z);
        orderZ.addItem(c, 1);
        processor.pay(orderZ, new PayPalPayment(100.0));
    }
}
