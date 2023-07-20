/**
 * 
 */
package calc_test;

import org.eclipse.swt.widgets.Button;

/**
 * Regroups the computation methods.
 * 
 * @author Stagiaire_XMA
 *
 */
public class Calcul {

	/**
	 * Method used for simple computation (+, -, *, / and ^), convert the memory and the texte on display from String to Double
	 * , replace "," with "." for the the computation, and convert back everything from Double to String for the display. 
	 * Also reset the memory.
	 * 
	 * nb : "^" is classified as an "advanced computation" when creating all the composites but when it comes to the 
	 * computation itself it is closer in form to the simple ones (it takes 2 variables to calculate a new one)
	 * 
	 */
	protected static void operation() {
		Double calcul = (double) 0;
		try {
			switch (Calculette.memoireOperation) {
			case "+":
				calcul = Double.parseDouble(Calculette.memoireValeur.replaceAll(",", "\\."))
						+ Double.parseDouble(Calculette.memoireValeur2.replaceAll(",", "."));
				break;
			case "-":

				calcul = Double.parseDouble(Calculette.memoireValeur.replaceAll(",", "\\."))
						- Double.parseDouble(Calculette.memoireValeur2.replaceAll(",", "\\."));
				break;
			case "x":
				calcul = Double.parseDouble(Calculette.memoireValeur.replaceAll(",", "\\."))
						* Double.parseDouble(Calculette.memoireValeur2.replaceAll(",", "\\."));
				break;
			case "/":
				calcul = Double.parseDouble(Calculette.memoireValeur.replaceAll(",", "\\."))
						/ Double.parseDouble(Calculette.memoireValeur2.replaceAll(",", "\\."));
				break;
			case "^":
				calcul = Math.pow(Double.parseDouble(Calculette.memoireValeur.replaceAll(",", "\\."))
				, Double.parseDouble(Calculette.memoireValeur2.replaceAll(",", "\\.")));
				break;
			default:
				calcul = Double.parseDouble(Calculette.memoireValeur2.replaceAll(",", "\\."));
				break;
			}
			Util.affichage(calcul);
		} catch (NumberFormatException e) {
			Util.reset();
		}

	}
	
	/**
	 * Method used for advenced computation (square, square root, exponential and neperian logarithm), convert the texte on display from String to Double
	 * , replace "," with "." for the the computation, and convert back everything from Double to String for the display. 
	 * Does not reset the memory as the computation only afect the number on display.
	 * 
	 * nb : Compare to the simple computation, all the calulus seen below only take the number on diplay
	 * 
	 * @param pBouton passed from the "boutonFonction" method
	 * 
	 */
	protected static void operationAvancee(Button pBouton) {
		Double calcul = (double) 0;
		switch (pBouton.getText()) {
		case "x²":
			calcul = Double.parseDouble(Calculette.ecran.getText().replaceAll(",", "\\."))
					* Double.parseDouble(Calculette.ecran.getText().replaceAll(",", "\\."));
			break;
		case "\u221Ax":
			calcul = Math.sqrt(Double.parseDouble(Calculette.ecran.getText().replaceAll(",", "\\.")));
			break;
		case "exp":
			calcul = Math.exp(Double.parseDouble(Calculette.ecran.getText().replaceAll(",", "\\.")));
			break;
		case "ln":
			calcul = Math.log(Double.parseDouble(Calculette.ecran.getText().replaceAll(",", "\\.")));
			break;
		default:
			calcul = Double.parseDouble(Calculette.memoireValeur2.replaceAll(",", "\\."));
			break;
		}
		Util.affichage(calcul);

	}

}
