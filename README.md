## SWEN301 Assignment 1, 2021, Yun

#a. How you checked the correctness of dependencies between the UI and the domain layer using the generated jdepend reports.


#b. How to generate the standalone CLI application with mvn, and how to use
<br>
1) clone my repo with the command: <p>
       git clone https://gitlab.ecs.vuw.ac.nz/course-work/swen301/2021/zhouyun/Assignment1.git
<p>
2) run the command: cd Assignment1 to open into the folder.
<p>
3) mvn compile
Command-Line-Interface application

#c. Discuss (less than 100 words) whether your design is prone to memory leaks by interfering with Garbage Collection and how this has been or could be addressed.
<br>
In my design, Connection, Statement, PreparedStatement, ResultSet are used and they occupy the space resource on heap. <p>
In order to release it, I add an extra closeResources() method and it's called surround within the final{} block, all these arguments are passing into it to release. As it's the final block, this can ensure the Garbage collection and try the best to restore memory allocations and avoid memory leaks.<p>
Except for these arguments, static methods and variables also occupy the heap, should be minimised as much as possible to release memory, reduce the usage of static can also help to address memory leaks.
<p>
 (p.s. btw, due to the performance test, I didn't add it in readDegree() and readStudent(), as this will cause run more 0.5 sec lateness.)

