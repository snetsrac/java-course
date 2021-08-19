import java.util.Arrays;

public class BlueAstronaut extends Player implements Crewmate {
  private static final int DEFAULT_SUS_LEVEL = 15;
  private static final int DEFAULT_NUM_TASKS = 6;
  private static final int DEFAULT_TASK_SPEED = 10;
  

  private int numTasks;
  private int taskSpeed;

  public BlueAstronaut(String name) {
    this(name, DEFAULT_SUS_LEVEL, DEFAULT_NUM_TASKS, DEFAULT_TASK_SPEED);
  }

  public BlueAstronaut(String name, int susLevel, int numTasks, int taskSpeed) {
    super(name, susLevel);
    this.numTasks = numTasks;
    this.taskSpeed = taskSpeed;
  }

  public int getNumTasks() {
    return this.numTasks;
  }

  public int setNumTasks(int numTasks) {
    return this.numTasks = numTasks;
  }

  public int getTaskSpeed() {
    return this.taskSpeed;
  }

  public int setTaskSpeed(int taskSpeed) {
    return this.taskSpeed = taskSpeed;
  }

  public void emergencyMeeting() {
    if (this.isFrozen()) return;

    Player[] players = Player.getPlayers();
    Arrays.sort(players);
    Player mostSus = null;
    Player nextMostSus = null;

    for (int i = players.length - 1; i >= 0; i--) {
      if (players[i].isFrozen()) continue;
      if (mostSus == null) {
        mostSus = players[i];
      } else {
        nextMostSus = players[i];
        break;
      }
    }

    if (nextMostSus != null && mostSus.getSusLevel() > nextMostSus.getSusLevel()) mostSus.setFrozen(true);
    this.gameOver();
  }

  public void completeTask() {
    if (this.isFrozen() || this.numTasks == 0) return;

    int tasksCompleted = this.taskSpeed > 20 ? 2 : 1;
    this.numTasks = Math.max(this.numTasks - tasksCompleted, 0);

    if (this.numTasks == 0) {
      System.out.println("I have completed all my tasks");
      this.setSusLevel((int) (this.getSusLevel() * 0.5));
    }
  }

  public boolean equals(Object o) {
    if (o instanceof BlueAstronaut) {
      BlueAstronaut player = (BlueAstronaut) o;
      return this.getName() == player.getName() && this.isFrozen() == player.isFrozen()
        && this.getSusLevel() == player.getSusLevel() && this.getNumTasks() == player.getNumTasks()
        && this.getTaskSpeed() == player.getTaskSpeed();
    }
    return false;
  }

  public String toString() {
    String blueAstronautString = super.toString() + " I have " + this.numTasks + " left over.";
    return this.getSusLevel() > 15 ? blueAstronautString.toUpperCase() : blueAstronautString;
  }
}
