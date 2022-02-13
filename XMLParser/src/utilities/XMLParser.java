package utilities;

import java.io.*;
import java.util.EmptyStackException;

import Exceptions.EmptyQueueException;

/**
 * 
 * Class Description: Class for the XML Parser. This class finds all the errors in a XML 
 * document.
 *
 * @author Saurav Adhikari (831561), Yun Ze Wei, Nevyn D'Souza, Yisong Wang
 *
 */
public class XMLParser {

	private MyStack<String> stack;
	private MyQueue<String> errorQ;
	private MyQueue<String> extrasQ;
	private int lineNumber = -1;
	private File filePathForPrint;
	private int tempCounter = 0;

	/**
	 * Empty constructor
	 */
	public XMLParser() {

	}

	/**
	 * constructor with supplied file path to read from
	 * 
	 * @param filePath
	 *            path to the xml file
	 */
	public XMLParser(String filePath) throws EmptyQueueException {

		stack = new MyStack<>();
		errorQ = new MyQueue<>();
		extrasQ = new MyQueue<>();

		File file = new File(filePath);
		filePathForPrint = new File(filePath);
		parseFile(file);
		System.out.println("All Errors in the XML document are listed below");
		System.out.println("-----------------------------------------------");
		reportErrors();
	}

	/**
	 * This method reads a error tag and returns the complete line for that error with the line number
	 * @param file the file object 
	 * @param Error the error text substring
	 * @return the complete line for the error with the line number
	 */
	private String parseFileForPrint(File file, String Error) {
		String checkLine  = " ";
		
		try {
			
			//read in the file
			FileReader fr;
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			
			//counter to skip lines previously reached
			if (tempCounter != 0) {
				for (int i = 0; i < tempCounter; i++) {
					br.readLine();
				}
			}
			
			//loop the file and find a line at a time
			while ((checkLine = br.readLine()) != null) {
				tempCounter++;
				
				//find the substring in the stack and queue containing the text for the error
				String errorTag = Error.substring(Error.indexOf('<'), Error.length()-1);
				
				//find the line number for the error
				Integer lineofError = Integer.parseInt(Error.substring(0, Error.indexOf('<')));

				//if the line of error matches the line of current iteration return the whole line
				if (tempCounter == lineofError && checkLine.contains(errorTag)) {

					return lineofError + " " + checkLine.trim();
				}
				
			}
				
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Error;
		
	}

	/**
     * This method parses the sample xml files
     * it will read the sample xml files line by line
     * it will check if the tag can be ignored or not
     * @param file file to parse
     */
	private void parseFile(File file) {
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			String checkLine;
			char charCheck;
			String charsRead = "";
			boolean isTag = false;
			boolean isSpace = false;

			// read in the whole xml file line by line
			while ((checkLine = br.readLine()) != null) {
				lineNumber++;
				charsRead = "";
				isTag = false;
				isSpace = false;

				for (int i = 0; i < checkLine.length(); i++) {
					// check to see if the tags can be ignored
					if (ignoreTag(checkLine)) {
						break;
					}

					charCheck = checkLine.charAt(i);

					// read < along with the tag name and stop once a space is found
					// after the tag name
					if (charCheck == '<') {
						isTag = true;
					}
					if (isTag && !isSpace) {
						if (checkLine.charAt(i) == ' ') {
							isSpace = true;
						}
						charsRead = charsRead + checkLine.charAt(i);
					}

					// read > and add it to the tag name only if its not a self closing
					// tag
					if (charCheck == '>') {
						if (charsRead.endsWith(" ")) {
							charsRead = charsRead.substring(0, charsRead.length() - 1) + ">";
						}
						if (!(charsRead.endsWith(">"))) {
							charsRead = charsRead + checkLine.charAt(i);
						}

					}

				}
				startTagCheck(charsRead);

				if (charsRead.startsWith("</")) {
					endTagCheck(charsRead);
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	  /**
     * This method is for certain tags that will be ignored as an error.
     * For the self closing tags it will be ignored as errors
     * and for tags that are in the following format <?xml somedata=”data”?> will be ignored as well.
     * 
     * @param tag
     *            xml line to be checked
     * @return returns true if the xml line can be ignored
     */
	private boolean ignoreTag(String tag) {
		tag = tag.replaceAll("\\s", "");
		// ignore tag of the following format: <?xml somedata=”data”?> continue
		// processing
		if (tag.startsWith("<?") && tag.endsWith("?>")) {
			return true;
		}
		// ignore self closing tags
		if (tag.startsWith("<") && tag.endsWith("/>")) {
			return true;
		}
		return false;
	}
	
	/**
	 * This method checks the start tag in a line
	 * @param tag the tag to be checked
	 */
	private void startTagCheck(String tag) {
		boolean rootTag = false;

		if (tag.startsWith("<") && !tag.startsWith("</")) {
			if (stack.size() == 0 && !rootTag) {
				rootTag = true;
			} else if (stack.size() == 0 && rootTag) {
				System.out.println("ERROR: only one root tag is allowed " + tag);
			}

			stack.push(tag);
		}

	}


	  /**
	     * This method checks the end tag
	     * if it matches the top of stack it will pop the stack.
	     * if the end tag matches the head of errorQ it will dequeue and ignore.
	     * and if the stack is empty it will add to errorQ.
	     * if it doesn't match it will search the stack for a matching start tag,
	     * if it matches the start tag it will pop each E from stack into errorQ until match and report it as error.
	     * and if it doesn't find a match in stack it will add E to extraQ
	     * @param charsRead
	     */
	private void endTagCheck(String charsRead) {
		try {

			String charsNoBackSlash = charsRead.replaceAll("/", "");

			if (!stack.isEmpty() && charsNoBackSlash.equals(stack.peek())) {
				// pop stack
				stack.pop();
			}

			// Else if head of errorQ matches, dequeue and ignore
			else if (!errorQ.isEmpty() && charsNoBackSlash.equals(errorQ.peek())) {
				System.out.println(errorQ.dequeue());
			}
			
			// Else if stack is empty, add to errorQ
			else if (stack.isEmpty()) {
				errorQ.enqueue(lineNumber + charsRead);

			} else {
				
				// search stack for matching start_tag
				// charsRead.replaceAll("/", "");
				if (stack.contains(charsNoBackSlash)) {

					while (!stack.isEmpty()) {

						if (stack.peek().equals(charsNoBackSlash)) {
							break;
						}
						errorQ.enqueue(lineNumber + stack.pop());
					}
					
					// pop off matched tag
					stack.pop();
				}
				
				// add element to extrasQ
				else {

					extrasQ.enqueue(lineNumber + charsRead);

				}
			}

		} catch (EmptyQueueException e) {
			e.printStackTrace();
		}
	}
	

   /**
     * This method will report the error that is in the xml file
     * it will print out the line number and also the line from the xml file
     * @throws EmptyQueueException
     */
	private void reportErrors() throws EmptyQueueException {
		while (!(errorQ.isEmpty() && extrasQ.isEmpty())) {
			
			// If stack is not empty, pop each E into errorQ
			if (stack.isEmpty() == false) {
				for (int i = 0; i < stack.size(); i++) {
					errorQ.enqueue(lineNumber + stack.pop());
				}
			}

			// If either queue is empty (but not both), report each E in both queues as
			// error
			if ((errorQ.isEmpty() || extrasQ.isEmpty()) && !(errorQ.isEmpty() && extrasQ.isEmpty())) {
				if (extrasQ.isEmpty() == false) {
					for (int i = 0; i < extrasQ.size(); i++) {
						String lineError = parseFileForPrint(filePathForPrint, extrasQ.dequeue());
						System.out.println("Error in Line "  + lineError);
					}
				}
				if (errorQ.isEmpty() == false) {
					for (int i = 0; i < errorQ.size(); i++) {
						String lineError = parseFileForPrint(filePathForPrint, errorQ.dequeue());
						System.out.println("Error in Line " + lineError);
					}
				}
			}
			
			// If both queues are not empty, peek both queues
			// If they don’t match, dequeue from errorQ and report as error
			// Else dequeue from both
			// Repeat until both queues are empty
			if (!(errorQ.isEmpty() && extrasQ.isEmpty())) {
				if (!(errorQ.equals(extrasQ))) {
					String lineError = parseFileForPrint(filePathForPrint, errorQ.dequeue());
					System.out.println("Error in Line " + lineError);
				} else {
					System.out.println(errorQ.dequeue());
					System.out.println(extrasQ.dequeue());
				}
			}
		}
	}

}
