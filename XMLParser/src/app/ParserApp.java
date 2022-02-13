package app;

import Exceptions.EmptyQueueException;
import utilities.XMLParser;
import java.lang.ArrayIndexOutOfBoundsException;

/**
 * 
 * Class Description: This class contains the main method to run the program.
 *
 * @author Saurav Adhikari (831561), Yun Ze Wei, Nevyn D'Souza, Yisong Wang
 *
 */
public class ParserApp {

	public static void main(String[] args) throws EmptyQueueException{
		
		try {
			new XMLParser(args[0]);
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Please enter a file path.");
		}
	}

}
