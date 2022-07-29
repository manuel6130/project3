//**************************************************************************************************
/*
CLASS: Sorter
AUTHOR:
Manuel Sanchez - msanc156 - msanc156@asu.edu
Ilsa Ramirez - iramirez - iramirez@asu.edu
Estevan Perez - eperez56 - eperez56@asu.edu
 */
//**************************************************************************************************
package po3;

import java.util.ArrayList;

public class Sorter {

    private static int partition(ArrayList<Student> pList, int pFromIdx, int pToIdx) {
        Student pivot = pList.get(pFromIdx);
        int leftIndex = pFromIdx - 1;
        int rightIndex = pToIdx + 1;
        while (leftIndex < rightIndex) {
            leftIndex++;
            while (pList.get(leftIndex).compareTo(pivot) == -1) {
                leftIndex++;
            }
            rightIndex--;
            while (pList.get(rightIndex).compareTo(pivot) == 1) {
                rightIndex--;
            }
            if (leftIndex < rightIndex) {
                swap(pList, leftIndex, rightIndex);
            }
        }
        return rightIndex;
    }

    private static void quickSort(ArrayList<Student> pList, int pFromIdx, int pToIdx) {
        if (pFromIdx >= pToIdx) {
            int p = partition(pList, pFromIdx, pToIdx);
            quickSort(pList, pFromIdx, p);
            quickSort(pList, p + 1, pToIdx);
        }
    }

    public static void sort(ArrayList<Student> pList) {
        quickSort(pList, 0, pList.size() - 1);
    }

    private static void swap(ArrayList<Student> pList, int pIdx1, int pIdx2) {
        Student temp = pList.get(pIdx1);
        pList.set(pIdx1, pList.get(pIdx2));
        pList.set(pIdx2, temp);
    }
}
