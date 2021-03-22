package test.nz.ac.wgtn.swen301.assignment1;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import nz.ac.wgtn.swen301.assignment1.StudentManager;
import nz.ac.wgtn.swen301.studentdb.Degree;
import nz.ac.wgtn.swen301.studentdb.NoSuchRecordException;
import nz.ac.wgtn.swen301.studentdb.Student;
import nz.ac.wgtn.swen301.studentdb.StudentDB;

public class TestStudentManager {

    // DO NOT REMOVE THE FOLLOWING -- THIS WILL ENSURE THAT THE DATABASE IS AVAILABLE
    // AND IN ITS INITIAL STATE BEFORE EACH TEST RUNS
    @Before
    public void init() {
        StudentDB.init();
    }
    // DO NOT REMOVE BLOCK ENDS HERE

    @Test
    public void dummyTest() throws Exception {
        Student student = new StudentManager().readStudent("id42");
        // THIS WILL INITIALLY FAIL !!
        assertNotNull(student);
    }

    /**
     * Description: <br/>
     * Check whether the fields of Degree object is correct or not. It check the correct degree
     * id .
     *
     * @author Yun Zhou
     */
    @Test
    public void test_readDegree() throws NoSuchRecordException {

        Degree degree0 = StudentManager.readDegree("deg0");
        assertNotNull(degree0);
        // System.out.println(degree_0.getId() + "\n" + degree_0.getName());//for debug
        assert degree0.getId().equals("deg0") && degree0.getName().equals("BSc Computer Science");

        Degree degree1 = StudentManager.readDegree("deg1");
        assertNotNull(degree1);
        assert degree1.getId().equals("deg1") && degree1.getName().equals("BSc Computer Graphics");

        Degree degree2 = StudentManager.readDegree("deg2");
        assertNotNull(degree2);
        assert degree2.getId().equals("deg2") && degree2.getName().equals("BE Cybersecurity");

        Degree degree3 = StudentManager.readDegree("deg3");
        assertNotNull(degree3);
        // System.out.println(degree3.getId() + "\n" + degree3.getName());//for debug
        assert degree3.getId().equals("deg3")
                && degree3.getName().equals("BE Software Engineering");

        Degree degree4 = StudentManager.readDegree("deg4");
        assertNotNull(degree4);
        assert degree4.getId().equals("deg4") && degree4.getName().equals("BSc Mathematics");

        Degree degree5 = StudentManager.readDegree("deg5");
        assertNotNull(degree5);
        assert degree5.getId().equals("deg5") && degree5.getName().equals("BSc Chemistry");

        Degree degree6 = StudentManager.readDegree("deg6");
        assertNotNull(degree6);
        assert degree6.getId().equals("deg6") && degree6.getName().equals("BA Art");

        Degree degree7 = StudentManager.readDegree("deg7");
        assertNotNull(degree7);
        assert degree7.getId().equals("deg7") && degree7.getName().equals("BA Philosophy");

        Degree degree8 = StudentManager.readDegree("deg8");
        assertNotNull(degree8);
        assert degree8.getId().equals("deg8") && degree8.getName().equals("BCom Finance");

        Degree degree9 = StudentManager.readDegree("deg9");
        assertNotNull(degree9);
        assert degree9.getId().equals("deg9") && degree9.getName().equals("BCom Marketing");

    }

    /**
     * Description: <br/>
     * Test for invalid range of the degree id. Just opposite of the above.
     *
     * @throws NoSuchRecordException
     * @author Yun Zhou
     */
    @Test
    public void test_readDegree1() throws NoSuchRecordException {
        // check the negative
        Degree degree_invalid = StudentManager.readDegree("deg-1");
        assertNull(degree_invalid);
        // random check
        for (int i = 0; i < 100; i++) {
            // define the range
            int max = 99;
            int min = 10;
            int range = max - min + 1;
            // generate random numbers within 10 to 99(including)
            int randomNumber = (int) (Math.random() * range) + min;
            // check if the random value is in the range
            assert randomNumber >= 10 && randomNumber <= 99;
            String degreeID_inValid = "deg" + Integer.toString(randomNumber);
            assertNull(StudentManager.readDegree(degreeID_inValid));
        }
        // System.out.println("MMMMMMMMMMMMMMMMMMM");//for debug

    }

    /**
     * Description: <br/>
     * Test for the valid studnet id. Run 910 times to check whether it will not return a null
     * student object.
     *
     * @throws NoSuchRecordException
     * @author Yun Zhou
     */
    @Test
    public void test_readStudent() throws NoSuchRecordException {
        // random check
        for (int i = 0; i < 910; i++) {
            // define the range
            int max = StudentManager.beUsedStudentID_max;
            int min = StudentManager.beUsedStudentID_min;
            int range = max - min + 1;

            // generate random numbers within 0 to 9999(including)
            int randomNumber = (int) (Math.random() * range) + min;
            // check if the random value is in the range
            assert randomNumber >= min && randomNumber <= max;

            String validID = "id" + Integer.toString(randomNumber);
            Student student = StudentManager.readStudent(validID);
            assertNotNull(student);
        }
        // System.out.println("SACACDKJCB VDUKICBIVHKCB");
    }

    /**
     * Description: <br/>
     * Check whether the corresponding fields are the same as the TABLE STUIDENTS.
     *
     * @throws NoSuchRecordException
     * @author Yun Zhou
     */
    @Test
    public void test_readStudent1() throws NoSuchRecordException {
        Student student0 = StudentManager.readStudent("id0");
        assert student0.getId().equals("id0") && student0.getFirstName().equals("James")
                && student0.getName().equals("Smith")
                && student0.getDegree().getId().equals("deg0");

        Student student1 = StudentManager.readStudent("id1");
        assert student1.getId().equals("id1") && student1.getFirstName().equals("John")
                && student1.getName().equals("Jones")
                && student1.getDegree().getId().equals("deg1");

        Student student2 = StudentManager.readStudent("id2");
        assert student2.getId().equals("id2") && student2.getFirstName().equals("Janice")
                && student2.getName().equals("Taylor")
                && student2.getDegree().getId().equals("deg2");

        Student student3 = StudentManager.readStudent("id3");
        assert student3.getId().equals("id3") && student3.getFirstName().equals("Max")
                && student3.getName().equals("Muller")
                && student3.getDegree().getId().equals("deg3");

        Student student4 = StudentManager.readStudent("id4");
        assert student4.getId().equals("id4") && student4.getFirstName().equals("Keira")
                && student4.getName().equals("Tipene")
                && student4.getDegree().getId().equals("deg4");

        // System.out.println("readStudnet 1-------------------------------------");
    }

    /**
     * Description: <br/>
     * check whether the invalid id will return null Studnet instance.
     *
     * @throws NoSuchRecordException
     * @author Yun Zhou
     */
    @Test
    public void test_readStudent2() throws NoSuchRecordException {
        // random check
        for (int i = 0; i < 910; i++) {
            // define the range
            int max = Integer.MAX_VALUE;
            int min = 1000000;
            int range = max - min + 1;

            // generate random numbers within 10000 to 99999(including)
            int randomNumber = (int) (Math.random() * range) + min;

            String invalidID = "id" + Integer.toString(randomNumber);
            Student student = StudentManager.readStudent(invalidID);
            assertNull(student);
        }

    }

    /**
     * Description: <br/>
     * Test for the performance of my programme is within 1 sec for 1000 random queries.
     * 
     * @author Yun Zhou
     * @throws Exception
     */
    @Test
    public void test_performance() throws Exception {
        for(int time = 0; time < 10; time++){
            long before_seconds = System.currentTimeMillis();
            // run 1000 random read queries
            for (int i = 0; i < 1000; i++) {
                // generate random numbers within 0 to 9(including)
                int randomNumber = (int) (Math.random() * 10);
                String degreeID = "deg" + randomNumber;
                StudentManager.readDegree(degreeID);
            }

            long after_seconds = System.currentTimeMillis();
            long diff = after_seconds - before_seconds;
            System.out.println(
                    "1000 DEGREE queries runs " + diff + " milliseconds" +
                            "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            assert diff > 0 && diff < 1000;
        }

        for (int time = 0; time < 90; time++) {

            long before_seconds = System.currentTimeMillis();
            // run 1000 random read queries
            for (int i = 0; i < 1000; i++) {
                // generate random numbers within 0 to 9(including)
//            int randomNumber = (int) (Math.random() * 10) ;
                String studentID = "id" + i;
                StudentManager.readStudent(studentID);
            }

            long after_seconds = System.currentTimeMillis();
            long diff = after_seconds - before_seconds;
            System.out.println(
                    "STUDENT: 1000 queries runs " + diff + " milliseconds" +
                            "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            assert diff > 0 && diff < 1000;


        }
    }

    /**
     * Description: <br/>
     * Test for checking whether the delete Student method is worked.
     * 
     * @author Yun Zhou
     * @throws NoSuchRecordException
     */
    @Test
    public void test_delete() throws NoSuchRecordException {
        String student_id = "id0";

        Student student = StudentManager.readStudent(student_id);
        // check the student is exist for now
        assertNotNull(student);

        // delete the student and
        // check whether the student has been deleted or not
        StudentManager.delete(student);
        assertNull(StudentManager.readStudent(student_id));
    }

    /**
     * Description: <br/>
     * Test for checking whether the delete Student method is worked.
     *
     * This test will try to delete more student instances and check if it is worked.
     * 
     * @author Yun Zhou
     * @throws NoSuchRecordException
     */
    @Test
    public void test_delete1() throws NoSuchRecordException {

        // random check
        for (int i = 0; i < 90; i++) {

            String validID = "id" + i;
            Student student = StudentManager.readStudent(validID);

            // check the student is exist for now
            assertNotNull(student);
            // delete the student and
            // check whether the student has been deleted or not
            StudentManager.delete(student);
            assertNull(StudentManager.readStudent(validID));
        }

        // System.out.println("DELETE TEST 1 DONE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!11");

    }

    /**
     * Description: <br/>
     * Test for checking whether update Student test is worked or not.
     * 
     * @author Yun Zhou
     * @throws NoSuchRecordException
     */
    @Test
    public void test_update() throws NoSuchRecordException {
        Student student = StudentManager.readStudent("id7");
        assertNotNull(student);

        String id = student.getId();
        String name = student.getName();
        String firstName = student.getFirstName();

        String new_name = "Zhou";
        String new_firstName = "Yun";
        student.setName(new_name);
        student.setFirstName(new_firstName);

        StudentManager.update(student);

        assert !name.equals(StudentManager.readStudent(id).getName())
                && !firstName.equals(StudentManager.readStudent(id).getFirstName());
    }

    /**
     * Description: <br/>
     * Another test for testing update() method is worked.
     * 
     * @author Yun Zhou
     * @throws NoSuchRecordException
     */
    @Test
    public void test_update1() throws NoSuchRecordException {
        Student student = StudentManager.readStudent("id38");
        assertNotNull(student);

        student.setFirstName("Yun");
        StudentManager.update(student);

        Student updated_student = StudentManager.readStudent("id38");
        assertNotNull(updated_student);
        assertTrue(updated_student.getFirstName().equals("Yun"));

    }

    /**
     * Description: <br/>
     * Use several different ways to check whether Student instance has been created
     * successfully as well as to check whether the created student instance has been inserted
     * into the STUDENTS table or not.
     * 
     * @author Yun Zhou
     * @throws NoSuchRecordException
     */
    @Test
    public void test_createStudent() throws NoSuchRecordException {
        Degree degree = StudentManager.readDegree("deg3");
        assertNotNull(degree);

        Student new_student = StudentManager.createStudent("Zhou", "Yun", degree);
        assertNotNull(new_student);

        // read id from above and check whether it's exist in the STUDENTS database
        Student student_fromDataBase = StudentManager.readStudent(new_student.getId());
        assertNotNull(student_fromDataBase);

        // check if two student instances are equal
        assertTrue(new_student.equals(student_fromDataBase));

        // check whether the name and first name of the inserted student is correct
        assertTrue(student_fromDataBase.getFirstName().equals("Yun")
                && student_fromDataBase.getName().equals("Zhou"));

        // check the degree
        assert student_fromDataBase.getDegree().getId().equals("deg3")
                && student_fromDataBase.getDegree().getName().equals("BE Software Engineering");

    }

    /**
     * Description: <br/>
     * Simple check the student ids are correct by checking the string.
     * 
     * @author Yun Zhou
     * @throws NoSuchRecordException
     */
    @Test
    public void test_getAllStudentIds() throws NoSuchRecordException {
        Collection<String> used_ids = StudentManager.getAllStudentIds();
        assertNotNull(used_ids);
        assert used_ids.size() > 0;
        int num = 0;
        for (String used_id : used_ids) {
            String id = "id" + Integer.toString(num);
            assertTrue(id.equals(used_id));
            num++;
        }
    }

    /**
     * Description: <br/>
     * Complex check the student ids collections.
     * <P>
     * I check this by first creating a new Student instance and delete one, and then check the
     * corresponding info has been changed or not, I think I checked everything out.
     *
     * @author Yun Zhou
     * @throws NoSuchRecordException
     */
    @Test
    public void test_getAllStudentIds_1() throws NoSuchRecordException {
        Collection<String> used_studentIDs = StudentManager.getAllStudentIds();
        assertNotNull(used_studentIDs);
        assert used_studentIDs.size() > 0;

        // try to create a new student and see whether the list of used_studentIDs is increased
        Degree degree = StudentManager.readDegree("deg6");
        int old_size = used_studentIDs.size();
        StudentManager.createStudent("Y", "Z", degree);// create a new student instance
        used_studentIDs = StudentManager.getAllStudentIds();// update the collections
        assertFalse(used_studentIDs.size() == old_size);
        assertTrue(used_studentIDs.size() > old_size && used_studentIDs.size() - 1 == old_size);
        // check the id of the new added student instance
        assertTrue(StudentManager.readStudent("id" + Integer.toString(old_size)).getFirstName()
                .equals("Z")
                && StudentManager.readStudent("id" + Integer.toString(old_size)).getName()
                        .equals("Y"));

        // try to delete a Student from the database
        old_size = used_studentIDs.size();// update the old size
        StudentManager.delete(StudentManager.readStudent("id250"));// delete a student
        used_studentIDs = StudentManager.getAllStudentIds();// update the collections
        assertFalse(used_studentIDs.size() == old_size);
        assertTrue(used_studentIDs.size() < old_size && used_studentIDs.size() + 1 == old_size);

        // System.out.println("Get all student ids test 1 is done!!!!!!!!");

    }

    /**
     * Description: <br/>
     * A test for checking the degreeID.
     * 
     * @author Yun Zhou
     * @throws NoSuchRecordException
     */
    @Test
    public void test_getAllDegreeIds() throws NoSuchRecordException {
        Collection<String> used_degreeIDs = (Collection<String>) StudentManager.getAllDegreeIds();
        assertTrue(used_degreeIDs.size() > 0 && used_degreeIDs.size() == 10);

        int num = 0;
        for (String degreeID : used_degreeIDs) {
            String id = "deg" + Integer.toString(num);
            assertTrue(id.equals(degreeID));
            num++;
        }

    }
}
