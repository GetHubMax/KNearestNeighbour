package ai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import subject.*;
/**@Name  is a modifeid factory implication of the k-nearest neighbors machine learning algorithm. 
 * @author Maxwell
 *
 */
public  class AI<E extends Subject>{

	
	@SuppressWarnings("unchecked")
	/**Using KNN, this method will return a labeled classification of the test list using the training set.      
	 * 
	 * @param training
	 * @param test
	 * @param k 
	 * @param numlabel
	 * @return 
	 */
	public  List<E> kNearestNeighbors ( List<E> training, List<E> test, int k,int numlabel){
				
			List<E> out = new ArrayList<>();//will be returned
			
			for(E subject: test){
				Map<String, Integer> labelCount = new HashMap<String, Integer>(numlabel);
				String label = null;
				List<Wrapper<E>> wl = new ArrayList<>();
				//Create a list of the distance to all of the training set to current  subject
				for(Subject b: training){
					wl.add(new Wrapper<E>(subject.distance(b, attributesRange(training)), b.label()));
				}
				//will sort the closest subjects to front of the list
				Collections.sort(wl);				
				
				//Count 
				for(int i=0; i<k;i++){
					String t_label = wl.get(i).label;
					if(labelCount.containsKey(t_label)){
						labelCount.put(t_label,	labelCount.get(t_label)+1 );// count++
	
					}else{
						labelCount.put(t_label,	1 );		
					}
					//System.out.println(wl.get(i).toString());
				}
				int hightest = Integer.MIN_VALUE;
				
				for(Entry<String, Integer> e: labelCount.entrySet()){
					if(e.getValue()> hightest){
						label = e.getKey();
						hightest = e.getValue();
					}
				}
				
				
				//subject.setLabel(label);
				E ip = (E)subject.clone();
						///new QuickSubject(subject.getAttribute(0), subject.getAttribute(1), subject.getAttribute(2), subject.getAttribute(3)); 
				ip.setLabel(label);
				//System.out.println(ip.toString());
				out.add(ip);
			}

		return out;
	}
	
	/**Returns the effectives of the leaveOneOut algorithm.  
	 * 
	 * @param training
	 * @param k
	 * @param numlabel
	 * @return
	 */
	public double leaveOneOut(List<E> training, int k, int numlabel){
		int match =0;
		for(int i=0; i<training.size();i++){
			E tmp = training.remove(i);
			String ans = tmp.label();
			List<E> tmpLs = new ArrayList<>();
			tmpLs.add(tmp);
			List<E> test = kNearestNeighbors(training,tmpLs, k,numlabel);
			training.add(i, tmp);
			if(test.get(0).label().equals(ans)){
				match += 1;
			}
		}
		
		//return 100 -(training.size()/ match); 
		
		System.out.println("Size of Training: " + training.size());
		System.out.println("Number of Success: " + match);
		double out = (double)(match/(double)training.size())*100; 
		return out;
	}
	/**Return the range of attributes of the list.
	 * 
	 * @param li
	 * @return
	 */
	public static double[] attributesRange(List<? extends Subject> li){
		int numAttribute = li.get(0).attributeNum();	
		double min= Double.MAX_VALUE;
		double max= Double.MIN_VALUE;			
		double range[] = new double[numAttribute];
		
		
		
		for(int i =0; i<numAttribute;i++){
			for(int a=0;a<li.size();a++){
				if(li.get(a).getAttribute(i)< min){
					min =li.get(a).getAttribute(i);
					
				}
				
				if(li.get(a).getAttribute(i)> max){
					max =li.get(a).getAttribute(i);
					
				}
			
			}
			range[i] = max -min;

		}	
		return range;
	
	
}
	
	
	
	
}
	/**A simple object the extends comparable use to order the distance of labels.  
	 * 
	 * @author Maxwell
	 *
	 * @param <E>
	 */
	
	@SuppressWarnings("rawtypes")
	class Wrapper<E extends Subject> implements Comparable{
		public final double d;
		public final String label;
		Wrapper(double d, String label){
			this.label=label;
			this.d=d;
		}
		@Override
		public boolean equals(Object o){
			if(o instanceof Wrapper){
				Wrapper other = (Wrapper) o;
				return d == other.d && label.equals(other.label);
				
			}
			
			return false;
		}
		@Override
		public int compareTo(Object o) {
			if(o instanceof Wrapper){
				Wrapper other = (Wrapper) o;
				return (int) (d*1000-other.d*1000);

			}
			return 0;
		}
		
		
		@Override
		public String toString(){
			
			return label+" d:"+d+"";
		}
		
		
		
	}
	