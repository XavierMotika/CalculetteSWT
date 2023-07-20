/**
 * 
 */
package calc_test;

/**
 * Bunch of small methods often used by other Class/methods.
 * 
 * @author Stagiaire_XMA
 *
 */
public class Util {
	/**
	 * Method used to remove the "," at the end of the number to set on display if what's after it is equal to "0"
	 * 
	 * @param affiche the number to set on display passed throught just after computation
	 * 
	 * @return affiche the same number without ".0" if condition is reached.
	 */
	public static String verrificationVirguleZero(String affiche) {
		if (affiche.substring(affiche.length() - 2, affiche.length()).equals(".0")) {
			return affiche.substring(0, affiche.length() - 2);
		}
		return affiche;
	}

	/**
	 * Method that resets the calculator's memory, set the memory operant to nothing, 
	 * both memory data to "0" and sets the new memory flag to TRUE.
	 * 
	 */
	protected static void reset() {
		Calculette.memoireOperation = "";
		Calculette.memoireValeur = "0";
		Calculette.memoireValeur2 = "0";
		Calculette.nouvelleMemoire = true;
	}

	/**
	 * String method that remove the last charater from a given String.
	 * 
	 */
	public static void retour() {
		Calculette.ecran.setText(Calculette.ecran.getText().substring(0, Calculette.ecran.getText().length() - 1));
	}

	/**
	 * Method that converts the given Double to a String, checks for useless ".0" and changes "." from "," while 
	 * setting the String to the screen for display.
	 * 
	 * @param pCalcul the Double given after computation.
	 * 
	 */
	public static void affichage(Double pCalcul) {
		String affiche = pCalcul.toString();
		affiche = Util.verrificationVirguleZero(affiche);
		Calculette.ecran.setText(affiche.replaceAll("\\.", ","));
	}
}
