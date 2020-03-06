import java.util.Arrays;

public class CountDown {
	// Attributes.
	int numberOfCombinations=0;
	int numberOfSymbolCombinations=0;
	int totals=1;
	double target=0;

	// Constructor
	CountDown(){
		int[] input={100,8,1,8,2,10};
		target=102;
		systemiser(input, new int[0]);
		System.out.println("Talkombinationer i alt: "+numberOfCombinations);
		//symbolSystemiser(input, new String[0]);
		System.out.println("Symbolkombinationer: "+numberOfSymbolCombinations);
	}

	// Modsat randomiser.
	void systemiser(int[] input, int[] oldOutput){
		int level = oldOutput.length;
		for(int element=0; element<input.length; element++){
			int[] output=new int[level+1];		// Opret ny output som er én større end gammel output. Første level er level nul, derfor plus en.
			for(int j=0; j<level; j++) output[j]=oldOutput[j];			//  Kopier gammel output til ny output
			output[level]=input[element];								// Tilføj nyt element til output.

			// Opret nyt array til at videresende input til næste level i rekursionen.
			// Kopier input til newInput, på nær element nummer i.
			int[] newInput=new int[input.length-1];
			int i;					// Erklæres udenfor for-løkke da værdien skal bruges i begge løkker.
			for(i=0; i<element; i++) newInput[i]=input[i];
			for(i++; i<input.length; i++){	// plus en for at springe et element i input over.
				newInput[i-1]=input[i];		// Minus en for at korigere newInput for den ene der blev lagt til for at springe en over.
			}
			symbolSystemiser(output, new String[0]);		// Resultat fundet.
			numberOfCombinations++;
			systemiser(newInput, output);
		}
	}

	// For hvert mellemrum i input kør kombinationer med alle symboler i symbols.
	String[] symbols={"+","-","*","/"};
	void symbolSystemiser(int[] input, String[] result){
		// Sidste element i input er nået.
		if(input.length==1){
			String[] newResult=new String[result.length+1];		// Der skal kun være plads til tal, ikke tegn.
			for(int i=0; i<result.length; i++) newResult[i]=result[i];	// Kopier gammel result over i nyt.
			newResult[newResult.length-1]=String.valueOf(input[0]);
			//System.out.println(numberOfSymbolCombinations+": "+Arrays.toString(newResult));		// Resultat fundet.
			calculate(newResult);
			numberOfSymbolCombinations++;
			return;
		}

		// Der er flere elementer i input.
		String[] newResult = new String[result.length+2];	// Opret ny result med plads til både et tal og et tegn.
		for(int i=0; i<result.length; i++) newResult[i]=result[i];	// Kopier gammel result over i nyt.
		newResult[newResult.length-2] = String.valueOf(input[0]);		// Sæt tal fra input ind på næstsidste plads i result.
		int[] newInput = new int[input.length-1];		// Der skal fjernes et element fra input, så newInput er en kortere.

		for(int j=1; j<input.length; j++) newInput[j-1]=input[j];	// Fjern første element fra input.

		for(int sym=0; sym<symbols.length; sym++){
			newResult[newResult.length-1]=symbols[sym];	// Sæt regnetegn ind på sidste plads i resultatet
			symbolSystemiser(newInput, newResult);		// Rekursion. Svarer til at gå til næste element i input.
		}
	}

	void calculate(String[] input){
		double total=Double.parseDouble(input[0]);
		for(int index=1; index<input.length; index+=2){
			switch(input[index]){
				case "+":
					total+=Double.parseDouble(input[index+1]);
					break;
				case "-":
					total-=Double.parseDouble(input[index+1]);
					break;
				case "*":
					total*=Double.parseDouble(input[index+1]);
					break;
				case "/":
					total/=Double.parseDouble(input[index+1]);
					if(total != (int)total)return;		// Delresultat er en brøk. Ugyldigt.
					break;
			}
		}
		if(total==target){
			System.out.println(totals+":  Target "+(int)target+"  --  "+Arrays.toString(input));
			totals++;
		}
	}
}
