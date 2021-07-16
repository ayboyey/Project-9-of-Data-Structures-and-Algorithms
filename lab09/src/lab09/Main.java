package lab09;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main
{

    static Scanner scan; // for input stream
    private static final String input = "go 10\n" + "ll 8\n" + "findset 4\n" + "union 1 2\n" + "union 3 4\n" + "union 0 1\n" + "union 0 4\n" + "union 5 7\n" + "show\n" + "findset 1\n" + "findset 4\n" + "findset 7\n" + "union 2 3\n" + "ch 1\n" + "dsf 8\n" + "findset 4\n" + "union 1 2\n" + "union 3 4\n" + "union 0 1\n" + "union 0 4\n" + "union 5 7\n" + "show\n" + "findset 1\n" + "findset 4\n" + "findset 7\n" + "show\n" + "union 2 3\n" + "ha";

    public static void main(String[] args)
    {
        System.out.println("START");
        scan = new Scanner(System.in);
        //scan = new Scanner(input);
        DisjointSetDataStructure[] dsds = null;
        int currentPos = 0;
        boolean halt = false;
        int maxNo = -1;
        while (!halt)
        {
            String line = scan.nextLine();
            // empty line and comment line - read next line
            if (line.length() == 0 || line.charAt(0) == '#')
            {
                continue;
            }
            // copy line to output (it is easier to find a place of a mistake)
            System.out.println("!" + line);
            String word[] = line.split(" ");
            // go n - start with array of the length n
            if (word[0].equalsIgnoreCase("go") && word.length == 2)
            {
                maxNo = Integer.parseInt(word[1]);
                dsds = new DisjointSetDataStructure[maxNo];
                continue;
            }
            //ch - change index
            if (word[0].equalsIgnoreCase("ch") && word.length == 2)
            {
                currentPos = Integer.parseInt(word[1]);
                continue;
            }
            // show
            if (word[0].equalsIgnoreCase("show") && word.length == 1)
            {
                System.out.println(dsds[currentPos].toString());
                continue;
            }
            // ha
            if (word[0].equalsIgnoreCase("ha") && word.length == 1)
            {
                halt = true;
                continue;
            }
            // ll <size>
            // linkedlist <size>
            if ((word[0].equalsIgnoreCase("ll") || word[0].equalsIgnoreCase("linkedlist")) && word.length == 2)
            {
                int size = Integer.parseInt(word[1]);
                dsds[currentPos] = new DisjointSetLinkedList(size);
                for (int i = 0; i < size; i++)
                {
                    dsds[currentPos].makeSet(i);
                }
                continue;
            }
            // dsf <size>
            if (word[0].equalsIgnoreCase("dsf") && word.length == 2)
            {
                int size = Integer.parseInt(word[1]);
                dsds[currentPos] = new DisjointSetForest(size);
                for (int i = 0; i < size; i++)
                {
                    dsds[currentPos].makeSet(i);
                }
                continue;
            }
            // findset <item>
            if (word[0].equalsIgnoreCase("findset") && word.length == 2)
            {
                int item = Integer.parseInt(word[1]);
                System.out.println(dsds[currentPos].findSet(item));
                continue;
            }
            // union <itemA> <itemB>
            if (word[0].equalsIgnoreCase("union") && word.length == 3)
            {
                int itemA = Integer.parseInt(word[1]);
                int itemB = Integer.parseInt(word[2]);
                System.out.println(dsds[currentPos].union(itemA, itemB));
                continue;
            }
            System.out.println("Wrong command");
        }
        System.out.println("END OF EXECUTION");
        scan.close();

    }


}