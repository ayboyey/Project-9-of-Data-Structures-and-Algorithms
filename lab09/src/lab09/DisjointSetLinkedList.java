package lab09;

import java.util.HashSet;
import java.util.Set;


public class DisjointSetLinkedList implements DisjointSetDataStructure
{

    private class Element
    {
        Element representative;
        Element next;
        Element last;
        int length;
        int value;
    }

    private static final int NULL = -1;

    Element arr[];

    public DisjointSetLinkedList(int size)
    {
        arr = new Element[size];
    }

    @Override
    public void makeSet(int item)
    {
        Element e = new Element();
        e.representative = e;
        e.next = null;
        e.length = 1;
        e.last = e;
        e.value = item;
        arr[item] = e;
    }

    @Override
    public int findSet(int item)
    {
        return arr[item].representative.value;
    }

    @Override
    public boolean union(int itemA, int itemB)
    {
        Element repA = arr[itemA].representative;
        Element repB = arr[itemB].representative;
        if (repA.value == repB.value)
        {
            return false;
        }
        if (repB.length > repA.length)
        {
            Element tmp = repA;
            repA = repB;
            repB = tmp;
        }

        Element lastA = repA.last;
        lastA.next = repB;
        repA.last = repB.last;
        while (repB != null)
        {
            repA.length++;
            repB.representative = repA;
            repB = repB.next;
        }
        return true;
    }


    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder("Disjoint sets as linked list:\n");
        Set<Integer> processed = new HashSet<>();
        for (int i = 0; i < arr.length; i++)
        {
            Element e = arr[i].representative;
            if (!processed.contains(e.value))
            {
                processed.add(e.value);
                while (e != null)
                {
                    result.append(e.value);
                    e = e.next;
                    if (e != null)
                    {
                        result.append(", ");
                    }
                }
                if (i < arr.length - 2)
                {
                    result.append("\n");
                }
            }
        }
        return result.toString();
    }

}
