# Teste de criação de um programa python em uma linha
# Condições:    Se horas forem iguais, considerar como 24 horas
#               Se a primeira hora for menor que a segunda, considerar o período como um mesmo dia
#               Se a primeira hora for maior que a segunda, considerar o período como um dia diferente

hrInicial = int(input("Hora Inicial: ")); hrFinal = int(input("Hora Final: ")); hrTotal = 24 if (hrInicial == hrFinal) else ((hrFinal - hrInicial)) if (hrInicial < hrFinal) else ((24-hrInicial) + hrFinal); print("Total de Horas: ", hrTotal)
