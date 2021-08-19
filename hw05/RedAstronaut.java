import java.util.Arrays;

public class RedAstronaut extends Player implements Impostor {
  private static final int DEFAULT_SUS_LEVEL = 15;
  private static final String DEFAULT_SKILL = "experienced";

  private String skill;

  public RedAstronaut(String name) {
    this(name, DEFAULT_SUS_LEVEL, DEFAULT_SKILL);
  }

  public RedAstronaut(String name, int susLevel, String skill) {
    super(name, susLevel);
    this.skill = skill;
  }

  public String getSkill() {
    return this.skill;
  }

  public String setSkill(String skill) {
    return this.skill = skill;
  }

  public void emergencyMeeting() {
    if (this.isFrozen()) return;

    Player[] players = Player.getPlayers();
    Arrays.sort(players);
    Player mostSus = null;
    Player nextMostSus = null;

    for (int i = players.length - 1; i >= 0; i--) {
      if (this.equals(players[i]) || players[i].isFrozen()) continue;
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

  public void freeze(Player p) {
    if (this.isFrozen() || p.isFrozen() || p instanceof Impostor) return;

    if (this.getSusLevel() < p.getSusLevel()) {
      p.setFrozen(true);
    } else {
      this.setSusLevel(this.getSusLevel() * 2);
    }

    this.gameOver();
  }

  public void sabotage(Player p) {
    if (this.isFrozen() || p.isFrozen() || p instanceof Impostor) return;

    if (this.getSusLevel() < 20) {
      p.setSusLevel((int) (p.getSusLevel() * 1.5));
    } else {
      p.setSusLevel((int) (p.getSusLevel() * 1.25));
    }
  }

  public boolean equals(Object o) {
    if (o instanceof RedAstronaut) {
      RedAstronaut player = (RedAstronaut) o;
      return this.getName() == player.getName() && this.isFrozen() == player.isFrozen()
        && this.getSusLevel() == player.getSusLevel() && this.getSkill() == player.getSkill();
    }
    return false;
  }

  public String toString() {
    String redAstronautString = super.toString() + " I am an " + this.skill + " player!";
    return this.getSusLevel() > 15 ? redAstronautString.toUpperCase() : redAstronautString;
  }
}
