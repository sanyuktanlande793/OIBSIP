import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Book 
{
    String title;
    String author;
    boolean available;

    public Book(String title, String author) 
{
        this.title = title;
        this.author = author;
        this.available = true;
    }
}

class User
{
    String username;
    String password;

    public User(String username, String password)
 {
        this.username = username;
        this.password = password;
    }
}

class Library 
{
    Map<String, Book> catalog = new HashMap<>();
    List<User> users = new ArrayList<>();
    User currentUser;

    public void addBook(String title, String author) 
{
        catalog.put(title, new Book(title, author));
    }

    public void addUser(String username, String password) 
    {
        users.add(new User(username, password));
    }

    public boolean login(String username, String password)
 {
        for (User user : users)
 {
            if (user.username.equals(username) && user.password.equals(password)) 
            {
                currentUser = user;
                return true;
            }
        }
        return false;
    }

    public void browseCatalog() 
{
        System.out.println("Catalog:");
        for (Book book : catalog.values()) 
{
            System.out.println(book.title + " by " + book.author + " (Available: " + book.available + ")");
        }
    }

    public Book searchBook(String title) 
{
        return catalog.get(title);
    }

    public void issueBook(String title) 
{
        Book book = catalog.get(title);
        if (book != null && book.available) 
       {
            book.available = false;
            System.out.println("Book '" + title + "' issued to " + currentUser.username);
        } 
else 
        {
            System.out.println("Book '" + title + "' is not available.");
        }
    }

    public void returnBook(String title)
 {
        Book book = catalog.get(title);
        if (book != null && !book.available) 
     {
            book.available = true;
            System.out.println("Book '" + title + "' returned by " + currentUser.username);
        } else {
            System.out.println("Book '" + title + "' cannot be returned.");
        }
    }
}

public class DigitalLibraryT5 
{
    public static void main(String args[]) 
    {
        Library library = new Library();
        library.addBook("Ikigai", "Author Hector Garcia");
        library.addBook("LeanIn", "Author Sheryl Sandberg");
        library.addUser("admin", "adminpass");
        library.addUser("user", "userpass");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Library System");

        while (true)
  {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            if (library.login(username, password)) 
          {
                System.out.println("Welcome, " + username + "!");
                break;
            } 
       else 
             {
                System.out.println("Invalid username or password. Please try again.");
            }
        }

        while (true) 
        {
            System.out.println("Menu:");
            System.out.println("1. Browse Catalog");
            System.out.println("2. Search Book");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");
            int option = Integer.parseInt(scanner.nextLine());

            switch (option) 
         {
                case 1:
                    library.browseCatalog();
                    break;
                case 2:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    Book book = library.searchBook(title);
                    if (book != null) 
                   {
                        System.out.println("Book found: " + book.title + " by " + book.author);
                    }  
                 else {
                        System.out.println("Book not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter book title to issue: ");
                    title = scanner.nextLine();
                    library.issueBook(title);
                    break;
                case 4:
                    System.out.print("Enter book title to return: ");
                    title = scanner.nextLine();
                    library.returnBook(title);
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
