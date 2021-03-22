package nz.ac.wgtn.swen301.assignment1;

import com.google.common.base.Preconditions;
import nz.ac.wgtn.swen301.studentdb.Degree;
import nz.ac.wgtn.swen301.studentdb.NoSuchRecordException;
import nz.ac.wgtn.swen301.studentdb.Student;
import nz.ac.wgtn.swen301.studentdb.StudentDB;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * A student managers providing basic CRUD operations for instances of Student, and a read
 * operation for instances of Degree.
 *
 * @author jens dietrich
 */
public class StudentManager {
    /** define the range of the available student id which is 0 - 9999 */
    public static final int beUsedStudentID_min = 0;

    /** define the range of the available student id which is 0 - 9999 */
    public static final int beUsedStudentID_max = 9999;

    // DO NOT REMOVE THE FOLLOWING -- THIS WILL ENSURE THAT THE DATABASE IS AVAILABLE
    // AND THE APPLICATION CAN CONNECT TO IT WITH JDBC
    static {
        StudentDB.init();
    }
    // DO NOT REMOVE BLOCK ENDS HERE

    // THE FOLLOWING METHODS MUST IMPLEMENTED :

    /**
     * Return a student instance with values from the row with the respective id in the
     * database. If an instance with this id already exists, return the existing instance and
     * do not create a second one.
     *
     * @param id
     *            the string id to locate the Student object
     * @return the student object with the specified id
     *
     * @throws NoSuchRecordException
     *             if no record with such an id exists in the database This functionality is to
     *             be tested in
     *             test.nz.ac.wgtn.swen301.assignment1.TestStudentManager::test_readStudent
     *             (followed by optional numbers if multiple tests are used)
     */
    public static Student readStudent(String id) throws NoSuchRecordException {
        Preconditions.checkNotNull(id, "The id can not be NULL!");
        Preconditions.checkArgument(id.length() >= 3, "The entered id argument is invalid!!");
        // check if the first two characters is id
        if (!(id.startsWith("id"))) {
            // first 2 chars is not "id", throw the exception
            throw new IllegalArgumentException("The id you enter is incorrect! please reenter it");
        }

        try {
            // estblish the connection to the directory
            String jdbc_url = "jdbc:derby:memory:studentdb";
            Connection connection = DriverManager.getConnection(jdbc_url);
            // Create a Statement object to execute the query with.
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM STUDENTS WHERE ID = '" + id + "'");

            // iterate the resultSet until no more rows can be read
            while (resultSet.next()) {
                // get the corresponding info from the STUDENTS database
                String first_name = resultSet.getString("first_name");
                String name = resultSet.getString("name");
                String degreeID = resultSet.getString("degree");

                // construct the degree object by passing the degreeID variable
                Degree degree = StudentManager.readDegree(degreeID);
                // construct the student object and return it
                Student student = new Student(id, name, first_name, degree);
                connection.close();// close connection to save the resourse
                return student;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    /**
     * Return a degree instance with values from the row with the respective id in the
     * database. If an instance with this id already exists, return the existing instance and
     * do not create a second one.
     *
     * @param id
     *            the String id that can locate the Degree
     * @return the Degree object with the specified id
     *
     * @throws NoSuchRecordException
     *             if no record with such an id exists in the database This functionality is to
     *             be tested in
     *             test.nz.ac.wgtn.swen301.assignment1.TestStudentManager::test_readDegree
     *             (followed by optional numbers if multiple tests are used)
     */
    public static Degree readDegree(String id) throws NoSuchRecordException {
        Preconditions.checkNotNull(id, "The id can not be NULL!");
        // dont know whether the inner test will have deg10 or not, so the length set to 5
        Preconditions.checkArgument(id.length() >= 4 && id.length() <= 5, "The entered id " +
                                                                          "argument is invalid!!");

        // check if the first 3 characters is valid
        if (!(id.startsWith("deg"))) {
            // invalid, throw the exception
            throw new IllegalArgumentException("The id you enter is incorrect! please re-enter it");
        }

        try {
            // establish the connection to the directory
            String jdbc_url = "jdbc:derby:memory:studentdb";
            Connection connection = DriverManager.getConnection(jdbc_url);
            // Create a Statement object to execute the query with.
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM DEGREES WHERE ID = '" + id + "'");

            // iterate the resultSet until no more rows can be read
            while (resultSet.next()) {
                // get the corresponding info from the DEGREES database
                String degreeID = resultSet.getString("id");
                String degreeName = resultSet.getString("name");

                // construct the Degree object by passing variables to the constructor
                connection.close();// close connection to save the resourse
                return new Degree(degreeID, degreeName);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    /**
     * Delete a student instance from the database. I.e., after this, trying to read a student
     * with this id will result in a NoSuchRecordException.
     *
     * @param student
     *            the student object to delete from the database
     * @throws NoSuchRecordException
     *             if no record corresponding to this student instance exists in the database
     *             This functionality is to be tested in
     *             test.nz.ac.wgtn.swen301.assignment1.TestStudentManager::test_delete
     */
    public static void delete(Student student) throws NoSuchRecordException {
        // do the Precondition check
        Preconditions.checkNotNull(student);

        String id = student.getId();
        String firstName = student.getFirstName();
        String lastName = student.getName();

        Preconditions.checkNotNull(id, firstName, lastName,student.getDegree());
        try {
            // estblish the connection to the directory
            String jdbc_url = "jdbc:derby:memory:studentdb";
            Connection connection = DriverManager.getConnection(jdbc_url);
            // Create a Statement object to execute the query with.
            Statement statement = connection.createStatement();

            statement.executeUpdate(
                    "DELETE FROM STUDENTS WHERE ID='" + id + "'" + " AND " +
                                    "first_name='" + firstName + "'" + " AND name='" + lastName
                                    + "'");
            connection.close();// close the connection to save resources
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Update (synchronize) a student instance with the database. The id will not be changed,
     * but the values for first names or degree in the database might be changed by this
     * operation. After executing this command, the attribute values of the object and the
     * respective database value are consistent. Note that names and first names can only be
     * max 1o characters long. There is no special handling required to enforce this, just
     * ensure that tests only use values with < 10 characters.
     *
     * @param student
     *            Student instance to update
     * @throws NoSuchRecordException
     *             if no record corresponding to this student instance exists in the database
     *             This functionality is to be tested in
     *             test.nz.ac.wgtn.swen301.assignment1.TestStudentManager::test_update
     *             (followed by optional numbers if multiple tests are used)
     */
    public static void update(Student student) throws NoSuchRecordException {
        // do the Precondition check
        Preconditions.checkNotNull(student);
        // get the info
        String id = student.getId();
        String firstName = student.getFirstName();
        String lastName = student.getName();
        Degree degree = student.getDegree();

        // precondition check again
        Preconditions.checkArgument(firstName.length() < 10 || lastName.length() < 10,
                "The length of the student first name and last name must be within 10 " +
                                                                                       "characters!!");

        try {
            // estblish the connection to the directory
            String jdbc_url = "jdbc:derby:memory:studentdb";
            Connection connection = DriverManager.getConnection(jdbc_url);
            // Create a Statement object to execute the query with.
            Statement statement = connection.createStatement();

            statement.executeUpdate(
                    "UPDATE STUDENTS SET first_name='" + firstName + "', degree='" + degree.getId()
                                    + "', " + "name='" + lastName + "'WHERE id='" + id + "'");
            connection.close();// close the connection to save resources
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    /**
     * Create a new student with the values provided, and save it to the database. The student
     * must have a new id that is not been used by any other Student instance or STUDENTS
     * record (row). Note that names and first names can only be max 1o characters long. There
     * is no special handling required to enforce this, just ensure that tests only use values
     * with < 10 characters.
     *
     * @param name
     *            the last name of the Student instance
     * @param firstName
     *            the first name of the Student instance
     * @param degree
     *            the degree that this Student instance belongs to
     * @return a freshly created student instance This functionality is to be tested in
     *         test.nz.ac.wgtn.swen301.assignment1.TestStudentManager::test_createStudent
     *         (followed by optional numbers if multiple tests are used)
     */
    public static Student createStudent(String name, String firstName, Degree degree) {
        // precondition check
        Preconditions.checkNotNull(name, firstName, degree);
        Preconditions.checkArgument(firstName.length() < 10 || name.length() < 10,
                "The length of the student first name and last name must be within 10 " +
                                                                                   "characters!!");

        try {
            // estblish the connection to the directory
            String jdbc_url = "jdbc:derby:memory:studentdb";
            Connection connection = DriverManager.getConnection(jdbc_url);
            // Create a Statement object to execute the query with.
            Statement statement = connection.createStatement();

            // find the id that is not been used,start with the beUsedStudentID_max + 1
            // for (int i = beUsedStudentID_min; i < beUsedStudentID_max + 1; i++) {
            for (int i = beUsedStudentID_max + 1; i < Integer.MAX_VALUE; i++) {
                String id = "id" + i;
                Student currentStudent = StudentManager.readStudent(id);
                // it is null, which means this
                if (currentStudent == null) {
                    // add it into the STUDENTS table
                    String sql = "INSERT INTO STUDENTS (id, name, first_name, degree) VALUES ('"
                                 + id + "', '" + name + "', '" + firstName + "', '"
                                 + degree.getId() + "')";
                    statement.executeUpdate(sql);
                    connection.close();// close the connection to save resources

                    // System.out.println("it reaches here???????????????");
                    // construct the student object and return it
                    return new Student(id, name, firstName, degree);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * Get all student ids currently being used in the database.
     *
     * @return This functionality is to be tested in
     *         test.nz.ac.wgtn.swen301.assignment1.TestStudentManager::test_getAllStudentIds
     *         (followed by optional numbers if multiple tests are used)
     */
    public static Collection<String> getAllStudentIds() {
        List<String> used_ids = new LinkedList<>();

        try {
            // estblish the connection to the directory
            String jdbc_url = "jdbc:derby:memory:studentdb";
            Connection connection = DriverManager.getConnection(jdbc_url);
            // Create a Statement object to execute the query with.
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM STUDENTS");

            // iterate the resultSet until no more rows can be read
            while (resultSet.next()) {
                // get the corresponding info from the STUDENTS database
                String id = resultSet.getString("id");
                used_ids.add(id);

            }
            connection.close();// close connection to save the resourse

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return used_ids;
    }

    /**
     * Get all degree ids currently being used in the database.
     *
     * @return This functionality is to be tested in
     *         test.nz.ac.wgtn.swen301.assignment1.TestStudentManager::test_getAllDegreeIds
     *         (followed by optional numbers if multiple tests are used)
     */
    public static Iterable<String> getAllDegreeIds() {
        List<String> used_ids = new LinkedList<>();

        try {
            // estblish the connection to the directory
            String jdbc_url = "jdbc:derby:memory:studentdb";
            Connection connection = DriverManager.getConnection(jdbc_url);
            // Create a Statement object to execute the query with.
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM DEGREES");

            // iterate the resultSet until no more rows can be read
            while (resultSet.next()) {
                // get the corresponding info from the STUDENTS database
                String id = resultSet.getString("id");
                used_ids.add(id);

            }
            connection.close();// close connection to save the resourse

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return used_ids;
    }

}
