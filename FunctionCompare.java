package gp_project;

import java.util.List;

public class FunctionCompare {
	
	boolean getFitnessValue(Tree tree, double fitness)
	{
		GPConfig config = GPConfig.getInstance();
		List<TrainingDataPair> tdList = config.getTrainingData();
		/*
		for each point in training data, evaluate function at that point and find the difference. Sum the differences to get the overall fitness
		*/ 
		fitness = 0;
		boolean isValid;
		
		
		/*
		 *  Todo possibly reconsider this behavior. May not want to throw out the whole function just
		 *  because one evaluation involved division by zero. Will NEED to reconsider if the prof asks us to find y = 1/x
		 */
		for (TrainingDataPair pair : tdList)
		{	
				FunctionEvaluator eval = new FunctionEvaluator();
				double funcYValue = 0;
				isValid = eval.evaluateFunction(tree, pair.getxValue(), funcYValue);
				if(!isValid)
				{
					return false;
				}
				double diff = Math.abs(funcYValue - pair.getyValue());
				fitness += diff;
		}
		
		return true;
	}
}
