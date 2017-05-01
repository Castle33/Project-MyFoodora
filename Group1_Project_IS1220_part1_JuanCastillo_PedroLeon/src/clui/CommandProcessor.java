package clui;

/**
 * Interface for all the command actions. Implements the method <code>process</code>,
 * which calls any needed function from the <code>Core</code> or other classes of the system.
 * @author Juan Castillo
 *
 */
public interface CommandProcessor {
	String process(String[] args) ;

}
