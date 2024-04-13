package Lab1;

import java.lang.System.LoggerFinder;
import java.util.Map;
import java.util.Set;

public class DrirectedGraph extends Graph {


	@Override
	public void addEdge(String u, String v) {
		// TODO Auto-generated method stub
		if (!adjList.containsKey(u)) {
			// Khong them canh
		}
		else this.adjList.get(u).add(v);
	}

	@Override
	public void removeEdge(String u, String v) {
		// TODO Auto-generated method stub
		if (!adjList.containsKey(u)) {
			// Khong xoa canh
		}
		else this.adjList.get(u).remove(v);
	}

	@Override
	//Tinh bac cua do thi
	public int degree(String v) {
		// TODO Auto-generated method stub
		return indegree(v) + outdegree(v);
	}
	
	//Tinh nua bac trong cua do thi co huong
	public int indegree(String v) {
		int d = adjList.get(v).size();
		return d;
	}
	
	//Nua bac ngoai
	public int outdegree(String v) {
		int d =0;
		for (Map.Entry<String, Set<String>> entry: adjList.entrySet()) {
			if (entry.getValue().contains(v)) 
				d++;
		}
		return d;
	}

	@Override
	//Tinh so canh cua do thi
	public int edges() {
		// TODO Auto-generated method stub
		int d = 0;
		for (Map.Entry<String, Set<String>> entry: adjList.entrySet()) {
				d += indegree(entry.getKey());
		}
		return d;
	}

}
