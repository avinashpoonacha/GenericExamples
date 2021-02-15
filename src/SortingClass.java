import java.util.Arrays;
import java.util.Comparator;



public class SortingClass {

    static class SortingbyLenght implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.length() - o2.length();
        }
    }

    public static void main(String[] args)
    {
        String[] input = {"qq","ghgft","gtdfgsvddvsdx","ewfrtr"};
        for ( String i : input) {
            System.out.println(i);
        }
       // Arrays.sort(input, new SortingbyLenght());

       /* Arrays.sort(input, new Comparator<String>() {
            public int compare(String a, String b) {
                return a.length() - b.length();
            }
        });*/

        Arrays.sort(input, (a,b) -> b.length() - a.length());

        for ( String i : input) {
            System.out.println(i);
        }


    }




}
