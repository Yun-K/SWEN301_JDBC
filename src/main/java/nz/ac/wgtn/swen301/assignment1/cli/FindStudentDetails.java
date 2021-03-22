package nz.ac.wgtn.swen301.assignment1.cli;

import nz.ac.wgtn.swen301.assignment1.StudentManager;
import nz.ac.wgtn.swen301.studentdb.NoSuchRecordException;
import nz.ac.wgtn.swen301.studentdb.Student;

public class FindStudentDetails {

    // THE FOLLOWING METHOD MUST IMPLEMENTED

    /**
     * Executable: the user will provide a student id as single argument, and if the details
     * are found, the respective details are printed to the console. E.g. a user could invoke
     * this by running
     * "java -cp <someclasspath> nz.ac.wgtn.swen301.assignment1.cli.FindStudentDetails id42"
     *
     * I use the putty to connect to the my school account and run the command below,
     * it can run successfully.(maybe you can just replace zhouyun to your username.
     *
     * java -cp /am/st-james/home1/zhouyun/git/Assignment1/target/classes:/am/st-james/home1/zhouyun/git/Assignment1/lib/studentdb-1.1.1.jar:/am/st-james/home1/zhouyun/.m2/repository/com/google/guava/guava/30.1.1-jre/guava-30.1.1-jre.jar:/am/st-james/home1/zhouyun/.m2/repository/org/apache/derby/derby/10.14.2.0/derby-10.14.2.0.jar nz.ac.wgtn.swen301.assignment1.cli.FindStudentDetails id42
     *
     * @param arg
     *            arguments that is entered by the user. e.g.the student id: id42
     * @throws NoSuchRecordException
     *             if the student with the id has not been found
     */
    public static void main(String[] arg) throws NoSuchRecordException {
        if (arg == null ) {
            System.out.println("The argument is null, please enter the argument and try again.");
            throw new NullPointerException("Please enter the id argument.");
        }
        if (arg.length<1){
            System.out.println("Please enter the argument and try again.");
            throw new NullPointerException("Please enter the id argument.");
        }

        String student_id = arg[0];//only read the first argument
        Student student = StudentManager.readStudent(student_id);
        if (student != null) {
            System.out.println("student ID:\t"+ student.getId() );
            System.out.println("First Name:\t " + student.getFirstName());
            System.out.println("Last Name:\t" + student.getName() );
            System.out.println("Degree ID:\t" + student.getDegree().getId());
            System.out.println("Degree Name:\t" + student.getDegree().getName());
        }

    }
}
