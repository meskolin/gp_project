package gp_project;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class FunctionEvaluator {
	
	public Integer evaluate(List<String> postOrderList, int xValue)
	{
		
		Stack<Integer> st = new Stack<Integer>();
		
		for (String nodeValue : postOrderList)
		{
			if (isInteger(nodeValue)) //is operator
			{
				st.push(Integer.parseInt(nodeValue));
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
		
		
		return st.pop();
	}
	
	public Integer evaluateFunction(Tree tree, int xValue)
	{	
		//List<String> postOrderList = getPostOrderList(tree);
		List<String> postOrderListTmp = Arrays.asList("3","2","*","6","2","/","+");
		return evaluate(postOrderListTmp, xValue);
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
