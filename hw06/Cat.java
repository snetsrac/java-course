public class Cat extends Pet {
  private static final int DEFAULT_MICE_CAUGHT = 0;
  private int miceCaught;

  public Cat(String name, double health, int painLevel) {
    this(name, health, painLevel, DEFAULT_MICE_CAUGHT);
  }

  public Cat(String name, double health, int painLevel, int miceCaught) {
    super(name, health, painLevel);
    this.miceCaught = Math.max(miceCaught, 0);
  }

  public int getMiceCaught() {
    return miceCaught;
  }

  public int treat() {
    double treatmentTime =
      calcTreatmentTimeFactor() * this.getPainLevel() / this.getHealth();
      heal();
    return (int) Math.ceil(treatmentTime);
  }

  public void speak() {
    super.speak();
    String meow = this.getPainLevel() > 5 ? "MEOW" : "meow";
    StringBuilder meowString = new StringBuilder(meow);
    for (int i = this.getPainLevel() - 1; i > 0; i--) {
      meowString.append(" " + meow);
    }
    System.out.println(meowString);
  }

  public boolean equals(Object o) {
    if (o instanceof Cat) {
      return super.equals(o) && miceCaught == ((Cat) o).miceCaught;
    }
    return false;
  }

  private double calcTreatmentTimeFactor() {
    double factor;
    if (miceCaught < 4) {
      factor = 2.0;
    } else if (miceCaught <= 7) {
      factor = 1.0;
    } else {
      factor = 0.5;
    }
    return factor;
  }
}
