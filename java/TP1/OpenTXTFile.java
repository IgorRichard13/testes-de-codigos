import java.util.*;
import java.io.*;

public class OpenTXTFile { // Classe que lê o arquivo TXT e retorna um vetor com os dados

    public Vector<String> LerTXT(String file) {
      Scanner scanner;
      File txt = new File(file);
      Vector<String> vetorDeStrings = new Vector<>();
  
      try { // Tenta abrir o arquivo
        scanner = new Scanner(txt);
        scanner.nextLine(); // Pula a primeira linha do arquivo
  
        while (scanner.hasNextLine()) { // Enquanto o arquivo ainda tiver linhas, pega os dados
          String line = scanner.nextLine(); // Pega a linha
          vetorDeStrings.add(line); // Adiciona o dado no vetor
        }
        scanner.close(); // Fecha o scanner
      } catch (Exception e) { // Se der erro, exibe a mensagem
        System.out.println("Erro: " + e.getMessage());
      }
      return vetorDeStrings;
    }
}
