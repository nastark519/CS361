import java.util.*;

/**
 * @author Nathan Stark
 *
 */
public class DFA {
		
	//this is to define the alphabet.
	private String  alph;
	//this field is to hold our final states.
	private ArrayList<State> stateF;
	//This field represents our Q set of states.
	private ArrayList<State> states;
	//This is the input to be tested.
	private char[] input;
	//The start state.
	private State start;
	
	/**
	 * Constructor for the deterministic finite automata
	 * 
	 * @param alphabet sets the alphabet of this machine.
	 * enter only the characters in the alphabet you intend 
	 * to use.
	 * @param start The start state.
	 */
	public DFA(String alphabet, State start){
		this.start=start;
		alph = alphabet;
		states = new ArrayList<State>();
		stateF = new ArrayList<State>();
		
	}
	
	/**
	 * Enter in states that you want example: "q1", "q2" and so on,
	 * as individual strings.
	 * @param newState The states that you want to have in
	 * the machine.
	 */
	public void addFinal(State newState){
		stateF.add(newState);
	}
	
	/**
	 * Enter in states that will be part of the machine
	 * @param newState The states that will make up the 
	 * machine.
	 */
	public void addState(State newState){
		states.add(newState);
	}
	
	/**
	 * This is to simulate the transition function.
	 */
	public boolean transition(String put){
		char c;
		int i;
		boolean acceptance = false;
		for (i = 0; i < alph.length(); i++) {
			c = alph.charAt(i);
			if(!acceptance){
				for(int t=0;t<put.length();t++){
					if ((put.charAt(t) != c)&&(!acceptance)){
						acceptance = false;
					}else{
						acceptance = true;
					}
				}
			}
		}
		if(!acceptance){
			return acceptance;
		}
		State stat = start;
		
		acceptance=false;
		input=put.toCharArray();
		for(i=0;i<input.length;i++){
			String g = String.valueOf(input[i]);
			//if(!stat.passChar(g).equals(null)){
				//return false;
			//}
			stat= stat.passChar(g);
		}
		if(stateF.contains(stat)){
			acceptance=true;
		}
		return acceptance;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		State s = new State();
		State q1 = new State();
		State q2 = new State();
		State r1 = new State();
		State r2 = new State();
		
		s.addNextState("a", q1);
		s.addNextState("b", r1);
		q1.addNextState("a", q1);
		q1.addNextState("b", q2);
		q2.addNextState("a", q1);
		q2.addNextState("b", q2);
		r1.addNextState("a", r2);
		r1.addNextState("b", r1);
		r2.addNextState("a", r2);
		r1.addNextState("b", r1);
		
		DFA myDFA = new DFA("ba",s);
		//my final state.
		myDFA.addFinal(q1);
		myDFA.addFinal(r1);
		
		if(myDFA.transition("ababa")){
			System.out.println("1. Accepted string: ababa");
		}else{
			System.out.println("1. Unaccepted string: ababa");
		}
		if(myDFA.transition("baba")){
			System.out.println("2. Accepted string: baba");
		}else{
			System.out.println("2. Unaccepted string: baba");
		}
		if(myDFA.transition("aababaab")){
			System.out.println("3. Accepted string: aababaab");
		}else{
			System.out.println("3. Unaccepted string: aababaab");
		}
		if(myDFA.transition("babaabaaabb")){
			System.out.println("4. Accepted string: babaabaaabb");
		}else{
			System.out.println("4. Unaccepted string: babaabaaabb");
		}
		if(myDFA.transition("")){
			System.out.println("5. Accepted string: e");
		}else{
			System.out.println("5. Unaccepted string: e");
		}
	}
}
