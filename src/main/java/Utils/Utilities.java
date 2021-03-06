package Utils;

public class Utilities {

    public static boolean validTime(String time){

        if(time.length()==5) {
            String half1=time.substring(0,2);
            String half2=time.substring(3,5);
            if (half1.matches("[0-9]+") && String.valueOf(time.charAt(2)).equals(":") && half2.matches("[0-9]+"))
                return (Integer.parseInt(half1) >= 0 && Integer.parseInt(half1) < 24 && Integer.parseInt(half2) >= 0 && Integer.parseInt(half2) < 60);
        }
        return false;
    }

    public static boolean validPPS(String text) {
        if((text.length()==9))
            return (text.substring(0,7).matches("[0-9]+"))&&(text.substring(7,9).matches("[a-zA-Z]+"));
           else
            return false;

    }
    public static boolean validBoothID(String text){
        if(text.length()==2)
        return Character.isLetter(text.charAt(0)) && Character.isDigit(text.charAt(1));
        else
            return false;

    }


    public static boolean max10Chars(String string){ return string.length() <= 10;}

    public static boolean validIndex(int index, CoolLinkedList list) {
        return ((index >= 0) && (index < list.size()));
    }

    static String getAlphaNumericString(int n)
    {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }


}