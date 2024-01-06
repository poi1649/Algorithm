package main

import (
	"fmt"
	"sort"
	"strings"
)

var n int

func main() {
	//var n int
	//fmt.Scan(&n)
	for i := 1; i <= 1; i++ {
		solve()
	}
}

func solve() {
	fmt.Scan(&n)
	arr := make([]int, n)
	for i := 0; i < n; i++ {
		fmt.Scan(&arr[i])
	}
	sort.Ints(arr)
	// joining array's elements into a string, separated by a space
	fmt.Println(strings.Trim(fmt.Sprint(arr), "[]"))
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
