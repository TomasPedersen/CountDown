import java.util.Arrays;
import java.util.ArrayList;

public class Calc {
    // Class attributes.
    int maxLevel=0;
    int numberOfCombinations=0;

    // Constructor.
    Calc(){
        ArrayList<Integer> numbers = new ArrayList<Integer>(Arrays.asList(6,9,10));
        maxLevel = numbers.size();
        numberCombinations(numbers, new ArrayList<Integer>());

        System.out.println("Talkombinationer i alt: "+ numberOfCombinations);
        System.out.println("Symbolkombinationer i alt: "+numSymbolsResults);
    }

    // Print numbers i alle mulige kombinationer.
    void numberCombinations(ArrayList<Integer> input, ArrayList<Integer> output){
        int level=output.size();
        System.out.println("maxLevel: "+maxLevel+"  Level: "+level+"  input: "+input+"  output: "+output);
        if(level==maxLevel) handleResult(output);
        if(level>=maxLevel){
            System.out.println("return");
            return;
        }
        if(level<maxLevel) {
            for (int i = 0; i < input.size(); i++) {
                output.add(level, input.get(i));        // Tag et tegn og tilføj til output.
                //handleResult(output);                   // 1. Accepter som resultat
                ArrayList<Integer> inputCopy = new ArrayList(input);           // Lav en kopi af input.
                inputCopy.remove(i);        // Fjern aktuelt element fra kopi.
                ArrayList<Integer> outputCopy = new ArrayList(output);  // Output skal også være en kopi.
                numberCombinations(inputCopy, outputCopy);                      // 2. Send videre som parameter i yderligere rekursion.
            }
        }
    }

    void handleResult(ArrayList<Integer> result){
        numberOfCombinations++;
        System.out.println(result);
    }

    // For en talrække, indsæt symboler i samtlige kombinationer.
    // Læs klassevariabel result[] for talrække.
    // Rekursiv metode, derfor gives talrække ikke som parameter.
    int numSymbolsResults=0;
    String symbolsResult=new String();
    String[] symbols={"+","-","*","/"};
    void addSymbols(int symbolsLevel){
//        symbolsResult+=numberCombinationsResult.charAt(symbolsLevel);  // Tilføj et tal til resultatet.
        if(symbolsLevel!=0) {                               // Hvis der er flere tal i rækken, kør rekursion
            for (int sym=0; sym < symbols.length; sym++) {  // for hvert regnesymbol.
                symbolsResult += symbols[sym];              // Tilføj regnesymbol til resultatet.
                addSymbols(symbolsLevel-1);                 // Og udfør rekursionen.
            }
        }
        if(symbolsLevel==0) {     // Hvis level er nul er der ikke flere tal i rækken og et resultat er nået.
            numSymbolsResults++;
            System.out.println("Symbolkombination nummer "+numSymbolsResults+": "+symbolsResult);      // Udskriv resultat.
            symbolsResult="";       // Klar til nyt symbolresultat.
        }
    }
}
