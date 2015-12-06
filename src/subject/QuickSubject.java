package subject;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//import static java.lang.Math.abs;
/**A quick an easy subject class that an be customized to any "subject" with "X" amount of attributes and a single label.    
 * Note that there is a danger of comparing two @Name of different number of attributes. 
 * 
 * @author Maxwell
 *
 */
public class QuickSubject implements Subject{

	public final int numAttribute;
	private  String label;
	private boolean isLabeled;
	final double[] attributes;
	
	public QuickSubject(double[] attributes){
		this.attributes=attributes;
		numAttribute = attributes.length;
		isLabeled=false;
	}
	
	public QuickSubject(double[] attributes, String label){
		this.attributes=attributes;
		numAttribute = attributes.length;
		this.label=label;
		isLabeled=true;
	}
	public QuickSubject(double sepalLength, double sepalWidth, double petalLength, double petalWidth, String label){
//		this.sepalLength =sepalLength; 
//		this.sepalWidth=sepalWidth;
//		this.petalLength = petalLength;
//		this.petalWidth = petalWidth;
		attributes = new double[4];
		attributes[0] =sepalLength;
		attributes[1] =sepalWidth;
		attributes[2] =petalLength;
		attributes[3] =petalWidth;
		this.label = label;
		this.isLabeled=true;
		numAttribute = attributes.length;
	}
	
	
	
	public QuickSubject(double sepalLength, double sepalWidth, double petalLength, double petalWidth){
		attributes = new double[4];
		attributes[0] =sepalLength;
		attributes[1] =sepalWidth;
		attributes[2] =petalLength;
		attributes[3] =petalWidth;
		this.isLabeled =false;
		numAttribute = attributes.length;
	}
	
	
	@Override
	public double  distance(Subject b, double[] r) {
		
		//TODO use Math abs instead of sq and s 
		double rt=0;
		for(int i=0;i<numAttribute;i++){
			rt =+Math.abs(this.attributes[i] - b.getAttribute(i))/r[i];
			
		}
		return rt;
	}

	public double  distance(Subject b, double[] r, double[] w) {
		
		//TODO use Math abs instead of sq and s 
		
		double rt=0;
		for(int i=0;i<numAttribute;i++){
			rt =+w[i]*Math.abs(this.attributes[i] - b.getAttribute(i))/r[i];
			
		}
		return rt;
			
//		return Math.sqrt(
//				pow(w[0] *(this.attributes[0] - b.attributes[0]),2)	/pow(r[0],2)+
//				pow(w[1] *(this.attributes[0] - b.attributes[1]),2)	/pow(r[1],2)+
//				pow(w[2] *(this.attributes[0] - b.attributes[2]),2) /pow(r[2],2)+
//				pow(w[3]* (this.attributes[0] - b.attributes[3]),2)	/pow(r[3],2)			
//				)
//				;
	}
	

	
	@Override
	public String label() {
	
		return label;
	}


	@Override
	public void setLabel(String label){
		this.label = label;
		isLabeled = label != null;
	}
	
	
	@Override
	public boolean isLabeled(){
		return isLabeled;
		
	}
	

	@Override
	public double getAttribute(int i){
		
		return attributes[i];
				
	}
	
	@Override
	public int attributeNum() {
		
		return numAttribute;
	}

	
	@Override
	public String toString(){
		
		return "spepalLength :"+ attributes[0] +
				" sepalWidth :"+ attributes[1]  +
				" petalLength :"+attributes[2]+
				" petalWidth :"+ attributes[3]+
				label;
		
	}
	@Override 
	public Subject clone(){
		return new QuickSubject(attributes, new String(label));
	}
	
	@Override
	public boolean equals(Object ob){
		if(ob instanceof QuickSubject){
			QuickSubject ir = (QuickSubject) ob;
			if(ir.numAttribute == this.numAttribute){
				if(ir.isLabeled != this.isLabeled){
					return false;
				}
				
				if(ir.isLabeled == this.isLabeled){
					return ir.label().equals(this.label());
				}
				
				for(int i=0; i< this.numAttribute;i++){
					if(ir.attributes[i] != this.attributes[i]){
						return false;
					}
					
				}
			
				return true;
			}
		}
		return false;
		
		
	}
	/**Originally designed for Iris plants, could be used for any thing that follows this file format 
	 * 
	 * 		double, double, double, double, string
	 * 
	 * Phase takes a scanner and create a list of QuickSubject.  
	 * 
	 * @param scanner
	 * @return
	 */
	public static  List<QuickSubject> phase(Scanner scanner){		
		 List<QuickSubject> li  = new ArrayList<>();
		 scanner.useDelimiter(" ");
		 
		
		 
		mainLoop: while(scanner.hasNext()){
		
			 while(!scanner.hasNextDouble() && !scanner.hasNextFloat()){
				 scanner.next();
				 if(!scanner.hasNext()){
					 break mainLoop;//ends loop
				 }
			 }
		
			 double sepalLength = scanner.nextDouble();	
			
			 while(!scanner.hasNextDouble() && !scanner.hasNextFloat()){
				 scanner.next();
				 if(!scanner.hasNext()){
					 break mainLoop;
				 }
			 }
			 double sepalWidth  = scanner.nextDouble();
			 while(!scanner.hasNextDouble() && !scanner.hasNextFloat()){
				 scanner.next();
				 if(!scanner.hasNext()){
					 break mainLoop;
				 }
			 }
			 double petalLength = scanner.nextDouble();
			 while(!scanner.hasNextDouble() && !scanner.hasNextFloat()){
				 scanner.next();
				 if(!scanner.hasNext()){
					 break mainLoop;
				 }
			 }
			
			 double petalWidth  = scanner.nextDouble();
			
			 if(!scanner.hasNextLine()){
				 break;
			 }
			 String label = scanner.nextLine().substring(1);

			 //System.out.println(sepalLength+" "+sepalWidth+" "+petalLength+" "+petalWidth+" "+label);
			 
			 li.add(new QuickSubject(sepalLength,sepalWidth,petalLength, petalWidth, label));
		 }
		  
		  
		  scanner.close();
		  
		  
		  
		  return li;
}
	/**Like phase but ignored.  
	 * 
	 * @param scanner
	 * @return
	 */
	public static  List<QuickSubject> phaseIgnoredLabel(Scanner scanner){		
		 List<QuickSubject> li  = new ArrayList<>();
		 scanner.useDelimiter(" ");
		 
		 
		mainLoop: while(scanner.hasNext()){
			 while(!scanner.hasNextDouble() && !scanner.hasNextFloat()){
				 scanner.next();
				 if(!scanner.hasNext()){
					 break mainLoop;
				 }
			 }
			 double sepalLength = scanner.nextDouble();	
			 
			 while(!scanner.hasNextDouble() && !scanner.hasNextFloat()){
				 scanner.next();
				 if(!scanner.hasNext()){
					 break mainLoop;
				 }
			 }
			 
			 double sepalWidth  = scanner.nextDouble();
			 while(!scanner.hasNextDouble() && !scanner.hasNextFloat()){
				 scanner.next();
				 if(!scanner.hasNext()){
					 break mainLoop;
				 }
			 }
			 double petalLength = scanner.nextDouble();
			
			 while(!scanner.hasNextDouble() && !scanner.hasNextFloat()){
				 scanner.next();
				 if(!scanner.hasNext()){
					 break mainLoop;
				 }
			 }
			 double petalWidth  = scanner.nextDouble();
			
			 if(!scanner.hasNextLine()){
				 break;
			 }
			scanner.nextLine();

			 //System.out.println(sepalLength+" "+sepalWidth+" "+petalLength+" "+petalWidth+" "+label);
			 
			 li.add(new QuickSubject(sepalLength,sepalWidth,petalLength, petalWidth));
		 }
		  
		  
		  scanner.close();
		  
		  
		  
		  return li;
}
/**Like phase but does not expect a label to be given per object.
 * 
 * @param scanner
 * @return
 */
	public static  List<QuickSubject> phaseWithOutLabel(Scanner scanner){		
		 List<QuickSubject> li  = new ArrayList<>();
		 scanner.useDelimiter(" ");
	
		 
		mainLoop: while(scanner.hasNext()){
			 while(!scanner.hasNextDouble() && !scanner.hasNextFloat()){
				 scanner.next();
				 if(!scanner.hasNext()){
					 break mainLoop;
				 }
			 }
			 double sepalLength = scanner.nextDouble();	
			 
			 while(!scanner.hasNextDouble() && !scanner.hasNextFloat()){
				 scanner.next();
				 if(!scanner.hasNext()){
					 break mainLoop;
				 }
			 }
			 
			 double sepalWidth  = scanner.nextDouble();
			 
			 while(!scanner.hasNextDouble() && !scanner.hasNextFloat()){
				 scanner.next();
				 if(!scanner.hasNext()){
					 break mainLoop;
				 }
			 }
			 double petalLength = scanner.nextDouble();
			
			 while(!scanner.hasNextDouble() && !scanner.hasNextFloat()){
				 scanner.next();
				 if(!scanner.hasNext()){
					 break mainLoop;
				 }
			 }
			 double petalWidth  = scanner.nextDouble();
 
			 li.add(new QuickSubject(sepalLength,sepalWidth,petalLength, petalWidth));
		 }
		  
		  
		  scanner.close();
		  
		  
		  
		  return li;
}
	
	public static double[] atributeRange(List<QuickSubject> li){
			int numAttribute = li.get(0).numAttribute;	
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
