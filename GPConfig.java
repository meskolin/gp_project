package gp_project;

import java.util.LinkedList;
import java.util.Random;
import java.util.List;
import java.util.Arrays;
import java.util.logging.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GPConfig {

	private static GPConfig instance = null;

	private Random m_rnd;
	
	protected GPConfig() {
		 m_rnd = new Random(System.currentTimeMillis());
	}

	public static GPConfig getInstance() {
		if (instance == null) {
			instance = new GPConfig();
		}
		return instance;
	}
	
	public Random getRand()
	{
		return m_rnd;		
	}

	public int getMaxDepth()
	{
		return 1;
	}
	
	public int getSizePopulation()
	{
		//todo read from config
		return 30;
		
	}
	
	public double getMutatePercent()
	{
		double mutatePercent = 0.5; //todo read from config
		return mutatePercent; 
	}
	
	public double getCrossoverPercent()
	{
		double crossPercent = 0.5; //todo read from config
		return crossPercent; 
	}
	
	public String getRandOperator() {
		if (m_rnd != null) {
			
			// todo read this in
			List<String> operators = Arrays.asList("-", "/", "*");
			int numOperators = operators.size();
	
			int index = m_rnd.nextInt(numOperators - 1);
	

			if (index < 0 || index >= numOperators) {
				throw new IllegalArgumentException("INVALID");
			}

			String oper = operators.get(index);
			return oper;
		}

		return "0";
	}

	public String getRandOperand() {
		// todo read this in

		List<String> operands = Arrays.asList("1", "2", "x");
		int numoperands = operands.size();
		int index = m_rnd.nextInt(numoperands);
		
		if (index < 0 || index >= numoperands) {
			throw new IllegalArgumentException("INVALID");
		}
		
		return operands.get(index);
	}
	
	public String getRandOperatorOrOperand()
	{
		
		int flipCoin = m_rnd.nextInt(2);
		if (flipCoin == 1)
		{
			return getRandOperand();
		}
		else
		{
			return getRandOperator();
		}
	}
	
	List<TrainingDataPair> getTrainingData()
	{
		
		List<TrainingDataPair> list = new LinkedList<TrainingDataPair>();
		String line = null;
		
		try
		{
			//Print out for debugging in case we can't find the training data file
			//  System.out.println("Working Directory = " +
		    //          System.getProperty("user.dir"));
			BufferedReader reader = new BufferedReader(new FileReader("simple_data.txt"));
			
			
			while ((line = reader.readLine()) != null) 
			{
				TrainingDataPair pair = new TrainingDataPair();
				String[] parts = line.split("\\s");
				if(parts.length != 2)
				{
					throw new RuntimeException("Unexpected characters found in training data file");
				}
							
				pair.setxValue(Integer.parseInt(parts[0]));
				pair.setyValue(Double.parseDouble(parts[1]));
				list.add(pair);
			}
		}
		catch(IOException ex)
		{
			System.out.println(ex);
			System.out.println("Exception during training data file read");
		}
		return list;
	}
}
