package nz.ac.wgtn.swen301.assignment1;

import nz.ac.wgtn.swen301.studentdb.*;
import java.sql.*;
import java.util.Collection;

/**
 * A student managers providing basic CRUD operations for instances of Student, and a read
 * operation for instances of Degree.
 *
 * @author jens dietrich
 */
public class StudentManager {
    /** defind the range of the available student id which is 0 - 9999 */
    public static final int availableStudentID_min = 0;

    /** defind the range of the available student id which is 0 - 9999 */
    public static final int availableStudentID_max = 9999;

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
        try {
            // estblish the connection to the directory
            String jdbc_url = "jdbc:derby:memory:studentdb";
            Connection connection = DriverManager.getConnection(jdbc_url);
            // Create a Statement object to execute the query with.
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM STUDENTS WHERE ID = '" + id + "'");

            if (resultSet == null) {
                throw new NoSuchRecordException(
                        "We didn't find the student id: " + id + " in our database. ");
            }
            // iterate the resultSet until no more rows can be read
            while (resultSet.next()) {
                // get the corresponding info from the STUDENTS database
                String first_name = resultSet.getString("first_name");
                String name = resultSet.getString("name");
                String degreeID = (resultSet.getString("degree"));

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
        try {
            // estblish the connection to the directory
            String jdbc_url = "jdbc:derby:memory:studentdb";
            Connection connection = DriverManager.getConnection(jdbc_url);
            // Create a Statement object to execute the query with.
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM DEGREES WHERE ID = '" + id + "'");

            if (resultSet == null) {
                throw new NoSuchRecordException(
                        "We didn't find the degree id: " + id + " in our database. ");
            }
            // iterate the resultSet until no more rows can be read
            while (resultSet.next()) {
                // get the corresponding info from the DEGREES database
                String degreeID = resultSet.getString("id");
                String degreeName = resultSet.getString("name");

                // construct the Degree object by passing variables to the constructor
                Degree degree = new Degree(degreeID, degreeName);
                connection.close();// close connection to save the resourse
                return degree;
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
     * @throws NoSuchRecordException
     *             if no record corresponding to this student instance exists in the database
     *             This functionality is to be tested in
     *             test.nz.ac.wgtn.swen301.assignment1.TestStudentManager::test_update
     *             (followed by optional numbers if multiple tests are used)
     */
    public static void update(Student student) throws NoSuchRecordException {
    }

    /**
     * Create a new student with the values provided, and save it to the database. The student
     * must have a new id that is not been used by any other Student instance or STUDENTS
     * record (row). Note that names and first names can only be max 1o characters long. There
     * is no special handling required to enforce this, just ensure that tests only use values
     * with < 10 characters.
     *
     * @param name
     * @param firstName
     * @param degree
     * @return a freshly created student instance This functionality is to be tested in
     *         test.nz.ac.wgtn.swen301.assignment1.TestStudentManager::test_createStudent
     *         (followed by optional numbers if multiple tests are used)
     */
    public static Student createStudent(String name, String firstName, Degree degree) {
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
        return null;
    }

    /**
     * Get all degree ids currently being used in the database.
     *
     * @return This functionality is to be tested in
     *         test.nz.ac.wgtn.swen301.assignment1.TestStudentManager::test_getAllDegreeIds
     *         (followed by optional numbers if multiple tests are used)
     */
    public static Iterable<String> getAllDegreeIds() {
        return null;
    }

}
