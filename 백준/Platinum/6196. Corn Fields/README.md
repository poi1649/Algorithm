# [Platinum IV] Corn Fields - 6196 

[문제 링크](https://www.acmicpc.net/problem/6196) 

### 성능 요약

메모리: 3944 KB, 시간: 4 ms

### 분류

비트마스킹, 다이나믹 프로그래밍, 비트필드를 이용한 다이나믹 프로그래밍

### 문제 설명

<p>Farmer John has purchased a lush new rectangular pasture composed of M by N (1 <= M <= 12; 1 <= N <= 12) square parcels. He wants to grow some yummy corn for the cows on a number of squares. Regrettably, some of the squares are infertile and can't be planted. Canny FJ knows that the cows dislike eating close to each other, so when choosing which squares to plant, he avoids choosing squares that are adjacent; no two chosen squares share an edge. He has not yet made the final choice as to which squares to plant.</p>

<p>Being a very open-minded man, Farmer John wants to consider all possible options for how to choose the squares for planting. He is so open-minded that he considers choosing no squares as a valid option!  Please help Farmer John determine the number of ways he can choose the squares to plant.</p>

### 입력 

 <ul>
	<li>Line 1: Two space-separated integers: M and N</li>
	<li>Lines 2..M+1: Line i+1 describes row i of the pasture with N space-separated integers indicating whether a square is fertile (1 for fertile, 0  for infertile)</li>
</ul>

<p> </p>

### 출력 

 <ul>
	<li>Line 1: One integer: the number of ways that FJ can choose the squares modulo 100,000,000.</li>
</ul>

