
     push 0
     push 69
     set
     push 1
     push 110
     set
     push 2
     push 116
     set
     push 3
     push 101
     set
     push 4
     push 114
     set
     push 5
     push 32
     set
     push 6
     push 97
     set
     push 7
     push 32
     set
     push 8
     push 110
     set
     push 9
     push 117
     set
     push 10
     push 109
     set
     push 11
     push 98
     set
     push 12
     push 101
     set
     push 13
     push 114
     set
     push 14
     push 58
     set
     push 15
     push 32
     set
     push 16
     push 0
     set
     push 20
     push 32
     set
     push 21
     push 45
     set
     push 22
     push 62
     set
     push 23
     push 32
     set
     push 24
     push 0
     set
     push 0
     call "D"
     push 100
     iint
     push 100
     get
     push 1
     push 3
     push 2
     call "A"
     exit
part "A"
     push 103
     swap
     set
     push 102
     swap
     set
     push 101
     swap
     set
     push 100
     swap
     set
     push 100
     get
     zero "B"
     push 100
     get
     push 101
     get
     push 102
     get
     push 103
     get
     push 100
     get
     push 1
     sub
     push 101
     get
     push 103
     get
     push 102
     get
     call "A"
     push 103
     swap
     set
     push 102
     swap
     set
     push 101
     swap
     set
     push 100
     swap
     set
     push 101
     get
     oint
     push 20
     call "D"
     push 102
     get
     oint
     call "H"
     push 100
     get
     push 101
     get
     push 102
     get
     push 103
     get
     push 100
     get
     push 1
     sub
     push 103
     get
     push 102
     get
     push 101
     get
     call "A"
     push 103
     swap
     set
     push 102
     swap
     set
     push 101
     swap
     set
     push 100
     swap
     set
part "B"
     back
part "C"
     add
     back
part "D"
     copy
     get
     copy
     zero "E"
     ochr
     push 1
     add
     goto "D"
part "E"
     away
     away
     back
part "F"
     copy
     copy
     ichr
     get
     copy
     push 10
     sub
     zero "G"
     away
     push 1
     add
     goto "F"
part "G"
     away
     push 1
     add
     push 0
     set
     back
part "H"
     push 10
     push 13
     ochr
     ochr
     back
