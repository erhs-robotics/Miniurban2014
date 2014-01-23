package org.erhsroboticsclub.libx.util;

import java.util.HashMap;
import java.util.Map;

/**
 * A continuous domain classifier that uses one dimensional Gaussians to 
 * describe feature probability distributions and the strict application of 
 * Bayes Rule to probabalisticaly determine the best label for a set of features.
 * The Bayes Classifier assumes conditional independance among features.
 * 
 * @author Michael Stevens
 */
 
public class NaiveBayes {
    
	/**
     * A Class that describes a data set with a one dimensional Gaussian
     */     
	public static class Gaus {
		private double mu, sigma2;
		
        /** 
         * @param mu The average of the data set
         * @param sigma2 The variance of the data set
         */         
		public Gaus(double mu, double sigma2) {
			this.mu = mu;
			this.sigma2 = sigma2;			
		}
		
        /**
         * Evaluates the probability that value x belongs to the data set
         * 
         * @param x The value to be tested
         * @return The value of the Gaussian defined by mu and sigma2 at point x
         */
		public double eval(double x) {
			double k = 1.0 / Math.sqrt(2 * Math.PI * sigma2);
			return k * Math.exp(-Math.pow(x - mu, 2) / (2 * sigma2));
		}
	}
    
	/**
     * Keeps track of the probability distributions and prior probability
     * for a label
     */
	public static class Label {
		int id;
		Map<Integer, Gaus> features;
		double prior;
		
        /**
         * @param id A unique identification number for the label
         * @param features A map relating each feature for the 
         *        label to a Gaussian distribution describing it
         * @param prior The probability that an observation belongs to a 
         *        label given no Gaussian information (should be based off of frequencies)
         */
		public Label(int id, Map<Integer, Gaus> features, double prior) {
			this.id = id;
			this.features = features;
			this.prior = prior;
		}
	}
	
	Map<Integer, Label> labels = new HashMap<>();
	
	public void addLabel(Label label) {
		labels.put(label.id, label);
	}
    
	/**
     * Calculates the numerator value for Bayes rule
     * @param z The set of feature observation
     * @param id The label to test the feature set against
     * @return A value porportional to the probability that z should be 
     *         labeled as id
     */
	private double posterior(Map<Integer, Double> z, int id) {
		Label label = labels.get(id);
		double p = label.prior;		
		for(int i : label.features.keySet()) {
			if(z.containsKey(i)) {
				Gaus gaus = label.features.get(i);
				p *= gaus.eval(z.get(i));
			}
		}
		return p;
	}
	
    /**
     * Classifies an observation with a given label
     * 
     * @param z The observation to be classified
     * @return The label that best fits the observation 
     */
	public int classify(Map<Integer, Double> z) {
		int id = -1;
		double maxp = 0;
		for(int i : labels.keySet()) {
			double p = posterior(z, i);
			if(p > maxp) {
				maxp = p;
				id = i;
			}
		}
		
		return id;
	}	
}
