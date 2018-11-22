
     push 0
     push 80
     set
     push 1
     push 108
     set
     push 2
     push 101
     set
     push 3
     push 97
     set
     push 4
     push 115
     set
     push 5
     push 101
     set
     push 6
     push 32
     set
     push 7
     push 101
     set
     push 8
     push 110
     set
     push 9
     push 116
     set
     push 10
     push 101
     set
     push 11
     push 114
     set
     push 12
     push 32
     set
     push 13
     push 121
     set
     push 14
     push 111
     set
     push 15
     push 117
     set
     push 16
     push 114
     set
     push 17
     push 32
     set
     push 18
     push 110
     set
     push 19
     push 97
     set
     push 20
     push 109
     set
     push 21
     push 101
     set
     push 22
     push 58
     set
     push 23
     push 32
     set
     push 24
     push 0
     set
     push 30
     push 72
     set
     push 31
     push 101
     set
     push 32
     push 108
     set
     push 33
     push 108
     set
     push 34
     push 111
     set
     push 35
     push 32
     set
     push 36
     push 0
     set
     push 0
     call "B"
     push 100
     call "D"
     push 30
     call "B"
     push 100
     call "B"
     call "F"
     exit
part "A"
     add
     back
part "B"
     copy
     get
     copy
     zero "C"
     ochr
     push 1
     add
     goto "B"
part "C"
     away
     away
     back
part "D"
     copy
     copy
     ichr
     get
     copy
     push 10
     sub
     zero "E"
     away
     push 1
     add
     goto "D"
part "E"
     away
     push 1
     add
     push 0
     set
     back
part "F"
     push 10
     push 13
     ochr
     ochr
     back
