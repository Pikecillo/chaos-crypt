package ccrypt.cmn;

/**
 * Some of the usual chaotic maps.
 */
public class Maps {

	/**
	 * Logarithmic map: x_{i+1} = b + ln(|x_{i}|).
	 * This map is chaotic with no periodic windows for b in the
	 * interval (-1, 1).
	 */
	public static class Logarithmic implements ChaoticMap {

		private double b;

		/**
		 * Create and instance of the logarithmic map.
		 *
		 * @param p Translation parameter.
		 */
		public Logarithmic(double p){
			b = p;
		}

		/**
		 * Evaluate logarithmic map given the current state.
		 *
		 * @param x Current state.
		 * @return Next state.
		 */
		public double eval(double x){
			return b + Math.log(Math.abs(x));
		}
	}

	/**
	 *  Logistic map: x_{i+1} = a * x_{i} * (1 - x_{i}).
	 *	This map is chaotic with periodic windows (i. e. for most values)
	 *	for a in the interval (3.57, 4).
	 */
	public static class Logistic implements ChaoticMap {
		private double a;

		/**
		 * Create and instance of the logistic map.
		 *
		 * @param p Scale parameter.
		 */
		public Logistic(double p){
			a = p;
		}

		/**
		 * Evaluate logistic map given the current state.
		 *
		 * @param x Current state.
		 * @return Next state.
		 */
		public double eval(double x){
			return a * x * (1 - x);
		}
	}
}
