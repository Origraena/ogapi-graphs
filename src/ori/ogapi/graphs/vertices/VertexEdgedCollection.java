package ori.ogapi.graphs.vertices;

import ori.ogapi.graphs.edges.NeighbourEdge;
import ori.ogapi.util.Iterator;

public interface VertexEdgedCollection<V,E> extends VertexCollection<V> {

	int getNbEdges();
	void setEdgeValue(int idV,E value);
	E getEdgeValue(int idU, int idV);
	int getNbNeighbours(int id);
	int getNeighbourAt(int idU, int index);
	void removeEdge(int idU, int idV);
	Iterator<NeighbourEdge<E> > neighbourIterator(int id);

};

