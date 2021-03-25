## SWEN301 Assignment 1, 2021, Yun

#a. How you checked the correctness of dependencies between the UI and the domain layer using the generated jdepend reports.
<p>
By checking the jdepend-report.html in Chrome, I can see that there are no cyclic dependencies in the Cycles section, which prove the correctness.
<p>


#b. How to generate the standalone CLI application with mvn, and how to use
<p>
1) clone my repo, and run: cd Assignment1 to open into the folder.
<p>
2) run the following command to get the assoicated studentdb.jar file install :<p>
mvn install:install-file -Dfile=lib/studentdb-1.1.1.jar -DgroupId=nz.ac.wgtn.swen301 -DartifactId=studentdb -Dversion=1.1.1 -Dpackaging=jar
<p>
3) run the following command to package the project: mvn package
<p>
4) now, the executable jar file with the name studentfinder.jar can be found in target/studentfinder.jar
<p>
5)finally, this standalone Command-Line-Interface application which is the studentfinder.jar can be run in this command:<p>
java -jar target/studentfinder.jar id42
<p>A little bit slow, please be patient. :)
<p>
#c. Discuss (less than 100 words) whether your design is prone to memory leaks by interfering with Garbage Collection and how this has been or could be addressed.
<p>
In my design, Connection, Statement, PreparedStatement, ResultSet are used and they occupy the space resource on heap. <p>
In order to release it, I add an extra closeResources() method and it's called surround within the final{} block, all these arguments are passing into it to release. As it's the final block, this can ensure the Garbage collection and try the best to restore memory allocations and avoid memory leaks.<p>
Except for these arguments, static methods and variables also occupy the heap, should be minimised as much as possible to release memory, reduce the usage of static can also help to address memory leaks.
<p>
 (p.s. btw, due to the performance test, I didn't add it in readDegree() and readStudent(), as this will cause run more 0.5 sec lateness.)

