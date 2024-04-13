package Lab2_DanhSachKe;

import java.util.Map;
import java.util.Set;

public class UndrirectedGraph extends Graph{

	public UndrirectedGraph(String link) {
		super(link);
		// TODO Auto-generated constructor stub
	}
	
	//Lab1
	@Override
	public void addEdge(Integer u, Integer v) {
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
	public void removeEdge(Integer u, Integer v) {
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
	public int degree(Integer v) {
		// TODO Auto-generated method stub
		int d = adjList.get(v).size();
		if (adjList.get(v).contains(v))
			d++;
		return d;
	}

	@Override
	public int edges() {
		// TODO Auto-generated method stub
		int d =0;
		for (Map.Entry<Integer, Set<Integer>> entry: adjList.entrySet()) {
			d += degree(entry.getKey());
		}
		return d/2;
	}
	
	//Lab2
	@Override
	public boolean isSingleGraph() {
		for (Map.Entry<Integer, Set<Integer>> entry: adjList.entrySet()) {
			if (entry.getValue().contains(entry.getKey()))
				return false;
		}
		return true;
	}

	@Override
	//kiem tra do thi co lien thong khong
	public boolean isConnected() {
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
			for (int i =0; i< v; i++)
				if (degree(i)%2 !=0)
					return false;
		}
		return true;
	}

	@Override
	public boolean isSemiEuler() {
		if (!isConnected())
			return false;
		else {
			int dem =0;
			int i = 0;
			while (dem <=2) {
				if (degree(i)%2 != 0)
					dem++;
				i++;
			}
			if (dem == 2) return true;
			else return false;
		}
	}

	

	

}
