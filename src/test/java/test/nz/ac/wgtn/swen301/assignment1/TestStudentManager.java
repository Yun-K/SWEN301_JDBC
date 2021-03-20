package test.nz.ac.wgtn.swen301.assignment1;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import nz.ac.wgtn.swen301.assignment1.StudentManager;
import nz.ac.wgtn.swen301.studentdb.Degree;
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
     * Check whether the fields of Degree object is correct or not.
     * 
     * @author Yun Zhou
     * @throws Exception
     */
    @Test
    public void test_readDegree() throws Exception {

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

    @Test
    public void test_readStudent() throws Exception {

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
