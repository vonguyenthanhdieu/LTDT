package Lab2_matranke;

import java.lang.System.LoggerFinder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DrirectedGraph extends Graph {

	public DrirectedGraph(String link) {
		super(link);
	}

	// Lab 1
	// thêm cạnh
	public void addEdge(int[][] matrix, int x, int y) {
		matrix[x][y] += 1;
	}

	@Override
	// xoa canh
	public void removeEdge(int[][] matrix, int x, int y) {
		// TODO Auto-generated method stub
		if (matrix[x][y] != 0)
			matrix[x][y] -= 1;
	}

	@Override
	public int degree(int[][] matrix, int u) {
		// TODO Auto-generated method stub
		return indegree(matrix, u) + outdegree(matrix, u);
	}

	// nua bac ngoai = so cung xuat phat tu u
	public int outdegree(int[][] matrix, int u) {
		int re = 0;
		for (int i = 0; i < v; i++)
			re += matrix[u][i];
		return re;
	}

	// nua bac trong = so cung ket thuc tai u
	public int indegree(int[][] matrix, int u) {
		int re = 0;
		for (int i = 0; i < v; i++)
			re += matrix[i][u];
		return re;
	}

	// đếm số cung/ cạnh
	public int edges(int[][] matrix) {
		int d = 0;
		for (int i = 0; i < v; i++) {
			d += degree(matrix, i);
		}
		return d;
	}

	// Lab 2
	@Override
	// kiểm tra xem có phải là đơn đồ thị có hướng không
	public boolean isSingleGraph() {
		// TODO Auto-generated method stub
		for (int i = 0; i < adjMatrix.length; i++) {
			for (int j = 0; j < adjMatrix[i].length; j++)
				if (adjMatrix[i][j] > 1 || (i == j & adjMatrix[i][j] != 0))
					return false;
		}
		return true;
	}

	// chuyen do thi co huong ve do thi vo huong
	private void convert() {
		int[][] convertMatrix = new int[v][v];
		for (int i = 0; i < v; i++) {
			for (int j = 0; j < v; j++)
				convertMatrix[i][j] = adjMatrix[i][j] + adjMatrix[j][i];
		}
	}

	@Override
	// Kiểm tra xem đồ thị có hướng có liên thông yếu hay không
	public boolean isConnected() {
		boolean[] visited = new boolean[v];
		convert();
		BFS(0, visited);
		for (int i = 0; i < v; i++)
			if (!visited[i])
				return false;
		return true;
	}

	@Override
	public boolean isEuler() {
		if (!isConnected())
			return false;
		else {
			for (int i = 0; i < v; i++)
				if (indegree(adjMatrix, i) != outdegree(adjMatrix, i))
					return false;
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
				if (indegree(adjMatrix, i) == outdegree(adjMatrix, i)+1)
					y++;
				else
					if (outdegree(adjMatrix, i) == indegree(adjMatrix, i)+1)
						x++;
				i++;
			}
			if (x == 1 & y == 1)
				return true;
		}
		return false;
	}

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
					if (outdegree(copyMatrix, i)> 0) {
						t = i;
						break;
					}
				Integer i = t; //dinh bat dau trong chu trinh con
				List<Integer> subcircuit = new ArrayList<>(); //tao 1 chu trinh con rong
				while (outdegree(copyMatrix, i)> 0) {
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
		public List<Integer> printEulerPath(int start) {
			// TODO Auto-generated method stub
			return null;
		}

}
