# [Platinum III] Simplifying the Farm - 5915 

[문제 링크](https://www.acmicpc.net/problem/5915) 

### 성능 요약

메모리: 76204 KB, 시간: 968 ms

### 분류

그래프 이론, 최소 스패닝 트리

### 문제 설명

<p>Farmer John has been taking an evening algorithms course at his local university, and he has just learned about minimum spanning trees.  However, Farmer John now realizes that the design of his farm is not as efficient as it could be, and he wants to simplify the layout of his farm.</p><p>The farm is currently arranged like a graph, with vertices representing fields and edges representing pathways between these fields, each having an associated length.  Farmer John notes that for each distinct length, at most three pathways on his farm share this length.  FJ would like to remove some of the pathways on his farm so that it becomes a tree -- that is, so that there is one unique route between any pair of fields.  Moreover, Farmer John would like this to be a minimum spanning tree -- a tree having the smallest possible sum of edge lengths.</p><p>Help Farmer John compute not only the sum of edge lengths in a minimum spanning tree derived from his farm graph, but also the number of different possible minimum spanning trees he can create.</p>

### 입력 

 <ul><li>Line 1: Two integers N and M (1 <= N <= 40,000; 1 <= M <= 100,000), representing  the number of vertices and edges in the farm graph, respectively.  Vertices are numbered as 1..N.</li><li>Lines 2..M+1: Three integers a_i, b_i and n_i (1 <= a_i, b_i <= N; 1 <= n_i <= 1,000,000)  representing an edge from vertex a_i to b_i with length n_i.  No edge length n_i will occur more than three times.</li></ul>

### 출력 

 <ul><li>Line 1: Two integers representing the length of the minimal spanning tree and the number of minimal spanning trees (mod 1,000,000,007).</li></ul>

