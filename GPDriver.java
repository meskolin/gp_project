package gp_project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.*;
import java.util.List;

public class GPDriver {

	public static void main(String[] Args) {
		
	
		Population pop = new Population();
		pop.generateFirstPopulation();
		
		/* Pseudocode
		while(!Found)
		{
			pop.mutateSome();
			pop.crossoverSome();
			eval.evaluateFitness(pop);
			if eval.getBestFitness() < desiredValue
			{
				found = true;
				print bestTree
			}
			
		}*/
		
		
		FunctionEvaluator fEval = new FunctionEvaluator();
		FunctionModifier modifier = new FunctionModifier();
		
		int numGenerations = 0;
		
		boolean keepGoing = fEval.evaluatePop(pop);
		while(keepGoing && numGenerations < 500)
		{
			modifier.mutatePop(pop);
			modifier.crossoverPop(pop);
			numGenerations ++;
			keepGoing = fEval.evaluatePop(pop);
		}
	
		//Print out the winning tree
		System.out.println("Winning tree fitness value:" + fEval.getBestFitness());
		Tree best = fEval.getBestTree();
		//List<String> postOrderList = new ArrayList<String>();
		//fEval.getPostOrderList(best.getRootNode(),postOrderList);
		//System.out.println(Arrays.toString(postOrderList.toArray()));
		
		TreePrinter printer = new TreePrinter();
		printer.printNode(best.getRootNode());
		
		
	}

}
