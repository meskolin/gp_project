#Gp_Project
##Genetic Programming to find a polynomial function given a set of training data

Genetic programming is a methodology inspired by biological evolution mechanisms that finds the “most fit” member of a population by  preserving the fittest members from successive generations. In our example, the individuals in the population will be polynomial functions whose fitness is evaluated based on how close they represent given training data. 

###Terminology:

<b>Individual:</b> In our GPE, individuals will be polynomial functions, represented as function trees. 

<b>Fitness:</b>  The fitness is a measure of how well a function matches a set of training data, 

determined by calculating the sum of the error between the training data points and the function. 

<b>Crossover:</b>  A modification to a function in which two functions swap nodes and produce two 

child trees.

<b>Mutatation:</b>  A modification to a function in which a node of the function tree is randomly changed 

to a new, random operator or operand.

Property |	Allowed Values	| Sample Value used to find y = (-3x2+7)/2    	| Keyword in Settings File|
|-------| ---------| -----------|-------------|
The GPE shall generate a number of random functions(individuals)	| Any integer	| 100	| POPULATION_SIZE
The GPE shall generate functions using a given set of operands.	|-9, -8, -7, -6, -5, -4,-3 ,-2 ,-1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, x	 |-3, 7, 2	|OPERANDS
The GPE shall generate functions using a given set of operators.	|minus (-), addition (+), multiple (*), and divide (/)| + ,/, * | OPERATORS
	The complexity of the randomly generated functions shall be configurable by specifying the maximum depth of the function tree | estimated 3 - 8 nodes	| 3	| MAX_DEPTH
	In each generation, the GPE shall eliminate a certain percentage of low fitness functions. 	| 0.0-1.0	| 0.5	|ELIMINATE_PERCENT
In each generation, the GPE shall mutate a certain percentage of functions. |	0.0-1.0	|0.2	|MUTATE_PERCENT
In each generation, the GPE shall crossover a certain percentage of functions. |	0.0-1.0	| 0.1|	CROSSOVER_PERCENT
The GPE shall determine that the specified function has been found when the fitness value is less than or equal to a certain threshold. |	Positive Integer 	| 0	| FITNESS_THRESHOLD

###Running the program
Quick Start
After building the single jar, verify that settings.txt, data.txt and jar exist in the same directory. Simply run the jar file with no arguments.

As the program runs, it will print out to the console the current generation number. In addition, it will print out what is the best fitness score that has been found so far.
After a tree with a fitness value of zero has been found or the 15 minute time limit has been reached, the best fit tree we have found will be printed in the console in two different formats. One format will be the graphical binary tree representation of the function. The second format will be the single line representation of the function that comes from traversing the tree and applying parenthesis for each subtree. 

With training data representing y=x^2 + 5, here is some sample output:
```
java -jar gp.jar
----Found best fit tree--------
Winning tree fitness value:0.0
       -
      / \
     /   \
    /     \
   /       \
   *       -
  / \     / \
 /   \   /   \
 *   *   -   *
/ \ / \ / \ / \
2 2 1 1 1 2 x x
```
This tree can simplified to y=x^2 + 5.
