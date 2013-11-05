package gp_project;


import java.util.Random;
import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GPConfig {

	private static GPConfig instance = null;

	private static List<TrainingDataPair> m_trainingData;
	
	private Random m_rnd;
	
	protected GPConfig() {
		 m_rnd = new Random(System.currentTimeMillis());
		 m_trainingData = parseTrainingData();
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
		return 3;
	}
	
	public int getSizePopulation()
	{
		//todo read from config
		return 100;
		
	}
	
	public double getMutatePercent()
	{
		double mutatePercent = 0.2; //todo read from config
		return mutatePercent; 
	}
	
	public double getCrossoverPercent()
	{
		double crossPercent = 0.3; //todo read from config
		return crossPercent; 
	}
	
	public double getPrunePercent()
	{
		double prunePercent = 0.2 ; // todo read from config
		return prunePercent;
	}
	public String getRandOperator() {
		if (m_rnd != null) {
			
			// todo read this in
			List<String> operators = Arrays.asList("-", "/", "*");
			int numOperators = operators.size();
	
			int index = m_rnd.nextInt(numOperators);
	

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
	
	public List<TrainingDataPair> getTrainingData()
	{
		return m_trainingData;
	}
	
	private List<TrainingDataPair> parseTrainingData()
	{
		
		List<TrainingDataPair> list = new LinkedList<TrainingDataPair>();
		String line = null;
		BufferedReader reader;
		try
		{
			//Print out for debugging in case we can't find the training data file
			//  System.out.println("Working Directory = " +
		    //          System.getProperty("user.dir"));
			 reader = new BufferedReader(new FileReader("simple_data.txt"));
			
			
			while ((line = reader.readLine()) != null) 
			{
				TrainingDataPair pair = new TrainingDataPair();
				String[] parts = line.split("\\s");
				if(parts.length != 2)
				{
					reader.close();
					throw new RuntimeException("Unexpected characters found in training data file");
				}
							
				pair.setxValue(Integer.parseInt(parts[0]));
				pair.setyValue(Double.parseDouble(parts[1]));
				list.add(pair);
			}
			reader.close();
		}
		catch(IOException ex)
		{
			System.out.println(ex);
			System.out.println("Exception during training data file read");
		

		}
		return list;
	}
}
