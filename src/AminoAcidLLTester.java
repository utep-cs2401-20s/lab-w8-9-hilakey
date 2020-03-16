
public class AminoAcidLLTester {
    /*This file contains the method tests for AminoAcidLL.java*/
    public static void main(String[] args) {
        /* testRNA sequences*/
        String rna1 = "AGUGGACAUAACCAUUGAUGGAGA";
        String nn = "UACCCCAAA";
        String rna2 = "GAUBCAAAACCCULUCAGGUA";
        String rna3 = "AU";
        String rna4 = "";
        String rna5 = "123AUGCGU876GGG343";
        String rna6 = "UGAUAGUAAUGA";
        String rna7 = "AAAUUUCCCGGGUGAACUGAACAU";
        String rna8 = "AAC UGA GGG GCG AUU";
        RNA1(nn);
    }

    public static void RNA1(String test){
        AminoAcidLL a = AminoAcidLL.createFromRNASequence(test);
        System.out.println(a.isSorted());

    }




}
