import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class User 
{
    private String username;
    private String password;
    private String name;

    public User(String username, String password, String name) 
    {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public String getUsername() 
    {
        return username;
    }

    public String getPassword()
     {
        return password;
     }

    public String getName() 
    {
        return name;
    }
}

class Question 
{
    private String question;
    private String[] options;
    private int correctOption;

    public Question(String question, String[] options, int correctOption) 
    {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getQuestion() 
    {
        return question;
    }

    public String[] getOptions()
     {
        return options;
     }

    public int getCorrectOption() 
    {
        return correctOption;
    }
}

public class OnlineExamT4
 {
    private static Map<String, User> users = new HashMap<>();
    private static User currentUser;
    private static Timer timer = new Timer();
    private static int timeRemaining =30; // TIME LIMIT in seconds

    public static void main(String args[])
     {
        initializeUsers();
        login();

        if (currentUser != null) 
        {
            updateProfile();
            startExam();
        }
    }

    private static void initializeUsers() 
    {
        //  sample users
        users.put("user1", new User("user1", "1234", "sanyukta lande"));
        users.put("user2", new User("user2", "5678", "charlie chaplin"));
    }

    private static void login() 
    {
        Scanner snl = new Scanner(System.in);
        System.out.println("Enter username: ");
        String username = snl.nextLine();
        System.out.println("Enter password: ");
        String password = snl.nextLine();

        User user = users.get(username);
        if (user != null && user.getPassword().equals(password))
         {
            currentUser = user;
            System.out.println("Login successful. Welcome, " + currentUser.getName() + "!");
         } 
        else
        {
            System.out.println("Login failed. Invalid username or password.");
        }
    }

    private static void updateProfile() 
    {
        if (currentUser != null) 
        {
            Scanner snl = new Scanner(System.in);
            System.out.print("Enter new name: ");
            System.out.println("Enter new password:");
            String newName = snl.nextLine();
            currentUser = new User(currentUser.getUsername(), currentUser.getPassword(), newName);
            System.out.println("Profile updated successfully.");
        }
    }

    private static void startExam()
     {
        Question[] questions =
         {
            new Question("What is the capital of INDIA?", new String[]{"A. DILHI", "B. Vidarbha", "C. Kerla", "D. Mumbai"}, 0),
            new Question("What is the largest planet in our solar system?", new String[]{"A. Earth", "B. Jupiter", "C. Venus", "D. Mars"}, 1)
            //MCQ Questions 
        };

        System.out.println("Welcome to the Online Exam, " + currentUser.getName() + "!");
        System.out.println("You have 30 sec to complete the exam.");

        TimerTask task = new TimerTask()
         {
            public void run()
             {
                System.out.println("Time's up! Your exam is automatically submitted.");
                submitExam();
            }
        };

        timer.schedule(task, timeRemaining * 1000);

        int score = 0;
        Scanner snl = new Scanner(System.in);

        for (int i = 0; i < questions.length; i++) 
        {
            Question question = questions[i];
            System.out.println(question.getQuestion());
            for (int j = 0; j < question.getOptions().length; j++) 
            {
                System.out.println(question.getOptions()[j]);
            }
            System.out.print("Enter your answer (A, B, C, or D): ");
            String userAnswer = snl.nextLine().toUpperCase();

            if (userAnswer.equals("A") || userAnswer.equals("B") || userAnswer.equals("C") || userAnswer.equals("D")) {
                int userChoice = userAnswer.charAt(0) - 'A';
                if (userChoice == question.getCorrectOption())
                 {
                    System.out.println("Correct!");
                    score++;
                }
                 else 
                 {
                    System.out.println("Incorrect. The correct answer is " + question.getOptions()[question.getCorrectOption()]);
                 }
            } 
            else
            {
                System.out.println("Invalid choice. Please choose A, B, C, or D.");
                i--; // Repeat the current question
            }
        }

        timer.cancel();
        submitExam();
    }

    private static void submitExam()
     {
        System.out.println("Exam finished. Your score: " + currentUser.getName() + ", " + currentUser.getUsername() + "!");
        logout();
    }

    private static void logout() 
    {
        currentUser = null;
        System.out.println("Logged out successfully.");
    }
}
