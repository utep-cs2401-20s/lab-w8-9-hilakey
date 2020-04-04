import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class AminoAcidLLTester {
    /*This file contains the method tests for AminoAcidLL.java*/

        /************************************************************************************************/
        /*Testing for createFromRNASequence() method.*/

        /*Test #1:
         * This first test is a general test sequence making sure that the method is behaving
         * properly by returning the starting head when given a string sequence. To see if the list was created I will also
         * be calling my helper print method that will print the list. I believe that my test will PASS.
         * The test PASSED.
         */
        @Test
        public void testCreateFromRNA(){
            String rna1 = "AGUGGACAUAAAAAG";
            AminoAcidLL test1 = AminoAcidLL.createFromRNASequence(rna1); // not sorted
            AminoAcidLL result = AminoAcidLL.createFromRNASequence("AGU");
            AminoAcidLL.printLList(test1);
            assertEquals(result.aminoAcid, test1.aminoAcid);

        }

        /*Test #2:
         * This test is going to check if createFromRNASequence will throw out an error if the sequence is less than 3.
         * Because of this, I expect no actual node to be made. And expect a print statement instead.
         * The test will pass because nothing is being asserted.
         *
         */
        @Test
        public void testCreateFromRNA2(){
            String rna9 = "AU";
            AminoAcidLL test2 = AminoAcidLL.createFromRNASequence(rna9);

        }
    /************************************************************************************************/
    /*Testing for sort()  and isSorted method.*/

    /*Test #3:
     * This test is going to see if the sorting method works. I'm going to use the isSorted() method
     * to test if the list is in fact sorted when assessing. I believe that the test will PASS.
     * The test PASSED.
     */
    @Test
    public void testSortandISorted(){
        String rna2 = "GAUBCAAAACCCULUCAGGUA";
        AminoAcidLL test2 = AminoAcidLL.createFromRNASequence(rna2);
        test2 = AminoAcidLL.sort(test2);
        assertTrue(test2.isSorted());

    }

    /* Test #4:
     * This test is going to sort a sequence with spaces. The method should try and ignore these spaces
     * and still try to make 3 character codons. Then one the list is created, isSorted should verify that it is sorted.
     * I expect the test to fail.
     * The test PASSED.
     */
    @Test
    public void testSortandISorted2(){
        String rna8 = "AAC UGA GGG GCG AUU";
        AminoAcidLL test4 = AminoAcidLL.createFromRNASequence(rna8);
        test4 = AminoAcidLL.sort(test4);
        assertTrue(test4.isSorted());
    }

    /************************************************************************************************/
    /*Testing for aminoAcidList method.*/

    /* Test #5:
     * This test is going to check is aminoAcidList is behaving correctly. The list will not be sorted.
     * The catch is that the string will have a stop codon at the beginning, meaning that it will create the stop
     * codon in the list, BUT it will stop the translation,
     * Result ----> [*]
     * I believe that the test will pass.
     * The test PASSED.
     */
    @Test
    public void testAminoAcidList(){
        String rna3 = "UGAUAGUAAUGA";
        AminoAcidLL test3 = AminoAcidLL.createFromRNASequence(rna3);
        char[] test = test3.aminoAcidList();
        char[] result = {'*'};

        assertArrayEquals(result, test);

    }

    /************************************************************************************************/
    /*Testing for aminoAcidCounts method.*/

    /* Test #6:
     * This test is going to check if AminoAcidCounts is behaving correctly. The sequence being given will have numbers
     * as well as chars. I want to test is if it will give the correct amount of counts when it finds valid codons.
     * I believe that the test will fail.
     * The test FAILED;
     * Sequence:"123AUGCGU876GGG343"
     * Codons:"AUGCGUGGG"
     * Counts---> [1, 1, 1]
     */
    @Test
    public void testAminoAcidCounts(){
        String rna4 = "123AUGCGU876GGG343";
        AminoAcidLL test4 = AminoAcidLL.createFromRNASequence(rna4);
        AminoAcidLL.printLList(test4);

        int[] test = test4.aminoAcidCounts();
        int[] result = {1,1,1};

        assertArrayEquals(result,test);
    }
    /************************************************************************************************/
    /*Testing for aminoAcidCompare method.*/

    /* Test #7:
     * This test checks if aminoAcidCompare will return the totalCount of one of the lists,
     * IF the other is NULL.
     * I expect the test to pass.
     * The test PASSED.
     */
    @Test
    public void testAminoAcidCompare(){
        String rna7 = "AAAUUUCCCGGGUGAACUGAACAU";
        AminoAcidLL test7 = AminoAcidLL.createFromRNASequence(rna7);
        test7 = AminoAcidLL.sort(test7);

        int result = 3;
        assertEquals(result, test7.aminoAcidCompare(null));
    }

    /* Test #8:
    * This test will now do a codon compare with two lists.
    * This purpose is to see if the method is working.
    * I expect the the test to pass.
    * The test PASSED.
    */
    @Test
    public void testAminoAcidCompare2(){
        String rna = "AGUGGACAUAAAAAG";
        AminoAcidLL first = AminoAcidLL.createFromRNASequence(rna);
        first = AminoAcidLL.sort(first);

        String rna0 = "AGUGGACAUAAAAAAUCC";
        AminoAcidLL second = AminoAcidLL.createFromRNASequence(rna0);
        second = AminoAcidLL.sort(second);

        int result = 1;
        assertEquals(result, first.aminoAcidCompare(second));
    }





    /************************************************************************************************/
    /*Testing for CodonCompare method.*/

    /* Test #9:
     * Similar to aminoAcidCompare, it checks if the method is working if one of the lists is NULL.
     * I expect the test to pass.
     * The test PASSED.
     */
    @Test
    public void testCodonCompare(){
        String rna22 = "AGCAGUCCC";
        AminoAcidLL first = AminoAcidLL.createFromRNASequence(rna22);
        first = AminoAcidLL.sort(first);

        int result = 3;

        assertEquals(result, first.codonCompare(null));


    }

    /* Test #10:
     *This test checks if codonCompare works with two lists.
     * I expect the test to pass.
     * The test failed.
     */
    @Test
    public void testCodonCompare2(){
        String rna22 = "AGCAGUCCC";
        AminoAcidLL first = AminoAcidLL.createFromRNASequence(rna22);
        first = AminoAcidLL.sort(first);
        AminoAcidLL.printLList(first);

        String rna23 = "CCUAGGACA";
        AminoAcidLL second = AminoAcidLL.createFromRNASequence(rna23);
        second = AminoAcidLL.sort(second);
        AminoAcidLL.printLList(second);

        int result = 1;
        assertEquals(result, first.codonCompare(second));
    }














}
