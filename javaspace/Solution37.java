// sTest1
import java.util.*;

public class Solution37 {
    public static void main(String[] args) {
        // 공항 개수
        int n = 5; 
        // 목적지 idx(+1)
        int k = 4; 
        // {from 공항, to 공항, 시간, 마일리지}
        int[][] paths = {{1,5,1,1},{1,2,4,3,},{1,3,3,2},{2,5,2,1},{2,4,2,3},{3,4,2,2}};

        // 1번 공항에서 k번 공항으로 가는 최단 시간, 최대 마일리지 출력
        System.out.println(Arrays.toString(solution1(n, k, paths)));
        //System.out.println(Arrays.toString(solution2(n, k, paths)));   
    }

    // 다익스트라 알고리즘의 동작
    // 1. 출발 노드와 도착 노드를 설정한다.
    // 2. '최단 거리 테이블'을 초기화한다.
    // 3. 현재 위치한 노드의 인접 노드 중 방문하지 않은 노드를 구별하고, 방문하지 않은 노드 중 거리가 가장 짧은 노드를 선택한다. 그 노드를 방문 처리한다.
    // 4. 해당 노드를 거쳐 다른 노드로 넘어가는 간선 비용(가중치)을 계산해 '최단 거리 테이블'을 업데이트한다.
    // 5. 3~4의 과정을 반복한다.
    
    // '최단 거리 테이블'은 1차원 배열로, N개 노드까지 오는 데 필요한 최단 거리를 기록한다. 
    // N개(1부터 시작하는 노드 번호와 일치시키려면 N + 1개) 크기의 배열을 선언하고 큰 값을 넣어 초기화시킨다.

    // '노드 방문 여부 체크 배열'은 방문한 노드인지 아닌지 기록하기 위한 배열로, 크기는 '최단 거리 테이블'과 같다. 
    // 기본적으로는 False로 초기화하여 방문하지 않았음을 명시한다.

    /* solution 1 start */
    public static int[] solution1(int n, int k, int[][] paths) {
        int[] answer = new int[2];

        if (n==2) {
            answer[0] = paths[0][2];
            answer[1] = paths[0][3];
        }
        
        int V = n;
        k = k-1;

        // 공항 개수만큼 인접 리스트 생성
        List<Node>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        // 최단거리를 저장할 리스트
        ArrayList<Node> minNodeList = new ArrayList<>();
         
        for (int i = 0; i < paths.length; i++) {
            int start = paths[i][0]; // 시작 노드
            int end = paths[i][1]; // 도착 노드
            int weight1 = paths[i][2]; //가중치1
            int weight2 = paths[i][3]; //가중치2 
            
            // 생성한 인접 리스트에 add
            adj[start-1].add(new Node(end-1, weight1, weight2));
            adj[end-1].add(new Node(start-1, weight1, weight2));
        }

        // 다익스트라 알고리즘은 우선순위큐를 사용
        // 우선순위큐는 큐처럼 FIFO가 아닌 우선순위가 높은 데이터가 먼저 나가는 자료구조
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        boolean[] check = new boolean[V];
        Node[] N = new Node[V];
         
        for (int i = 0; i < V; i++) {
            if (i == 0) {
               N[i] = new Node(i, 0, 0);
            } else {
               N[i] = new Node(i, Integer.MAX_VALUE, Integer.MAX_VALUE);
            }
            
            pq.add(N[i]);
        }
         
        while (!pq.isEmpty()) {
            Node node = pq.poll();

            for (Node next : adj[node.v]) {
                // 아직 방문하지 않고, 인접한 다음 방문지의 시간 가중도가 가장 작은 것을 선택
                if (!check[next.v] && N[next.v].weight1 >= N[node.v].weight1 + next.weight1) {
                    // 시간 update
                    N[next.v].weight1 = N[node.v].weight1 + next.weight1;
                    // 마일리지 update
                    N[next.v].weight2 = N[node.v].weight2 + next.weight2;
                  
                    minNodeList.remove(N[next.v]);
                    minNodeList.add(N[next.v]);

                    pq.remove(N[next.v]);
                    pq.add(N[next.v]);
                }
            }
            check[node.v] = true;
        }
   
        for (Node nn : minNodeList) {
            // 도착지가 k일 때의 시간과 마일리지
            if((nn.v)==k) {
                answer[0] = nn.weight1; 
                answer[1] = nn.weight2;
            }
        }
        return answer;
    }

    public static class Node implements Comparable<Node>{
        int v;
        int weight1; // 시간
        int weight2; // 마일리지

        public Node(int v, int weight1, int weight2) {
            this.v = v;
            this.weight1 = weight1;
            this.weight2 = weight2;
        }
        
        // 2차원 Array 정렬
        @Override
        public int compareTo(Node o) {
           int result = Integer.compare(this.weight1, o.weight1);
           
            if(result == 0) { 
                result = Integer.compare(this.weight2, o.weight2);
            }

           return result;
        }
    }
    /* solution 1 end */

    /* solution 2 start */
    public static int[] solution2(int n, int k, int[][] paths) {
        int[] answer = new int[2];
   
        return answer;
    }
    /* solution 2 end */
}
