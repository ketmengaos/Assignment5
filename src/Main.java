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
            Tree<String, String> secondTree = new Tree<String, String>();

            while(input.hasNextLine()) {
                word = input.nextLine();
                String[] line = word.split("[ ]");

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

            System.out.println(firstTree);
            String[] treeOne = firstTree.toString().replaceFirst("\\(", "").split("[ ().,;'\"]+");

            System.out.println(Arrays.toString(treeOne));
            for(int i = 0; i < treeOne.length; i = i + 2)
                secondTree.add(treeOne[i+1], treeOne[i]);

            System.out.println(secondTree);

        } catch (FileNotFoundException e) {
            System.out.println("Error Found: " + e);
            System.exit(0);
        }

    }
}
