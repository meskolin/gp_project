package gp_project;

import java.util.logging.*;

public class GPDriver {

	public static void main(String[] Args) {
		System.out.println("generating functions");
		FunctionGenerator fGen = new FunctionGenerator();
		Tree tree = fGen.GenerateFullTree(2);
		tree.printTree();
	}

}
