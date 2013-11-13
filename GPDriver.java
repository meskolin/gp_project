package gp_project;

import java.util.concurrent.TimeUnit;

public class GPDriver {

	
	public static void main(String[] Args) {
		
	
		Population pop = new Population();
		pop.generateFirstPopulation();
		
		FunctionEvaluator fEval = new FunctionEvaluator();
		FunctionModifier modifier = new FunctionModifier();
		
		int numGenerations = 0;
		
		boolean keepGoing = fEval.evaluatePop(pop);
		
		long stop=System.nanoTime()+TimeUnit.MINUTES.toNanos(15);
			
		while(keepGoing && stop> System.nanoTime())
		{
			modifier.mutatePop(pop);
			modifier.crossoverPop(pop);
			pop.regenerate();
			numGenerations ++;
			keepGoing = fEval.evaluatePop(pop);
			pop.pruneLowFitnessTrees();
		}
	
		//Print out the winning tree
		System.out.println("Found tree in " + numGenerations + " generations");
		System.out.println("Winning tree fitness value:" + fEval.getBestFitness());
		Tree best = fEval.getBestTree();		
		TreePrinter printer = new TreePrinter();
		printer.printNode(best.getRootNode());
		
		
	}

}
