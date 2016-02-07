package ccrypt.cmn;

/*
    Some of the usual chaotic maps.
*/
public class Maps {

    /*
	Interface for a one dimensional chaotic map
    */
    public interface OneDimensionalMap {

	/*
	    The implementation of this method should
	    provide the state of the map for the subsequent
	    time step
	*/
	public double function(double x);
    }

    /*
	Representation of the logarithmic map:
	    x_{i+1} = b + ln(|x_{i}|)
	This map is chaotic with no periodic windows
	for b in the interval (-1, 1)
    */
    public static class Logarithmic implements OneDimensionalMap {
	// Parameter of the map
	private double b;

	public Logarithmic(double p){
	    b = p;
	}

	public double function(double x){
	    return b + Math.log(Math.abs(x));
	}
    }

    /*
	Representation of the logistic map:
	    x_{i+1} = a * x_{i} * (1 - x_{i})
	This map is chaotic with periodic windows (i. e. for most values)
	for a in the interval (3.57, 4)
    */
    public static class Logistic implements OneDimensionalMap {
	private double a;

	public Logistic(double p){
	    a = p;
	}

	public double function(double x){
		return a * x * (1 - x);
	}
    }
}
