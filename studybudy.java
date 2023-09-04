//+------------------------------------------------------------------+
//| Imports                                                          |
//+------------------------------------------------------------------+
import java.util.*;

public class studybudy {

//+------------------------------------------------------------------+
//|    Method to get subject input                                   |
//+------------------------------------------------------------------+
private static HashMap<String, List<String>> subjects = new HashMap<>();
private static String subInputString;
private static String topicInputString;

public static void getSubject() {
    char doubleQuotes = '"';
    String notice = "*******************************************************************************" +
                    "\n" + "                                     NOTE!!" +
                    "\n" + "PRESS " + doubleQuotes + "ENTER" + doubleQuotes +
                    " TWICE TO EXIT INPUT MODE OF SUBJECTS AND TOPICS RESPECTIVELY" +
                    "\n" + "*******************************************************************************";
    System.out.println(notice);

    Scanner sc = new Scanner(System.in);

    do {
        System.out.println("Enter Subjects: ");
        subInputString = sc.nextLine();
        if (!subInputString.equals("")) {
            subjects.put(subInputString, new ArrayList<>());

            do {
                System.out.println("Enter topics under " + subInputString + ":");
                topicInputString = sc.nextLine();
                if (!topicInputString.equals("")) {
                    subjects.get(subInputString).add(topicInputString);
                }
            } while (!topicInputString.equals(""));
        }
    } while (!subInputString.equals(""));

    // Display the subjects and topics after input is complete
    System.out.println("Subjects and Topics:");
    for (Map.Entry<String, List<String>> entry : subjects.entrySet()) {
        System.out.println("Subject: " + entry.getKey());
        System.out.println("Topics: " + entry.getValue());
    }
}

//+------------------------------------------------------------------+
//| Method to generate timetable                                     |
//+------------------------------------------------------------------+
      //x for days and y for time
    private static String[][] timeTable = new String[5][8];

    public static void generateTimeTable() {
        Random rand = new Random();
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 8; y++) {
                String[] allSubjectKeys = subjects.keySet().toArray(new String[0]);
                String randomSubject = allSubjectKeys[rand.nextInt(allSubjectKeys.length)];
                List<String> topics = subjects.get(randomSubject);
                if (topics != null && !topics.isEmpty()) {
                    String randomTopic = topics.get(rand.nextInt(topics.size()));
                    timeTable[x][y] = randomTopic;
                }
            }
        }
        System.out.println(Arrays.deepToString(timeTable)); // Display the timetable
    }

//+------------------------------------------------------------------+
//| Method to show  timetable in the terminal                        |
//+------------------------------------------------------------------+
    /*
      monday    = 6 chars
      tuesday   = 7 chars
      wednesday = 9 chars
      thursday  = 8 chars
      friday    = 6 chars
      x for days and y for time
    */
    

   
public static void printTimetable() {
    int[] daysLengths = {6, 7, 9, 8, 6}; // Lengths for monday to friday

    for (int x = 0; x < 5; x++) {
        for (String item : timeTable[x]) {
            if (item.length() > daysLengths[x]) {
                daysLengths[x] = item.length();
            }
        }
    }

    String divider = "+";
    for (int L : daysLengths) {
        divider += "-".repeat(L + 2) + "+";
    }
    System.out.println(divider);

    String header = "|";
    String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
    for (int x = 0; x < 5; x++) {
        header += " " + days[x];
        header += " ".repeat(daysLengths[x] - days[x].length()) + " |";
    }
    System.out.println(header);
    System.out.println(divider);
}


//+------------------------------------------------------------------+
//| Method to write TimeTable to csv and pdf file                    |
//+------------------------------------------------------------------+   


//+------------------------------------------------------------------+
//| Main Method                                                      |
//+------------------------------------------------------------------+
        public static void main(String[] args){
        getSubject();
        generateTimeTable();
        printTimetable();
        }

  }