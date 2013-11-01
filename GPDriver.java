package gp_project;

import java.util.logging.*;

public class GPDriver {

	public static void main(String[] Args) {
		System.out.println("generating functions");
		FunctionGenerator fGen = new FunctionGenerator();
		Tree tree = fGen.GenerateFullTree(2);
		tree.printTree();

		FunctionEvaluator fEval = new FunctionEvaluator();
		int result = fEval.evaluateFunction(tree, 0);
		System.out.println("result of eval:");
		System.out.println(result);
	}

}
