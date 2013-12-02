package gp_project;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GPDriver {

	public static void main(String[] Args) {

		Population pop = new Population();
		pop.generateFirstPopulation();

		FunctionEvaluator fEval = new FunctionEvaluator();
		FunctionModifier modifier = new FunctionModifier();

		
		int numGenerations = 0;
		
		boolean keepGoing = fEval.evaluatePop(pop);

		long startTime = System.nanoTime();
		long stop = startTime + TimeUnit.MINUTES.toNanos(15);

		while (keepGoing && stop > System.nanoTime()) {
			modifier.mutatePop(pop);
			modifier.crossoverPop(pop);
			pop.regenerate();
			numGenerations++;
			keepGoing = fEval.evaluatePop(pop);
			pop.pruneLowFitnessTrees();
			System.out.println("Evaluated " + numGenerations + " generations. Best fitness found: " + Math.ceil( fEval.getGenerationbestFitness() ) );
		}
		long durationNanos = System.nanoTime() - startTime;
		// Print out the winning tree
		long durationSeconds = TimeUnit.NANOSECONDS.toSeconds(durationNanos);
		
		System.out.println("Found tree in " + numGenerations + " generations. Ran for " + durationSeconds + " seconds");
		System.out.println("Winning tree fitness value:"
				+ fEval.getBestFitness());
		Tree best = fEval.getBestTree();
		
		System.out.println("--------------------------------------Function tree------------------------------");
		TreePrinter printer = new TreePrinter();
		printer.printNode(best.getRootNode());
		
		List<String> list = new LinkedList<String>();
		fEval.getInOrderNodeList(best.getRootNode(),list);

		System.out.println("------------------------------------Simplified tree------------------------------");
		for (String str : list)
		{
			System.out.print(str);
		}

	}

}
