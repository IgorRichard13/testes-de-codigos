import java.util.*;
import java.io.*;

public class ExportToCSVFile { // Classe que exporta o vetor ordenado para um arquivo CSV

    public void ExportarCSV(
      Vector<String> vetorDeStrings,
      long duration,
      String filename
    ) {
      File csv = new File(filename); // Cria o arquivo CSV
      try {
        PrintWriter writer = new PrintWriter(csv);
        writer.println("Nomes ordenados"); // Cabeçalho do arquivo
        for (int i = 0; i < vetorDeStrings.size(); i++) { // Percorre o vetor e escreve os dados no arquivo
          writer.println(vetorDeStrings.get(i));
        }
        writer.println();
        writer.println("Tempo de execução: " + duration + " milissegundos"); // Escreve o tempo de execução no
        // arquivo
        writer.close();
      } catch (Exception e) { // Se der erro, exibe a mensagem
        System.out.println("Erro: " + e.getMessage());
      }
    }
  }