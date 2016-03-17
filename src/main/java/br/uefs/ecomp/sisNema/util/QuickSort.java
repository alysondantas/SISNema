package br.uefs.ecomp.sisNema.util;

import br.uefs.ecomp.sisNema.model.*;

public class QuickSort {
    
    //metodo de ordenação
    public static void quickSort(Comprador[] array, int inicio, int fim) {
         int meio;

        if (inicio < fim) {
            //realiza chamada recursiva, dividindo o array em "sub-arrays" até que esteja completamente ordenado
            meio = metaderecursiva(array, inicio, fim);
            quickSort(array, inicio, meio);
            quickSort(array, meio + 1, fim);
        }
    }

    //percorre o array dividindo-o na ultima posicao em que foi realizada uma troca
    public static int metaderecursiva(Comprador[] array, int inicio, int fim) {
        Comprador pivo;
        int topo; 
        int contador;
        pivo = array[inicio];
        topo = inicio;

        //percorre array de compradores a partir da segunda posicao
        for (contador = inicio + 1; contador <= fim; contador++) {
            //verifica se um nome é maior que outro e realiza a troca de posicoes
            if (array[contador].getNome().compareToIgnoreCase(pivo.getNome()) > 0) {
                array[topo] = array[contador];
                array[contador] = array[topo + 1];
                topo++;
            }
        }
        array[topo] = pivo;
        return topo;
    }
}