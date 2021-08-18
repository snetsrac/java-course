public class Frog {
  // Constants
  private static final int DEFAULT_AGE = 5;
  private static final double DEFAULT_TONGUE_SPEED = 5.0;
  private static final double MIN_TONGUE_SPEED = 5.0;

  // Static variables
  private static String species = "Rare Pepe";

  // Instance variables
  private String name;
  private int age;
  private double tongueSpeed;
  private boolean isFroglet;

  // Constructors
  public Frog(String name, int age, double tongueSpeed) {
    this.name = name;
    this.age = age;
    this.tongueSpeed = tongueSpeed;
    this.isFroglet = isFroglet();
  }

  public Frog(String name, double ageInYears, double tongueSpeed) {
    this(name, (int) (ageInYears * 12), tongueSpeed);
  }

  public Frog(String name) {
    this(name, DEFAULT_AGE, DEFAULT_TONGUE_SPEED);
  }

  // Accessors
  public static String getSpecies() {
    return species;
  }

  // Mutators
  public static String setSpecies(String newSpecies) {
    return species = newSpecies;
  }

  // Instance methods
  public void grow(int months) {
    while (months > 0) {
      age++;
      months--;

      if (age <= 12) {
        tongueSpeed += 1.0;
      }

      if (age > 30) {
        tongueSpeed -= 1.0;
        tongueSpeed = Math.max(tongueSpeed, MIN_TONGUE_SPEED);
      }
    }

    isFroglet = isFroglet();
  }

  public void grow() {
    grow(1);
  }

  public void eat(Fly fly) {
    if (fly.isDead()) return;

    if (tongueSpeed > fly.getSpeed()) {
      if (fly.getMass() >= 0.5 * age) grow();
      fly.setMass(0.0);
    } else {
      fly.grow(1);
    }
  }

  public String toString() {
      return "My name is " + name + " and I'm a rare " + (isFroglet ? "froglet!" : "frog.") +
      " I'm " + age + " months old and my tongue has a speed of " + String.format("%.2f", tongueSpeed) + ".";
  }

  // Helper methods
  private boolean isFroglet() {
    return age > 1 && age < 7;
  }
}
