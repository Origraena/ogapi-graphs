package ori.ogapi.graphs;

import java.util.ArrayList;

import ori.ogapi.graphs.Graph;
import ori.ogapi.graphs.vertices.ParentFunction;
import ori.ogapi.graphs.vertices.Vertex;

public class Heuristique<E> extends ArrayList<E>{
	public Heuristique() {
		super();
	}
	
	public Heuristique(int nbVertices) {
		super(nbVertices);
	}
	
	public void setEuclidianDistance(Graph<Point,Long> g, ArrayList<Vertex<Point>> ends) {
		super.clear();
		for (int i = 0 ; i < g.getNbVertices() ; i++) {
			Long min = new Long(-1);
			for (Vertex<Point> q : ends) {
				if ((min < 0) || (Point.euclidianDistance(q.getValue(),g.get(i)) < min))
					min = Point.euclidianDistance(q.getValue(),g.get(i));
			}
			super.add((E)min);
		}
	}
	
	public void setManhattanDistance(Graph<Point,Long> g, ArrayList<Vertex<Point>> ends) {
		super.clear();
		for (int i = 0 ; i < g.getNbVertices() ; i++) {
			Long min = new Long(-1);
			for (Vertex<Point> q : ends) {
				if ((min < 0) || (Point.euclidianDistance(q.getValue(),g.get(i)) < min))
					min = Point.manhattanDistance(q.getValue(),g.get(i));
			}
			super.add((E)min);
		}
	}
	
};


