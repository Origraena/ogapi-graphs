package ori.ogapi.graphs.visu;

import ori.ogapi.graphs.vertices.Vertex;
import ori.ogapi.graphs.vertices.VertexIdentityFunction;

public class SCCIdentityFunction extends VertexIdentityFunction {
	public String exec(Vertex v) {
		return "C"+v.getID();
	}
	public static SCCIdentityFunction instance() {
		if (_instance == null)
			_instance = new SCCIdentityFunction();
		return _instance;
	}
	private static SCCIdentityFunction _instance = null;
	protected SCCIdentityFunction() { }
};

