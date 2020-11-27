import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * @author İlker Rişvan
 * @since 27.10.2019
 */
public class regex {
    private static Scanner input;

    public static void main(String[] args) throws IOException {
        try(PrintWriter output = new PrintWriter("outputtext.txt")){
            input = new Scanner(new BufferedReader(new FileReader("inputtext.txt")));
            while (input.hasNext()){
                String text = input.nextLine();
                System.out.println("INPUT TEXT : " + text);
                text = or_changer(text);
                System.out.println("1:  " + text);
                text = and_changer(text);
                System.out.println("2:  " + text);
                text = update_before_said(text);
                System.out.println("3:  " + text);
                text = mix_to_lower(text);
                text = mix_to_lower_fix(text);
                System.out.println("5:  " + text);
                text = to_roman_numbers(text);
                System.out.println("6:  " + text);
                text = punctuations(text);
                System.out.println("7:  " + text);
                text = convert_form(text);
                System.out.println("8:  " + text);
                text = phone_number(text);
                System.out.println("9:  " + text);
                text = change_to_dots(text);
                System.out.println("10: " + text);
                text = add(text);
                System.out.println("11: " + text);
                output.println(text);
            }}}

    /*
     * This method for 1st task in the assignment.
     * It's convert "or" to "/".
     */
    public static String or_changer(String text) {
        String regex1  ="\\s(or)\\s";
        Pattern p  = Pattern.compile(regex1);
        Matcher m  = p.matcher(text);
        if(m.find())
            text=text.replaceAll(m.group()," / ");
        return text;}
    /*
     * This method for 1st task in the assignment.
     * It's convert "and" to "&".
     */
    public static String and_changer(String text) {
        String regex11 ="\\s(and)\\s";
        Pattern p  = Pattern.compile(regex11);
        Matcher m  = p.matcher(text);
        if(m.find())
            text=text.replaceAll(m.group()," & ");
        return text;}
    /*
     * This method uses for 6th task in the assignment.
     * When a word contains the same number consecutively, this method replaces these repetitive numbers with its roman form.
     * ***IMPORTANT NOTE***
     * There is a tiny mistake for example as input asli222444cos can't transform asliII444cos,this word transforms asliII444 with this method.
     */

    public static String to_roman_numbers(String text) {
        String regex6  ="([a-zA-Z]+)(([0-9]){2,})([a-zA-Z]+)";
        String regex66 ="([0-9])\\1+";
        Pattern p  = Pattern.compile(regex6);
        Matcher m  = p.matcher(text);
        Pattern p1  = Pattern.compile(regex66);
        Matcher m1  = p1.matcher(text);
        if(m.find())
            if(m1.find()){
                if(Integer.parseInt(m1.group(1)) == 1)
                    text=text.replaceAll(m1.group(0),"I");
                if(Integer.parseInt(m1.group(1)) == 2)
                    text=text.replaceAll(m1.group(0),"II");
                if(Integer.parseInt(m1.group(1)) == 3)
                    text=text.replaceAll(m1.group(0),"III");
                if(Integer.parseInt(m1.group(1)) == 4)
                    text=text.replaceAll(m1.group(0),"IV");
                if(Integer.parseInt(m1.group(1)) == 5)
                    text=text.replaceAll(m1.group(0),"V");
                if(Integer.parseInt(m1.group(1)) == 6)
                    text=text.replaceAll(m1.group(0),"VI");
                if(Integer.parseInt(m1.group(1)) == 7)
                    text=text.replaceAll(m1.group(0),"VII");
                if(Integer.parseInt(m1.group(1)) == 8)
                    text=text.replaceAll(m1.group(0),"VIII");
                if(Integer.parseInt(m1.group(1)) == 9)
                    text=text.replaceAll(m1.group(0),"IX");
                text=text.replaceAll(m.group(),m1.group(0));}
        return text;}


    /*
     * This method uses for 4th task in the assignment.
     * Converting mix cases and single(one element) upper cases to lower case.
     */
    public static String mix_to_lower(String text) {
        String regex4 = "[a-z]+[A-Z]+|[A-Z]+[a-z]+|[A-Z]{1}";
        Pattern p1 = Pattern.compile(regex4);
        Matcher m = p1.matcher(text);
        while(m.find())
            for (int i = 0; i <= m.groupCount(); i++)
                text=text.replaceAll(m.group(i),m.group(i).toLowerCase());
        return text;}

    /*
     * This method uses for 4th task in the assignment.
     * For get correct result the first letters converting to upper case.
     * **IMPORTANT NOTE**
     * Each line can have only one sentence so this method does not fix more than one sentence for one line.
     * Can have some tiny errors,depending on the input text.
     */
    public static String mix_to_lower_fix(String text) {
        String regex44 ="(^[a-zA-Z])";
        Pattern p  = Pattern.compile(regex44);
        Matcher m  = p.matcher(text);
        if(m.find())
            for (int i = 0; i <= m.groupCount(); i++)
                text=text.replaceAll(m.group(i),m.group(i).toUpperCase());
        return text;}

    /*
     * This method uses for 2nd and 7th task in the assignment.
     * regex2 and regex22 for 2nd one.
     * regex7 and regex77 for 7th one.
     * regex7 uses for get all words that end with ‘lk’ or ‘ch’.
     * regex77 uses for get all words that end with ‘eg’ and ‘ag’.
     * regex2 uses for get all words '-s, -ss, -sh, -ch, -x, or -z'.
     * regex2 uses for get all the rest words.
     */
    public static String add(String text) {
        String regex2  = "([a-zA-Z]*(ss|s|sh|ch|x|es|z)\\b)";
        String regex22 = "([a-zA-Z]*[^s^ss^ch^x^es^z^sh^eg^ag^ch^lk^\\s^\\W^\\d])\\b";
        String regex7  = "[a-zA-Z]*(ch|lk)\\b";
        String regex77 = "[a-zA-Z]*(eg|ag)\\b";
        Pattern p      = Pattern.compile(regex2);
        Matcher m      = p.matcher(text);
        Pattern p2     = Pattern.compile(regex7);
        Matcher m2     = p2.matcher(text);
        Pattern p3     = Pattern.compile(regex77);
        Matcher m3     = p3.matcher(text);
        Pattern p4     = Pattern.compile(regex22);
        Matcher m4     = p4.matcher(text);
        while (m.find()) {
            text=text.replaceAll(m.group(),m.group()+"es");} //regex2
        while (m2.find()) {
            text=text.replaceAll(m2.group(),m2.group()+"ed");}
        while (m3.find()) {
            text=text.replaceAll(m3.group(),m3.group()+"ged");}
        while (m4.find()) {
            text=text.replaceAll(m4.group(),m4.group()+"s");}
        return text;}

    /*
     * This method uses for 3rd task in the assignment.
     * If a sentence contains the verb “said” as the second to the last word, all preceding words converting quoted.
     */
    public static String update_before_said(String text) {
        String regex3="(.*)(said)(\\s[a-zA-Z]*)[.]";
        Pattern p      = Pattern.compile(regex3);
        Matcher m      = p.matcher(text);
        if(m.find())
            text = text.replaceAll(m.group(0),"\""+m.group(1)+"\" "+m.group(2)+m.group(3)+".");
        return text;}
    /*
     * This method uses for 5th task in the assignment.
     * If a sentence is terminated with more than one dot “.” or with any other punctuation,this method replace all these punctuations with a single dot.
     */
    public static String change_to_dots(String text) {
        text=text.replaceAll("([.!?-]{3})|([.]{2,}.*)", ".");
        return text;}
    /*
     * This method uses for 8th task in the assignment.
     * If a noun phrase contains a possession  (via an apostrophe and an ‘s’), convert it to a new form.
     * For example: Mary's swimsuit converting the swimsuit of Mary.
     */

    public static String convert_form(String text) {
        String regex8  ="(.*)([’])([s])\\s([a-zA-Z]*)";
        Pattern p  = Pattern.compile(regex8);
        Matcher m  = p.matcher(text);
        if(m.find())
            text=text.replace(m.group(),"the "+m.group(4) +" of "+ m.group(1));
        return text; }
    /*
     * This method uses for 9th task in the assignment.
     * Converting phone numbers (xxx) xxx xxxx form to xxx-xxxxxxx
     *
     */

    public static String phone_number(String text) {
        Pattern p  = Pattern.compile("([(])(\\d{3})([)])(\\s)(\\d{3})(\\s)(\\d{4})");
        Matcher m  = p.matcher(text);
        if(m.find())
            text=text.replace(m.group(),m.group(2)+"-"+m.group(5)+m.group(7));
        return text; }

    /*
     * This method uses for 10th task in assignment.
     * It's remove first punctuations if the word begin and finish punctuations.
     */

    public static String punctuations(String text) {
        Pattern p  = Pattern.compile("^([.!?;):,(\\/-]+)([a-zA-Z]*)([.!;,:()?\\/-]+)");
        Matcher m  = p.matcher(text);
        if(m.find())
            text=text.replace(m.group(),m.group(2)+m.group(3));
        return text; }
}
