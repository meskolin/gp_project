package gp_project;

import java.util.Arrays;
import java.util.logging.*;
import java.util.List;

public class GPDriver {

	public static void main(String[] Args) {
		System.out.println("generating functions");
		FunctionGenerator fGen = new FunctionGenerator();
		Tree tree = fGen.GenerateFullTree(2);
		tree.printTree();

		FunctionEvaluator fEval = new FunctionEvaluator();
		int result = fEval.evaluateFunction(tree, 2);
		System.out.println("result of eval:");
		System.out.println(result);
		GPConfig config = GPConfig.getInstance();
		List<TrainingDataPair> list = config.getTrainingData();
		System.out.println(Arrays.toString(list.toArray()));
		System.out.println("fitness value:");
		
		
		FunctionCompare comp = new FunctionCompare();
		System.out.println(comp.getFitnessValue(tree));
	}

}
