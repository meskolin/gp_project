package gp_project;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class FunctionModifier {

	private GPConfig m_config = GPConfig.getInstance();
	
	public void mutatePop(Population pop)
	{
		List<Tree> trees = pop.getTrees();
		int numTrees = trees.size();
		
		double mutatePercent = m_config.getMutatePercent();
		long numToMutate = Math.round(mutatePercent*numTrees);
		
		System.out.println("mutating " + numToMutate + "trees");
		for (int i=0; i< numToMutate; i++)
		{
			int index = m_config.getRand().nextInt(numTrees);
			mutate(trees.get(index));
		}
		
	}
	
	public void mutate(Tree tree)
	{
		
		FunctionEvaluator eval = new FunctionEvaluator();
		List<Node> allNodes = new LinkedList<Node>();
		eval.getPostOrderNodeList(tree.getRootNode(), allNodes);
		
		int numNodes = allNodes.size();
		
		Random rnd = GPConfig.getInstance().getRand();
		int index = rnd.nextInt(numNodes);
		
		Node node = allNodes.get(index); 
		if (node.getNodeType() == NodeType.OPERAND)
		{
			node.setData(m_config.getRandOperand());
		}
		else if (node.getNodeType() == NodeType.OPERATOR)
		{
			node.setData(m_config.getRandOperator());
		}
		else
		{
			throw new IllegalArgumentException("Invalid node type");
		}
	}
	
	public void crossOver(Tree t1, Tree t2)
	{
		FunctionEvaluator eval = new FunctionEvaluator();
		List<Node> t1Nodes = new LinkedList<Node>();
		List<Node> t2Nodes = new LinkedList<Node>();
		
		eval.getPostOrderNodeList(t1.getRootNode(), t1Nodes);
		eval.getPostOrderNodeList(t2.getRootNode(), t2Nodes);
		
		int numNodesT1 = t1Nodes.size();
		int numNodesT2 = t2Nodes.size();
		
		Random rnd = GPConfig.getInstance().getRand();
		int index1 = rnd.nextInt(numNodesT1);
		int index2 = rnd.nextInt(numNodesT2);
		
		Node nodeT1 = t1Nodes.get(index1);
		Node nodeT2 = t2Nodes.get(index2);
		
		Node nodeT1Copy = new Node(nodeT1);
		
		System.out.println("------------Inserting this node into T2--------------");
		nodeT1Copy.printNode();
		
		nodeT1.setData(nodeT2.getData());
		nodeT1.setLeftNode(nodeT2.getLeftNode());
		nodeT1.setRightNode(nodeT2.getRightNode());
		nodeT1.setNodeType(nodeT2.getNodeType());
		//nodeT2 = nodeT1Copy;
	
		//nodeT2 = nodeT1;
		
		nodeT2.setData(nodeT1Copy.getData());
		nodeT2.setLeftNode(nodeT1Copy.getLeftNode());
		nodeT2.setRightNode(nodeT1Copy.getRightNode());
		nodeT2.setNodeType(nodeT1Copy.getNodeType());
		
		
	}
}
