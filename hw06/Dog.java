public class Dog extends Pet {
  private static final double DEFAULT_DROOL_RATE = 5.0;
  private double droolRate;

  public Dog(String name, double health, int painLevel) {
    this(name, health, painLevel, DEFAULT_DROOL_RATE);
  }

  public Dog(String name, double health, int painLevel, double droolRate) {
    super(name, health, painLevel);
    this.droolRate = droolRate <= 0 ? 0.5 : droolRate;
  }

  public double getDroolRate() {
    return droolRate;
  }

  public int treat() {
    double treatmentTime =
      calcTreatmentTimeFactor() * this.getPainLevel() / this.getHealth();
    heal();
    return (int) Math.ceil(treatmentTime);
  }

  public void speak() {
    super.speak();
    String bark = this.getPainLevel() > 5 ? "BARK" : "bark";
    StringBuilder barkString = new StringBuilder(bark);
    for (int i = this.getPainLevel() - 1; i > 0; i--) {
      barkString.append(" " + bark);
    }
    System.out.println(barkString);
  }

  public boolean equals(Object o) {
    if (o instanceof Dog) {
      return super.equals(o) && droolRate == ((Dog) o).droolRate;
    }
    return false;
  }

  private double calcTreatmentTimeFactor() {
    double factor;
    if (droolRate < 3.5) {
      factor = 2.0;
    } else if (droolRate <= 7.5) {
      factor = 1.0;
    } else {
      factor = 0.5;
    }
    return factor;
  }
}
