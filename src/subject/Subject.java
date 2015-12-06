package subject;


/** This interface is used by AI for its subjects. 
 * 
 * @author Maxwell
 *
 */
public interface Subject {

	
	/** The distance or different between this subject and subject target.  
	 *  r is the range or max values of an attribute used for normalizing.   
	 * @param target
	 * @param r
	 * @return distance
	 */
	public abstract double distance(Subject target, double[] r);

	/**The label of a subject. 
	 * 
	 * @return label
	 */
	public abstract String label();

	/**Sets the subject label. 
	 * 
	 * @param label
	 */
	public abstract void setLabel(String label);
	
	/**Label may be null. This is for when it is unknown. Use this method to check if its null or not.
	 * 
	 * 
	 */
	public abstract boolean isLabeled();

	/**Get a value at i location. 
	 * 
	 * @param i
	 * @return isLabeled
	 */
	public abstract double getAttribute(int i);

	/**The length or number of attributes.  
	 * 
	 * @return attributeNum
	 */
	public abstract int attributeNum();
	
	
	/**
	 * 
	 * @return string
	 */
	public abstract String toString();
	
	
	/**Returns a new instances that as the same values.
	 * 
	 * @return clone
	 */
	public Subject clone();
	

}