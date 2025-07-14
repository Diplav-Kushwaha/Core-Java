package CoreJava;


import java.util.Map;
import java.util.List;
import java.util.Stack;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.time.LocalDate;

class Product {
    private String name;
    private String company;
    private double costPrice;
    private double sellingPrice;
    private int quantity;
    private LocalDate expiryDate;

    public Product(String name, String company, double costPrice, double sellingPrice, int quantity, LocalDate expiryDate) {
        this.name = name;
        this.company = company;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }

    public String getName() {
        return name;
    }
    public String getCompany() {
        return company;
    }
    public double getCostPrice() {
        return costPrice;
    }
    public double getSellingPrice() {
        return sellingPrice;
    }
    public int getQuantity() {
        return quantity;
    }
    public LocalDate getExpiryDate() {
        return expiryDate;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String toString() {
        return "Product{" +
                ", name='"        +name+'\''+
                ", company='"     +company+'\''+
                ", Price="        +sellingPrice+
                ", quantity="     +quantity+
                ", expiryDate="   +expiryDate+'}';
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Product)) return false;

        Product product = (Product) obj;
        return name.equalsIgnoreCase(product.name) && company.equalsIgnoreCase(product.company);
    }
    @Override
    public int hashCode() {
        return name.toLowerCase().hashCode() + company.toLowerCase().hashCode();
    }
}
class Shopkeeper {

    private List<Product> inventory;
    public Shopkeeper(){
        inventory=new ArrayList<>();
    }
    public void addProduct(Product product){
        inventory.add(product);
        System.out.println("Product added successfully!");
    }
    public void removeProductByName(String name){
        Iterator<Product> iterator=inventory.iterator();
        boolean found=false;
        while(iterator.hasNext()){
            Product prod=iterator.next();
            if (prod.getName().equalsIgnoreCase(name)){
                iterator.remove();
                found=true;
                System.out.println("Product '"+name+"' removed successfully!");
            }
        }
        if (!found) {
            System.out.println("Product not found with name: "+name);
        }
    }
    public void removeExpiredProducts() {
        LocalDate today=LocalDate.now();
        Iterator<Product> iterator=inventory.iterator();
        while(iterator.hasNext()){
            Product prod=iterator.next();
            if(prod.getExpiryDate().isBefore(today)){
                iterator.remove();
                System.out.println("Removed expired product: "+prod.getName());
            }
        }
    }
    public void searchByName(String name){
        boolean found=false;
        for(Product prod : inventory){
            if(prod.getName().equalsIgnoreCase(name)){
                System.out.println(prod);
                found=true;
            }
        }
        if(!found){
            System.out.println("Product not found with name: "+name);
        }
    }
    public void searchByCompany(String company){
        boolean found=false;
        for(Product prod : inventory){
            if(prod.getCompany().equalsIgnoreCase(company)){
                System.out.println(prod);
                found=true;
            }
        }
        if(!found){
            System.out.println("Product not found from company: "+company);
        }
    }
    public void searchByPrice(double min, double max){
        boolean found=false;
        for(Product prod : inventory){
            if(prod.getSellingPrice() >= min && prod.getSellingPrice() <= max){
                System.out.println(prod);
                found=true;
            }
        }
        if(!found){
            System.out.println("Products not found in price range "+min+" - "+max);
        }
    }
    public List<Product> getInventory(){
        return inventory;
    }
    public void showAllProducts(){
        if (inventory.isEmpty()){
            System.out.println("No products in inventory.");
        }else{
            System.out.println("==============All Products in Inventory============");
            for(Product prod: inventory) {
                System.out.println(prod);
            }
        }
    }
}
class Customer{

    private Map<Product, Integer> cart;
    private Stack<Product> returnHistory;

    public Customer(){
        cart=new HashMap<>();
        returnHistory=new Stack<>();
    }
    public void purchaseProduct(Shopkeeper shopkeeper, Scanner sc){

        shopkeeper.showAllProducts();
        System.out.print("Enter product name to purchase: ");
        String productName = sc.nextLine();
        boolean found=false;

        for(Product prod : shopkeeper.getInventory()){
            if(prod.getName().equalsIgnoreCase(productName)){
                System.out.print("Enter quantity to purchase: ");
                int qty=Integer.parseInt(sc.nextLine());

                try{
                    if (prod.getQuantity()>=qty){
                        prod.setQuantity(prod.getQuantity()-qty);
                        cart.put(prod, cart.getOrDefault(prod, 0)+qty);
                        System.out.println("Product added to cart!");
                        found = true;
                        break;
                    }else{
                        throw new Exception("Insufficient stock available.");
                    }
                }catch(Exception e){
                    System.out.println("Error: "+e.getMessage());
                }
            }
        }
        if(!found){
            System.out.println("Product not found in inventory.");
        }
    }

    public void returnProduct(Scanner sc) {
        if(cart.isEmpty()){
            System.out.println("Cart is empty. Nothing to return.");
            return;
        }

        System.out.print("Enter product name to return: ");
        String returnName=sc.nextLine();
        boolean found=false;

        for(Map.Entry<Product, Integer> entry : cart.entrySet()){
            Product prod=entry.getKey();
            int qty=entry.getValue();

            if (prod.getName().equalsIgnoreCase(returnName)) {
                System.out.print("Enter quantity to return: ");
                int returnQty=Integer.parseInt(sc.nextLine());

                if(returnQty <= qty){
                    cart.put(prod, qty-returnQty);
                    prod.setQuantity(prod.getQuantity()+returnQty);
                    returnHistory.push(prod);
                    System.out.println("Returned "+returnQty+" items of "+prod.getName());

                    if(cart.get(prod)==0){
                        cart.remove(prod);
                    }
                    found = true;
                    break;
                }else{
                    System.out.println("You only purchased "+qty+" items.");
                }
            }
        }
        if(!found){
            System.out.println("Product not found in cart.");
        }
    }
    public void viewCart(){
        if(cart.isEmpty()){
            System.out.println("Your cart is empty.");
        }else{
            System.out.println("==============Cart Items===========");
            for(Map.Entry<Product, Integer> entry : cart.entrySet()){
                Product prod=entry.getKey();
                int qty=entry.getValue();
                System.out.println(prod.getName()+" x "+qty+" = ₹"+(prod.getSellingPrice()*qty));
            }
        }
    }
    public void printBill() {

        double total = 0;
        System.out.println("============= FINAL BILL =============");

        for(Map.Entry<Product, Integer> entry : cart.entrySet()){
            Product prod=entry.getKey();
            int qty=entry.getValue();
            double subtotal=prod.getSellingPrice()*qty;
            total += subtotal;
            System.out.println(prod.getName()+" ("+qty+" x ₹"+prod.getSellingPrice()+") = ₹"+subtotal);
        }
        System.out.println("Total Amount: ₹"+total);
        System.out.println("=======================================");
    }
    public Map<Product, Integer> getCart(){
        return cart;
    }
}
class Bill {
    private Map<Product, Integer> cart;

    public Bill(Map<Product, Integer> cart){
        this.cart = cart;
    }
    public void generateSummary(){
        double totalSelling=0;
        double totalCost=0;

        System.out.println("=========== Shopkeeper Billing Summary ===========");

        for(Map.Entry<Product, Integer> entry : cart.entrySet()){

            Product p=entry.getKey();
            int qty=entry.getValue();
            double cost=p.getCostPrice()*qty;
            double sell=p.getSellingPrice()*qty;
            totalCost +=cost;
            totalSelling +=sell;

            System.out.println(p.getName()+" ("+qty+" items): Cost = ₹"+cost+", Sell = ₹"+sell);
        }

        System.out.println("==============================================");
        System.out.println("Total Cost Price  = ₹"+totalCost);
        System.out.println("Total Selling Price = ₹"+totalSelling);

        double profit=totalSelling-totalCost;
        if(profit>0){
            System.out.println("Total Profit = ₹"+profit);
        }else if(profit<0){
            System.out.println("Total Loss = ₹"+(-profit));
        }else{
            System.out.println("Break-even: No profit, no loss.");
        }

        System.out.println("==========================================");
    }
}
public class MarketShopApp {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Shopkeeper shopkeeper = new Shopkeeper();
        Customer customer = new Customer();

        shopkeeper.addProduct(new Product("Soap", "Dove", 20, 30, 50, LocalDate.of(2025, 12, 31)));

        boolean exit = false;
        System.out.println("====================== Welcome to Market Shop ===========================");

        while(!exit){
            System.out.println("\nChoose user type:");
            System.out.println("1. Shopkeeper");
            System.out.println("2. Customer");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    shopkeeperMenu(shopkeeper, sc);
                    break;
                case "2":
                    customerMenu(shopkeeper, customer, sc);
                    break;
                case "3":
                    System.out.println("Thanks for using Market Shop! Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
    public static void shopkeeperMenu(Shopkeeper shopkeeper, Scanner sc) {

        boolean back = false;
        while (!back) {
            System.out.println("\n==========Shopkeeper Menu=========");
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product by Name");
            System.out.println("3. Search Product by Name");
            System.out.println("4. Search Product by Company");
            System.out.println("5. Search Product by Price Range");
            System.out.println("6. Show All Products");
            System.out.println("7. Remove Expired Products");
            System.out.println("8. Back to Main Menu");
            System.out.print("Enter your choice: ");
            String option = sc.nextLine();

            switch(option){
                case "1":
                    System.out.print("Enter product name: ");
                    String name=sc.nextLine();
                    System.out.print("Enter company: ");
                    String company=sc.nextLine();
                    System.out.print("Enter cost price: ");
                    double cost=Double.parseDouble(sc.nextLine());
                    System.out.print("Enter selling price: ");
                    double sell=Double.parseDouble(sc.nextLine());
                    System.out.print("Enter quantity: ");
                    int qty=Integer.parseInt(sc.nextLine());
                    System.out.print("Enter expiry date (YYYY-MM-DD): ");
                    LocalDate date=LocalDate.parse(sc.nextLine());

                    Product newProduct=new Product(name, company, cost, sell, qty, date);
                    shopkeeper.addProduct(newProduct);
                    break;

                case "2":
                    System.out.print("Enter product name to remove: ");
                    String removeName=sc.nextLine();
                    shopkeeper.removeProductByName(removeName);
                    break;

                case "3":
                    System.out.print("Enter product name to search: ");
                    String searchName=sc.nextLine();
                    shopkeeper.searchByName(searchName);
                    break;

                case "4":
                    System.out.print("Enter company name to search: ");
                    String companySearch=sc.nextLine();
                    shopkeeper.searchByCompany(companySearch);
                    break;

                case "5":
                    System.out.print("Enter min price: ");
                    double min=Double.parseDouble(sc.nextLine());
                    System.out.print("Enter max price: ");
                    double max=Double.parseDouble(sc.nextLine());
                    shopkeeper.searchByPrice(min, max);
                    break;

                case "6":
                    shopkeeper.showAllProducts();
                    break;

                case "7":
                    shopkeeper.removeExpiredProducts();
                    break;

                case "8":
                    back = true;
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
    public static void customerMenu(Shopkeeper shopkeeper, Customer customer, Scanner sc){

        boolean back=false;
        while(!back){
            System.out.println("\n========Customer Menu=========");
            System.out.println("1. Purchase Product");
            System.out.println("2. Return Product");
            System.out.println("3. View Cart");
            System.out.println("4. Generate Final Bill");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            String option=sc.nextLine();

            switch (option) {
                case "1":
                    customer.purchaseProduct(shopkeeper, sc);
                    break;

                case "2":
                    customer.returnProduct(sc);
                    break;

                case "3":
                    customer.viewCart();
                    break;

                case "4":
                    customer.viewCart();
                    customer.printBill();
                    Bill bill=new Bill(customer.getCart());
                    bill.generateSummary();
                    back=true;
                    break;

                case "5":
                    back=true;
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

}
