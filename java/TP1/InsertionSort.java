import java.util.*;

public class InsertionSort { //Método de ordenar usando o Insertion Sort

    public Vector<String> ordenar(Vector<String> vetor) { //Usando Vector<String>
      int i, j; //Declaração de variáveis
      String x; //Declaração de variáveis
  
      for (i = 1; i < vetor.size(); i++) { //Percorre o vetor
        x = vetor.get(i); //Pega o elemento do vetor
        j = i; //Pega o elemento do vetor
        while (j > 0 && x.compareTo(vetor.get(j - 1)) < 0) { //Compara o elemento com o elemento anterior
          vetor.set(j, vetor.get(j - 1)); //Se o elemento for menor, ele troca
          j--; //Decrementa o contador
        }
        vetor.set(j, x); //Se o elemento for maior, ele não troca
      }
      return vetor; // Retorna o vetor ordenado
    }
}
