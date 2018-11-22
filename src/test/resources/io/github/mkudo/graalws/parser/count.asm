
     push 1
part "A"
     copy
     oint
     push 10
     ochr
     push 1
     add
     copy
     push 11
     sub
     zero "B"
     goto "A"
part "B"
     away
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
