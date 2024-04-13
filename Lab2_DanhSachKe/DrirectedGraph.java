package Lab2_DanhSachKe;

import java.util.Map;
import java.util.Set;

public class DrirectedGraph extends Graph {

	public DrirectedGraph(String link) {
		super(link);
	}

	// Lab1
	@Override
	public void addEdge(Integer u, Integer v) {
		// TODO Auto-generated method stub
		if (!adjList.containsKey(u)) {
			// Khong them canh
		} else
			this.adjList.get(u).add(v);
	}

	@Override
	public void removeEdge(Integer u, Integer v) {
		// TODO Auto-generated method stub
		if (!adjList.containsKey(u)) {
			// Khong xoa canh
		} else
			this.adjList.get(u).remove(v);
	}

	@Override
	public int degree(Integer v) {
		// TODO Auto-generated method stub
		return indegree(v) + outdegree(v);
	}

	// Tinh nua bac trong cua do thi co huong
	private int indegree(Integer v) {
		int d = adjList.get(v).size();
		return d;
	}

	// Nua bac ngoai
	private int outdegree(Integer v) {
		int d = 0;
		for (Map.Entry<Integer, Set<Integer>> entry : adjList.entrySet()) {
			if (entry.getValue().contains(v))
				d++;
		}
		return d;
	}

	@Override
	public int edges() {
		// TODO Auto-generated method stub
		int d = 0;
		for (Map.Entry<Integer, Set<Integer>> entry: adjList.entrySet()) {
				d += indegree(entry.getKey());
		}
		return d;
	}

	// Lab2
	@Override
	public boolean isSingleGraph() {
		// TODO Auto-generated method stub
		for (Map.Entry<Integer, Set<Integer>> entry: adjList.entrySet()) {
			if (entry.getValue().contains(entry.getKey()))
				return false;
		}
		return true;
	}
	
	//chuyen do thi co huong sang do thi vo huong
	private void convert() {
		for (Map.Entry<Integer, Set<Integer>> entry: adjList.entrySet()) {
			for (Integer i: entry.getValue())
				adjList.get(i).add(entry.getKey());
		}
	}

	@Override
	public boolean isConnected() {
		convert();
		boolean[] visited = new boolean[v];
		BFS(0, visited);
		for (int i=0; i< v; i++)
			if (!visited[i]) return false;
		return true;
	}

	@Override
	public boolean isEuler() {
		if (!isConnected())
			return false;
		else {
			for (int i=0; i< v; i++) {
				if (indegree(i) != outdegree(i))
					return false;
			}
		}
		return true;
	}

	@Override
	public boolean isSemiEuler() {
		if (!isConnected())
			return false;
		else {
			int x = 0; //dem so dinh co deg+ = deg- +1
			int y = 0; //dem so dinh co deg- = deg+ +1
			int i = 0;
			while (x <= 1 & y <=1 & i< v) {
				if (indegree(i) == outdegree(i)+1)
					y++;
				else
					if (outdegree(i) == indegree(i)+1)
						x++;
				i++;
			}
			if (x == 1 & y == 1)
				return true;
		}
		return false;
	}
	
	

}
