import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Clinic {
  private File patientFile;
  private int day;

  public Clinic(String fileName) {
    this(new File(fileName));
  }

  public Clinic(File file) {
    this.patientFile = file;
    this.day = 1;
  }

  public String nextDay(String fileName) throws FileNotFoundException {
    return nextDay(new File(fileName));
  }

  public String nextDay(File f) throws FileNotFoundException {
    Scanner input = new Scanner(System.in);
    StringBuilder appointmentRecords = new StringBuilder();
    Scanner appointments = new Scanner(f);

    while(appointments.hasNextLine()) {
      String[] line = appointments.nextLine().split(",");
      String name = line[0];
      String petParam = line[2];
      String typeOfPet = line[1];
      String timeIn = line[3];

      if (!typeOfPet.equals("Dog") && !typeOfPet.equals("Cat")) {
        appointments.close();
        throw new InvalidPetException();
      }

      System.out.println("Consultation for " + name + " the " + typeOfPet
        + " at " + timeIn + ".\nWhat is the health of " + name + "?");
      double health = getHealthInput(input);

      System.out.println("On a scale of 1 to 10, how much pain is " + name
        + " in right now?");
      int painLevel = getPainLevelInput(input);

      Pet pet = null;
      switch (typeOfPet) {
        case "Dog":
          pet = new Dog(name, health, painLevel, Double.parseDouble(petParam));
          break;
        case "Cat":
          pet = new Cat(name, health, painLevel, Integer.parseInt(petParam));
          break;
      }

      pet.speak();
      int treatmentTime = pet.treat();
      String timeOut = addTime(timeIn, treatmentTime);
      appointmentRecords.append(buildAppointmentInfo(
        pet.getName(),
        typeOfPet,
        petParam,
        "Day " + day,
        timeIn,
        timeOut,
        String.format("%.2f", health),
        String.format("%d", painLevel)
      ));
    }

    appointments.close();
    day++;
    return appointmentRecords.toString();
  }

  public boolean addToFile(String patientInfo) {
    String[] patientParams = patientInfo.split(",");
    String patientName = patientParams[0];
    StringBuilder patientRecords = new StringBuilder();
    boolean isExistingPatient = false;

    try (Scanner input = new Scanner(patientFile)) {
      while (input.hasNextLine()) {
        String line = input.nextLine();
        if (line.split(",")[0].equals(patientName)) {
          patientRecords.append(line + ","
            + patientParams[3] + "," + patientParams[4] + ","
            + patientParams[5] + "," + patientParams[6] + ","
            + patientParams[7] + "\n");
          isExistingPatient = true;
        } else {
          patientRecords.append(line + "\n");
        }
      }

      if (!isExistingPatient) {
        patientRecords.append(patientInfo);
      }
    } catch (FileNotFoundException e) {
      return false;
    }

    try (PrintWriter output = new PrintWriter(patientFile)) {
      output.write(patientRecords.toString());
    } catch (FileNotFoundException e) {
      return false;
    }

    return true;
  }

  private double getHealthInput(Scanner input) {
    double result = 0.0;
    boolean success = false;
    while (!success) {
      try {
        result = input.nextDouble();
        success = true;
      } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter a number.");
      } finally {
        input.nextLine();
      }
    }
    return result;
  }

  private int getPainLevelInput(Scanner input) {
    int result = 0;
    boolean success = false;
    while (!success) {
      try {
        result = input.nextInt();
        success = true;
      } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter an integer.");
      } finally {
        input.nextLine();
      }
    }
    return result;
  }

  private String addTime(String timeIn, int treatmentTime) {
    int hours = Integer.parseInt(timeIn.substring(0, 2));
    int minutes = Integer.parseInt(timeIn.substring(2, 4));
    hours += treatmentTime / 60;
    minutes += treatmentTime % 60;
    if (minutes > 59) {
      hours += 1;
      minutes -= 60;
    }
    return String.format("%02d%02d", hours, minutes);
  }

  private String buildAppointmentInfo(String... params) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < params.length; i++) {
      String s = i == params.length - 1 ? params[i] + "\n" : params[i] + ",";
      sb.append(s);
    }
    return sb.toString();
  }
}
