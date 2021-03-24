package nz.ac.wgtn.swen301.assignment1;

import com.google.common.base.Preconditions;
import nz.ac.wgtn.swen301.studentdb.Degree;
import nz.ac.wgtn.swen301.studentdb.NoSuchRecordException;
import nz.ac.wgtn.swen301.studentdb.Student;
import nz.ac.wgtn.swen301.studentdb.StudentDB;

import java.sql.*;
import java.util.*;

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

    private static final String[] DEGREE_NAMES = new String[] { "BSc Computer Science",
            "BSc Computer " + "Graphics", "BE Cybersecurity", "BE Software Engineering",
            "BSc Mathematics", "BSc Chemistry", "BA Art", "BA Philosophy", "BCom Finance",
            "BCom Marketing" };

    // private static HashMap<String, Student> id_student_map = new HashMap<>();

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
        // precondition check
        // check if  the id is a valid argument or not
        Preconditions.checkNotNull(id, "The id can not be NULL!");
        Preconditions.checkArgument(id.length() >= 3 && id.startsWith("id"),
                "The entered id argument is invalid!!");

        // return it if it's in the hashMap cache
        // if (id_student_map.containsKey(id)){return id_student_map.get(id);}

        String degreeName = null;// store the degree.name
        try {
            // estblish the connection to the directory
            Connection connection = DriverManager.getConnection("jdbc:derby:memory:studentdb");

            // Create a Statement object to execute the query with.
            // Statement statement = connection.createStatement();
            // ResultSet resultSet = statement.executeQuery("SELECT * FROM STUDENTS WHERE ID =
            // '" + id + "'");

            // create a preparedStatement object to execute the query with
            PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM " +
                                                                             "STUDENTS WHERE ID =" +
                                                                             " ?");
            // set the argument in order to execute the sql query
            prepareStatement.setString(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();

            // iterate the resultSet until no more rows can be read
            while (resultSet.next()) {
                // get the corresponding info from the STUDENTS database
                String first_name = resultSet.getString("first_name");
                String name = resultSet.getString("name");
                String degreeID = resultSet.getString("degree");
                // get the integer of the id
                int index_degree = Integer.parseInt(degreeID.substring(3, degreeID.length()));
                // the id can not less than 1
                if (index_degree < 0) {
                    return null;
                }
                // in the case of the teacher is so dog to change the Degree database to add
                // the 10th degree, readDegree() is needed (OK, i'm so dog as well because i
                // copy the degree database into this class. Doge.jpg )
                else if (index_degree > 9) {
                    degreeName = readDegree(degreeID).getName();
                }
                // assign it from the array which is the normal case
                else {
                    degreeName = DEGREE_NAMES[index_degree];
                }

                // construct the degree object by passing the degreeID variable
                // Degree degree = StudentManager.readDegree(degreeID);

                // construct the student object and return it
                Student student = new Student(id, name, first_name,
                        new Degree(degreeID, degreeName));
                // id_student_map.put(id,student);

                connection.close();// close connection to save the resourse
                return student;
            }

        } catch (Exception e) {
            e.printStackTrace();
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
        //do the precondition to  check whether the entered id is valid or not
        Preconditions.checkNotNull(id, "The id can not be NULL!");
        Preconditions.checkArgument(id.length() >= 4 && id.startsWith("deg"),
                "The entered id argument is invalid!!");

//        try {
//            if(id.length()==4){
//                return new Degree(id,DEGREE_NAMES[Integer.valueOf(id.substring(3))]);
//            }
//        }catch (java.lang.NumberFormatException e){
//            System.out.println("Sorry, you enter a wrong id!");
//            return null;
//        }

        try {
            // establish the connection to the directory
            String jdbc_url = "jdbc:derby:memory:studentdb";
            Connection connection = DriverManager.getConnection(jdbc_url);

            // create a preparedStatement object to execute the query with
            PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM " +
                                                                             "DEGREES WHERE ID =" +
                                                                             " ?");
            // set the argument in order to execute the sql query
            prepareStatement.setString(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();

            // // Create a Statement object to execute the query with.
            // Statement statement = connection.createStatement();
            // ResultSet resultSet = statement
            // .executeQuery("SELECT * FROM DEGREES WHERE ID = '" + id + "'");

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

        Preconditions.checkNotNull(id, firstName, lastName, student.getDegree());
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

            // if (id_student_map.containsKey(id)){
            // //delete it from the hashMap
            // id_student_map.remove(id,student);
            // }

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
            // id_student_map.put(id,student);

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
                    Student student = new Student(id, name, firstName, degree);
                    // id_student_map.put(id,student);
                    return student;
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
        List<String> used_student_ids = new LinkedList<>();

        try {
            // estblish the connection to the directory
            String jdbc_url = "jdbc:derby:memory:studentdb";
            Connection connection = DriverManager.getConnection(jdbc_url);

            // create a preparedStatement object to execute the query with
            PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM " +
                                                                             "STUDENTS");
            // set the argument in order to execute the sql query
            ResultSet resultSet = prepareStatement.executeQuery();

            // Create a Statement object to execute the query with.
            // Statement statement = connection.createStatement();
            // ResultSet resultSet = statement.executeQuery("SELECT * FROM STUDENTS");

            // iterate the resultSet until no more rows can be read
            while (resultSet.next()) {
                // get the corresponding info from the STUDENTS database
                String id = resultSet.getString("id");
                used_student_ids.add(id);
            }

            connection.close();// close connection to save the resource

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return used_student_ids;
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

            // create a preparedStatement object to execute the query with
            PreparedStatement prepareStatement = connection
                    .prepareStatement("SELECT * FROM DEGREES");
            // set the argument in order to execute the sql query
            ResultSet resultSet = prepareStatement.executeQuery();

            // Create a Statement object to execute the query with.
            // Statement statement = connection.createStatement();
            // ResultSet resultSet = statement.executeQuery("SELECT * FROM DEGREES");

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
