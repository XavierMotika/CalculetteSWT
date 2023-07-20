/**
 * 
 */
package calc_test;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;

/**
 * Class gathering all the methods used to set a behavior to a button
 * 
 * @author Stagiaire_XMA
 *
 */
public class Comportement {

	/**
	 * Method used for behavior definition it takes the button, and the button's "familly" (actually an int) and
	 * passes the throught a swith case.
	 * 
	 * @param typeBouton the button's familly, from 0 (numeric button) to 7 (advanced computation button)
	 * 
	 * @param bouton newly created 
	 * 
	 * nb : the case 6 (Sci button used for swtiching from normal mode to "Scientifique" mode is processed differently
	 */
	protected static void definition(int typeBouton, Button bouton) {
		switch (typeBouton) {
		case 0:
			Comportement.boutonNumerique(bouton);
			break;
		case 1:
			Comportement.boutonRetour(bouton);
			break;
		case 2:
			Comportement.boutonSeparateur(bouton);
			break;
		case 3:
			Comportement.boutonFonction(bouton);
			break;
		case 4:
			Comportement.boutonClear(bouton);
			break;
		case 5:
			Comportement.boutonEgale(bouton);
			break;
		case 7:
			Comportement.boutonFonctionAvancee(bouton);
			break;
		}
	}
	
	/**
	 * Method used to set the "advanced computation" behavior, does a computation and set the new memory flag to TRUE.
	 * 
	 * @param pBouton passed throught the switch case. 
	 */
	protected static void boutonFonctionAvancee(Button pBouton) {
		pBouton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Calcul.operationAvancee(pBouton);
				Calculette.nouvelleMemoire = true;
			}
		});
	}

	/**
	 * Method used to set the equals button behavior, set the number on display to the memory, does a computation 
	 * then reset the memory.
	 * 
	 * @param pBouton passed throught the switch case. 
	 */
	protected static void boutonEgale(Button pBouton) {
		pBouton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Calculette.memoireValeur2 = Calculette.ecran.getText();
				Calcul.operation();
				Util.reset();
			}
		});

	}

	/**
	 * Method used to set the CLEAR button behavior, sets the number on display to 0 and resets the memory.
	 * 
	 * @param pBouton passed throught the switch case 
	 */
	protected static void boutonClear(Button pBouton) {
		pBouton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Calculette.ecran.setText("0");
				Util.reset();
			}
		});

	}

	/**
	 * Method that takes the selected button and directs action to do based on the seleted button and on what's
	 * already in memory.
	 * 
	 * - If no operant registered, takes the number on display as well as the operant pushed and puts them in memory
	 * 
	 * - Else checks if the pushed button is the same the the operant from the memory.
	 * 
	 * 	- If yes puts the new number on display in the memory and does the computation.
	 * 
	 * 	   - Also check if the new memory flag is true.
	 * 
	 * 	- If not the number on display overwrties the first memory.
	 * 
	 *     - if not stops here.
	 *     
	 * - Else the number on display is put in le second memory, computation is done,
	 *   the first memory is overwriten with the new number on display and the operant is registerd.
	 *   
	 * Sets the new memory flag to TRUE.
	 *   
	 * @param pBouton passed throught the switch case 
	 */
	protected static void boutonFonction(Button pBouton) {
		pBouton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if (Calculette.memoireOperation.equals("")) {
					Calculette.memoireOperation = pBouton.getText();
					Calculette.memoireValeur = Calculette.ecran.getText();
	
				} else if (Calculette.memoireOperation.equals(pBouton.getText())) {
					Calculette.memoireValeur2 = Calculette.ecran.getText();
					Calcul.operation();
					
					
					if (!Calculette.nouvelleMemoire) {
						Calculette.memoireValeur = Calculette.ecran.getText();
					}
					
				} else {
					Calculette.memoireValeur2 = Calculette.ecran.getText();
					Calcul.operation();
					Calculette.memoireOperation = pBouton.getText();
					Calculette.memoireValeur = Calculette.ecran.getText();
				}
				Calculette.nouvelleMemoire = true;
			}
		});

	}

	/**
	 * Method used to set the "," button behavior, if the number on display already has a "," it does nothing
	 * otherwise it adds a "," to the number on display.
	 * If the new memory flag is TRUE , it set the number on display to "0," and sets the flag yo FALSE.
	 * 
	 * @param pBouton passed throught the switch case 
	 * 
	 */
	protected static void boutonSeparateur(Button pBouton) {
		pBouton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!((Calculette.ecran.getText()).contains(",")) && !Calculette.nouvelleMemoire) {
					Calculette.ecran.setText(Calculette.ecran.getText() + pBouton.getText());
				}
				if (Calculette.nouvelleMemoire) {
					Calculette.ecran.setText("0,");
					Calculette.nouvelleMemoire = false;
				}
			}
		});
	}

	/**
	 * Method used to set the return button behavior, if the new memory flag is off and the number on display is 
	 * bigger than 1, it splits the last numeral from the number, if the number on display's size is equal to 1 but 
	 * is not a "0" its set the number on display to "0". If the new memory flag is true, it sets the number on display to "0"
	 * and the flag to FALSE.
	 * 
	 * @param pBouton passed throught the switch case 
	 * 
	 */
	protected static void boutonRetour(Button pBouton) {
		pBouton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!Calculette.nouvelleMemoire) {
					if (Calculette.ecran.getText().length() > 1) {
						if (!((Calculette.ecran.getText()).equals("0"))) {
							Util.retour();
						}
					} else {
						Calculette.ecran.setText("0");
					}
				} else {
					Calculette.ecran.setText("0");
					Calculette.nouvelleMemoire = false;
				}

			}

		});

	}

	/**
	 * Method used to set the behaviro of numeric buttons and of the button (-), can only have one "-".
	 * Also, sets the new memroy flag to FALSE.
	 * 
	 * @param pBouton passed throught the switch case 
	 * 
	 */
	protected static void boutonNumerique(Button pBouton) {
		pBouton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (pBouton.getText().equals("(-)")) {
					if (!Calculette.ecran.getText().contains("-")) {
						Calculette.ecran.setText("-");
					}
					if (Calculette.nouvelleMemoire) {
						Calculette.ecran.setText("-");
						Calculette.nouvelleMemoire = false;
					}
				} else {
					if (Calculette.ecran.getText().equals("0")) {
						Calculette.ecran.setText(pBouton.getText());
					} else {
						Calculette.ecran.setText(Calculette.ecran.getText() + pBouton.getText());
					}
					if (Calculette.nouvelleMemoire) {
						Calculette.ecran.setText(pBouton.getText());
						Calculette.nouvelleMemoire = false;
					}
				}

			}
		});
	}

}
