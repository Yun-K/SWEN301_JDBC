package nz.ac.wgtn.swen301.assignment1.cli;

import nz.ac.wgtn.swen301.assignment1.StudentManager;
import nz.ac.wgtn.swen301.studentdb.Degree;
import nz.ac.wgtn.swen301.studentdb.NoSuchRecordException;
import nz.ac.wgtn.swen301.studentdb.Student;

public class FindStudentDetails {

    // THE FOLLOWING METHOD MUST IMPLEMENTED

    /**
     * Executable: the user will provide a student id as single argument, and if the details
     * are found, the respective details are printed to the console. E.g. a user could invoke
     * this by running
     * <p>
     * "java -cp <someclasspath> nz.ac.wgtn.swen301.assignment1.cli.FindStudentDetails id42"
     *
     * @param arg
     *            arguments that is entered by the user. e.g.the student id: id42
     * @throws NoSuchRecordException
     *             if the student with the id has not been found
     */
    public static void main(String[] arg) throws NoSuchRecordException {
        if (arg == null) {
            throw new NullPointerException("Please enter the id argument.");
        }

        String student_id = arg[0];
        Student student = StudentManager.readStudent(student_id);
        if (student != null) {
            System.out.println("ID\tFirst Name\tLast Name\tDegree ID\tDegree Name");
            System.out.print(student.getId());
            System.out.print("\t");
            System.out.print(student.getFirstName());
            System.out.print("\t");
            System.out.print(student.getName());
            System.out.print("\t");
            System.out.print(student.getDegree().getId());
            System.out.print("\t");
            System.out.print(student.getDegree().getName());
        }

    }
}
