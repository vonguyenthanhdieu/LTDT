package Lab1;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UndrirectedGraph extends Graph{

	@Override
	//Them canh
	public void addEdge(String u, String v) {
		// TODO Auto-generated method stub
		if (!adjList.containsKey(u) || !adjList.containsKey(v)) {
			// Khong them canh
		}
		else {
			this.adjList.get(v).add(u);
			if (!v.equals(u))
				this.adjList.get(u).add(v);
		}
	}

	@Override
	//Xoa canh
	public void removeEdge(String u, String v) {
		// TODO Auto-generated method stub
		if (!adjList.containsKey(u) || !adjList.containsKey(v)) {
			// Khong xoa canh
		}
		else {
			this.adjList.get(v).remove(u);
			if (!v.equals(u))
				this.adjList.get(u).remove(v);
		}
	}

	@Override
	//Tinh bac cua do thi
	public int degree(String v) {
		// TODO Auto-generated method stub
		int d = adjList.get(v).size();
		if (adjList.get(v).contains(v))
			d++;
		return d;
	}

	@Override
	//Tinh so canh cua do thi
	public int edges() {
		// TODO Auto-generated method stub
		int d =0;
		for (Map.Entry<String, Set<String>> entry: adjList.entrySet()) {
			d += degree(entry.getKey());
		}
		return d/2;
	}

}
