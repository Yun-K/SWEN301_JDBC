package test.nz.ac.wgtn.swen301.assignment1;

import static org.junit.Assert.*;

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
     * @author Yun Zhou
     * @throws NoSuchRecordException
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
     * 
     * @author Yun Zhou
     * @throws NoSuchRecordException
     */
    @Test
    public void test_readStudent() throws NoSuchRecordException {
        // random check
        for (int i = 0; i < 910; i++) {
            // define the range
            int max = StudentManager.availableStudentID_max;
            int min = StudentManager.availableStudentID_min;
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
     * @author Yun Zhou
     * @throws NoSuchRecordException
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

    @Test
    public void test_performance() {

    }

    @Test
    public void test_delete() throws Exception {
    }

    @Test
    public void test_update() throws Exception {
    }

    @Test
    public void test_createStudent() throws Exception {
    }

    @Test
    public void test_getAllStudentIds() throws Exception {
    }

    @Test
    public void test_getAllDegreeIds() throws Exception {

    }
}
