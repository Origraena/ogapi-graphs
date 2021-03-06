package ori.ogapi.graphs;

import ori.ogapi.graphs.vertices.VertexArrayList;
import ori.ogapi.graphs.edges.NeighboursLists;
import ori.ogapi.graphs.edges.NeighbourEdge;

import ori.ogapi.util.Iterator;

public class DirectedSimpleGraph<V,E> extends Graph<V,E> {

	public DirectedSimpleGraph() throws IllegalConstructionException {
		super(new VertexArrayList<V>(),new NeighboursLists<E>());
	}

	public DirectedSimpleGraph(Graph<V,E> g) throws IllegalConstructionException {
		super(g);
	}

	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		int i = 0;
		for (V v : this) {
			stringBuilder.append(i);
			stringBuilder.append(" : ");
			stringBuilder.append(v);
			stringBuilder.append('\n');
			i++;
		}
		stringBuilder.append('\n');
		for (i = 0 ; i < getNbVertices() ; i++) { 
			for (Iterator<NeighbourEdge<E> > neighbourIterator = neighbourIterator(i) ; neighbourIterator.hasNext() ; ) {
				stringBuilder.append(i);
				stringBuilder.append(" --> ");
				stringBuilder.append(neighbourIterator.next().getIDV());
				stringBuilder.append('\n');
			}
		}
		return stringBuilder.toString();
	}

};

