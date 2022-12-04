import java.util.Scanner;
public class SafeInput {

    /**
     * Get an integer value within a range
     *
     * @param in     Scanner to use for console input
     * @param prompt String that tells the user what to input
     * @param low    low value for a range low - high
     * @param high   high value for the range
     * @return an int within the low - high range
     */
    public static int getRangedInt(Scanner in, String prompt, int low, int high) {
        int retVal = 0;
        boolean done = false;
        String trash = "";

        do {
            System.out.print(prompt + " [" + low + " - " + high + "] : ");
            if (in.hasNextInt()) {
                retVal = in.nextInt();
                in.nextLine();
                if (retVal >= low && retVal <= high) {
                    System.out.println("\nYou said your num is " + retVal);
                    done = true;
                } else {
                    System.out.println("You said your number is " + retVal + " but that is out of range [" + low + " - " + high + "]");
                }
            } else {
                trash = in.nextLine();
                System.out.println(trash + " is not a valid number");
            }
        } while (!done);

        return retVal;
    }


    /**
     * Use Scanner to get an int form the console with no constraints
     *
     * @param in     Scanner to use for console input
     * @param prompt String prompt to tell the user what to input
     * @return
     */
    public static int getInt(Scanner in, String prompt) {
        int retVal = 0;
        boolean done = false;
        String trash = "";

        do {
            System.out.print(prompt + ": ");
            if (in.hasNextInt()) {
                retVal = in.nextInt();
                in.nextLine();
                done = true;
            } else {
                trash = in.nextLine();
                System.out.println(trash + " is not a valid number");
            }
        } while (!done);

        return retVal;
    }

    /**
     * Use Scanner to get a double form the console with no constraints
     *
     * @param in     Scanner to use for console input
     * @param prompt String prompt to tell the user what to input
     * @return
     */
    public static double getDouble(Scanner in, String prompt) {
        double retVal = 0;
        boolean done = false;
        String trash = "";

        do {
            System.out.print(prompt + ": ");
            if (in.hasNextDouble()) {
                retVal = in.nextDouble();
                in.nextLine();
                done = true;
            } else {
                trash = in.nextLine();
                System.out.println(trash + " is not a valid number");
            }
        } while (!done);

        return retVal;
    }

    /**
     * Get a double value within a range
     *
     * @param in     Scanner to use for console input
     * @param prompt String that tells the user what to input
     * @param low    low value for a range low - high
     * @param high   high value for the range
     * @return a double within the low - high range
     */
    public static double getRangedDouble(Scanner in, String prompt, double low, double high) {
        double retVal = 0;
        boolean done = false;
        String trash = "";

        do {
            System.out.print(prompt + " [" + low + " - " + high + "] : ");
            if (in.hasNextDouble()) {
                retVal = in.nextDouble();
                in.nextLine();
                if (retVal >= low && retVal <= high) {
                    done = true;
                } else {
                    System.out.println("You said your number is " + retVal + " but that is out of range [" + low + " - " + high + "]");
                }
            } else {
                trash = in.nextLine();
                System.out.println(trash + " is not a valid number");
            }
        } while (!done);

        return retVal;
    }

    /**
     * Get an arbitrary String of at least one character in length
     *
     * @param in     Scanner to use for input
     * @param prompt Prompt msg for use about what to input
     * @return a String entered by the user if at least one character
     */
    public static String getNonZeroLength(Scanner in, String prompt) {
        String retVal = "";
        boolean done = false;

        do {
            System.out.print(prompt + ": ");
            retVal = in.nextLine();
            if (retVal.length() > 0) {
                done = true;
            } else {
                System.out.println("You must enter at least one character ");
            }
            return retVal;
        } while (!done);
    }

    /**
     *
     * @param in
     * @param prompt
     * @return
     */
    public static boolean getYNConfirm(Scanner in, String prompt)
    {
        String respondYN="";
        boolean retVal = false;
        boolean done=false;

        do
        {
            System.out.println(prompt + ": ");
            respondYN = in.nextLine();

            if(respondYN.equalsIgnoreCase("Y"))
            {
                retVal=true;
                done=true;
            }else if(respondYN.equalsIgnoreCase("N"))
            {
                retVal=false;
                done=true;
            }else
            {
                System.out.println("You must enter [Y/N]");
            }
        }while (!done);
        return retVal;
    }

    /**
     *
     * @param in
     * @param prompt
     * @param pattern
     * @return
     */
    public static String getRegExString(Scanner in, String prompt, String pattern)
    {
        String retVal="";
        boolean done = false;

        do {
            System.out.print(prompt + ": ");
            retVal = in.nextLine();

            if (retVal.matches(pattern))
            {
                done = true;
            }
            else {
                System.out.println("What you entered does not match the pattern. " + pattern);
            }
        }while (!done);
        return retVal;
    }

    /**
     * Make a pretty header with centered message
     *
     * @param msg Prompt user's msg
     * @return pretty header
     */
    public static String prettyHeader(String msg) {
        Scanner console = new Scanner(System.in);

        int space_taken = (60-6) - msg.length();

        for(int i=1; i <=60; i++)
        {
            System.out.print("*");
        }
        System.out.println();
        for(int i=1; i<=3; i++)
        {
            System.out.print("*");
        }
        for(int i = 1; i<=space_taken/2; i++)
        {
            System.out.print(" ");
        }
        System.out.print(msg);
        if(msg.length() % 2 >0) {
            for (int i = 1; i <= (space_taken/2)+1; i++) {
                System.out.print(" ");
            }
        }
        for(int i=1; i<=3; i++)
        {
            System.out.print("*");
        }
        System.out.println();
        for(int i=1; i <=60; i++)
        {
            System.out.print("*");
        }
        return msg;
    }

}