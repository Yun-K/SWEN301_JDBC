## SWEN301 Assignment 1, 2021, Yun

#a. How you checked the correctness of dependencies between the UI and the domain layer using the generated jdepend reports.


#b. How to generate the standalone CLI application with mvn, and how to use


#c. Discuss (less than 100 words) whether your design is prone to memory leaks by interfering with Garbage Collection and how this has been or could be addressed.
In my design, the arguments: Connection, Statement, PrepareStatement and ResultSet are used and they occupy the space resource on heap. 
<br>
In order to release it, I add an extra closeResources() method and it is called surround within the final{} block, all these arguments are passing into it to release. As the final block will always be executed, so this can ensure the Garbage collection and try the best to restore memory allocations and avoid memory leaks.
 (p.s. btw, due to the performance test, I did not add closeResources() method in readDegree() and readStudent(),this will cause run 0.5 sec lateness.)
<p>
Except for these arguments, the static methods and variables also occupy the heap, should be minimised as much as possible in order to release memory, reduce the usage of static can also help to address memory leaks.
