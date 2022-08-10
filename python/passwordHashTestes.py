# Teste de verificação de senha utilizando o algoritmo de hash MD5

import hashlib

hashedpassword = hashlib.md5(input("Digite a senha para cadastro: ").encode('utf-8'))
print("O token gerado por sua senha foi " + hashedpassword.hexdigest())

while True:
    passwordTest = hashlib.md5(input("Insira a senha: ").encode('utf-8'))
    print("O token gerado por esta senha foi " + passwordTest.hexdigest())
    if(passwordTest.hexdigest() == hashedpassword.hexdigest()):
        break
    print("As senhas não coincidem! Tente novamente")
    
print("Senha confirmada! Bem vindo, usuário.")
