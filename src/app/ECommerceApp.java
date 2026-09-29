package app;

import domain.Product;
import domain.Roles;
import domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ECommerceApp extends Application {
    @Override
    public void start() {
        users.add(new User(1, "ADMIN", "admin@gmail.com", "123", Roles.ADMIN));

        while (true) {
            printMainMenu();
            int choice = getChoice();

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    System.out.println("Proqramdan çıxılır. Sağ olun!");
                    return;
                default:
                    System.out.println("Yanlış seçim! Yenidən cəhd edin.");
            }
        }
    }

    private static Scanner scanner = new Scanner(System.in);

    private static List<User> users = new ArrayList<>();
    private static List<Product> products = new ArrayList<>();
    private static List<String> cart = new ArrayList<>();
    private static List<String> orders = new ArrayList<>();

    private static void printMainMenu() {
        System.out.println("\n===== E-COMMERCE PLATFORM =====");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print("Seçiminizi edin: ");
    }

    private static void register() {
        System.out.print("İstifadəçi adı yarat: ");
        String username = scanner.next();
        System.out.print("Şifrə yarat: ");
        String password = scanner.next();
        System.out.print("Email daxil et: ");
        String email = scanner.next();
        int nextId = users.stream()
                .mapToInt(User::getId)
                .max()
                .orElse(0) + 1;

        users.add(new User(nextId, username, password, email, Roles.CUSTOMER));
        System.out.println(username + " Uğurla qeydiyyatdan keçdiniz!");
    }

    private static void login() {
        System.out.print("İstifadəçi adı: ");
        String username = scanner.next();
        System.out.print("Şifrə: ");
        String password = scanner.next();

        for (User user : users) {
            if (user.getName().equals(username) && user.authenticate(password)) {
                System.out.println("Giriş uğurludur! Xoş gəldiniz, " + username);
                if (user.getRole() == Roles.ADMIN) {
                    adminMenu();
                } else {
                    customerMenu();
                }
                return;
            }
        }
        System.out.println("İstifadəçi adı və ya şifrə səhvdir!");
    }

    private static void customerMenu() {
        while (true) {
            System.out.println("\n===== CUSTOMER MENU =====");
            System.out.println("1. Browse Products (filter: category / price range)");
            System.out.println("2. Add to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Apply Coupon");
            System.out.println("5. Checkout");
            System.out.println("6. Order History");
            System.out.println("7. Logout");
            System.out.print("Seçiminizi edin: ");

            int choice = getChoice();
            switch (choice) {
                case 1:
                    browseProducts();
                    break;
                case 2:
                    addToCart();
                    break;
                case 3:
                    viewCart();
                    break;
                case 4:
                    System.out.println("Kupon tətbiq edildi: 10% endirim!");
                    break;
                case 5:
                    checkout();
                    break;
                case 6:
                    viewOrderHistory();
                    break;
                case 7:
                    System.out.println("Hesabdan çıxıldı.");
                    return;
                default:
                    System.out.println("Yanlış seçim!");
            }
        }
    }

    private static void adminMenu() {
        while (true) {
            System.out.println("\n===== ADMIN MENU =====");
            System.out.println("1. Manage Products (add / update / remove)");
            System.out.println("2. Manage Categories");
            System.out.println("3. View All Orders");
            System.out.println("4. Update Order Status");
            System.out.println("5. Manage Coupons");
            System.out.println("6. Logout");
            System.out.print("Seçiminizi edin: ");

            int choice = getChoice();
            switch (choice) {
                case 1:
                    manageProducts();
                    break;
                case 2:
                    System.out.println("[Admin] Kateqoriyalar idarəetməsi açıldı.");
                    break;
                case 3:
                    viewAllOrders();
                    break;
                case 4:
                    System.out.println("[Admin] Sifariş statusu yeniləndi.");
                    break;
                case 5:
                    System.out.println("[Admin] Kuponlar idarəetməsi açıldı.");
                    break;
                case 6:
                    System.out.println("Admin hesabından çıxıldı.");
                    return;
                default:
                    System.out.println("Yanlış seçim!");
            }
        }
    }

    private static int getChoice() {
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            scanner.next();
            return -1;
        }
    }

    private static void browseProducts() {
        System.out.println("\n--- MƏHSULLAR ---");
        for (Product p : products) {
            System.out.println("ID: " + p.getId() + " | Ad: " + p.getName() + " | Kateqoriya: " + p.getCategory()
                    + " | Qiymət: " + p.getPrice() + " AZN");
        }
    }

    private static void addToCart() {

    }

    private static void viewCart() {
        System.out.println("\n--- SƏBƏTİNİZ ---");
    }

    private static void checkout() {
        if (cart.isEmpty()) {
            System.out.println("Səbətiniz boşdur, sifariş verə bilməzsiniz!");
            return;
        }
        orders.addAll(cart);
        cart.clear();
        System.out.println("Sifarişiniz uğurla tamamlandı!");
    }

    private static void viewOrderHistory() {
        System.out.println("\n--- SİFARİŞ TARİXÇƏSİ ---");
    }

    private static void manageProducts() {
        System.out.print("Yeni məhsul əlavə etmək istəyirsiniz? (ad|kateqoriya|qiymət formatında yazın): ");

    }

    private static void viewAllOrders() {
        System.out.println("\n--- BÜTÜN SİFARİŞLƏR (ADMİN) ---");
    }
}
