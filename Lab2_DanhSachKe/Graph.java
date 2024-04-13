package Lab2_DanhSachKe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public abstract class Graph {
	protected static Integer v;
	protected static Map<Integer, Set<Integer>> adjList;

	public Graph(String link) {
		loadData(link);
	}

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
				// Khoi tao danh sach ke
				if (kt) {
					v = Integer.parseInt(line);
					adjList = new LinkedHashMap<>();
					kt = false;
				}

				// add cac phan tu vao danh sach ke
				else {
					Set<Integer> values = new LinkedHashSet<>();
					adjList.put(i, values);
					int j = 0;
					for (String w : words) {
						values.add(Integer.parseInt(w));
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

	// Lab 1
	// Them dinh
	public void addVertex(Integer u) {
		Set<Integer> values = new HashSet<>();
		adjList.put(u, values);
	}

	// Xoa dinh
	public void removeVertex(Integer v) {
		adjList.remove(v);
		for (Map.Entry<Integer, Set<Integer>> entry : adjList.entrySet()) {
			entry.getValue().remove(v);
		}
	}

	// In danh sach ke
	public void printAdjList() {
		for (Map.Entry<Integer, Set<Integer>> entry : adjList.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue().toString());
		}
	}

	public abstract void addEdge(Integer u, Integer v);

	public abstract void removeEdge(Integer u, Integer v);

	public abstract int degree(Integer v);

	public abstract int edges();

	// Lab2
	// kiểm tra xem đồ thị đã cho có phải đơn đồ thị hay không
	public abstract boolean isSingleGraph();

	// Duyệt đồ thị theo chiều rộng BFS
	public List<Integer> BFS(int start, boolean[] visited) {
		List<Integer> result = new LinkedList<>();
		Queue<Integer> queue = new LinkedList<Integer>();
		int currentNode;
		queue.add(start);
		visited[start] = true;

		while (!queue.isEmpty()) {
			currentNode = queue.poll();
			result.add(currentNode);

			for (Integer i : adjList.get(currentNode))
				if (!visited[i]) {
					queue.add(i);
					visited[i] = true;
				}
		}

		return result;
	}

	// Duyet do thi theo chieu sau DFS
	public List<Integer> DFS(int start, boolean[] visited) {
		List<Integer> result = new LinkedList<>();
		Stack<Integer> stack = new Stack<>();

		visited[start] = true;
		stack.add(start);
		result.add(start);
		int currentNode = start;

		while (!stack.isEmpty()) {
			boolean stop = false;
			
			while (!stop) {
				stop = true;
				for (Integer i : adjList.get(currentNode))
					if (!visited[i]) {
						stack.add(currentNode);
						visited[i] = true;
						result.add(i);
						stop = false;
						currentNode = i;
						break;
					}
				if (stop & !stack.isEmpty()) {
					currentNode = stack.pop();
				}
			}
		}

		return result;
	}
	
	//kiem tra xe do thi da cho co ton tai duong di tu 
	//dinh x den dinh y cho truoc hay khong
	public boolean hasWay(int x, int y) {
		boolean[] visited = new boolean[v];
		BFS(x, visited);
		if (!visited[y]) return false;
		return true;
	}
	
	//kiem tra xem do thi co lien thong khong
	public abstract boolean isConnected();
	
	//dem so thanh phan lien thong cua do thi da cho
	public int countIsConnected() {
		int count = 0;
		boolean[] visited = new boolean[v];
		for (int i=0; i< v; i++) 
			if (!visited[i]) {
				DFS(i,visited);
				count++;
			}
		return count;
	}
	
	//lab3
	public abstract boolean isEuler();
	public abstract boolean isSemiEuler();

	public static void main(String[] args) {
		Graph h2 = new UndrirectedGraph(".\\src\\Lab2_DanhSachKe\\DataH2.txt");
		System.out.println(v);
		for (Map.Entry<Integer, Set<Integer>> entry : adjList.entrySet())
			System.out.println(entry.getKey() + " " + entry.getValue().toString());

		// BFS
		System.out.println("BFS: ");
		boolean[] visited = new boolean[v];
		for (Integer i : h2.BFS(0, visited))
			System.out.print(i + " ");
		
		System.out.println();
		System.out.println("DFS: ");
		visited = new boolean[v];
		for (Integer i : h2.DFS(0, visited))
			System.out.print(i + " ");
		
		System.out.println();
		System.out.println(h2.isConnected());
		System.out.println(h2.countIsConnected());
	}
}
