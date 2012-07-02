package ori.ogapi.graphs.visu;

import java.util.NoSuchElementException;
import ori.ogapi.graphs.Point;
import ori.ogapi.graphs.Graph;

public interface Localizer {

	public void process(Graph graph);
	Point position(int vertexID) throws NoSuchElementException;
	int x(int vertexID) throws NoSuchElementException;
	int y(int vertexID) throws NoSuchElementException;

};

