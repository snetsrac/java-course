public class Fly {
  // Constants
  private static final double DEFAULT_MASS = 5.0;
  private static final double DEFAULT_SPEED = 10.0;

  // Instance variables
  private double mass;
  private double speed;

  // Constructors
  public Fly() {
    this(DEFAULT_MASS, DEFAULT_SPEED);
  }

  public Fly(double mass) {
    this(mass, DEFAULT_SPEED);
  }

  public Fly(double mass, double speed) {
    this.mass = mass;
    this.speed = speed;
  }

  // Accessors
  public double getMass() {
    return mass;
  }

  public double getSpeed() {
    return speed;
  }

  // Mutators
  public double setMass(double mass) {
    if (mass >= 0.0) {
      this.mass = mass;
    }
    return this.mass;
  }

  public double setSpeed(double speed) {
    if (speed >= 0.0) {
      this.speed = speed;
    }
    return this.speed;
  }

  // Instance methods

  public void grow(int mass) {
    while (mass > 0) {
      // System.out.println("mass: " + this.mass + ", speed: " + this.speed);
      this.mass += 1.0;
      mass--;

      if (this.mass <= 20.0) {
        this.speed += 1.0;
      } else {
        this.speed -= 0.5;
      }
    }
  }

  public boolean isDead() {
    return mass == 0;
  }

  public String toString() {
    if (mass == 0) {
      return "I'm dead, but I used to be a fly with a speed of " +
        String.format("%.2f", speed) + ".";
    } else {
      return "I'm a speedy fly with " + String.format("%.2f", speed) +
        " speed and " + String.format("%.2f", mass) + " mass.";
    }
  }
}
