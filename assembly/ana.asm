extern _printf
global _main

section .data
msg: db "Hello World",10,0

section .text
_main:
    push msg
    call _printf
    add esp,4   
    ret
