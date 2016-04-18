//**********************************************************************************
//******** || Ket-Meng Jimmy Cheng ~ April 17, 2016 ~ BST Assignment # 5 || ********
//******** || ---------------------------------------------------------- || ********
//******** || This program reads from an input: the Bible. From this,    || ********
//******** || the program lists the top 100 words ordered by usage       || ********
//******** || by creating two Binary Search Trees: the first to list the || ********
//******** || words and increase the counter every time the word is used || ********
//******** || and the second, which is constructed to list the usage and || ********
//******** || words in order.                                            || ********
//**********************************************************************************

//All code is hosted at github.com/ketmengaos.

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try {
            String word;
            int newValue;

            Scanner input = new Scanner(new FileReader("Bible"));

            Tree<String, Integer> firstTree = new Tree<String, Integer>();
            Tree<Integer, String> secondTree = new Tree<Integer, String>();

            while(input.hasNextLine()) {
                word = input.nextLine();
                String[] line = word.split("[ 0-9():;*,.?!\"]+"); //Should've been all non-whitespace, i know.

                for(int i = 0; i < line.length; i++) {
                    if (firstTree.find(line[i]) == null && !line[i].equals("")) {
                        firstTree.add(line[i], 1);
                    }
                    else if(!line[i].equals("")){
                        newValue = firstTree.find(line[i]) + 1;
                        firstTree.modify(line[i], newValue);
                    }
                }
            }

            //System.out.println(firstTree); //Prints out the first tree. Obviously. Used for debugging.
            String[] treeOne = firstTree.toString().trim().split("[ ]+"); //Split the first BST by spaces.

            //System.out.println(Arrays.toString(treeOne)); //Prints out the first tree in String[] format. Debugging.
            for(int i = 0; i < treeOne.length; i = i + 2)
                secondTree.add(Integer.parseInt(treeOne[i+1]), treeOne[i]);

            //System.out.println(secondTree); //Prints out the second tree in BST format.

            for(int i = 0; i < 100; i++) {
                System.out.println(secondTree.removeRightMost().data);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error Found: " + e);
            System.exit(0);
        }

    }
}
