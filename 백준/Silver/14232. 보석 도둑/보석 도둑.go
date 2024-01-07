package main

import (
	"bufio"
	"fmt"
	"os"
	"strings"
)

var reader *bufio.Reader = bufio.NewReader(os.Stdin)
var n int64
var builder strings.Builder

func main() {

	fmt.Fscanln(reader, &n)
	var count int64
	var ans []int64

	for n%2 == 0 {
		count++
		ans = append(ans, 2)
		n /= 2
	}

	var i int64
	for i = 3; i <= 1000000; i += 2 {
		for n%i == 0 {
			count++
			ans = append(ans, i)
			n /= i
		}
	}
	if n > 2 {
		count++
		ans = append(ans, n)
	}

	fmt.Println(count)
	for _, prime := range ans {
		builder.WriteString(fmt.Sprintf("%d ", prime))
	}
	fmt.Println(builder.String())
}
