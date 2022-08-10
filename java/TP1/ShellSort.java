import java.util.*;

public class ShellSort {
    Vector<String> listaNomes = new Vector<String>(); // Cria um vetor de Strings
  
    public Vector<String> ordenar(Vector<String> vectorNames) { // Método que ordena o vetor
      int i, j, h = 1; // Cria variaveis
      String temp; // Cria variavel
      while (h <= vectorNames.size() / 3) { // Enquanto h for menor ou igual ao tamanho do vetor dividido por 3, h recebe o valor de h + 1
        h = h * 3 + 1;
      }
      while (h > 0) { // Enquanto h for maior que 0,
        for (i = h; i < vectorNames.size(); i++) { // Para i igual a h,
          temp = vectorNames.get(i); // temp recebe o valor do vetor na posição i
          for (
            j = i;
            j >= h && vectorNames.get(j - h).compareTo(temp) > 0;
            j -= h
          ) { // Para j igual a i, e enquanto j for maior que h e o vetor na posição j - h for maior que temp,
            vectorNames.set(j, vectorNames.get(j - h)); // o vetor na posição j recebe o vetor na posição j - h
          }
          vectorNames.set(j, temp); // o vetor na posição j recebe o valor de temp
        }
        h = (h - 1) / 3; // h recebe o valor de h - 1 dividido por 3
      }
      return vectorNames; // Retorna o vetor ordenado
    }
}
