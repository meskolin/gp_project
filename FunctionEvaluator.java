package gp_project;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

public class FunctionEvaluator {
	
	private Tree m_bestFit;
	
	public boolean evaluate(List<String> postOrderList, int xValue, double yvalueOUT)
	{
		
		Stack<Integer> st = new Stack<Integer>();
		
		for (String nodeValue : postOrderList)
		{
			if (isInteger(nodeValue))
			{
				st.push(Integer.parseInt(nodeValue));
			}
			else if (isVariable(nodeValue))
			{
				st.push(xValue);
			}
			else 
			{
				int num1 =  st.pop();
				int num2 =  st.pop();
				
				switch(getOperator(nodeValue))
				{
				case ADD:
					st.push(num1 + num2);
					break;
				case MULTIPLY:
					st.push(num1 * num2);
					break;
				case DIVIDE:
					if(num2 == 0)
					{
						return false;
					}
					st.push(num1 / num2);
					break;
				case SUBTRACT:
					st.push(num1 - num2);
					break;
				default:
					throw new IllegalArgumentException("Invalid Operator");
				}				
			
			}
		}
		
		yvalueOUT = st.pop();
		return true;
	}
	
	//return true to continue, false if tree is found
	public boolean evaluatePop(Population pop)
	{
		FunctionCompare comp = new FunctionCompare();
		double fitness = -1;
		boolean isValid = true;
		
		List<Tree> list= pop.getTrees();
		for (Iterator<Tree> iter = list.iterator(); iter.hasNext(); )
		{
			Tree func = iter.next();
			isValid = comp.getFitnessValue(func, fitness);
			if(isValid)
			{
				if (fitness == 0)
				{
					System.out.println("----Found best fit tree--------");
					func.printTree();
					m_bestFit = func;
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
	
	/* Todo not sure we need this. Maybe will just check for validity while doing evaluation
	public void discardInvalid(Population pop)
	{
		List<Tree> list= pop.getTrees();
		
		for (Iterator<Tree> iter = list.iterator(); iter.hasNext(); ) {
		    Tree tree = iter.next();
		    
		    if(!validateTree(tree))
		    {
		    	iter.remove();
		    }
		}
	}*/
	
	public boolean evaluateFunction(Tree tree, int xValue, double yValueOUT)
	{	
		List<String> postOrderList = new ArrayList<String>();
		getPostOrderList(tree.getRootNode(),postOrderList);
		
		//System.out.println("evaluateFunction");
		//System.out.println(Arrays.toString(postOrderList.toArray()));
		
		 return evaluate(postOrderList, xValue, yValueOUT); 
		
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
