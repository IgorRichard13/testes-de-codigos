import java.io.*;
import java.util.*;

public class Ordenar { // Classe Principal

  public static void main(String[] args) {
    OpenTXTFile openTXTFile = new OpenTXTFile(); // Instanciando a classe OpenTXTFile
    MergeSort mergesort = new MergeSort(); // Instanciando a classe MergeSort
    InsertionSort insertionSort = new InsertionSort(); // Instanciando a classe InsertionSort
    ShellSort shellSort = new ShellSort(); // Instanciando a classe ShellSort
    ExportToCSVFile exportar = new ExportToCSVFile(); // Instanciando a classe ExportToCSVFile
    Vector<String> vetorDeStrings; // Vetor de Strings
    long startTime, endTime, duration; // Variáveis de tempo

    vetorDeStrings = openTXTFile.LerTXT("entrada-100-ale.txt"); // Lendo o arquivo de entrada
    startTime = System.currentTimeMillis(); // Iniciando o tempo
    vetorDeStrings =
      mergesort.OrdenarViaMergeSort(vetorDeStrings, vetorDeStrings.size()); // Ordenando o vetor de Strings
    endTime = System.currentTimeMillis(); // Finalizando o tempo
    duration = endTime - startTime; // Calculando o tempo
    exportar.ExportarCSV(
      vetorDeStrings,
      duration,
      "entrada-100-ale-ordenada-por-mergesort.csv"
    ); // Exportando o vetor de Strings ordenado para um arquivo CSV

    vetorDeStrings = openTXTFile.LerTXT("entrada-100-cre.txt");
    startTime = System.currentTimeMillis();
    vetorDeStrings =
      mergesort.OrdenarViaMergeSort(vetorDeStrings, vetorDeStrings.size());
    endTime = System.currentTimeMillis();
    duration = endTime - startTime;
    exportar.ExportarCSV(
      vetorDeStrings,
      duration,
      "entrada-100-cre-ordenada-por-mergesort.csv"
    );

    vetorDeStrings = openTXTFile.LerTXT("entrada-100-dec.txt");
    startTime = System.currentTimeMillis();
    vetorDeStrings =
      mergesort.OrdenarViaMergeSort(vetorDeStrings, vetorDeStrings.size());
    endTime = System.currentTimeMillis();
    duration = endTime - startTime;
    exportar.ExportarCSV(
      vetorDeStrings,
      duration,
      "entrada-100-dec-ordenada-por-mergesort.csv"
    );

    vetorDeStrings = openTXTFile.LerTXT("entrada-1000-ale.txt");
    startTime = System.currentTimeMillis();
    vetorDeStrings =
      mergesort.OrdenarViaMergeSort(vetorDeStrings, vetorDeStrings.size());
    endTime = System.currentTimeMillis();
    duration = endTime - startTime;
    exportar.ExportarCSV(
      vetorDeStrings,
      duration,
      "entrada-1000-ale-ordenada-por-mergesort.csv"
    );

    vetorDeStrings = openTXTFile.LerTXT("entrada-1000-cre.txt");
    startTime = System.currentTimeMillis();
    vetorDeStrings =
      mergesort.OrdenarViaMergeSort(vetorDeStrings, vetorDeStrings.size());
    endTime = System.currentTimeMillis();
    duration = endTime - startTime;
    exportar.ExportarCSV(
      vetorDeStrings,
      duration,
      "entrada-1000-cre-ordenada-por-mergesort.csv"
    );

    vetorDeStrings = openTXTFile.LerTXT("entrada-1000-dec.txt");
    startTime = System.currentTimeMillis();
    vetorDeStrings =
      mergesort.OrdenarViaMergeSort(vetorDeStrings, vetorDeStrings.size());
    endTime = System.currentTimeMillis();
    duration = endTime - startTime;
    exportar.ExportarCSV(
      vetorDeStrings,
      duration,
      "entrada-1000-dec-ordenada-por-mergesort.csv"
    );

    vetorDeStrings = openTXTFile.LerTXT("entrada-10000-ale.txt");
    startTime = System.currentTimeMillis();
    vetorDeStrings =
      mergesort.OrdenarViaMergeSort(vetorDeStrings, vetorDeStrings.size());
    endTime = System.currentTimeMillis();
    duration = endTime - startTime;
    exportar.ExportarCSV(
      vetorDeStrings,
      duration,
      "entrada-10000-ale-ordenada-por-mergesort.csv"
    );

    vetorDeStrings = openTXTFile.LerTXT("entrada-10000-cre.txt");
    startTime = System.currentTimeMillis();
    vetorDeStrings =
      mergesort.OrdenarViaMergeSort(vetorDeStrings, vetorDeStrings.size());
    endTime = System.currentTimeMillis();
    duration = endTime - startTime;
    exportar.ExportarCSV(
      vetorDeStrings,
      duration,
      "entrada-10000-cre-ordenada-por-mergesort.csv"
    );

    vetorDeStrings = openTXTFile.LerTXT("entrada-10000-dec.txt");
    startTime = System.currentTimeMillis();
    vetorDeStrings =
      mergesort.OrdenarViaMergeSort(vetorDeStrings, vetorDeStrings.size());
    endTime = System.currentTimeMillis();
    duration = endTime - startTime;
    exportar.ExportarCSV(
      vetorDeStrings,
      duration,
      "entrada-10000-dec-ordenada-por-mergesort.csv"
    );

    vetorDeStrings = openTXTFile.LerTXT("entrada-100-ale.txt");
    startTime = System.currentTimeMillis();
    vetorDeStrings = insertionSort.ordenar(vetorDeStrings);
    endTime = System.currentTimeMillis();
    duration = endTime - startTime;
    exportar.ExportarCSV(
      vetorDeStrings,
      duration,
      "entrada-100-ale-ordenada-por-insertionsort.csv"
    );

    vetorDeStrings = openTXTFile.LerTXT("entrada-100-cre.txt");
    startTime = System.currentTimeMillis();
    vetorDeStrings = insertionSort.ordenar(vetorDeStrings);
    endTime = System.currentTimeMillis();
    duration = endTime - startTime;
    exportar.ExportarCSV(
      vetorDeStrings,
      duration,
      "entrada-100-cre-ordenada-por-insertionsort.csv"
    );

    vetorDeStrings = openTXTFile.LerTXT("entrada-100-dec.txt");
    startTime = System.currentTimeMillis();
    vetorDeStrings = insertionSort.ordenar(vetorDeStrings);
    endTime = System.currentTimeMillis();
    duration = endTime - startTime;
    exportar.ExportarCSV(
      vetorDeStrings,
      duration,
      "entrada-100-dec-ordenada-por-insertionsort.csv"
    );

    vetorDeStrings = openTXTFile.LerTXT("entrada-1000-ale.txt");
    startTime = System.currentTimeMillis();
    vetorDeStrings = insertionSort.ordenar(vetorDeStrings);
    endTime = System.currentTimeMillis();
    duration = endTime - startTime;
    exportar.ExportarCSV(
      vetorDeStrings,
      duration,
      "entrada-1000-ale-ordenada-por-insertionsort.csv"
    );

    vetorDeStrings = openTXTFile.LerTXT("entrada-1000-cre.txt");
    startTime = System.currentTimeMillis();
    vetorDeStrings = insertionSort.ordenar(vetorDeStrings);
    endTime = System.currentTimeMillis();
    duration = endTime - startTime;
    exportar.ExportarCSV(
      vetorDeStrings,
      duration,
      "entrada-1000-cre-ordenada-por-insertionsort.csv"
    );

    vetorDeStrings = openTXTFile.LerTXT("entrada-1000-dec.txt");
    startTime = System.currentTimeMillis();
    vetorDeStrings = insertionSort.ordenar(vetorDeStrings);
    endTime = System.currentTimeMillis();
    duration = endTime - startTime;
    exportar.ExportarCSV(
      vetorDeStrings,
      duration,
      "entrada-1000-dec-ordenada-por-insertionsort.csv"
    );

    vetorDeStrings = openTXTFile.LerTXT("entrada-10000-ale.txt");
    startTime = System.currentTimeMillis();
    vetorDeStrings = insertionSort.ordenar(vetorDeStrings);
    endTime = System.currentTimeMillis();
    duration = endTime - startTime;
    exportar.ExportarCSV(
      vetorDeStrings,
      duration,
      "entrada-10000-ale-ordenada-por-insertionsort.csv"
    );

    vetorDeStrings = openTXTFile.LerTXT("entrada-10000-cre.txt");
    startTime = System.currentTimeMillis();
    vetorDeStrings = insertionSort.ordenar(vetorDeStrings);
    endTime = System.currentTimeMillis();
    duration = endTime - startTime;
    exportar.ExportarCSV(
      vetorDeStrings,
      duration,
      "entrada-10000-cre-ordenada-por-insertionsort.csv"
    );

    vetorDeStrings = openTXTFile.LerTXT("entrada-10000-dec.txt");
    startTime = System.currentTimeMillis();
    vetorDeStrings = insertionSort.ordenar(vetorDeStrings);
    endTime = System.currentTimeMillis();
    duration = endTime - startTime;
    exportar.ExportarCSV(
      vetorDeStrings,
      duration,
      "entrada-10000-dec-ordenada-por-insertionsort.csv"
    );

    vetorDeStrings = openTXTFile.LerTXT("entrada-100-ale.txt");
    startTime = System.currentTimeMillis();
    vetorDeStrings = shellSort.ordenar(vetorDeStrings);
    endTime = System.currentTimeMillis();
    duration = endTime - startTime;
    exportar.ExportarCSV(
      vetorDeStrings,
      duration,
      "entrada-100-ale-ordenada-por-shellsort.csv"
    );

    vetorDeStrings = openTXTFile.LerTXT("entrada-100-cre.txt");
    startTime = System.currentTimeMillis();
    vetorDeStrings = shellSort.ordenar(vetorDeStrings);
    endTime = System.currentTimeMillis();
    duration = endTime - startTime;
    exportar.ExportarCSV(
      vetorDeStrings,
      duration,
      "entrada-100-cre-ordenada-por-shellsort.csv"
    );

    vetorDeStrings = openTXTFile.LerTXT("entrada-100-dec.txt");
    startTime = System.currentTimeMillis();
    vetorDeStrings = shellSort.ordenar(vetorDeStrings);
    endTime = System.currentTimeMillis();
    duration = endTime - startTime;
    exportar.ExportarCSV(
      vetorDeStrings,
      duration,
      "entrada-100-dec-ordenada-por-shellsort.csv"
    );

    vetorDeStrings = openTXTFile.LerTXT("entrada-1000-ale.txt");
    startTime = System.currentTimeMillis();
    vetorDeStrings = shellSort.ordenar(vetorDeStrings);
    endTime = System.currentTimeMillis();
    duration = endTime - startTime;
    exportar.ExportarCSV(
      vetorDeStrings,
      duration,
      "entrada-1000-ale-ordenada-por-shellsort.csv"
    );

    vetorDeStrings = openTXTFile.LerTXT("entrada-1000-cre.txt");
    startTime = System.currentTimeMillis();
    vetorDeStrings = shellSort.ordenar(vetorDeStrings);
    endTime = System.currentTimeMillis();
    duration = endTime - startTime;
    exportar.ExportarCSV(
      vetorDeStrings,
      duration,
      "entrada-1000-cre-ordenada-por-shellsort.csv"
    );

    vetorDeStrings = openTXTFile.LerTXT("entrada-1000-dec.txt");
    startTime = System.currentTimeMillis();
    vetorDeStrings = shellSort.ordenar(vetorDeStrings);
    endTime = System.currentTimeMillis();
    duration = endTime - startTime;
    exportar.ExportarCSV(
      vetorDeStrings,
      duration,
      "entrada-1000-dec-ordenada-por-shellshort.csv"
    );

    vetorDeStrings = openTXTFile.LerTXT("entrada-10000-ale.txt");
    startTime = System.currentTimeMillis();
    vetorDeStrings = shellSort.ordenar(vetorDeStrings);
    endTime = System.currentTimeMillis();
    duration = endTime - startTime;
    exportar.ExportarCSV(
      vetorDeStrings,
      duration,
      "entrada-10000-ale-ordenada-por-shellsort.csv"
    );

    vetorDeStrings = openTXTFile.LerTXT("entrada-10000-cre.txt");
    startTime = System.currentTimeMillis();
    vetorDeStrings = shellSort.ordenar(vetorDeStrings);
    endTime = System.currentTimeMillis();
    duration = endTime - startTime;
    exportar.ExportarCSV(
      vetorDeStrings,
      duration,
      "entrada-10000-cre-ordenada-por-shellsort.csv"
    );

    vetorDeStrings = openTXTFile.LerTXT("entrada-10000-dec.txt");
    startTime = System.currentTimeMillis();
    vetorDeStrings = shellSort.ordenar(vetorDeStrings);
    endTime = System.currentTimeMillis();
    duration = endTime - startTime;
    exportar.ExportarCSV(
      vetorDeStrings,
      duration,
      "entrada-10000-dec-ordenada-por-shellshort.csv"
    );
  }
}
