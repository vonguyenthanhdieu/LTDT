package Lab1;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public abstract class Graph {
	protected Map<String, Set<String>> adjList;

	public Graph() {
		adjList = new LinkedHashMap<>();
	}

	// Them dinh
	public void addVertex(String v) {
		Set<String> values = new HashSet<>();
		adjList.put(v, values);
	}

	// Xoa dinh
	public void removeVertex(String v) {
		adjList.remove(v);
		for (Map.Entry<String, Set<String>> entry : adjList.entrySet()) {
			entry.getValue().remove(v);
		}
	}

	// In danh sach ke
	public void printAdjList() {
		for (Map.Entry<String, Set<String>> entry : adjList.entrySet()) {
			System.out.println(entry.getKey()+ " " + entry.getValue().toString());
		}
	}

	public abstract void addEdge(String u, String v);

	public abstract void removeEdge(String u, String v);

	public abstract int degree(String v);

	public abstract int edges();
	
	public static void main(String[] args) {
		Graph g = new UndrirectedGraph();
		Graph w = new DrirectedGraph();

		//DO thi vo huong
		g.addVertex("a");
		g.addVertex("b");
		g.addVertex("c");
		g.addVertex("d");
		g.addVertex("e");

		g.addEdge("a", "b");//
		g.addEdge("a", "e");
		g.addEdge("a", "d");
		g.addEdge("b", "b");//
		g.addEdge("b", "a");
		g.addEdge("b", "c");
		g.addEdge("b", "d");
		g.addEdge("b", "e");
		g.addEdge("c", "b");//
		g.addEdge("d", "a");//
		g.addEdge("d", "b");
		g.addEdge("d", "e");
		g.addEdge("e", "a");//
		g.addEdge("e", "b");
		g.addEdge("e", "d");

		g.removeEdge("a", "b");
		g.removeVertex("a");

		g.printAdjList();

//		System.out.println(g.degree("a"));
		System.out.println(g.degree("b"));
		System.out.println(g.edges());
		
		System.out.println("===========================");
		
		//Do thi co huong
		w.addVertex("a");
		w.addVertex("b");
		w.addVertex("c");
		w.addVertex("d");
		w.addVertex("e");

		w.addEdge("a", "b");//
		w.addEdge("b", "e");//
		w.addEdge("c", "b");//
		w.addEdge("c", "c");//
		w.addEdge("c", "d");
		w.addEdge("d", "c");
		w.addEdge("e", "a");//
		w.addEdge("e", "d");
		w.addEdge("e", "e");
		
//		w.removeEdge("a", "b");
//		w.removeVertex("a");
		
		w.printAdjList();
		
		System.out.println(w.degree("a"));
		System.out.println(w.degree("b"));
		System.out.println(w.edges());
	}
}
