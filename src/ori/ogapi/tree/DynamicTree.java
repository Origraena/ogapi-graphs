package ori.ogapi.tree;

public abstract class DynamicTree<V> {

	public class Node {
		public Node() { }
		public Node(V value) {
			_value = value;
		}
		public V getValue() {
			return _value;
		}
		public void setValue(V value) {
			_value = value;
		}
		public Iterable<Node> gchildren() throws UnsupportedOperationException {
			if (_children == null)
				_children = processChildren(this);
			return _children;
		}
		public Node gparent() throws UnsupportedOperationException {
			return parent(this);
		}
		public boolean gisLeaf() {
			return isLeaf(this);
		}
		private V _value;
		private Iterable<Node> _children = null;
	};

	public Iterable<Node> children(Node n) throws UnsupportedOperationException {
		return n.gchildren();
	}
	public abstract Iterable<Node> processChildren(Node n) throws UnsupportedOperationException;
	public abstract Node parent(Node n) throws UnsupportedOperationException;
	public abstract boolean isLeaf(Node n);
	public abstract boolean isMinNode(Node n);
	public boolean isMaxNode(Node n) {
		return !isMinNode(n);
	}

	public Node minimax(Node n, NodeFunction f) {
		return minimax(n,0,0,f);
	}
	public Node minimax(Node n, int maxDepth, NodeFunction f) {
		return minimax(n,0,maxDepth,f);
	}
	protected Node minimax(Node n, int depth, int maxDepth, NodeFunction f) {
		Node result = null;
		int value = f.maxValue();
		int childValue;
		if (isLeaf(n))
			result = n;
		else if ((maxDepth > 0) && (depth >= maxDepth))
			result = n;
		else if (!isMinNode(n)) {	
			value = f.maxValue();
			for (Node child : children(n)) {
				childValue = f.exec(minimax(child,depth+1,maxDepth,f));
				if (childValue < value) {
					value = childValue;
					result = child;
				}
			}
		}
		else {	// n is a max node
			value = f.minValue();
			for (Node child : children(n)) {
				childValue = f.exec(minimax(child,depth+1,maxDepth,f));
				if (childValue > value) {
					value = childValue;
					result = child;
				}
			}
		}
		return result;
	}

	public Node negamax(Node n, NodeFunction f) {
		return negamax(n,0,0,f);
	}
	public Node negamax(Node n, int maxDepth, NodeFunction f) {
		return negamax(n,0,maxDepth,f);
	}
	protected Node negamax(Node n, int depth, int maxDepth, NodeFunction f) {
		Node result = null;
		int value = f.maxValue();
		int childValue;
		if (isLeaf(n))
			result = n;
		else if ((maxDepth > 0) && (depth >= maxDepth))
			result = n;
		else { 
			value = f.minValue();
			for (Node child : children(n)) {
				if (depth % 2 == 0)
					childValue = 1;
				else
					childValue = -1;
				childValue *= f.exec(negamax(child,depth+1,maxDepth,f));
				if (childValue > value) {
					value = childValue;
					result = child;
				}
			}
		}
		return result;
	}
	public Node alphabeta(Node n, NodeFunction f) {
		return alphabeta(n,0,f);
	}
	public Node alphabeta(Node n, int maxDepth, NodeFunction f) {
		return alphabeta(n,0,maxDepth,f.minValue(),f.maxValue(),f);
	}
	protected Node alphabeta(Node n, int depth, int maxDepth, int alpha, int beta, NodeFunction f) {
		Node result = n;
		int value;
		int childValue;
		if (isLeaf(n))
			return n;
		if ((maxDepth > 0) && (depth >= maxDepth))
			return n;
		if (!isMinNode(n)) {	
			value = beta;
			for (Node child : children(n)) {
				childValue = f.exec(alphabeta(child,depth+1,maxDepth,alpha,value,f));
				if (childValue <= value) {
					value = childValue;
					result = child;
					if (value <= alpha)
						return result;
				}
			}
			return result;
		}
		else {
			value = alpha;
			for (Node child : children(n)) {
				childValue = f.exec(alphabeta(child,depth+1,maxDepth,value,beta,f));
				if (childValue > value) {
					value = childValue;
					result = child;
					if (value >= beta)
						return result;
				}
			}
			return result;
		}
	}


};

