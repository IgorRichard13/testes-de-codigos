import java.util.*;

public class MergeSort { // Classe que ordena o vetor

    public Vector<String> OrdenarViaMergeSort(
      Vector<String> vetorDeStrings,
      int tamanhoDoVetor
    ) { // Método que ordena o vetor
      if (tamanhoDoVetor < 2) { // Se o tamanho do vetor for menor que 2, ele retorna o vetor original
        return vetorDeStrings;
      }
      int meio = tamanhoDoVetor / 2; // Divide o vetor em 2 partes
      Vector<String> vetorEsquerda = new Vector<>();
      Vector<String> vetorDireita = new Vector<>();
      for (int i = 0; i < meio; i++) {
        vetorEsquerda.add(vetorDeStrings.get(i));
      }
      for (int i = meio; i < tamanhoDoVetor; i++) {
        vetorDireita.add(vetorDeStrings.get(i));
      }
      vetorEsquerda = OrdenarViaMergeSort(vetorEsquerda, vetorEsquerda.size()); // Chama o método recursivamente para ordenar as partes
      vetorDireita = OrdenarViaMergeSort(vetorDireita, vetorDireita.size());
      vetorDeStrings = Merge(vetorEsquerda, vetorDireita, vetorDeStrings); // Junta as partes ordenadas
      return vetorDeStrings; // Retorna o vetor ordenado
    }
  
    public Vector<String> Merge(
      Vector<String> vetorEsquerda,
      Vector<String> vetorDireita,
      Vector<String> vetorDeStrings
    ) { // Método que junta as partes ordenadas
      int i = 0; // Contador para o vetor esquerda
      int j = 0; // Contador para o vetor direita
      int k = 0; // Contador para o vetor de coisas
      while (i < vetorEsquerda.size() && j < vetorDireita.size()) { // Enquanto não chegar no final dos dois vetores, faz a comparação e junta os vetores
        if (vetorEsquerda.get(i).compareTo(vetorDireita.get(j)) < 0) { // Se o elemento do vetor esquerda for menor que o do vetor direito, ele junta
          vetorDeStrings.set(k, vetorEsquerda.get(i));
          i++;
        } else { // Se o elemento do vetor direito for menor que o do vetor esquerda, ele junta
          vetorDeStrings.set(k, vetorDireita.get(j));
          j++;
        }
        k++; // Incrementa o contador do vetor de coisas
      }
      while (i < vetorEsquerda.size()) { // Se o vetor esquerda ainda tiver elementos, junta
        vetorDeStrings.set(k, vetorEsquerda.get(i));
        i++;
        k++;
      }
      while (j < vetorDireita.size()) { // Se o vetor direita ainda tiver elementos, junta
        vetorDeStrings.set(k, vetorDireita.get(j));
        j++;
        k++;
      }
      return vetorDeStrings; // Retorna o vetor ordenado
    }
  }
  
  /*
 * Fontes Consultadas:
 * https://www.baeldung.com/java-csv
 * https://www.youtube.com/watch?v=HnO13VKQJKo
 * https://www.javatpoint.com/java-vector#:~:text=Vector%20is%20like%20the%20dynamic,is%20found%20in%20the%20java.
 * https://www.baeldung.com/java-merge-sort
 * http://www.universidadejava.com.br/pesquisa_ordenacao/merge-sort/
 * https://beginnersbook.com/2018/10/java-program-to-sort-strings-in-an-alphabetical-order/
 * https://www.w3schools.com/java/ref_string_compareto.asp
 * https://www.youtube.com/watch?v=xLDViuYlqGM
 * https://github.com/devsuperior/aulao002
 * https://copilot.github.com
 */
