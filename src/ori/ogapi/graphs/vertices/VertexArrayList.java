package ori.ogapi.graphs.vertices;

import ori.ogapi.util.Iterator;
import ori.ogapi.util.AdaptedIterator;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class VertexArrayList<V> extends ArrayList<Vertex<V> > implements VertexCollection<V> {

	private static final long serialVersionUID = 2012022600L; 

	public VertexArrayList() {
		super();
	}

	public VertexArrayList(int capacity) {
		super(capacity);
	}

	public VertexArrayList<V> subset(int idBegin, int idEnd) {
		VertexArrayList<V> result = new VertexArrayList<V>();
		for (int i = idBegin ; i < idEnd ; i++)
			result.add(get(i).clone());
		return result;
	}

	public boolean add(Vertex<V> v) {
		//v.setID(size());
		return super.add(v);
	}

	public Vertex<V> remove(int id) {
		Vertex<V> v = super.remove(id);
		for (int i = id ; i < size() ; i++)
			get(i).setID(get(i).getID()-1);
		return v;
	}

	public Iterator<Vertex<V> > iterator() {
		return new AdaptedIterator(super.iterator());
	}

};

