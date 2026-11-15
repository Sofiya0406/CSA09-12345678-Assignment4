import java.io.*;
import java.util.*;

public class Atm {
    static int balance = 5000;

    static void withdraw(int amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Amount must be positive");
        if (amount > balance)
            throw new IllegalStateException("Insufficient balance");
        balance -= amount;
        System.out.println("Dispensed " + amount + ", balance " + balance);
    }

    static void readCard(String file) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            System.out.println("Card read: " + br.readLine());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            readCard("card.txt");
            System.out.print("Account number: ");
            int acc = Integer.parseInt(sc.nextLine());
            System.out.print("Amount: ");
            int amt = Integer.parseInt(sc.nextLine());
            withdraw(amt);
        } catch (FileNotFoundException e) {      // narrower, before IOException
            System.out.println("Card reader file missing");
        } catch (IOException e) {
            System.out.println("Card read error: " + e.getMessage());
        } catch (NumberFormatException | IllegalStateException e) { // multi-catch
            System.out.println("Invalid input/state: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Bad amount: " + e.getMessage());
        } finally {
            System.out.println("Session closed");
            sc.close();
        }
    }
    }
