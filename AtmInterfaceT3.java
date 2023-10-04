import java.util.*;

class BankAccount
 {
    private double balance;

    public BankAccount(double initialBalance)
     {
        balance = initialBalance;
    }

    public boolean deposit(double amount)
     {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount)
     {
        if (amount > 0 && amount <= balance)
         {
            balance -= amount;
            return true;
        }
        return false;
    }

    public double checkBalance() 
    {
        return balance;
    }
}

class ATM 
{
    private BankAccount bankAccount;
    private Scanner scanner;

    public ATM(BankAccount account)
     {
        bankAccount = account;
        scanner = new Scanner(System.in);
    }

    public void displayMenu()
     {
        System.out.println("ATM Menu:");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }

    public void run()
     {
        while (true) 
        {
            displayMenu();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice)
             {
                case 1:
                    System.out.print("Enter the withdrawal amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    if (bankAccount.withdraw(withdrawAmount)) {
                        System.out.println("Withdrawal successful.");
                    } 
                    else 
                    {
                        System.out.println("Withdrawal failed. Insufficient balance.");
                    }
                    break;

                case 2:
                    System.out.print("Enter the deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    if (bankAccount.deposit(depositAmount))
                     {
                        System.out.println("Deposit successful.");
                    }
                     else
                      {
                        System.out.println("Deposit failed. Invalid amount.");
                    }
                    break;

                case 3:
                    System.out.println("Your account balance: " + bankAccount.checkBalance());
                    break;

                case 4:
                    System.out.println("Thank you for using the ATM.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}

public class AtmInterfaceT3 
{
    public static void main(String[] args)
     {
        Scanner snl = new Scanner(System.in);
        System.out.print("Enter your initial account balance: ");
        double initialBalance = snl.nextDouble();
        BankAccount userAccount = new BankAccount(initialBalance);
        ATM atm = new ATM(userAccount);
        atm.run();
    }
}
