import java.util.ArrayList;
import java.util.Arrays;

class AminoAcidLL {
  char aminoAcid;
  String[] codons;
  int[] counts;
  AminoAcidLL next;

  /**************************/
//  /*These attributes were not part of the original file, but I created them to help my methods.*/
  static AminoAcidLL head;
  static AminoAcidLL iterator;

  AminoAcidLL() {

  }

  /********************************************************************************************/
  /* Creates a new node, with a given amino acid/codon
   * pair and increments the codon counter for that codon.
   * NOTE: Does not check for repeats!! */
  public AminoAcidLL(String inCodon) {
    aminoAcid = AminoAcidResources.getAminoAcidFromCodon(inCodon);
    codons = AminoAcidResources.getCodonListForAminoAcid(aminoAcid);
    counts = new int[codons.length];
    findCodon(inCodon);
    next = null;
  }


  /********************************************************************************************/
  /* Helper Method that searches through current node's codons list. If the codon is found, then the codon
   *counter increases in the counts array.
   */
  public void findCodon(String inCodon) {
    /* We search through the codons list IF the codon in the parameter is not NULL or "*" .*/
    for (int i = 0; i < codons.length; i++) {
      /*Compare the current codon string with the codon we have*/
      /* If the codon is found in the list, increase it's count by 1.*/
      if (inCodon.equals('*') || inCodon.equals('\0')) {
        /* if inCodon is either STOP or NULL, continue*/
        continue;
      } else {
        if (codons[i].equals(inCodon)) {
          counts[i] += 1;
        }
      }
      /* If the codon does not match, do not increase count and continue traversing the list.*/
    }

  }

  /* Helper method that prints the linked list and it's contents*/
  public static void printLList(AminoAcidLL a) {
    AminoAcidLL temp = a;
    while (temp != null) {
      System.out.print("\nAmino Acid: " + temp.aminoAcid);
      System.out.print("\nCodon list: ");
      System.out.print(Arrays.toString(temp.codons));
      System.out.print("\nCount list: ");
      System.out.print(Arrays.toString(temp.counts));
      System.out.println();

      temp = temp.next;
    }
  }

  /********************************************************************************************/
  /* Recursive method that increments the count for a specific codon:
   * If it should be at this node, increments it and stops,
   * if not passes the task to the next node.
   * If there is no next node, add a new node to the list that would contain the codon.
   */
  private void addCodon(String inCodon) {

      if(aminoAcid == AminoAcidResources.getAminoAcidFromCodon(inCodon)){

        findCodon(inCodon);
      }else if(next != null){

        next.addCodon(inCodon);
      }else if (next == null){

        next = new AminoAcidLL(inCodon);

      }



  }

  /********************************************************************************************/
  /* Shortcut to find the total number of instances of this amino acid */
  private int totalCount() {
    //sum up total in counts[] for specific node ,,,,is called by aminoAcidCounts()
    //System.out.println("The current amino acid being counted is.." + next.aminoAcid);
    int sum = 0;
    for (int i = 0; i < counts.length; i++) {
      sum += counts[i];
    }
    return sum;

  }

  /********************************************************************************************//*
  /* helper method for finding the list difference on two matching nodes
  *  must be matching, but this is not tracked */
  private int totalDiff(AminoAcidLL inList) {
    //only use if both lists are NOT empty
    return Math.abs(totalCount() - inList.totalCount());
  }


  /********************************************************************************************//*
  /* helper method for finding the list difference on two matching nodes
  *  must be matching, but this is not tracked */
  private int codonDiff(AminoAcidLL inList) {
    int diff = 0;
    for (int i = 0; i < codons.length; i++) {
      diff += Math.abs(counts[i] - inList.counts[i]);
    }
    return diff;
  }

  /********************************************************************************************//*
  /* Recursive method that finds the differences in **Amino Acid** counts.
   * the list *must* be sorted to use this method */
  public int aminoAcidCompare(AminoAcidLL inList) {
    //**Done with Cynthia during office hours **//

    //base case
    //this checks if inList is sorted, if it is not let the user know it needs to be sorted.


    // creating a variable diff that will store the difference in amino acid counts.//
    int diff = 0;

    if(inList == null) {
      diff += totalCount();

      if (next != null) {
        diff += next.aminoAcidCompare(inList);
      }
    }
    else if (aminoAcid == inList.aminoAcid) {
        diff = totalDiff(inList);

      if(next != null){
        diff += next.aminoAcidCompare(inList.next);
      }

      if(next == null && inList.next != null){
        diff += aminoAcidCompare(inList.next);
      }
    }
    else if(next != null && aminoAcid < inList.aminoAcid){
      diff += totalCount();
        if(next != null){
          diff += next.aminoAcidCompare(inList);
        }
    }
    else if(next== null || aminoAcid > inList.aminoAcid){
      diff += inList.totalCount();
        if(inList.next != null){
          diff += aminoAcidCompare(inList.next);
        }

    }

    return diff;
  }


  /********************************************************************************************//*
   /* Same as above, but counts the codon usage differences
   * Must be sorted. */
  public int codonCompare(AminoAcidLL inList) {
    //**Done with Cynthia during office hours **//
    //this checks if inList is sorted, if it is not let the user know it needs to be sorted.


    // creating a variable diff that will store the difference in codon usage.//
    int diff = 0;

    if(inList == null) {
      diff += totalCount();

      if (next != null) {
        diff += next.codonCompare(inList);
      }
    }
    else if (aminoAcid == inList.aminoAcid) {
      diff = codonDiff(inList);

      if(next != null){
        diff += next.codonCompare(inList.next);
      }

      if(next == null && inList.next != null){
        diff += codonCompare(inList.next);
      }
    }
    else if(next != null && aminoAcid < inList.aminoAcid){
      diff += totalCount();
      if(next != null){
        diff += next.codonCompare(inList);
      }
    }
    else if(next== null || aminoAcid > inList.aminoAcid){
      diff += inList.totalCount();
      if(inList.next != null){
        diff += codonCompare(inList.next);
      }

    }

    return diff;
  }


  /********************************************************************************************//*
  /* Recursively returns the total list of amino acids in the order that they are in in the linked list. */
  public char[] aminoAcidList() {
    // **Worked on with Cynthia during office hours**
    if (next == null) {
      return new char[]{aminoAcid};
    }

    //creating a temp char array that stores the amino acids of the linked list.(but it does not store the first amino acid)
    char[] temp = next.aminoAcidList();

    char[] toReturn = new char[temp.length + 1];
    //storing first amino acid into the first index of the returning array.
    toReturn[0] = aminoAcid;

    //populating toReturn with all the amino acids
    for (int i = 0; i < temp.length; i++) {
      toReturn[i + 1] = temp[i];
    }

    return toReturn;
  }

  /********************************************************************************************//*
  /* Recursively returns the total counts of amino acids in the order that they are in in the linked list. */
  public int[] aminoAcidCounts() {
    //**Also worked on this with Cynthia during office hours**
    if (next == null) {
      return new int[]{totalCount()};
    }
    //creating a temp int array that stores the amino acid counts
    int[] temp = next.aminoAcidCounts();

    int[] toReturn = new int[temp.length + 1];

    toReturn[0] = totalCount();

    for (int i = 0; i < temp.length; i++) {
      toReturn[i + 1] = temp[i];
    }


    return toReturn;

  }


  /********************************************************************************************//*
  /* recursively determines if a linked list is sorted or not */
  public boolean isSorted() {
    /*Base Cases*/
    if (next == null) { // if the whole list has been searched without stopping, then it IS sorted.
      return true;
    }

    if (aminoAcid > next.aminoAcid) {
      return false;
    } else {
      next.isSorted();
    }
    return true;
  }


  /********************************************************************************************//*
  /* Static method for generating a linked list from an RNA sequence */
  public static AminoAcidLL createFromRNASequence(String inSequence) {
    head = null; // This node "head" is to not be modified and keeps track of the beginning of the list*/
    iterator = null; // "iterator" will traverse the list and/or link nodes to the list.

    if (inSequence.length() < 3) {
      System.out.println("There is no sequence to work with. Try again.");
      return null;
    }

    /* With a valid sequence, we can begin to parse the sequence into 3 character codons*/
    int index = 0;

    while (inSequence.length() > 0) {
      String newCodon = "";
      while (index < 3) {
        if(inSequence.charAt(index) == ' '){
          index++;
          inSequence = inSequence.substring(index);
        }else {
          newCodon = newCodon + inSequence.charAt(index);
          index++;
        }


      }
      inSequence = inSequence.substring(index); /*inSequence updates in length where the last value of index was at.*/
      //System.out.println("Testing if new codon was made...");
      //System.out.println(newCodon);
      index = 0; // index resets


      //setting our head
      if(head == null){
        head = new AminoAcidLL(newCodon); // once the head is created we do not modify it
        iterator = head; //storing head
      }
      else{
        if(head != null){
          head.addCodon(newCodon);
        }
      }


       // when the list is complete, return the starting node of the list.
    }
    return head;
  }


  /********************************************************************************************//*
  /* sorts a list by amino acid character*/
  public static AminoAcidLL sort(AminoAcidLL inList) {
    //TODO: returns the new starting node(head).
    AminoAcidLL newHead = inList; //stores the location of where the head node is
    AminoAcidLL begin = newHead; // begin points to the first unsorted node;
    AminoAcidLL temp;//iterator that will go through list.
    AminoAcidLL item = null;//stores location of where smallest value is at.
    AminoAcidLL sorted = begin;
    //String chain = ""; //prints out the sorted amino acids.

    char minValue; //will store the minimum value.

    while (begin != null) {
      temp = begin; // temp starts it iteration at the first "unsorted" node.
      minValue = begin.aminoAcid; // the first default minValue is from the begin- unsorted node.
      while (temp != null) {
        //temp begins iterating through list.
        //begins comparing the temp(current) node values tp the minValue.
        if (temp.next == null) {
          if (temp.aminoAcid < minValue) {
            //replace the current minValue with the new one.
            minValue = temp.aminoAcid;
            //storing the reference pointer to item.
            item = temp;
          }
        } else if (temp.next.aminoAcid < minValue) {
          //replace the current minValue with the new one.
          minValue = temp.next.aminoAcid;
          //storing the reference pointer to item.
          item = temp.next;
        }

        //if the current node's value is not less than the minValue, do nothing and continue through the list.
        temp = temp.next;

      }

      //Once we have finished going through the list, we need to figure out where to switch the nodes around.
      if (begin.aminoAcid <= minValue) {
        //if begin is already "sorted" and in the correct place, do nothing and update begin to
        // be the next unsorted node.
        sorted = begin;
        begin = begin.next;
      } else if (begin.aminoAcid > minValue && begin == sorted) {
        // first, change begin's pointer
        begin.next = item.next;
        //moving item to the front of the list.
        item.next = begin;
        sorted = item;
        newHead = item;
      } else {
        begin.next = item.next;
        sorted.next = item;
        item.next = begin;
        //update sorted to the new sorted value;
        sorted = item;
      }
      //chain = chain + sorted.aminoAcid;
      //System.out.println(chain);
    }

    head = newHead;
    iterator = head;

    return head;
  }
}