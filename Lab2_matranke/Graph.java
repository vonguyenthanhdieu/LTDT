package Lab2_matranke;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public abstract class Graph {
	protected static int[][] adjMatrix;
	protected static int v;

	public Graph(String link) {
		loadData(link);
	}

	// Lab 1
	
	//Thêm cạnh xy
	public abstract void addEdge(int[][] matrix, int x, int y);
	
	// Xóa cạch xy trong đồ thị
	public abstract void removeEdge(int[][] matrix, int x, int y);

	// Tính bậc của đỉnh
	public abstract int degree(int[][] matrix, int u);
	
	//Tinh so canh/ cung cua do thi
	public abstract int edges(int[][] matrix);

	// In ma tran ke
	public void printMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++)
				System.out.print(matrix[i][j] + " ");
			System.out.println();
		}
	}

	// danh sach canh
	public Map<Integer, Set<Integer>> adjList() {
		Map<Integer, Set<Integer>> result = new LinkedHashMap<>();
		for (int i = 0; i < adjMatrix.length; i++) {
			result.put(i, new HashSet<>());
			for (int j = 0; j < adjMatrix[i].length; j++) {
				if (adjMatrix[i][j] != 0)
					result.get(i).add(j);
			}
		}
		return result;
	}

	// in danh sach canh
	public void printEdgeList(Map<Integer, Set<Integer>> edgeList) {
		for (Map.Entry<Integer, Set<Integer>> entry : edgeList.entrySet()) {
			for (Integer v : entry.getValue())
				System.out.print(entry.getKey() + "" + v + "\t");
		}
	}

	// Lap 2
	public void loadData(String link) {
		try {
			FileReader fileReader = new FileReader(link);
			BufferedReader bufferReader = new BufferedReader(fileReader);

			String line;
			String[] words;
			int i = 0;
			boolean kt = true; // tạo một biến kt xem đây có phải là dòng đầu tiên không
			while ((line = bufferReader.readLine()) != null) {
				words = line.split(" ");
				// Khoi tao ma tran ke
				if (kt) {
					v = Integer.parseInt(line);
					adjMatrix = new int[v][v];
					kt = false;
				}

				// add cac phan tu vao ma tran ke
				else {
					int j = 0;
					for (String w : words) {
						adjMatrix[i][j] = Integer.parseInt(w);
						j++;
					}
					i++;
				}
			}
			bufferReader.close();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// duyệt đồ thị theo chiều rộng
	public List<Integer> BFS(int start, boolean[] visited) {
//		boolean[] visited = new boolean[v];
		List<Integer> result = new LinkedList<>();
		Queue<Integer> queue = new LinkedList<Integer>();

		visited[start] = true;
		queue.add(start);

		while (!queue.isEmpty()) {
			// lay dinh dang duyet hien tai
			int currentNode = queue.poll();
//			System.out.print(currentNode + " ");
			result.add(currentNode);

			for (int i = 0; i < adjMatrix[currentNode].length; i++) {
				if ((!visited[i]) & (adjMatrix[currentNode][i] > 0)) {
					queue.add(i);
					visited[i] = true;
				}
			}
		}
		
		return result;

	}

	// duyet do thi theo chieu sau
	public List<Integer> DFS(int start, boolean[] visited) {
//		boolean[] visited = new boolean[v];
		List<Integer> result = new LinkedList<>();
		Stack<Integer> stack = new Stack<>();

		visited[start] = true;
		stack.add(start);
//		System.out.print(start + " ");
		result.add(start);
		int i = start;

		while (!stack.isEmpty()) {
			boolean stop = false;
			while (!stop) {
				stop = true;
				for (int j = 0; j < v; j++) {
					if (adjMatrix[i][j] > 0 && !visited[j]) {
						visited[j] = true;
						stack.push(i);
//						System.out.print(j + " ");
						result.add(j);
						stop = false;
						i = j;
						break;
					}
				}
				if (!stack.isEmpty() && stop)
					i = stack.pop();
			}
		}
		return result;
	}

	// Kiểm tra trong đồ thị có tồn tại đường đi từ một đỉnh x
	// đến một đỉnh y cho trước hay không
	public boolean hasWay(int x, int y) {
		boolean[] visited = new boolean[v];
		BFS(x, visited);
		if (!visited[y])
			return false;
		return true;
	}

	// đếm số lượng thành phần liên thông
	public int countIsConected() {
		int count = 0;
		boolean[] visited = new boolean[v];
		for (int i =0; i< v; i++) 
			if (!visited[i]) {
				BFS(i, visited);
				count++;
			}
		return count;

	}

	// kiểm tra xem đồ thị đã cho có phải đơn đồ thị hay không
	public abstract boolean isSingleGraph();

	// kiểm tra xem đồ thị có liên thông hay không
	public abstract boolean isConnected();

	// Kiểm tra xem đồ thị đã cho có phải là đồ thị euler không
	public abstract boolean isEuler();

	// Kiểm tra đồ thị có phải là đồ thị nửa euler không
	public abstract boolean isSemiEuler();

	// Tìm chu trình Euler của đồ thị đã cho
	public abstract List<Integer> printEulerCycle(int start);
	
	//Kiem tra xem co canh xy trong do thi khong
	public boolean hasEdge(int[][] matrix, int x, int y) {
		if (matrix[x][y] != 0)
			return true;
		return false;
	}
	
	//tim duong di Euler
	public abstract List<Integer> printEulerPath();
	public static void main(String[] args) {
		
		// lab 2
		Graph h1 = new UndrirectedGraph(".\\src\\Lab2_matranke\\dataH1.txt");
		h1.addEdge(adjMatrix, 1, 2);
		h1.removeEdge(adjMatrix, 1, 2);
		
		h1.printMatrix(adjMatrix);
		System.out.print("Danh sach canh:");
		h1.printEdgeList(h1.adjList());
		System.out.println();
		System.out.println(h1.isSingleGraph());
		
		boolean[] visited = new boolean[v];
		for (Integer i: h1.BFS(0, visited))
			System.out.print(i + " ");
		System.out.println("BFS");
		System.out.println("-----------------------------------");
		visited = new boolean[v];
		for (Integer i: h1.DFS(0, visited))
			System.out.print(i + " ");
		System.out.println("DFS");
		System.out.println("-----------------------------------");
		System.out.println(h1.isConnected());
		System.out.println(h1.hasWay(0, 7));
		System.out.println(h1.countIsConected());
		System.out.println("-----------------------------------");

		// lab 3
		Graph h3 = new DrirectedGraph(".\\src\\Lab2_matranke\\dataH3.txt");
		System.out.println(h3.isEuler());
		System.out.println(h3.isSemiEuler());
		System.out.println(h3.printEulerCycle(0));
	}
}
