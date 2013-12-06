package gp_project;

public class TrainingDataPair {

	private double xValue;

	private double yValue;

	public double getxValue() {
		return xValue;
	}

	public double getyValue() {
		return yValue;
	}

	public void setxValue(double d) {
		this.xValue = d;
	}

	public void setyValue(double yValue) {
		this.yValue = yValue;
	}

	@Override
	public String toString() {

		StringBuilder result = new StringBuilder();

		result.append("X:" + xValue + ",");
		result.append("Y:" + yValue);

		return result.toString();
	}
}
