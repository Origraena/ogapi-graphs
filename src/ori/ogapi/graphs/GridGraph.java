package ori.ogapi.graphs;

import ori.ogapi.util.OperatorPlus1T;

import ori.ogapi.graphs.vertices.Vertex;
import ori.ogapi.graphs.vertices.VertexGrid;
import ori.ogapi.graphs.vertices.ParentFunction;
import ori.ogapi.graphs.edges.Edge;
import ori.ogapi.graphs.edges.EdgeCollectionByVertices;
import ori.ogapi.graphs.edges.IllegalEdgeException;
import ori.ogapi.graphs.edges.NeighbourEdge;
import ori.ogapi.util.Iterator;

import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Comparator;


public class GridGraph<V,E> extends Graph<V,E> {
	
	public static final int NONE = VertexGrid.NONE;
	public static final int LEFT = VertexGrid.LEFT;
	public static final int TOP = VertexGrid.TOP;
	public static final int RIGHT = VertexGrid.RIGHT;
	public static final int BOTTOM = VertexGrid.BOTTOM;

	public GridGraph(int height, int width) throws IllegalConstructionException {
		super();	
		VertexGrid<V,E> vertices = new VertexGrid<V,E>(height,width);
		_vertices = vertices;
		_edges = new EdgeCollectionByVertices<E>(vertices);
	}


	public GridGraph(int height, int width, E initEdgeValue) throws IllegalConstructionException {
		super();	
		VertexGrid<V,E> vertices = new VertexGrid<V,E>(height,width,initEdgeValue);
		_vertices = vertices;
		_edges = new EdgeCollectionByVertices<E>(vertices);
	}

	public int width() {
		return getVertexGrid().getWidth();
	}

	public int height() {
		return getVertexGrid().getHeight();
	}

	public int globalID(int x, int y) {
		return getVertexGrid().globalID(x,y);
	}

	public Point localID(int id) {
		return getVertexGrid().localID(id);
	}

	public VertexGrid<V,E> getVertexGrid() {
		return (VertexGrid<V,E>)getVertexCollection();
	}

	public void set(int id, V value, E edgeValue) {
		super.set(id,value);
		getVertexGrid().setEdgeValue(id,edgeValue);
	}

	public void setEdgeValue(int idV, E edgeValue) {
		getVertexGrid().setEdgeValue(idV,edgeValue);
	}

	public int side(int idU, int idV) {
		return getVertexGrid().side(idU,idV);
	}

	public Path AStarPath(int root,
						  E zeroValue,
						  OperatorPlus1T<E> plus,
						  Comparator<E> comp,
						  ArrayList<Vertex<V> > ends,
						  Heuristique<E> h) {
		return new Path(super.AStarPath(root,zeroValue,plus,comp,ends,h));		
	}
	
	public class Path extends Graph.Path {
		public Path(Graph.Path p) {
			super(p);
		}
		public int nextDirection() {
			if (!hasNext())
				return NONE;
			if (_current+1 >= length())
				return NONE;
			return side(get(_current+1).getID(),get(_current).getID());
		}
	};

};

