package main

import (
	"bufio"
	"fmt"
	"os"
	"sort"
	"strconv"
	"strings"
)

var n int

func main() {
	//fmt.Scan(&n)
	for i := 1; i <= 1; i++ {
		solve()
	}
}

func solve() {
	reader := bufio.NewReader(os.Stdin)
	writer := bufio.NewWriter(os.Stdout)
	s2, _, _ := reader.ReadLine()
	n, _ = strconv.Atoi(string(s2))
	arr := make([]int, 0, n)

	for i := 0; i < n; i++ {
		var number int
		fmt.Fscan(reader, &number)
		arr = append(arr, number)
	}

	sort.Ints(arr)
	writer.WriteString(strings.Trim(fmt.Sprint(arr), "[]"))
	writer.Flush()
}
