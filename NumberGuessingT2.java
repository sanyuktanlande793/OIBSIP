import java.util.*;

public class NumberGuessingT1
 {
    public static void main(String[] args) 
    {
        Scanner snl = new Scanner(System.in);
        Random rand= new Random();

        int minRange = 1;
        int maxRange = 100;
        int maxAttempts = 3;
        int totalRounds = 3; // Number of rounds to play

        System.out.println("Let's play the Number Guessing Game!");

        int totalScore = 0;
        for (int round = 1; round <= totalRounds; round++)
         {
            int targetNumber = rand.nextInt(maxRange - minRange + 1) + minRange;

            System.out.println("\nRound " + round + ":");

            for (int attempts = 1; attempts <= maxAttempts; attempts++) 
            {
                System.out.print("Attempt " + attempts + "/" + maxAttempts + ": Enter your guess: ");
                int yourGuess = snl.nextInt();

                if (yourGuess == targetNumber)
                 {
                    System.out.println("Congratulations! You guessed the correct number " + targetNumber + " in " + attempts + " attempts.");
                    totalScore += maxAttempts - attempts + 1;
                    break;
                }
                 else if (yourGuess < targetNumber)
                 {
                    System.out.println("Too low! Try again.");
                } 
                else 
                {
                    System.out.println("Too high! Try again.");
                }

                if (attempts == maxAttempts)
                 {
                    System.out.println("Sorry, you've reached the maximum number of attempts.");
                    System.out.println("The correct number was: " + targetNumber);
                }
            }
        }

        System.out.println("\nGame over. Your total score is: " + totalScore);
    }
}
