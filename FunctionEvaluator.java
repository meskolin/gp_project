package gp_project;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class FunctionEvaluator {

	private static double overallBestFitness = 10000;
	private static double generationbestFitness = 10000;
	
	public static double getGenerationbestFitness() {
		return generationbestFitness;
	}

	/**
	 * Parses out the operator type from a string and returns an enumerated type
	 * 
	 * @param str - String that contains an operator
	 * @return -type of operator found
	 */
	public static OperatorType getOperator(String str) {
		if (str.equals("+")) {
			return OperatorType.ADD;
		} else if (str.equals("-")) {
			return OperatorType.SUBTRACT;
		} else if (str.equals("/")) {
			return OperatorType.DIVIDE;
		} else if (str.equals("*")) {
			return OperatorType.MULTIPLY;
		} else {
			String msg = "Invalid Operator: ";
			msg += str;
			throw new IllegalArgumentException(msg);
		}

	}

	/**
	 * Parse a string and determine if it is an integer
	 * 
	 * @param str
	 * @return true if the string is an integer
	 */
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

	/**
	 * Parse a string and determine if it is a variable
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isVariable(String str) {

		return str.equals("x");

	}

	private Tree m_bestFit;

	/**
	 * Evaluate a function at a particular x value and return the y value (EvaluationResult class stores y value) 
	 * 
	 * @param postOrderList - List of operators and operands already parse in post order method
	 * @param xValue - value at which to evaluate the tree
	 * @return -result of evaluation. If evaluation involved division by zero, the result is invalid (EvaluationResult member variable isValid set to false)
	 */
	public EvaluationResult evaluate(List<String> postOrderList, int xValue) {
		EvaluationResult result = new EvaluationResult();
		Stack<Double> st = new Stack<Double>();
		for (String nodeValue : postOrderList) {
			if (isInteger(nodeValue)) {
				st.push((double) Integer.parseInt(nodeValue));
			} else if (isVariable(nodeValue)) {
				st.push((double) xValue);
			} else {
				if (st.size() < 2) {
					throw new IllegalArgumentException(
							"Invalid Tree encountered");
				}
				double num1 = st.pop();
				double num2 = st.pop();

				switch (getOperator(nodeValue)) {
				case ADD:
					st.push(num1 + num2);
					break;
				case MULTIPLY:
					st.push(num1 * num2);
					break;
				case DIVIDE:
					if (num1 == 0) {
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

		if (st.size() > 1) {
			throw new IllegalArgumentException("Invalid Tree encountered");
		}

		result.yValue = st.pop();
		return result;
	}
	/**
	 * Evaluate a function at a particular x value and return the y value (EvaluationResult class stores y value) 
	 * 
	 * @param tree - Tree to evaluate
	 * @param xValue - value at which to evaluate the tree
	 * @return -result of evaluation. If evaluation involved division by zero, the result is invalid (EvaluationResult member variable isValid set to false)
	 */
	public EvaluationResult evaluateFunction(Tree tree, int xValue) {
		List<String> postOrderList = new ArrayList<String>();
		getPostOrderList(tree.getRootNode(), postOrderList);

		// System.out.println("evaluateFunction");
		// System.out.println(Arrays.toString(postOrderList.toArray()));
		return evaluate(postOrderList, xValue);
	}


	/**
	 * 
	 * Evaluate all trees in the population to find their fitness values.
	 * If we find the tree we are looking for, store it in m_bestFit
	 * 
	 * @param pop
	 * @return return true to continue, false if tree is found
	 */
	public boolean evaluatePop(Population pop) {
		List<Tree> list = pop.getTrees();
		for (Iterator<Tree> iter = list.iterator(); iter.hasNext();) {
			Tree func = iter.next();
			FitnessResult fitResult = getFitnessValue(func);
			if (fitResult.isValid) {
				
				if (fitResult.fitnessValue < generationbestFitness) {
					generationbestFitness = fitResult.fitnessValue;
				}				
				if (fitResult.fitnessValue < overallBestFitness) {
					overallBestFitness = fitResult.fitnessValue;
					m_bestFit = func;
				}
				
				if (fitResult.fitnessValue == 0) //If the function we need to find is a more complex polynomial, consider changing this to something greater than zero 
				{
					System.out.println("----Found best fit tree--------");

					return false;
				}
			} else {
				iter.remove();
			}

		}

		return true;

	}

	public double getBestFitness() {
		return overallBestFitness;
	}

	public Tree getBestTree() {
		return m_bestFit;
	}

	/**
	 * Find the fitness value for a given tree
	 * 
	 * @param tree
	 * @return
	 */
	FitnessResult getFitnessValue(Tree tree) {
		FitnessResult result = new FitnessResult();
		GPConfig config = GPConfig.getInstance();
		List<TrainingDataPair> tdList = config.getTrainingData();
		/*
		 * for each point in training data, evaluate function at that point and
		 * find the difference. Sum the differences to get the overall fitness
		 */
		double fitness = 0;

		/*
		 * Todo possibly reconsider this behavior. May not want to throw out the
		 * whole function just because one evaluation involved division by zero.
		 * Will NEED to reconsider if the prof asks us to find y = 1/x
		 */
		for (TrainingDataPair pair : tdList) {
			EvaluationResult evalResult = evaluateFunction(tree,
					pair.getxValue());
			if (!evalResult.isValid) {
				result.isValid = false;
			}
			double diff = Math.abs(evalResult.yValue - pair.getyValue());
			fitness += diff;
		}

		tree.setFitnessValue(fitness);
		result.fitnessValue = fitness;

		return result;
	}

	/**
	 * Parse the tree if post order traversal and store the results in list of strings
	 * 
	 * @param node
	 * @param list
	 */
	void getPostOrderList(Node node, List<String> list) {
		if (node == null)
			return;

		getPostOrderList(node.getLeftNode(), list);
		getPostOrderList(node.getRightNode(), list);

		list.add(node.getData());
	}

	/**
	 * Parse the tree if post order traversal and store the results in list of nodes
	 * 
	 * Similar to the function above, but returns a list of nodes
	 * Need to use this function for crossover since we need more context than just the value at that node
	 * 
	 * @param node
	 * @param list
	 */
	public void getPostOrderNodeList(Node node, List<Node> list) {
		if (node == null)
			return;

		getPostOrderNodeList(node.getLeftNode(), list);
		getPostOrderNodeList(node.getRightNode(), list);

		list.add(node);
	}
	
	
	public void getInOrderNodeList(Node node,  List<String> list) {
		if (node == null)
			return;

		boolean useParen = false;
		if ((node.getLeftNode() != null) && (node.getRightNode() != null))
		useParen = true;
		
		if(useParen)
		list.add("(");
		
		getInOrderNodeList(node.getLeftNode(), list);
		list.add(node.getData());
		getInOrderNodeList(node.getRightNode(), list);
		
		if(useParen)
		list.add(")");
		
	}
}
