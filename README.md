<MAZE GENERATOR>
_____________________

Summary:

A program that creates a random maze based on a seed. It has three options for what size maze the user would like to create, outputs it to a text file, and then asks if the used wants to create another maze. If not, then it exits the program.


How to run / What this file includes:

The main, as well as the majority of the source code is in MazeGenerator.java
This file also includes the java files Queue.java, LinkedList.java, and Node.java.
In addition, it includes this README file as well as a PDF of the tile set that this program is based on.


The tile set:
There are 6 different tile sets that this program uses that it will randomly choose the orientation of where these tile sets will be placed. Each tile set is 4x4 (row x column) and puts each row into a node of a Linked List that is designated for that particular tile option. The original source for these tile sets is included in the pdf labeled "tile set template" (the blue is the wall and the white is the path). Nothing besides these tile sets were used from this source, but if one would like to verify this, here is the link:
https://www.gamasutra.com/blogs/HermanTulleken/20161005/282629/Algorithms_for_making_more_interesting_mazes.php


Order of operations (aka the main method explained) :

The program begins by using a method to fill each created linked list with one of the 6 pre-made 4x4 tile types. It inserts each 4x1 piece onto each node of the linked list which gives each -linked list a length of 4. The program then uses the Random class in Java to create a seed for the maze. The program will use this seed to create an array of tile choices for each maze size. The program then uses a method to ask the user for their preferred maze size which will determine which method for creating the maze will run. It then runs a try / catch block that, in essence, makes a dramatic wait sequence that has no actual correlation to the code or the process. It simply looks cool. Sue me. After this dramatic output, the program then goes into and if, else if block which determines what size maze to output. In each if, else if section, it does the same operation, but it calls a different method that will return the desired maze size. The program creates a Queue and sets it equal to a method call that will create a maze based on the size with the randomized seed choice as the only parameter. It then goes into a loop dequeueing the queue, and revealing the created maze. The program will also show the user a label for the size of the graph, the seed that the program has randomly chosen, and displays a unique maze-based “Maze Created” output. Finally, the program calls a method to ask the user if they would like to create another maze. If they do, then It will do everything that was said previously again. If they do not want to create another maze, it will bid the user farewell, and then it will ends the program.
