package Lab2_matranke;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UndrirectedGraph extends Graph {

	public UndrirectedGraph(String link) {
		super(link);
		// TODO Auto-generated constructor stub
	}

	// Lab 1
	@Override
	// tính bậc
	public int degree(int[][] matrix, int u) {
		int re = 0;
		for (int i = 0; i < v; i++)
			re += matrix[u][i];
		return re;
	}

	// thêm cạnh
	public void addEdge(int[][] matrix, int x, int y) {
		// TODO Auto-generated method stub
		matrix[x][y] += 1;
		matrix[y][x] += 1;

	}

	@Override
	// Xóa cạnh
	public void removeEdge(int[][] matrix, int x, int y) {
		// TODO Auto-generated method stub
		if (matrix[x][y] != 0) {
			matrix[x][y] -= 1;
			matrix[y][x] -= 1;
		}
	}

	// đếm số cung/ cạnh
	public int edges(int[][] matrix) {
		int d = 0;
		for (int i = 0; i < v; i++) {
			d += degree(matrix, i);
		}
		return d / 2;
	}

	// Lab 2
	@Override
	// kiểm tra xem có phải là đơn đồ thị không
	public boolean isSingleGraph() {
		// TODO Auto-generated method stub
		for (int i = 0; i < adjMatrix.length; i++) {
			for (int j = 0; j < adjMatrix[i].length; j++)
				if (adjMatrix[i][j] > 1 || (i == j & adjMatrix[i][j] != 0))
					return false;
		}
		return true;
	}

	@Override
	// Kiểm tra xem đồ thị vô hướng có liên thông không
	public boolean isConnected() {
		// TODO Auto-generated method stub
		boolean[] visited = new boolean[v];
		BFS(0, visited);
		for (int i = 0; i < v; i++)
			if (!visited[i])
				return false;
		return true;
	}

	// Lab 3
	@Override
	public boolean isEuler() {
		if (!isConnected())
			return false;
		else {
			for (int i = 0; i < v; i++)
				if (degree(adjMatrix, i) % 2 != 0)
					return false;
		}
		return true;
	}

	@Override
	public boolean isSemiEuler() {
		if (!isConnected())
			return false;
		else {
			int dem = 0;
			int i = 0;
			while (dem <= 2 & i< v) {
				if (degree(adjMatrix, i) % 2 != 0)
					dem++;
				i++;
			}
			if (dem == 2)
				return true;
			else
				return false;
		}
	}

	@Override
	//tìm chu trình euler
	public List<Integer> printEulerCycle(int start) {
		if (!isEuler()) {
			System.out.println("Khong co chu trinh Euler");
			return null;
		}
		
		//List cac dinh lien tiep nhau trong chu trinh euler can tim
		List<Integer> c = new ArrayList<>(); 
		c.add(start);
		int[][] copyMatrix = new int[v][v];
		//cop ma tran ke
		for (int i=0; i< v; i++) {
			for (int j=0; j< v; j++)
				copyMatrix[i][j] = adjMatrix[i][j];
		}
		Integer t=0;
		while (edges(copyMatrix)>0) {
			//tim dinh t trong c va H co bac > 0
			for (Integer i: c) 
				if (degree(copyMatrix, i)> 0) {
					t = i;
					break;
				}
			Integer i = t; //dinh bat dau trong chu trinh con
			List<Integer> subcircuit = new ArrayList<>(); //tao 1 chu trinh con rong
			while (degree(copyMatrix, i)> 0) {
				subcircuit.add(i);
				//Tim dinh j trong H ke voi i
				for (int j=0; j< v; j++)
					if (hasEdge(copyMatrix, i, j)) {
						removeEdge(copyMatrix, i, j);
						i = j;
						break;
					}
			}
			
			//Chen chu trinh con vao chu trinh euler
			c.addAll(c.indexOf(t), subcircuit);
		}
		
		return c;
	}

	@Override
	public List<Integer> printEulerPath() {
		if (!isSemiEuler()) {
			System.out.println("Khong co duong di Euler");
			return null;
		}
		List<Integer> result = new ArrayList<>();
		Integer start = null;
		Integer end = null;
		int i =0;
		
		return null;
	}

}
