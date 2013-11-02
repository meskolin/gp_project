package gp_project;

import java.util.List;

public class FunctionCompare {
	
	double getFitnessValue(Tree tree)
	{
		GPConfig config = GPConfig.getInstance();
		List<TrainingDataPair> tdList = config.getTrainingData();
		/*
		for each point in training data, evaluate function at that point and find the difference. Sum the differences to get the overall fitness
		*/ 
		int totalError = 0;
		
		for (TrainingDataPair pair : tdList)
		{	
				FunctionEvaluator eval = new FunctionEvaluator();
				double funcYValue = eval.evaluateFunction(tree, pair.getxValue());
				double diff = Math.abs(funcYValue - pair.getyValue());
				totalError += diff;
		}
		return totalError;
	}
}
