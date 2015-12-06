package ai;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import java.util.List;
import java.util.Scanner;

import subject.QuickSubject;
import subject.Subject;

/**
 * 
 * @author Maxwell
 *
 */
public class Main {
	/**The main class/method is a basic interface and wrapper for two different K Nearest neighbor testing, Leave One Out and normal  
	 * KNN. Passing one file will use Leave One Out. Two files will use the first file as the training set and the 2nd will
	 * be used as the test set. If 3 files were passed the 3rd will be written too in a data dump. 
	 * 
	 * @param arg
	 */
	public static void main(String arg[]){
		
		System.out.println("starting...");
		
		boolean isOutPut=false;
		if(arg==null || arg.length<1){
			System.out.println("Need at least one file");
			
			return;
		}
		try{
			if(arg.length==1){
				leaveOneOutTest(arg[0]);
				
			}else if(arg.length == 2 || arg.length ==3){
				if(arg.length ==3 && arg[2] !=null){
					System.setOut(new PrintStream(new File(arg[2])));
					isOutPut= true;
					
				}
				kNearestNeighbourTest(arg[0], arg[1]);
				
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		if(isOutPut){
			System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));		
		}
	
		System.out.println("...Ending");
	}
	
	/**A simple test with KNN with k =1 and k=3.
	 * 
	 * @param train
	 * @param test
	 */
	public static void kNearestNeighbourTest(String train, String test){

	try {
		Scanner test_scanner;
		test_scanner = new Scanner( new File(train));
	
		Scanner traning_scanner = new Scanner( new File(test));
		
	 AI<QuickSubject> ai = new AI<QuickSubject>();
		// List<IrisPlant> out =  ai.kNearestNeighbors(IrisPlant.phase(traning_scanner), IrisPlant.phaseInoreLabel(test_scanner),3,4);
		//System.out.println(out.toString());
	 List<QuickSubject> trainLi=QuickSubject.phase(traning_scanner); 
	 List<QuickSubject> testLi= QuickSubject.phase(test_scanner);
	 System.out.println("K: "+1);
	 List<QuickSubject> out1 = ai.kNearestNeighbors(trainLi,testLi, 1,4);
	 compareLists(testLi,out1);
	 System.out.println("K: "+3);
	 List<QuickSubject> out2 =ai.kNearestNeighbors(trainLi,testLi, 3,4);
		 
	 compareLists(testLi,out2);

		 
	 test_scanner.close();
	 traning_scanner.close();
	} catch (FileNotFoundException e) {
		
		e.printStackTrace();
	}
		
	}
	
	/**Print out user input options 
	 * 
	 */
	public static void readInput(){
		System.out.print("Enter something:");
		String input = System.console().readLine();
		if(input.equalsIgnoreCase("Help")){
			System.out.println("--------- PASS FILES--------");
			System.out.println("1 file: leaveOneOut");
			System.out.println("2 file: K-Nearest Neighbour");
			System.out.println("3 file: K-Nearest Neighbour with data dump");
						
		}
		
		
	}
	
	/**A leaveOneOutTest.
	 * 
	 * @param file
	 */
	public static void leaveOneOutTest(String file){
		File trainset = new File(file);
		//testset = new File(arg[1]);
		try {
		FileReader train_stream =null;
		
		Scanner traning_scanner;
	
		
	
			train_stream = new FileReader(trainset);
		
	
		
		//test_scanner = new Scanner(test_stream);
		traning_scanner = new Scanner(train_stream);
		
		 AI<QuickSubject> ai = new AI<QuickSubject>();
		// List<IrisPlant> out =  ai.kNearestNeighbors(IrisPlant.phase(traning_scanner), IrisPlant.phaseInoreLabel(test_scanner),3,4);
		//System.out.println(out.toString());
		 double p =ai.leaveOneOut(QuickSubject.phase(traning_scanner), 2,3);
		 System.out.println("Match " +p);
		 train_stream.close();
		 traning_scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**Checks two sets to see how much they match. 
	 * 
	 * @param in
	 * @param out
	 */	
	public static void compareLists(List<? extends Subject> in, List<? extends Subject> out){
		int n=0;
		for(int i=0; i<in.size()&& i<out.size();i++){
			if(in.get(i).label().equals(out.get(i).label())){
				n++;
			}
			
		}
		System.out.println("Size of Test " + in.size());
		System.out.println("Number of Matchs " + n);
		System.out.println(((double)n)/((double)in.size()));
}
	
}
