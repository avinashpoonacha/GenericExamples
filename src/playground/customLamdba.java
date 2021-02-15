package playground;

class customLambda {
    public static void main(String[] args) {

        //String s = "Avi";
        customLambdaI Avi = new customLambdaI() {

            @Override
            public void greeting(String name) {
                System.out.println(name + "Avi");
            }
        };
        Avi.greeting("Hello ");
        Avi.check();

        customLambdaI Avinash = (String s) -> { System.out.println(s + " Avinash ");};
        Avinash.greeting("Hello ");
        Avinash.check1();


        
    }
}



@FunctionalInterface
interface customLambdaI {
    void greeting(String name);

    default void check() {
        System.out.println( "checking ");
    }

    default void check1() {
        System.out.println( "checking 1 ");
    }

   // void seconds(String y);
}
