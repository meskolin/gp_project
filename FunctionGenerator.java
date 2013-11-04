package gp_project;

import java.util.logging.*;

public class FunctionGenerator {

	public Tree GenerateFullTree(int maxDepth) {
		GPConfig config = GPConfig.getInstance();
		Tree tree = new Tree(config.getRandOperator());
		int currentDepth = 1;
		addChildrenFullMethod(tree.getRootNode(), currentDepth, maxDepth);
		return tree;
	}

	void addChildrenFullMethod(Node node, int currentD, int maxD) {
		GPConfig config = GPConfig.getInstance();
		if (config == null | node == null) {
			throw new IllegalArgumentException("INVALID");

		}

		if (currentD == maxD) {
			node.setRightNode(new Node(config.getRandOperand(), NodeType.OPERAND));
			node.setLeftNode(new Node(config.getRandOperand(), NodeType.OPERAND));
			return;
		} else {

			node.setRightNode(new Node(config.getRandOperator(), NodeType.OPERATOR));
			node.setLeftNode(new Node(config.getRandOperator(),NodeType.OPERATOR));
		}

		addChildrenFullMethod(node.getRightNode(), currentD + 1, maxD);
		addChildrenFullMethod(node.getLeftNode(), currentD + 1, maxD);

		return;
	}
	
	public Tree GenerateGrowTree(int maxDepth) {
		GPConfig config = GPConfig.getInstance();
		Tree tree = new Tree(config.getRandOperator());
		int currentDepth = 1;
		addChildrenGrowMethod(tree.getRootNode(), currentDepth, maxDepth);
		return tree;
	}
	

	void addChildrenGrowMethod(Node node, int currentD, int maxD) {
		GPConfig config = GPConfig.getInstance();
		if (config == null | node == null) {
			throw new IllegalArgumentException("INVALID");

		}

		if (currentD == maxD) {
			node.setRightNode(new Node(config.getRandOperand(), NodeType.OPERAND));
			node.setLeftNode(new Node(config.getRandOperand(), NodeType.OPERAND));
			return;
		} 
		else if (node.getNodeType() == NodeType.OPERATOR) 
		{
			node.setRightNode(new Node(config.getRandOperatorOrOperand(), NodeType.OPERATOR));
			node.setLeftNode(new Node(config.getRandOperatorOrOperand(),NodeType.OPERATOR));
		}
		else if (node.getNodeType() == NodeType.OPERAND)
		{
			return;
		}


		addChildrenGrowMethod(node.getRightNode(), currentD + 1, maxD);
		addChildrenGrowMethod(node.getLeftNode(), currentD + 1, maxD);

		return;
	}
}
