package gp_project;

import java.util.List;

public class FunctionCompare {
	
	FitnessResult getFitnessValue(Tree tree)
	{
		FitnessResult result = new FitnessResult();
		GPConfig config = GPConfig.getInstance();
		List<TrainingDataPair> tdList = config.getTrainingData();
		/*
		for each point in training data, evaluate function at that point and find the difference. Sum the differences to get the overall fitness
		*/ 
		double fitness = 0;
		
		
		/*
		 *  Todo possibly reconsider this behavior. May not want to throw out the whole function just
		 *  because one evaluation involved division by zero. Will NEED to reconsider if the prof asks us to find y = 1/x
		 */
		for (TrainingDataPair pair : tdList)
		{	
				FunctionEvaluator eval = new FunctionEvaluator();
				EvaluationResult evalResult = eval.evaluateFunction(tree, pair.getxValue());
				if(!evalResult.isValid)
				{
					result.isValid = false;
				}
				double diff = Math.abs(evalResult.yValue - pair.getyValue());
				fitness += diff;
		}

		result.fitnessValue = fitness;
		
		return result;
	}
}
