package gp_project;

public class GPDriver {

	
	public static void main(String[] Args) {
		
	
		Population pop = new Population();
		pop.generateFirstPopulation();
		
		FunctionEvaluator fEval = new FunctionEvaluator();
		FunctionModifier modifier = new FunctionModifier();
		
		int numGenerations = 0;
		
		boolean keepGoing = fEval.evaluatePop(pop);
		while(keepGoing && numGenerations < 2000)
		{
			modifier.mutatePop(pop);
			modifier.crossoverPop(pop);
			pop.regenerate();
			numGenerations ++;
			keepGoing = fEval.evaluatePop(pop);
			pop.pruneLowFitnessTrees();
		}
	
		//Print out the winning tree
		
		System.out.println("Winning tree fitness value:" + fEval.getBestFitness());
		Tree best = fEval.getBestTree();		
		TreePrinter printer = new TreePrinter();
		printer.printNode(best.getRootNode());
		
		
	}

}
