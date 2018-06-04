import java.util.HashMap;
import java.util.Map;



/**
 * @author Nathan Stark
 *
 */
public class State {
	//This is a map for the next states given the next char passed in.
	Map<String,State> nextStates;
	
	/**
	 * Constructor that initiates the nextStates HashMap field.
	 */
	public State(){
		nextStates = new HashMap<String,State>();
		}
	/**
	 * Do the this that DFA's do the fine what state they should be at next.
	 * 
	 * @param putIn The char that is passed at the currant state.
	 * @return The next state that is a determined from the char passed in.
	 */
	public State passChar(String putIn){
		if(!nextStates.containsKey(putIn)){
			return null;
			}
		return nextStates.get(putIn);
		}
	
	/**
	 * Add a state that this state connects to.
	 * @param sta the state that this state connects to when
	 * a letter in the alphabet is passed to it.
	 * @param pass the letter that is passed to it.
	 */
	public void addNextState(String pass,State state){
		nextStates.put(pass,state);
		}
}
