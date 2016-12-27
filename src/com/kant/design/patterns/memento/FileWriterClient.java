/**
 * 
 */
package com.kant.design.patterns.memento;

/**
 * Check list
 * 
 * <br/>
 * Identify the roles of “caretaker” and “originator”. <br/>
 * Create a Memento class and declare the originator a friend. <br/>
 * Caretaker knows when to "check point" the originator. <br/>
 * Originator creates a Memento and copies its state to that Memento. <br/>
 * Caretaker holds on to (but cannot peek into) the Memento. <br/>
 * Caretaker knows when to "roll back" the originator. <br/>
 * Originator reinstates itself using the saved state in the Memento.
 * 
 * @author shaskant
 *
 */
public class FileWriterClient {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		FileWriterCaretaker caretaker = new FileWriterCaretaker();

		FileWriterUtil fileWriter = new FileWriterUtil("data.txt");
		fileWriter.write("First Set of Data\n");
		System.out.println(fileWriter + "\n\n");

		// lets save the file
		caretaker.save(fileWriter);
		// now write something else
		fileWriter.write("Second Set of Data\n");

		// checking file contents
		System.out.println(fileWriter + "\n\n");

		// lets undo to last save
		caretaker.undo(fileWriter);

		// checking file content again
		System.out.println(fileWriter + "\n\n");

	}

}

/**
 * ORIGINATOR
 * 
 * @author shaskant
 *
 */
class FileWriterUtil {

	private String fileName;
	private StringBuilder content;

	public FileWriterUtil(String file) {
		this.fileName = file;
		this.content = new StringBuilder();
	}

	@Override
	public String toString() {
		return this.content.toString();
	}

	public void write(String str) {
		content.append(str);
	}

	/**
	 * Originator knows only how to save it's state
	 * 
	 * @return
	 */
	public Memento save() {
		return new Memento(this.fileName, this.content);
	}

	/**
	 * Originator needs to be told which state it should switch to.
	 * 
	 * @param obj
	 */
	public void undoToLastSave(Object obj) {
		Memento memento = (Memento) obj;
		this.fileName = memento.fileName;
		this.content = memento.content;
	}

	/**
	 * This is the state.[MEMENTO]
	 * 
	 * @author shaskant
	 *
	 */
	private class Memento {
		private String fileName;
		private StringBuilder content;

		public Memento(String file, StringBuilder content) {
			this.fileName = file;
			// notice the deep copy so that Memento and FileWriterUtil content
			// variables don't refer to same object
			this.content = new StringBuilder(content);
		}
	}
}

/**
 * Can store stack of MementoObjects
 * 
 * @author shaskant
 *
 */
class FileWriterCaretaker {

	private Object lastState;

	/**
	 * save command
	 * 
	 * @param fileWriter
	 */
	public void save(FileWriterUtil fileWriter) {
		this.lastState = fileWriter.save();
	}

	/**
	 * Undo command
	 * 
	 * @param fileWriter
	 */
	public void undo(FileWriterUtil fileWriter) {
		fileWriter.undoToLastSave(lastState);
	}
}
