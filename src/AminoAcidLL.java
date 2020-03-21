import java.util.Arrays;

class AminoAcidLL{
  char aminoAcid;
  String[] codons;
  int[] counts;
  AminoAcidLL next;

  /**************************/
  /*These attributes were not part of the original file, but I created them to help my methods.*/
  static AminoAcidLL head;
  static AminoAcidLL tempHead;
  static AminoAcidLL iterator;
  static int size;
  static int[] aaCounts;
  static char[] aaList;
  int j = 0;

  AminoAcidLL(){

  }

  /********************************************************************************************/
  /* Creates a new node, with a given amino acid/codon 
   * pair and increments the codon counter for that codon.
   * NOTE: Does not check for repeats!! */
  public AminoAcidLL(String inCodon){
    aminoAcid = AminoAcidResources.getAminoAcidFromCodon(inCodon);
    codons = AminoAcidResources.getCodonListForAminoAcid(aminoAcid);
    counts = new int[codons.length];
    counts = findCodon(inCodon);
    next = null;
  }

  /* Helper Method that searches through current node's codons list. If the codon is found, then the codon
   *counter increases in the counts array.
   */
  private int[] findCodon(String inCodon){

    if(inCodon.equals('*') || inCodon.equals('\0')){
      /* if inCodon is either STOP or NULL, return an empty array*/
      return new int[]{};
    }

    /* We search through the codons list IF the codon in the parameter is not NULL or "*" .*/
      for(int i = 0; i < codons.length; i++){
        /*Compare the current codon string with the codon we have*/
        /* If the codon is found in the list, increase it's count by 1.*/
        if(codons[i].equals(inCodon)){
          counts[i] += 1;
        }
        /* If the codon does not match, do not increase count and continue traversing the list.*/
      }
      return counts;
  }

  /* Helper method that prints the list and it's contents*/
  public static void printAAList(AminoAcidLL a){
    AminoAcidLL current;
    current = a ;
    while(current != null){
      System.out.print("\nAmino Acid: " + current.aminoAcid);
      System.out.print("\nCodon list: ");
      System.out.print(Arrays.toString(current.codons));
      System.out.print("\nCount list: ");
      System.out.print(Arrays.toString(current.counts));
      System.out.println();

      current = current.next;
    }
  }

  /*Helper method that prints the totalCodoncounts array*/
  public static void printAminoAcidCounts(int[] counts){
      System.out.println(Arrays.toString(aaCounts));
      System.out.println(Arrays.toString(aaList));
  }

  /*Helper method that returns the char of amino acid*/
  public char getAminoAcidList(AminoAcidLL a){
    return a.aminoAcid;
  }




  /********************************************************************************************/
  /* Recursive method that increments the count for a specific codon:
   * If it should be at this node, increments it and stops, 
   * if not passes the task to the next node. 
   * If there is no next node, add a new node to the list that would contain the codon. 
   */
  public void addCodon(String inCodon){ /*(originally private and nonstatic)*/
    /*Base Cases*/
    /* if we have a NULL node, we create a new one and make it our head.*/
    if(tempHead == null){
      tempHead = new AminoAcidLL(inCodon);
    }
    if(AminoAcidResources.getAminoAcidFromCodon(inCodon) == tempHead.aminoAcid){
      System.out.println("inCodon is in this node...updating counts");
      tempHead.findCodon(inCodon);
    }else {
      if (tempHead.next == null) {
        System.out.println("Codon not in list, adding to end of list.");
        tempHead.next = new AminoAcidLL(inCodon);
      } else {
        System.out.println("Codon not found yet, continue search..");
        tempHead = tempHead.next;
        addCodon(inCodon);
      }
    }



  }

  /********************************************************************************************/
  /* Shortcut to find the total number of instances of this amino acid */
  private int totalCount(){
    /*TODO: this will return the number of times this amino acid is used in the sequence (the sum of all of the codon counts).*/
    //sum up total in counts[] for specific node ,,,,is called by aminoAcidCounts()
    System.out.println("The current amino acid being counted is.." + tempHead.aminoAcid);
    int sum = 0;

    for(int i = 0; i < counts.length; i++){
        sum += counts[i];
    }
    return sum;
  }

//  *//********************************************************************************************//*
//  *//* helper method for finding the list difference on two matching nodes
//  *  must be matching, but this is not tracked *//*
//  private int totalDiff(AminoAcidLL inList){
//    return Math.abs(totalCount() - inList.totalCount());
//  }
//
//
//  *//********************************************************************************************//*
//  *//* helper method for finding the list difference on two matching nodes
//  *  must be matching, but this is not tracked *//*
//  private int codonDiff(AminoAcidLL inList){
//    int diff = 0;
//    for(int i=0; i<codons.length; i++){
//      diff += Math.abs(counts[i] - inList.counts[i]);
//    }
//    return diff;
//  }
//
//  *//********************************************************************************************//*
//  *//* Recursive method that finds the differences in **Amino Acid** counts.
//   * the list *must* be sorted to use this method *//*
//  public int aminoAcidCompare(AminoAcidLL inList){
//    return 0;
//  }
//
//  *//********************************************************************************************//*
//  *//* Same as above, but counts the codon usage differences
//   * Must be sorted. *//*
//  public int codonCompare(AminoAcidLL inList){
//    return 0;
//  }


  /********************************************************************************************//*
  /* Recursively returns the total list of amino acids in the order that they are in in the linked list. */
  public char[] aminoAcidList(){
    //Base Cases
    if(tempHead == null){
      j = 0;
      tempHead = head;
      return null;
    }
    else{
      aaList[j] = getAminoAcidList(tempHead);
      j++;
      tempHead = tempHead.next;
      aminoAcidList();

    }
    return new char[]{};
  }

  /********************************************************************************************//*
  /* Recursively returns the total counts of amino acids in the order that they are in in the linked list. */
  public int[] aminoAcidCounts(){
    //Base Cases
    if(tempHead == null){
      j = 0;
      tempHead = head; ///resets tempHead to head.
      return null; // If the current node is NUll return the total counts as 0.
    }
    else{
      if(tempHead != null){
         aaCounts[j] = totalCount();
      }
      j++;
      tempHead = tempHead.next;
      aminoAcidCounts();
    }
   return new int[]{};
  }


  /********************************************************************************************//*
  /* recursively determines if a linked list is sorted or not */
  public boolean isSorted(){
    /*Base Cases*/
    if(tempHead.next == null){ /*this is if there is only one node in the entire list, OR the search made it to the end of the list continuously then it IS sorted.*/
      return true;
    }else {
      if (tempHead.aminoAcid > tempHead.next.aminoAcid) {
        return false;
      } else {
        tempHead = tempHead.next;
        tempHead.isSorted();
      }
    }
    return true;
  }


  /********************************************************************************************//*
  /* Static method for generating a linked list from an RNA sequence */
  public static AminoAcidLL createFromRNASequence(String inSequence){
    //TODO:link list to string(amino acid only)
     head = null; // This node "head" is to not be modified and keeps track of the beginning of the list*/
     iterator = null; // "iterator" will traverse the list and/or link nodes to the list.

    if(inSequence.length() < 3){
      System.out.println("There is no sequence to work with. Try again.");
    }

    /* With a valid sequence, we can begin to parse the sequence into 3 character codons*/
    int index = 0;

    while(inSequence.length() > 0){
      String newCodon = "";
      while(index <3){
        newCodon = newCodon + inSequence.charAt(index);
        index++;

      }
      inSequence = inSequence.substring(index); /*inSequence updates in length where the last value of index was at.*/
      System.out.println("Testing if new codon was made...");
      System.out.println(newCodon);
      index = 0;
      /* With the newCodon created, we can now create a new AminoAcidLL node*/
      /*Creating the head node*/
      if(head == null){
        System.out.println("This is the head.");
        head = new AminoAcidLL(newCodon);
        size++;
        iterator = head;
        System.out.println("Head contains: " + iterator.aminoAcid);
      }else{ //if head is not NULL, then we are creating the next node and attaching it to the list.
        iterator.next = new AminoAcidLL(newCodon);
        size++;
        iterator = iterator.next;// iterator now becomes the latest node
      }

    }
    tempHead = head;
    printAAList(head);
    System.out.println("The size of the list is " + size);
    aaCounts = new int[size]; //creating a new int array of the same size of the list that will hold the total counts for the amino acids.
    aaList = new char[size];
    tempHead.aminoAcidList();
    tempHead.aminoAcidCounts();
    printAminoAcidCounts(aaCounts);
    return head; // when the list is complete, return the starting node of the list.

  }



  /********************************************************************************************//*
  /* sorts a list by amino acid character*/
  public static AminoAcidLL sort(AminoAcidLL inList) {
    //TODO:  returns the new starting node(head).

  }
}