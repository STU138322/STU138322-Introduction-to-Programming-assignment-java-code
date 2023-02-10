import java.util.ArrayList;
import java.util.Scanner;

public class SubjectManagement {
    static ArrayList<String> classList = new ArrayList<>();
    static double[] studentMarks = new double[classList.size()];
    static String[] subjects = {"English", "Maths", "Science", "Geography", "History", "Physical education", "Digital & Creative", "Social studies"};
    static double[] totalMarks = {100, 120, 150, 100, 100, 80, 150, 100};
    static int tempSubjectIndex;
    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n1. Create a class list"); //Displaying menu
            System.out.println("2. Provide marks for a subject");
            System.out.println("3. Get grade statistics for a subject");
            System.out.println("4. Exit");
            System.out.print("\nEnter option: ");
            try {
                option = sc.nextInt();
                switch (option) { // options for calling methods
                    case 1:
                        createClassList();
                        break;
                    case 2:
                        provideMarks();
                        break;
                    case 3:
                        getGradeStats();
                        break;
                    case 4:
                        System.out.println("Exiting program");
                        break;
                    default:
                        System.out.println("Invalid option selected. Try again.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Try again.");
                sc.nextLine();
            }
        } while (option != 4);
    }

    public static void createClassList() {
        Scanner sc = new Scanner(System.in);
        String studentId;       // initializing the studentsid list
        System.out.println("\nEnter student IDs for all the students enrolled in the class (Enter 'q' to stop):\n");
        do {
            System.out.print("Student ID: ");
            studentId = sc.nextLine();
            if (!studentId.equals("q")) { // checking for "q"
                classList.add(studentId);
            }
        } while (!studentId.equals("q"));
        System.out.println("\nClass list created successfully");
    }

    public static void provideMarks() {
        Scanner sc = new Scanner(System.in);
        double[] tempStudentMarks = new double[classList.size()]; // creating a temp list for the studentmarks
        int subjectIndex = 0;
        int marks;
    
        if (classList.isEmpty()) {
            System.out.println("\nNo class list found. Please create a class list first."); // checking to make sure that there is no class list
            return;
        }
    
        System.out.println("\nEnter the subject for which you want to provide marks:");
        for (int i = 0; i < subjects.length; i++) {     // Displaying subjects menu
            System.out.println((i + 1) + ". " + subjects[i]);
        }
        try {
            System.out.print("\nEnter option: ");
            subjectIndex = Integer.parseInt(sc.nextLine()) - 1; // Storing the user input in subjectIndex
            tempSubjectIndex = subjectIndex;
            if (subjectIndex < 0 || subjectIndex >= subjects.length) {
                System.out.println("Invalid subject selected. Try again.");
                return;
            }
            System.out.println("\nMaximum possible score in " + subjects[subjectIndex] + " is " + Math.round(totalMarks[subjectIndex]) + ". Please enter a value between 0 and " + Math.round(totalMarks[subjectIndex]) + ".\n");
            int studentIndex = 0;
            for (String studentId : classList) {
                boolean inputValid = false; // used to make sure that the while loop will run
                while (!inputValid) {
                    System.out.print("Enter marks for " + studentId + " in " + subjects[subjectIndex] + ": ");
                    marks = Integer.parseInt(sc.nextLine());  // storing input in marks
                    if (marks < 0 || marks > totalMarks[subjectIndex]) {
                        System.out.println("Invalid marks. Try again.");
                    } else {
                        tempStudentMarks[studentIndex++] = marks;
                        studentMarks = tempStudentMarks; //tempstudentmarks are getting added to the global studentmarks list
                        inputValid = true;
                    }
                }
            }

            System.out.println("\nMarks provided successfully for " + subjects[subjectIndex]);
            System.out.print("\nDo you want to see the marks entered for " + subjects[subjectIndex] + "? (yes/no): ");
            String option = sc.nextLine();
            System.out.print("\n");
            if (option.equalsIgnoreCase("yes")) { // using scanner to error check and only accept yes or no. not case sensitive
                int studentNumber = 1;
                for (int i = 0; i < classList.size(); i++) {
                    System.out.println("Marks for student " + studentNumber + " (" + classList.get(i) + ") in " + subjects[subjectIndex] + ": " + Math.round(studentMarks[i]));
                    studentNumber++;
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Try again.");
        }
    }

    public static void getGradeStats() {

        int[] percentMarks = new int[studentMarks.length]; //creating a new percentmarks list the same length as studentmarks
        String[] grades = new String[studentMarks.length]; //creating a new grades list the same length as studentmarks
        int gradeCountAstar = 0;
        int gradeCountA = 0;
        int gradeCountB = 0;
        int gradeCountC = 0;
        int gradeCountD = 0;
        int gradeCountE = 0;
        int gradeCountU = 0;

        for (int i = 0; i < studentMarks.length; i++) {
            percentMarks[i] = (int) (studentMarks[i] / totalMarks[tempSubjectIndex] * 100); //populating the percentmarks list with the percerntages of the students
        }
        
        for (int i = 0; i < studentMarks.length; i++) {     // itterating through the list and assigning the amount of grades
            if (percentMarks[i] <= 100 && percentMarks[i] >= 90) {
                grades[i] = "A*";
                gradeCountAstar++;
            }if (percentMarks[i] <90 && percentMarks[i] >= 80) {
                grades[i] = "A";
                gradeCountA++;

            }if (percentMarks[i] <80 && percentMarks[i] >= 70) {
                grades[i] = "B";
                gradeCountB++;
               
            }if (percentMarks[i] <70 && percentMarks[i] >= 60) {
                grades[i] = "C";
                gradeCountC++;
                
            }if (percentMarks[i] <60 && percentMarks[i] >= 50) {
                grades[i] = "D";
                gradeCountD++;
              
            }if (percentMarks[i] <50 && percentMarks[i] >= 40) {
                grades[i] = "E";
                gradeCountE++;
               
            }if (percentMarks[i] <40 && percentMarks[i] >= 0) {
                grades[i] = "U";
                gradeCountU++;
              
            }
        }

        int sum = 0;
        int minimum = percentMarks[0];
        int maximum = percentMarks[0];
        for (int i = 0; i < percentMarks.length; i++) { // calculating min and max though comparison of the privous value against the next
            sum += percentMarks[i];
            if (percentMarks[i] < minimum) {
                minimum = percentMarks[i];
            }
            if (percentMarks[i] > maximum) {
                maximum = percentMarks[i];
            }
        }
        double classAverage = (double) sum / percentMarks.length;
        int range = maximum - minimum;
    
        double sumOfSquares = 0;
        for (int i = 0; i < percentMarks.length; i++) {
            sumOfSquares += Math.pow(percentMarks[i] - classAverage, 2); //using the built in Maths library to shorten programming
        }
        double variance = sumOfSquares / percentMarks.length;
        double standardDeviation = Math.sqrt(variance);

        System.out.println("\nSubject: " + subjects[tempSubjectIndex] + "\tMax marks: " + Math.round(totalMarks[tempSubjectIndex])); // \t and \n used to help with allignment of the output
        System.out.println("_______________________________________________________________________");
        System.out.println("\nStudent ID\t\t\tMarks\t\t\t\tGrade");
        for (int i = 0; i < classList.size(); i++) {
            System.out.println(classList.get(i) + "\t\t\t\t" + Math.round(studentMarks[i]) + "\t\t\t\t" + grades[i]);
        }
        System.out.println("_______________________________________________________________________\n");
        System.out.println("Class average: " + Math.round(classAverage) + "\tStandard deviation: " + Math.round(standardDeviation));
        System.out.println("Minimum: " + Math.round(minimum) + "\t\tMaximum: " +  Math.round(maximum) + "\t\tRange: " + Math.round(range));
        System.out.println("\nGrade: \tA* \tA \tB \tC \tD \tE \tU");
        System.out.println("Count: \t" + gradeCountAstar + "\t" + gradeCountA + "\t" + gradeCountB + "\t" + gradeCountC + "\t" + gradeCountD + "\t" + gradeCountE + "\t" + gradeCountU);
        System.out.println("_______________________________________________________________________\n");
    }
}