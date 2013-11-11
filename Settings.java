package gp_project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Settings {

	private static String mutateString = "MUTATE_PERCENT";
	private static String crossoverString = "CROSS_PERCENT";
	private static String eliminateString = "ELIMINATE_PERCENT";
	private static String populationString = "POPULATION_SIZE";
	private static String maxDepthString = "MAX_DEPTH";
	
	private double mutatePercent = 0.2;
	private double crossoverPercent = 0.3;
	private double eliminatePercent = 0.2;
	private int populationSize = 100;
	private int maxDepth = 3;
	
	public Settings() {
		
		parseSettings();
	}
	
	public void parseSettings()
	{
		String line = null;
		BufferedReader reader;
		try
		{
			 reader = new BufferedReader(new FileReader("settings.txt"));
			
			
			while ((line = reader.readLine()) != null) 
			{
				String[] parts = line.split("\\s");
				if(parts.length != 2)
				{
					reader.close();
					throw new RuntimeException("Unexpected characters found in training data file");
				}
							
				if(parts[0].equals(mutateString))
				{
					mutatePercent = Double.parseDouble(parts[1]);
					
				}
				else if(parts[0].equals(crossoverString))
				{
					crossoverPercent = Double.parseDouble(parts[1]);
					
				}
				else if(parts[0].equals(eliminateString))
				{
					eliminatePercent = Double.parseDouble(parts[1]);
					
				}
				else if(parts[0].equals(populationString))
				{
					populationSize = Integer.parseInt(parts[1]);
					
				}
				else if(parts[0].equals(maxDepthString))
				{
					maxDepth = Integer.parseInt(parts[1]);
					
				}
				else
				{
					reader.close();
					throw new RuntimeException("Unknown keyword found in settings file");
					
				}
			}
			reader.close();
		}
		catch(IOException ex)
		{
			System.out.println(ex);
			System.out.println("Exception during training data file read");
		

		}
		
	}
	
	public double getMutatePercent() {
		return mutatePercent;
	}
	public void setMutatePercent(double mutatePercent) {
		this.mutatePercent = mutatePercent;
	}
	public double getCrossoverPercent() {
		return crossoverPercent;
	}
	public void setCrossoverPercent(double crossoverPercent) {
		this.crossoverPercent = crossoverPercent;
	}
	public int getPopulationSize() {
		return populationSize;
	}
	public void setPopulationSize(int populationSize) {
		this.populationSize = populationSize;
	}
	public int getMaxDepth() {
		return maxDepth;
	}
	public void setMaxDepth(int maxDepth) {
		this.maxDepth = maxDepth;
	}

	public double getEliminatePercent() {
		return eliminatePercent;
	}

	public void setEliminatePercent(double eliminatePercent) {
		this.eliminatePercent = eliminatePercent;
	}
	
}
