package gp_project;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

public class FunctionEvaluator {
	
	private Tree m_bestFit;
	private static double bestFitness = 10000;
	
	public Tree getBestTree()
	{
		return m_bestFit;
	}
	
	public double getBestFitness()
	{
		return bestFitness;
	}
	
	public EvaluationResult evaluate(List<String> postOrderList, int xValue)
	{
		EvaluationResult result = new EvaluationResult();
		Stack<Double> st = new Stack<Double>();
		for (String nodeValue : postOrderList)
		{
			if (isInteger(nodeValue))
			{
				st.push((double)Integer.parseInt(nodeValue));
			}
			else if (isVariable(nodeValue))
			{
				st.push((double)xValue);
			}
			else 
			{
				if(st.size() < 2)
				{
					throw new IllegalArgumentException("Invalid Tree encountered");
				}
				double num1 =  st.pop();
				double num2 =  st.pop();
				
				switch(getOperator(nodeValue))
				{
				case ADD:
					st.push(num1 + num2);
					break;
				case MULTIPLY:
					st.push(num1 * num2);
					break;
				case DIVIDE:
					if(num1 == 0)
					{
						result.isValid = false;
					}
					st.push(num2 / num1);
					break;
				case SUBTRACT:
					st.push(num2 - num1);
					break;
				default:
					throw new IllegalArgumentException("Invalid Operator");
				}				
			
			}
		}
		
		if (st.size()> 1)
		{
			throw new IllegalArgumentException("Invalid Tree encountered");
		}
		
		result.yValue =  st.pop(); 
		return result;
	}
	
	//return true to continue, false if tree is found
	public boolean evaluatePop(Population pop)
	{
		FunctionCompare comp = new FunctionCompare();
		
		List<Tree> list= pop.getTrees();
		for (Iterator<Tree> iter = list.iterator(); iter.hasNext(); )
		{
			Tree func = iter.next();
			FitnessResult fitResult = comp.getFitnessValue(func);
			if(fitResult.isValid)
			{
				if (fitResult.fitnessValue < bestFitness)
				{
					bestFitness = fitResult.fitnessValue ;
					//System.out.println("New low fitness value: " + bestFitness);
					//func.printTree();
					m_bestFit = func;
				}
				if (fitResult.fitnessValue  == 0)
				{
					System.out.println("----Found best fit tree--------");
					
					return false;
				}	
			}
			else
			{
				iter.remove();
			}
			
		}
		
		return true;
		
	}

	public EvaluationResult evaluateFunction(Tree tree, int xValue)
	{	
		List<String> postOrderList = new ArrayList<String>();
		getPostOrderList(tree.getRootNode(),postOrderList);
		
		//System.out.println("evaluateFunction");
		//System.out.println(Arrays.toString(postOrderList.toArray()));
		return evaluate(postOrderList, xValue);
	}
	
	void getPostOrderList(Node node, List<String> list)
	{
		  if(node == null) return;
		  
		  getPostOrderList( node.getLeftNode(),list);
		  getPostOrderList( node.getRightNode(),list); 

		  list.add(node.getData()); 
	}

	public void getPostOrderNodeList(Node node, List<Node> list)
	{
		  if(node == null) return;
		  
		  getPostOrderNodeList( node.getLeftNode(),list);
		  getPostOrderNodeList( node.getRightNode(),list); 

		  list.add(node); 
	}
	
	public static boolean isInteger(String str) {
		if (str == null) {
			return false;
		}
		int length = str.length();
		if (length == 0) {
			return false;
		}
		int i = 0;
		if (str.charAt(0) == '-') {
			if (length == 1) {
				return false;
			}
			i = 1;
		}
		for (; i < length; i++) {
			char c = str.charAt(i);
			if (c <= '/' || c >= ':') {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isVariable(String str) {
		
		return str.equals("x");
		
	}
	
	public static OperatorType getOperator(String str) {
		if(str.equals("+"))
		{
			return OperatorType.ADD;
		}
		else if (str.equals("-"))
		{
			return OperatorType.SUBTRACT;
		}
		else if (str.equals("/"))
		{
			return OperatorType.DIVIDE;
		}
		else if (str.equals("*"))
		{
			return OperatorType.MULTIPLY;
		}
		else
		{
			String msg = "Invalid Operator: ";
			msg += str;
			throw new IllegalArgumentException(msg);
		}
	
	}
}
