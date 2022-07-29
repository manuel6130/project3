//**************************************************************************************************
/*
CLASS: Searcher
AUTHOR:
Manuel Sanchez - msanc156 - msanc156@asu.edu
Ilsa Ramirez - iramirez - iramirez@asu.edu
Estevan Perez - eperez56 - eperez56@asu.edu
 */
//**************************************************************************************************
package po3;

import java.util.ArrayList;

public class Searcher {

    public static int search(ArrayList<Student> pList, String pKey) {
        for (int index = 0; index < pList.size(); index++) {
            if (pKey.equals(pList.get(index).getLastName())) {
                return index;
            }
        }
        return -1;
    }
}
