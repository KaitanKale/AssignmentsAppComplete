import javax.swing.text.DateFormatter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static Random rand = new Random();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("\n\nHello, AssignmentsApp!\n");

        //Output the current date-time.
        LocalDateTime today = LocalDateTime.now();
        System.out.println("\nThe current date-time is " + today);

        //Output tomorrow's date using a formatter.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss");
        String formatDateTime = today.format(formatter);
        System.out.println("Tomorrow's formatted date is " + formatDateTime);

        //Add 5 weeks to today's LocalDateTime.
        LocalDateTime fiveWeeksLater = today.plusWeeks(5);
        System.out.println("The five weeks, the date will be " + fiveWeeksLater);

        //Initialize a LocalDateTime object to your birthdate and the time 12:35 PM.
        LocalDateTime birthDate = LocalDateTime.of(1999, 2, 7, 12, 35);
        System.out.println("Your birthdate is " + birthDate);

        //Output the day of the week (Sunday-Saturday) that you were born.
        System.out.println("The day of the week of your birthdate was " + birthDate.getDayOfWeek());

        //Output the number of days you've been alive.
        System.out.println("The number of days you have been alive is " + ChronoUnit.DAYS.between(birthDate, today) + " days.");

        //Output the number of days between two dates.
        LocalDateTime obamaInauguration = LocalDateTime.of(2009, 2, 20, 12, 0);
        System.out.println("The number of days between your birthdate and Obama's inauguration is " + ChronoUnit.DAYS.between(birthDate, obamaInauguration) + " days.");

        //Given two dates, output the earlier.
        System.out.println("The earlier date is " + FindEarlierDate(today, obamaInauguration));

        //Create a file with 100 random "month/day/year  hour:minutes" (in that format) on each line.
        ArrayList<LocalDateTime> hundredRandomDates = randomDateArray(100);
        hundredRandomDates.forEach(d -> System.out.println("Date is " + d));

        //Output the number of stored dates in the year [Y].
        System.out.print("\nWhat is the year you want to find the dates of? ");
        ArrayList<LocalDateTime> datesOfUserYear = searchByYear(hundredRandomDates, sc.nextInt());
        System.out.println("The number of dates with that year is " + datesOfUserYear.size());

        //Count the number of stored dates in the current year.
        ArrayList<LocalDateTime> datesOfCurrentYear = searchByYear(hundredRandomDates, today.getYear());
        System.out.println("\nThe number of dates in the current year is " + datesOfCurrentYear.size());

        //Count the number of duplicates.
        ArrayList<LocalDateTime> duplicatedDates = seekDuplicates(hundredRandomDates);
        System.out.println("\nThere are " + duplicatedDates.size() + " duplicated dates.");

        // Sort the dates in chronological order.
        Collections.sort(hundredRandomDates);
        System.out.println("\nThe sorted dates are as followed: ");
        hundredRandomDates.forEach(d -> System.out.println(d));

        //Count the number of duplicates in a sorted list without using a Java Set.
        System.out.println("\nWithout using a Set, the number of duplicated dates are " + countDuplicates(hundredRandomDates));

        //Count the number of evening (after 6pm) dates.
        ArrayList<LocalDateTime> eveningDates = searchDatesInTimeframe(hundredRandomDates,18, 24 );
        System.out.println("\nThe number of evening dates are " + eveningDates.size());

        //Count the number of dates in each of the individual 12 months without using a Java Map.
        System.out.print("\nWhat is the number of the month you are searching the dates for? ");
        int month = sc.nextInt();
        if (month > 12 || month < 1) System.out.println("That month value is not valid.");
        else {
            ArrayList<LocalDateTime> datesOfMonth = searchByMonth(hundredRandomDates, month);
            System.out.println("The number of dates in month " + month + " is " + datesOfMonth.size());
        }

        //Count the number of dates in each of the individual 12 months using a Java Map.
        if (month >= 1 && month <=12){
            System.out.println("Using a Java Map, the number of dates in month " + month + " is " + mapByMonthSearch(hundredRandomDates,month));
        }

        //Determine the index of the latest LocalDateTime.
        System.out.println("\nThe index of the latest LocalDateTime is " + indexLatestDate(hundredRandomDates));

        //Determine the indexes of the elements that have the earliest starting time, regardless of date.
        System.out.println("\nThe index of the date with the earliest starting time is " + indexEarliestTime(hundredRandomDates));

        //Output a date in the format "January 1st, 2018".
        System.out.print("\nWhat is the index of the date you want to be outputted in the format \"January 1st, 2018\"? ");
        System.out.println("The formatted date is " + formattedDate(hundredRandomDates.get(sc.nextInt())));
        //I had completely forgotten to submit the previous story, so if it looks like I completed this story incredibly quickly, it's because I temporarily removed this code so I could post the for the previous story.

        //Define and use a DayOfWeek enumerated types
        EnumTest firstDay = new EnumTest(Day.MONDAY);
        firstDay.tellItLikeItIs();
        EnumTest secondDay = new EnumTest(Day.TUESDAY);
        secondDay.tellItLikeItIs();
        EnumTest sixthDay = new EnumTest(Day.SATURDAY);
        sixthDay.tellItLikeItIs();

        //Define and use a Course enumerated type
        EnumTest firstCourse = new EnumTest(Course.CPSC);
        firstCourse.courseTell();
        EnumTest secondCourse = new EnumTest(Course.FRSM);
        secondCourse.courseTell();

        //Define and use a Category enumerated type
        EnumTest firstCategory = new EnumTest(Category.HOMEWORK);
        firstCategory.categoryTell();
        EnumTest secondCategory = new EnumTest(Category.QUIZ);
        secondCategory.categoryTell();
        EnumTest thirdCategory = new EnumTest(Category.TEST);
        thirdCategory.categoryTell();
        EnumTest fourthCategory = new EnumTest(Category.PRESENTATION);
        fourthCategory.categoryTell();
        EnumTest fifthCategory = new EnumTest(Category.FINAL_EXAM);
        fifthCategory.categoryTell();

        //In the driver, generate 2 random assignments named assign1 and assign2.
        Assignment assign1 = new Assignment(LocalDateTime.now(), new EnumTest(Course.CPSC), new EnumTest(Category.HOMEWORK), rand.nextInt(4));
        Assignment assign2 = new Assignment();


        //Copy assign1 to assign3
        Assignment copy = assign1;
        Assignment assign3 = copy;
        System.out.println("\nAssignment 1: " + assign1.toString());
        System.out.println("Assignment 2: " + assign2.toString());
        System.out.println("Assignment 3: " + assign3.toString());

        //Override an Assignment.equals() method.

        //Override an Assignment.compareTo() method then use it to output BEFORE, EQUALS, or AFTER based on the LocalDateTime.
        String before = "BEFORE";
        String equals = "EQUALS";
        String after = "AFTER";
        System.out.println("\n");
        if (assign1.compareTo(assign2) == 0) {
            System.out.println(before);
        }
        if (assign1.compareTo(assign2) == 1) {
            System.out.println(equals);
        }
        if (assign1.compareTo(assign2) == -1) {
            System.out.println(after);
        }

        //Which of assign1, assign2, or assign3 is the earliest?
        if (assign1.compareTo(assign2) == 0) {
            System.out.println("Assignments one and three are the earliest.");
        }
        if (assign1.compareTo(assign2) == 1) {
            System.out.println("All of the assignments were at the same time.");
        }
        if (assign1.compareTo(assign2) == -1) {
            System.out.println("Assignment two was the earliest.");
        }

        //Write [X] randomly generated assignments into the file 'input.dat'
        Scanner sc = new Scanner(System.in);
        System.out.println("How many random assignments would you like to generate?");
        int numRandomAssignments = sc.nextInt();
        File input = writeRandomAssignmentToFile(numRandomAssignments);



        //TODO Read assignments from the file 'input.dat' and store them in an Assignment object.
        File output = readRandomAssignmentFromFile();

        //TODO Remove any duplicate assignments.
        //Set

        //TODO Count the number of assignments for course [C].

        //TODO Sort the assignments in reverse chronological order.

        //TODO Sort the assignments by increasing priority order.

        //TODO Sort the assignments by Course.

        //TODO Which assignments are due today?

        //TODO Which assignments will be due within [X] days?
        System.out.println("How many days do you want to look ahead?");
        int daysAhead = sc.nextInt();

        //TODO Which assignments are past due, sorted by course?

        //TODO What is the next assignment due for each course?

        //TODO What are the highest priority assignments that are still due, sorted by date?


    }



    private static File writeRandomAssignmentToFile(int numRandomAssignments) {
        Random rand = new Random();
        File outfile = new File("input.dat");
        try (PrintWriter pw = new PrintWriter(outfile)) {
            System.out.println(numRandomAssignments + " random assignments have been generated and written to the file.");
            for (int j = 0; j < numRandomAssignments; j++) {
                pw.print(LocalDateTime.now().minusSeconds(rand.nextInt(100000)));
                pw.print(" ");
                pw.print(rand.nextInt(4));
                pw.print("\n");

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return outfile;
    }
    private static Assignment readRandomAssignmentFromFile(Assignment randomAssignments) {
        File infile = new File( "input.dat" );
        if( ! infile.exists() ) {
            System.out.println( "Oh no, you can't read from a file that doesn't exist!" );
        } else {
            try( Scanner sc2 = new Scanner( infile ) ) {
                while( sc2.hasNextLong() ) {
                    LocalDateTime randomLDT = sc2.next();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private static String formattedDate(LocalDateTime date) {
        String newDate = "";
        newDate += date.format(DateTimeFormatter.ofPattern("MMMM "));
        newDate += intToOrdinal(date.getDayOfMonth());
        newDate += date.format(DateTimeFormatter.ofPattern(", yyyy"));
        return newDate;
    }

    private static String intToOrdinal(int num){
        String[] suffixes = new String[] {"th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th" };
        //I probably should have made this into a switch/case.
        if (num % 100 == 11 || num % 100 == 12 || num % 100 == 13) {
            return num + "th";
        } else {
            return num + suffixes[num % 10];
        }
    }

    private static Integer indexEarliestTime(ArrayList<LocalDateTime> dateList){
        LocalDateTime earliestDateTime = dateList.get(0);
        for (LocalDateTime date: dateList) {
            if (earliestDateTime.toLocalTime().isAfter(date.toLocalTime())) earliestDateTime = date;
        }
        return dateList.indexOf(earliestDateTime);
    }

    private static Integer indexLatestDate(ArrayList<LocalDateTime> dateList) {
        return dateList.indexOf(Collections.max(dateList));
    }

    private static Integer mapByMonthSearch (ArrayList<LocalDateTime> dateList, int month){
        return mapByMonth(dateList).get(month);
    }

    private static Map<Integer, Integer> mapByMonth(ArrayList<LocalDateTime> dateList) {
        Map<Integer,Integer> returnMap = new HashMap<>();
        for (LocalDateTime date : dateList) {
            Integer count = returnMap.get(date.getMonthValue());
            returnMap.put(date.getMonthValue(), (count == null) ? 1 : count + 1);
        }
        return returnMap;
    }

    private static ArrayList<LocalDateTime> searchByMonth(ArrayList<LocalDateTime> dateList, int month) {
        return (ArrayList) dateList.stream()
                .filter( date -> date.getMonthValue() == month)
                .collect(Collectors.toList());
    }

    private static ArrayList searchDatesInTimeframe (ArrayList<LocalDateTime> dateList, int startHour, int endHour){
        return (ArrayList) dateList.stream()
                .filter( date -> date.getHour() >= startHour && date.getHour() < endHour)
                .collect(Collectors.toList());
    }

    private static int countDuplicates(ArrayList<LocalDateTime> hundredRandomDates) {
        int count = 0;
        for (LocalDateTime date: hundredRandomDates) {
            if (Collections.frequency(hundredRandomDates, date) >= 2) count++;
        }
        return count;
    }


    private static ArrayList<LocalDateTime> seekDuplicates(ArrayList<LocalDateTime> userList) {
        ArrayList<LocalDateTime> returnArray = new ArrayList<>();
        Set<LocalDateTime> dateSet = new HashSet<>();
        for (LocalDateTime date : userList) {
            if (dateSet.contains(date)) returnArray.add(date);
            dateSet.add(date);
        }
        return returnArray;
    }

    private static ArrayList<LocalDateTime> searchByYear(ArrayList<LocalDateTime> listOfLocalDateTimes, int year) {
        return (ArrayList) listOfLocalDateTimes.stream()
                .filter( date -> date.getYear()==year)
                .collect(Collectors.toList());
    }

    private static ArrayList<LocalDateTime> randomDateArray (int NumElements){
        ArrayList<LocalDateTime> returnArray = new ArrayList<>();
        for (int i = 0; i < NumElements; i++) {
            returnArray.add(randomDateGenerator());
        }
        return returnArray;
    }

    private static LocalDateTime randomDateGenerator (){
        long startOfTime = ChronoUnit.MINUTES.between(LocalDateTime.of(0,1,1,0,0),LocalDateTime.now());
        long minutes = rand.nextInt((int) startOfTime);
        return LocalDateTime.now().minusMinutes(minutes);
    }

    private static LocalDateTime FindEarlierDate(LocalDateTime date1, LocalDateTime date2) {
        LocalDateTime earlyDate = date1;
        if (date2.isBefore(date1)) {
            earlyDate = date2;
        }
        return earlyDate;
    }
    public enum Day {
        SUNDAY, MONDAY, TUESDAY, WEDNESDAY,
        THURSDAY, FRIDAY, SATURDAY;
    }
    public enum Course {
        CPSC, ENGL, ECON, FRSM, FREN
    }
    public enum Category {
        HOMEWORK, QUIZ, TEST, PRESENTATION, FINAL_EXAM
    }
    public enum Timing {
        BEFORE, EQUALS, AFTER
    }
    public static class EnumTest {
        Day day;
        Course course;
        Category category;
        Timing timing;
        private EnumTest(Day day) {
            this.day = day;
        }
        private EnumTest (Course course) { this.course = course;}
        private EnumTest (Category category) {this.category = category;}
        private EnumTest (Timing timing) { this.timing = timing; }
        private void tellItLikeItIs() {
            switch (day) {
                case MONDAY: case WEDNESDAY: case FRIDAY:
                    System.out.println("\nOn Mondays, Wednesdays, and Fridays our classes are 50 minutes");
                    break;
                case TUESDAY: case THURSDAY:
                    System.out.println("On Tuesdays and Thursdays, our classes are 75 minutes");
                    break;
                case SATURDAY: case SUNDAY:
                    System.out.println("On weekends we have no class");
                    break;
            }
        }
        private void courseTell() {
            switch (course) {
                case CPSC: case FREN: case ENGL: case ECON:
                    System.out.println("\nCPSC, FREN, ENGL, and ECON offer three credit hours.");
                    break;
                case FRSM:
                    System.out.println("FRSM offers one credit hour.");
                    break;
            }
        }
        private void categoryTell() {
            switch (category) {
                case HOMEWORK:
                    System.out.println("\nHomework is 20% of your grade.");
                    break;
                case QUIZ:
                    System.out.println("Quizzes are 15% of your grade.");
                    break;
                case TEST:
                    System.out.println("Tests are 20% of your grade.");
                    break;
                case PRESENTATION:
                    System.out.println("Presentations are 20% of your grade.");
                    break;
                case FINAL_EXAM:
                    System.out.println("The Final Exam is worth 25% of your grade.");
                    break;

            }}
        private void timingTell() {
            switch (timing) {
                case BEFORE:
                    System.out.println("BEFORE");
                case EQUALS:
                    System.out.println("EQUALS");
                case AFTER:
                    System.out.println("AFTER");
            }

}
}
    public static class Assignment {
        private LocalDateTime day;
        private EnumTest whichClass;
        private EnumTest work;
        private int priority;
        Random rand;

        public Assignment() {
            day = LocalDateTime.now();
            whichClass = new EnumTest(Course.CPSC);
            work = new EnumTest(Category.HOMEWORK);
            //rand = new Random();
            //priority = rand.nextInt(4);
        }

        public Assignment(LocalDateTime day, EnumTest whichClass, EnumTest work, int priority) {
            this.day = day;
            this.whichClass = whichClass;
            this.work = work;
            this.priority = priority;
        }

        public LocalDateTime getDay() {
            return day;
        }

        public void setDay(LocalDateTime day) {
            this.day = day;
        }

        public EnumTest getWhichClass() {
            return whichClass;
        }

        public void setWhichClass(EnumTest whichClass) {
            this.whichClass = whichClass;
        }

        public EnumTest getWork() {
            return work;
        }

        public void setWork(EnumTest work) {
            this.work = work;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        @Override
        public String toString() {
            return "Assignment{" +
                    "day=" + day +
                    ", whichClass=" + whichClass +
                    ", work=" + work +
                    ", priority=" + priority +
                    ", rand=" + rand +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Assignment)) return false;
            Assignment that = (Assignment) o;
            return (getPriority() == that.getPriority()) &&
                    Objects.equals(day, that.day) &&
                    Objects.equals(whichClass, that.whichClass) &&
                    Objects.equals(work, that.work);
        }

        @Override
        public int hashCode() {

            return Objects.hash(day, whichClass, work, priority, rand);
        }

        public int compareTo(Assignment dateTime) {
            if (getDay().compareTo(dateTime.getDay()) == 0)
                if (getDay().isEqual(dateTime.getDay())) {
                    return 0;
                } else if (getDay().isEqual(dateTime.getDay())) {
                    return 1;
                } else {
                    return -1;
                }
            return getDay().compareTo(dateTime.getDay());
        }


    }}

