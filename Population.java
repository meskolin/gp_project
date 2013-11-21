package gp_project;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Population {

	private List<Tree> m_functions = new LinkedList<Tree>();

	public void generateFirstPopulation() {
		int numIndividuals = GPConfig.getInstance().getSizePopulation();
		FunctionGenerator fGen = new FunctionGenerator();

		// Generate half the population using Full method
		for (int i = 0; i < numIndividuals / 2; i++) {
			Tree tree = fGen.GenerateFullTree(GPConfig.getInstance()
					.getMaxDepth());
			m_functions.add(tree);
		}

		// Generate half the population using Grow method
		for (int i = 0; i < numIndividuals / 2; i++) {
			Tree tree = fGen.GenerateGrowTree(GPConfig.getInstance()
					.getMaxDepth());
			m_functions.add(tree);

		}

	}

	List<Tree> getTrees() {

		return m_functions;
	}

	public void pruneLowFitnessTrees() {
		// Sort trees in order with High Error (low fitness) first
		Collections.sort(m_functions);

		double percentToEliminate = GPConfig.getInstance()
				.getEliminatePercent();
		long numToPrune = Math.round(percentToEliminate * m_functions.size());

		Iterator<Tree> iter = m_functions.iterator();
		int deleted = 0;

		while (iter.hasNext() && (numToPrune > deleted)) {
			iter.next();
			iter.remove();
			deleted++;
		}

	}

	public void regenerate() {
		FunctionGenerator fGen = new FunctionGenerator();

		int desiredSize = GPConfig.getInstance().getSizePopulation();
		int currentSize = m_functions.size();
		int numToGenerate = desiredSize - currentSize;

		// Generate half the population using Full method
		for (int i = 0; i < numToGenerate / 2; i++) {
			Tree tree = fGen.GenerateFullTree(GPConfig.getInstance()
					.getMaxDepth());
			m_functions.add(tree);
		}

		// Generate half the population using Grow method
		for (int i = 0; i < numToGenerate / 2; i++) {
			Tree tree = fGen.GenerateGrowTree(GPConfig.getInstance()
					.getMaxDepth());
			m_functions.add(tree);

		}

	}
}
