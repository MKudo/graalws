
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
     push 115
     set
     push 7
     push 111
     set
     push 8
     push 109
     set
     push 9
     push 101
     set
     push 10
     push 32
     set
     push 11
     push 110
     set
     push 12
     push 117
     set
     push 13
     push 109
     set
     push 14
     push 98
     set
     push 15
     push 101
     set
     push 16
     push 114
     set
     push 17
     push 115
     set
     push 18
     push 44
     set
     push 19
     push 32
     set
     push 20
     push 116
     set
     push 21
     push 104
     set
     push 22
     push 101
     set
     push 23
     push 110
     set
     push 24
     push 32
     set
     push 25
     push 45
     set
     push 26
     push 49
     set
     push 27
     push 32
     set
     push 28
     push 116
     set
     push 29
     push 111
     set
     push 30
     push 32
     set
     push 31
     push 102
     set
     push 32
     push 105
     set
     push 33
     push 110
     set
     push 34
     push 105
     set
     push 35
     push 115
     set
     push 36
     push 104
     set
     push 37
     push 0
     set
     push 42
     push 78
     set
     push 43
     push 117
     set
     push 44
     push 109
     set
     push 45
     push 98
     set
     push 46
     push 101
     set
     push 47
     push 114
     set
     push 48
     push 58
     set
     push 49
     push 0
     set
     push 60
     push 84
     set
     push 61
     push 111
     set
     push 62
     push 116
     set
     push 63
     push 97
     set
     push 64
     push 108
     set
     push 65
     push 32
     set
     push 66
     push 105
     set
     push 67
     push 115
     set
     push 68
     push 32
     set
     push 69
     push 0
     set
     push 0
     call "D"
     call "H"
     push 100
     push 0
     set
part "A"
     push 42
     call "D"
     push 101
     iint
     push 101
     get
     copy
     push -1
     sub
     zero "B"
     push 100
     get
     add
     push 100
     swap
     set
     goto "A"
part "B"
     away
     push 60
     call "D"
     push 100
     get
     oint
     call "H"
     exit
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
