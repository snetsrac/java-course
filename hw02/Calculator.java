import java.util.Scanner;

public class Calculator {
  private static final String ERROR_MESSAGE = "Invalid input entered. Terminating...";
  private static Scanner input;

  public static void main(String[] args) {
    input = new Scanner(System.in);

    System.out.println("List of operations: add subtract multiply divide alphabetize");
    System.out.println("Enter an operation:");

    String operation = input.nextLine().toLowerCase();

    switch (operation) {
      case "add":
        add();
        break;
      case "subtract":
        subtract();
        break;
      case "multiply":
        multiply();
        break;
      case "divide":
        divide();
        break;
      case "alphabetize":
        alphabetize();
        break;
      default:
        System.out.println(ERROR_MESSAGE);
    }
  }

  private static void add() {
    System.out.println("Enter two integers:");

    int x, y;

    if (input.hasNextInt()) {
      x = input.nextInt();
    } else {
      System.out.println(ERROR_MESSAGE);
      return;
    }

    if (input.hasNextInt()) {
      y = input.nextInt();
    } else {
      System.out.println(ERROR_MESSAGE);
      return;
    }

    System.out.printf("Answer: %d\n", x + y);
  }

  private static void subtract() {
    System.out.println("Enter two integers:");

    int x, y;

    if (input.hasNextInt()) {
      x = input.nextInt();
    } else {
      System.out.println(ERROR_MESSAGE);
      return;
    }

    if (input.hasNextInt()) {
      y = input.nextInt();
    } else {
      System.out.println(ERROR_MESSAGE);
      return;
    }

    System.out.printf("Answer: %d\n", x - y);
  }

  private static void multiply() {
    System.out.println("Enter two doubles:");

    double x, y;

    if (input.hasNextDouble()) {
      x = input.nextDouble();
    } else {
      System.out.println(ERROR_MESSAGE);
      return;
    }

    if (input.hasNextDouble()) {
      y = input.nextDouble();
    } else {
      System.out.println(ERROR_MESSAGE);
      return;
    }

    System.out.printf("Answer: %.2f\n", x * y);
  }

  private static void divide() {
    System.out.println("Enter two doubles:");

    double x, y;

    if (input.hasNextDouble()) {
      x = input.nextDouble();
    } else {
      System.out.println(ERROR_MESSAGE);
      return;
    }

    if (input.hasNextDouble()) {
      y = input.nextDouble();
    } else {
      System.out.println(ERROR_MESSAGE);
      return;
    }

    if (y == 0.0) {
      System.out.println(ERROR_MESSAGE);
      return;
    }

    System.out.printf("Answer: %.2f\n", x / y);
  }

  private static void alphabetize() {
    System.out.println("Enter two words:");

    String x = input.next();
    String y = input.next();
    int answer = x.toLowerCase().compareTo(y.toLowerCase());

    if (answer < 0) {
      System.out.println("Answer: " + x + " comes before " + y + " alphabetically.");
    } else if (answer > 0) {
      System.out.println("Answer: " + y + " comes before " + x + " alphabetically.");
    } else {
      System.out.println("Answer: Chicken or Egg.");
    }
  }
}