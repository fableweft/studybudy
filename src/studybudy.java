//+------------------------------------------------------------------+
//| Imports                                                          |
//+------------------------------------------------------------------+
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

// Import the csv classes
import java.io.FileWriter;
import java.io.IOException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;




public class studybudy {
    //+------------------------------------------------------------------+
//|    Method to get subject input                                   |
//+------------------------------------------------------------------+
    private static Set<String> subjects = new HashSet<>();
    private static String subInputString;

    public static void getSubjects() {
        char doubleQuotes = '"';
        String notice = "******************************************************************************" +
                "\n" + "                                     NOTE!!" +
                "\n" + "PRESS " + doubleQuotes + "ENTER" + doubleQuotes +
                " TWICE TO EXIT INPUT MODE OF SUBJECTS" +
                "\n" + "*******************************************************************************";
        System.out.println(notice);

        try (Scanner sc = new Scanner(System.in)) {
            do {
                System.out.println("Enter Subjects: ");
                subInputString = sc.nextLine();
                if (!subInputString.equals("")) {
                    subjects.add(subInputString);
                }
            } while (!subInputString.equals(""));
        }

        // Display the subjects after input is complete
        System.out.println("Subjects:");
        for (String subject : subjects) {
            System.out.println("Subject: " + subject);
        }
    }

//+------------------------------------------------------------------+
//| Method to generate timetable                                     |
//+------------------------------------------------------------------+
  // x for days and y for time
  private static String[][] timeTable = new String[5][8];

  public static void generateTimeTable() {
      Random rand = new Random();
      String[] allSubjects = subjects.toArray(new String[0]);

      for (int x = 0; x < 5; x++) {
          for (int y = 0; y < 8; y++) {
              String randomSubject = allSubjects[rand.nextInt(allSubjects.length)];
              timeTable[x][y] = randomSubject;
          }
      }
      System.out.println(Arrays.deepToString(timeTable)); // Display the timetable
  }

//+------------------------------------------------------------------+
//| Method to show  timetable in the terminal                        |
//+------------------------------------------------------------------+
public static void displayTimeTable() {
    String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};

    System.out.println("Timetable:");
    System.out.println("+------------+---------------+---------------+---------------+---------------+---------------+");
    System.out.print("|     Time    |");
    for (String day : days) {
        System.out.printf(" %-13s |", day);
    }
    System.out.println("\n+------------+---------------+---------------+---------------+---------------+---------------+");

    for (int y = 0; y < 4; y++) { // 4 time slots for a 2-hour interval
        int startHour = y * 2 + 8;
        int endHour = startHour + 1;
        String timeSlot = String.format("%02d:00-%02d:59", startHour, endHour);
        System.out.printf("| %-10s |", timeSlot);

        for (int x = 0; x < 5; x++) {
            System.out.printf(" %-13s |", timeTable[x][y]);
        }
        System.out.println("\n+------------+---------------+---------------+---------------+---------------+---------------+");
    }
}


//+------------------------------------------------------------------+
//| Method to write timetable to csv                                 |
//+------------------------------------------------------------------+   
public static void writeTimeTableToCSV(String fileName) {
    try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(fileName), CSVFormat.DEFAULT)) {
        // Write header row
        csvPrinter.printRecord("Time", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday");

        // Write timetable data
        for (int y = 0; y < 4; y++) {
            int startHour = y * 2 + 8;
            int endHour = startHour + 1;
            String timeSlot = String.format("%02d:00-%02d:59", startHour, endHour);
            List<String> rowData = new ArrayList<>();
            rowData.add(timeSlot);
            for (int x = 0; x < 5; x++) {
                rowData.add(timeTable[x][y]);
            }
            csvPrinter.printRecord(rowData);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}



//+------------------------------------------------------------------+
//| Method to write timetable to pdf file                            |
//+------------------------------------------------------------------+ 

//+------------------------------------------------------------------+
//| Main Method                                                      |
//+------------------------------------------------------------------+
public static void main(String[] args){
    getSubjects();
    generateTimeTable();
    displayTimeTable();
    writeTimeTableToCSV("timetable.csv");
    //writeTimeTableToPDF("timetable.pdf");
}


}
