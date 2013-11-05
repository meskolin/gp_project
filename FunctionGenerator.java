package gp_project;

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
		
		if (node.getNodeType() == NodeType.OPERAND)
		{
			return;
		}
		else if (currentD == maxD) {
			node.setRightNode(new Node(config.getRandOperand(), NodeType.OPERAND));
			node.setLeftNode(new Node(config.getRandOperand(), NodeType.OPERAND));
			return;
		} 
		else if (node.getNodeType() == NodeType.OPERATOR) 
		{
			int randNum = GPConfig.getInstance().getRand().nextInt(2);			
			
			//Pick Random number for Right Node
			if (randNum == 1)
			{
				node.setRightNode(new Node(config.getRandOperator(), NodeType.OPERATOR));
			}
			else
			{
				node.setRightNode(new Node(config.getRandOperand(), NodeType.OPERAND));
			}
			
			//Pick Random number for Left Node
			randNum = GPConfig.getInstance().getRand().nextInt(2);
			if (randNum == 1)
			{
				node.setLeftNode(new Node(config.getRandOperator(),NodeType.OPERATOR));
			}
			else
			{
				node.setLeftNode(new Node(config.getRandOperand(),NodeType.OPERAND));
			}
		
		}
		else
		{
			throw new IllegalArgumentException("Invalid Tree encountered during grow method");
		}
		


		addChildrenGrowMethod(node.getRightNode(), currentD + 1, maxD);
		addChildrenGrowMethod(node.getLeftNode(), currentD + 1, maxD);

		return;
	}
}
