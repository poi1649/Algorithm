package main

import (
	"fmt"
)

func main() {
	var n int
	fmt.Scan(&n)
	for i := 1; i <= n; i++ {
		solve()
	}
}

func solve() {
	var m int
	fmt.Scan(&m)
	arr := make([]int, m)
	for i := 0; i < m; i++ {
		fmt.Scan(&arr[i])
	}
	set := NewSet()

	for i := 1; i <= 100000; i++ {
		flag := false
		for j := 0; j < m; j++ {
			mod := arr[j] % i
			if set.Contains(mod) {
				flag = true
				set.Clear()
				break
			}
			set.Add(mod)
		}
		if !flag {
			fmt.Println(i)
			return
		}
	}
}

type Set struct {
	data map[int]struct{}
}

func NewSet() *Set {
	return &Set{
		data: make(map[int]struct{}),
	}
}

func (s *Set) Add(v int) {
	s.data[v] = struct{}{}
}

func (s *Set) Remove(v int) {
	delete(s.data, v)
}

func (s *Set) Contains(v int) bool {
	_, ok := s.data[v]
	return ok
}

func (s *Set) Size() int {
	return len(s.data)
}

func (s *Set) Clear() {
	s.data = make(map[int]struct{})
}
