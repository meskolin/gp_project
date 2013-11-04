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
		
		while(fEval.evaluatePop(pop) && numGenerations < 50)
		{
			System.out.println("num generation:" + numGenerations);
			modifier.mutatePop(pop);
			numGenerations ++;
		}
	
		List<Tree> list = pop.getTrees();
		for (Tree t : list)
		{
			List<String> postOrderList = new ArrayList<String>();
			fEval.getPostOrderList(t.getRootNode(),postOrderList);
			System.out.println(Arrays.toString(postOrderList.toArray()));
			
		}
		
	}

}
