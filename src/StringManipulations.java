// find number of vowels and consonants in the word
//create a method that takes an input string and gives you vowels and consonants
/*
sample word = avi

check a is n vowels , yes 1
check v is in vowels , no 0
cehck i is in vowels , yes 1

vowels : 2 , consonants = 1

 */


import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class StringManipulations {

    static void numberofVowelsandConsonants( String theWord) {

        System.out.println(theWord.toLowerCase().chars().mapToObj(o -> (char)o)
                .filter(e -> !e.toString().equals(" "))
        .filter( e -> "aeiouy".contains(e.toString())).count());

        System.out.println(theWord.toLowerCase().chars().mapToObj(o -> (char)o)
                .filter(e -> !e.toString().equals(" "))
                .filter( e -> !"aeiouy".contains(e.toString())).count());



    }

    static void numberofVowelsandConsonants2(String theWord) {

        String vowels = "aeiouy";
        int vc=0;
        int cc=0;

        char[] chartheWord = theWord.toLowerCase().toCharArray();

        for (char c : chartheWord) {
            if(vowels.toLowerCase().indexOf(c) != -1){
                vc += 1;
            } else if (c != ' '){
                cc +=1;
            }
        }
        System.out.println(vc);
        System.out.println(cc);
    }

    public static void main(String[] args) {
        numberofVowelsandConsonants("I am Happy");
        System.out.println("*********");
        numberofVowelsandConsonants2("I am Happy");
    }

}
