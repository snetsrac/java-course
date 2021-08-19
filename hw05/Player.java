public abstract class Player implements Comparable<Player> {
  private static Player[] players;

  private String name;
  private int susLevel;
  private boolean frozen;

  public Player(String name, int susLevel) {
    this.name = name;
    if (susLevel >= 0) {
      this.susLevel = susLevel;
    } else {
      this.susLevel = 0;
    }
    if (players == null) {
      players = new Player[1];
      players[0] = this;
    } else {
      Player[] temp = players;
      players = new Player[temp.length + 1];
      for (int i = 0; i < temp.length; i++) {
        players[i] = temp[i];
      }
      players[players.length - 1] = this;
    }
  }

  public abstract void emergencyMeeting();

  @Override
  public int compareTo(Player p) {
    return susLevel - p.getSusLevel();
  }

  public boolean equals(Object o) {
    if (o instanceof Player) {
      Player player = (Player) o;
      return name.equals(player.name) && frozen == player.frozen
        && susLevel == player.susLevel;
    }
    return false;
  }

  public String toString() {
    String frozenString = frozen ? "frozen" : "not frozen";
    return "My name is " + name + ", and I have a susLevel of "
      + susLevel + ". I am currently " + frozenString + ".";
  }

  public boolean gameOver() {
    int impostorCount = 0;
    int crewmateCount = 0;
    for (Player p : players) {
      if (p instanceof Impostor && !p.frozen) {
        impostorCount++;
      } else if (p instanceof Crewmate && !p.frozen) {
        crewmateCount++;
      }
    }
    if (impostorCount == 0) {
      System.out.println("Crewmates Win!");
      return true;
    } else if (impostorCount >= crewmateCount) {
      System.out.println("Impostors Win!");
      return true;
    }
    return false;
  }

  public String getName() {
    return name;
  }

  public int getSusLevel() {
    return susLevel;
  }

  public void setSusLevel(int susLevel) {
    if (susLevel >= 0) {
        this.susLevel = susLevel;
    } else {
        this.susLevel = 0;
    }
  }

  public boolean isFrozen() {
      return frozen;
  }

  public void setFrozen(boolean frozen) {
      this.frozen = frozen;
  }


  public static Player[] getPlayers() {
      return players;
  }
}
