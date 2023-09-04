//+------------------------------------------------------------------+
//| Imports                                                          |
//+------------------------------------------------------------------+
import java.util.*;


public class studybudy {

//+------------------------------------------------------------------+
//| Global variables                                                 |
//+------------------------------------------------------------------+
    public static String subInputString="";                                          //empty global string to hold subject input by user
    public static String topicInputString="";                                        //empty gloabal string to hold topics input by user
    public static String [][] timeTable = new String [5][8];                         //global string arrays to store days and time(duration)
    public static Map<String, ArrayList<String>> subjects = new HashMap<>();         //hashmap to store the subjects and arraylist to store the topics under each subject
  
//+------------------------------------------------------------------+
//|    Method to get subject input                                   |
//+------------------------------------------------------------------+
      public static void getSubject(){                                            
        char doublequotes = '"';
        String notice = "*******************************************************************************" + 
        "\n" + "                                     NOTE!!" +
        "\n" + "PRESS " + doublequotes + "ENTER" + doublequotes + 
        " TWICE TO EXIT INPUT MODE OF SUBJECTS AND TOPICS REPECTIVELY" + 
        "\n" + "*******************************************************************************";
        System.out.println(notice);

        Scanner sc = new Scanner(System.in);
        //getting subjects from user
        subInputString = " ";
        while (subInputString.length() > 0) {                                      
          System.out.println("Enter Subjects: ");
          subInputString = sc.nextLine();
          if (subInputString != "") {
            subjects.put(subInputString, new ArrayList<String>());                     //putting subject input into subject hashmap 
         
            //getting topics to be put under each subject entered    
            topicInputString = " ";
            while (topicInputString.length() > 0){
              System.out.println("Enter topics under " + subInputString + ":");        //putting topics into an arraylist under each subject input 
              topicInputString = sc.nextLine();
              if(topicInputString != ""){
                subjects.get(subInputString).add(topicInputString);
              }
            }
  
          }
        }
  
      System.out.println(subjects);                                                    //displaying the subjects and topics 
      sc.close();
      }

//+------------------------------------------------------------------+
//| Method to generate timetable                                     |
//+------------------------------------------------------------------+
      //x for days and y for time
     public static void generateTimeTable(){                                             
      Random rand = new Random();
      for(int x=0; x < 5; x++){
  
        for(int y=0; y < 8; y++){
          
  
        String[] allsubjectKeys = subjects.keySet().toArray(new String[0]);              //string array to store all subjectkeys from hashmap
        String randomSubject = allsubjectKeys[rand.nextInt(allsubjectKeys.length-1)];  
        
        String[] topics = subjects.get(randomSubject).toArray(new String[0]);            //string array to store all topics from hashmap         
        timeTable[x][y] = topics[rand.nextInt(topics.length-1)];  
        }
  
      }
     //system.out.println(Arrays.toString(timeTable));                                                     //displaying the timetable
   }

//+------------------------------------------------------------------+
//| Method to display TimeTable                                      |
//+------------------------------------------------------------------+   
    public static void printTimetable(){
    /*
      monday    = 6 chars
      tuesday   = 7 chars
      wednesday = 9 chars
      thursday  = 8 chars
      friday    = 6 chars
    */
    //x for days and y for time

      int [] daysLengths = {6,7,9,8,6};                                                //int array storing string lengths of the days of the week excluding the weekend 
  
      for(int x=0; x < 5; x++){
          for(String item : timeTable [x]){
            if(item.length() > daysLengths[x]){
              daysLengths[x] = item.length(); 
            }
          }
      }
   System.out.println(daysLengths);
  
  String divider = "+" + ("-".repeat(21)) + "+";
  for(int L : daysLengths){
    divider += "-".repeat(L + 2) + "+";
    
  }
  System.out.println(divider);
  
  String header = "|" + (" ".repeat(21)) + "|";
  
  String[] days = {"Monday","Teusday","Wednesday","Thursday","Friday"};
  for(int x = 0; x < 5; x++){
      header += " " + days[x];
      header += " ".repeat(daysLengths[x]-days[x].length()) + " |";
  
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