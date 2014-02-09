package erhs53.mapping.search;

import java.util.ArrayList;

/**
 * Instances of Path provide convenience functions to an array of actions.
 * Path also provides a Cost First Search method statically.
 * @author Michael Stevens
 *
 */
public class Path {
	public ArrayList<Action> actions;/** The actions that make up the path*/
	public double cost;/**The cost of following the path*/

	public Path() {
		actions = new ArrayList<>();
	}
	

	public Path(Action a, double cost) {
		actions = new ArrayList<Action>();
		actions.add(a);
		this.cost = cost;
	}

	public Path(ArrayList<Action> a, double cost) {
		actions = new ArrayList<Action>();
		actions.addAll(a);
		this.cost = cost;
	}

	/**
	 * Returns the ith action in the path
	 * @param i index
	 * @return the ith action
	 */
	public Action get(int i) {
		return actions.get(i);
	}

	public double length() {
		return actions.size();
	}

	public void addPath(Path path) {
		this.actions.addAll(path.actions);
	}

	public void add(Action a) {
		actions.add(a);
	}

	/**
	 * Performs a linear search for a state visited in the path
	 * @param state the state to search for
	 * @return if the state was found or not
	 */
	private boolean contains(State state) {
		for (Action a : actions) {
			if (a.state.name.equals(state.name))
				return true;
		}
		return false;
	}
	
	/**
	 * Returns a list of all possible paths proceeding one step forward in all directions
	 * @return
	 */
	private ArrayList<Path> successors() {
		ArrayList<Path> paths = new ArrayList<>();
		for (Action a : lastState().actions(this)) {
			if (!this.contains(a.state)) {
				double newCost = lastState().cost(a);
				Path p = new Path(actions, cost + newCost);
				p.add(a);
				paths.add(p);
			}
		}
		return paths;
	}

	public boolean compare(State road) {
		return road.name.equals(lastState().name);
	}

	public State lastState() {
		return actions.get(actions.size() - 1).state;
	}

	/**
	 * Returns the path of minimum cost
	 * @param paths
	 * @return
	 */
	private static Path min(ArrayList<Path> paths) {
		Path best = paths.get(0);
		for (Path p : paths) {
			if (p.cost < best.cost)
				best = p;
		}
		return best;
	}

	public void print() {
		for (Action a : actions) {
			switch (a.type) {
			case 0:
				System.out.print("GO");
				break;
			case 1:
				System.out.print("TL");
				break;
			case 2:
				System.out.print("TR");
				break;
			case 3:
				System.out.print("GS");
				break;
			case 4:
				System.out.print("PL");
				break;
			case 5:
				System.out.print("PR");
				break;
			case 6:
				System.out.print("S ");
				break;
			case 7:
				System.out.print("F ");
				break;
			default:
				System.out.print(a.type);
				

			}

			System.out.println(": " + a.state.name);
		}
	}

	/**
	 * Performs a cost first search
	 * @param start The start position
	 * @param end The end position
	 * @return A Path containing the list of actions needed to move from state start to state end
	 */
	public static Path CFS(State start, State end) {
		ArrayList<Path> open = new ArrayList<>();
		open.add(new Path(new Action(0, start), 0));

		while (open.size() != 0) {
			Path best = min(open);
			if (best.compare(end))
				return best;

			open.remove(best);
			open.addAll(best.successors());

		}
		return null;
	}

}
