/**
 * 
 */
package calc_test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * Main Class of the project, regroups the variables used for the calculator's memory, the main method and
 * a generic method for button creation.
 * 
 * @author Stagiaire_XMA 
 */
public class Calculette {

	/**
	 *  Static compostie used for display
	 */
	protected static Label ecran;
	/**
	 *  First number memory, "0" by default
	 */
	protected static String memoireValeur = "0";
	/**
	 *  Operant memory, "" by default
	 */
	protected static String memoireOperation = "";
	/**
	 *  Second number memory, "0" by default
	 */
	protected static String memoireValeur2 = "0";
	/**
	 *  Flag used to remeber if the screen has to be reseted or not, FALSE by default
	 */
	protected static boolean nouvelleMemoire = false;
	/**
	 *  Falg used for swtiching from normal mode to "Scientifique" mode, FALSE by default
	 */
	protected static boolean isScientifique = false;

	/**
	 * Method used to create the calculator's structure and launching / closing the program.
	 * 
	 * @param args the list of parameters.
	 * 
	 */
	public static void main(String[] args) {
		// Window's initialization 
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		shell.setLayout(new GridLayout(1, false));
		shell.setText("Calculatrice");

		// Visual composites initialization 
		Composite resultat = new Composite(shell, SWT.BORDER);
		Composite clavier = new Composite(shell, SWT.BORDER);
		Composite scientifique = new Composite(clavier, SWT.NONE);
		Composite scientifiqueToggle = new Composite(scientifique, SWT.NONE);
		Composite scientifiqueFonction = new Composite(scientifique, SWT.NONE);
		Composite centre = new Composite(clavier, SWT.NONE);
		Composite numerique = new Composite(centre, SWT.RIGHT_TO_LEFT);
		Composite fonction = new Composite(centre, SWT.NONE);
		Composite fin = new Composite(clavier, SWT.NONE);

		// Composite datas initialization
		GridData resultatData = new GridData(SWT.FILL, SWT.TOP, true, true);
		GridData clavierData = new GridData(SWT.FILL, SWT.FILL, true, true);
		GridData scientifiqueData = new GridData(SWT.FILL, SWT.FILL, true, true);
		GridData scientifiqueToggleData = new GridData(SWT.FILL, SWT.FILL, true, true);
		GridData scientifiqueFonctionData = new GridData(SWT.FILL, SWT.FILL, true, true);
		GridData centreData = new GridData(SWT.FILL, SWT.FILL, true, true);
		GridData numeriqueData = new GridData(SWT.FILL, SWT.FILL, true, true);
		GridData fonctionData = new GridData(SWT.FILL, SWT.FILL, true, true);
		GridData finData = new GridData(SWT.FILL, SWT.FILL, true, true);

		// Attributing data to composite
		resultat.setLayoutData(resultatData);
		clavier.setLayoutData(clavierData);
		scientifique.setLayoutData(scientifiqueData);
		scientifiqueToggle.setLayoutData(scientifiqueToggleData);
		scientifiqueFonction.setLayoutData(scientifiqueFonctionData);
		centre.setLayoutData(centreData);
		numerique.setLayoutData(numeriqueData);
		fonction.setLayoutData(fonctionData);
		fin.setLayoutData(finData);

		// Attributing layout to internal composites
		resultat.setLayout(new RowLayout(SWT.VERTICAL));
		clavier.setLayout(new GridLayout(1, false));
		scientifique.setLayout(new GridLayout(2, false));
		scientifiqueToggle.setLayout(new GridLayout(1, false));
		scientifiqueFonction.setLayout(new GridLayout(5, false));
		centre.setLayout(new GridLayout(2, false));
		numerique.setLayout(new GridLayout(3, false));
		fonction.setLayout(new GridLayout(1, false));
		fin.setLayout(new GridLayout(2, false));

		// Composites resizing
		shell.addListener(SWT.Resize, arg0 -> {
			Point size = shell.getSize();
			scientifiqueData.heightHint = (int) (size.y * 0.15);
			scientifique.addListener(SWT.Resize, arg1 -> {
				Point size1 = scientifique.getSize();
				scientifiqueToggleData.widthHint = (int) (size1.x / 6);
				scientifiqueFonctionData.widthHint = (int) (size1.x / 6 * 5);
			});
			centreData.heightHint = (int) (size.y * 0.55);
			centre.addListener(SWT.Resize, arg2 -> {
				Point size1 = centre.getSize();
				numeriqueData.widthHint = (int) (size1.x / 4 * 3);
				fonctionData.widthHint = (int) (size1.x) - numeriqueData.widthHint;
			});
			resultatData.heightHint = (int) (size.y * 0.15);
			finData.heightHint = (int) (size.y * 0.15);
			finData.widthHint = (int) (size.x * 0.5);
		});

		// Creating colors
		Color jaune = new Color(252, 252, 202);
		Color vert1 = new Color(227, 250, 227);
		Color vert2 = new Color(195, 250, 200);
		Color vert3 = new Color(145, 200, 175);
		Color rouge = new Color(255, 143, 143);
		Color bleu = new Color(109, 177, 255);
		Color gris1 = new Color(243, 243, 245);
		Color gris2 = new Color(223, 227, 232);

		// Creating the screen 
		Label label = new Label(resultat, SWT.RIGHT);
		RowData data = new RowData();
		resultat.addListener(SWT.Resize, arg0 -> {
			Point point = resultat.getSize();
			data.height = (int) (point.y);
			data.width = (int) (point.x * 0.98);
			label.setLayoutData(data);
			label.pack();
			FontData fontDataEcran = new FontData("Arial", (int) (point.y * 0.25), SWT.BORDER);
			Font fontEcran = new Font(null, fontDataEcran);
			label.setFont(fontEcran);
		});
		label.setText("0");
		label.setBackground(jaune);
		ecran = label;

		// Creating keybord for advanced computation
		creationBouton(scientifiqueToggle, "Sci", 6, vert3, scientifiqueFonction);
		creationBouton(scientifiqueFonction, "x²", 7, gris2, null);
		creationBouton(scientifiqueFonction, "\u221Ax", 7, gris2, null);
		creationBouton(scientifiqueFonction, "exp", 7, gris2, null);
		creationBouton(scientifiqueFonction, "ln", 7, gris2, null);
		creationBouton(scientifiqueFonction, "^", 3, gris2, null);
		scientifiqueFonction.setVisible(isScientifique);

		// Creating the main keys
		for (int i = 1; i < 10; i++) {
			Integer text = i;
			creationBouton(numerique, text.toString(), 0, gris1, null);
		}
		creationBouton(numerique, ">", 1, gris2, null);
		creationBouton(numerique, ",", 2, gris1, null);
		creationBouton(numerique, "0", 0, gris1, null);
		Control[] children = numerique.getChildren();
		for (int i = 0; i < children.length - 3; i++) {
			children[i].moveAbove(null);
		}

		// Creating simple computation keys
		creationBouton(fonction, "+", 3, gris2, null);
		creationBouton(fonction, "-", 3, gris2, null);
		creationBouton(fonction, "x", 3, gris2, null);
		creationBouton(fonction, "/", 3, gris2, null);
		creationBouton(fonction, "(-)", 0, gris2, null);

		// Creating the last keyboard's line
		creationBouton(fin, "CLEAR", 4, rouge, null);
		creationBouton(fin, "=", 5, bleu, null);

		// Colors attribution
		shell.setBackground(vert1);
		resultat.setBackground(jaune);
		clavier.setBackground(vert2);
		scientifique.setBackground(vert2);
		scientifiqueToggle.setBackground(vert2);
		scientifiqueFonction.setBackground(vert2);
		centre.setBackground(vert2);
		numerique.setBackground(vert2);
		fonction.setBackground(vert2);
		fin.setBackground(vert2);

		// Window's opening and size
		shell.setSize(500, 600);
		shell.pack();
		shell.open();

		// Calculator's closing
		while (!shell.isDisposed()) {
			//Used to resize the text when enlarging/reducing the window
			shell.layout();
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	/**
	 * Generic method used to create all the buttons, also set the button used to switch the calculator's mode
	 * from normal to "Scientifique". It was easier this way ...
	 * 
	 * @param composite where the button will be created
	 * 
	 * @param text visible text seen on the button
	 * 
	 * @param typeBouton the button's "familly" used to behavior's definition
	 * 
	 * @param pCouleur the button's color
	 * 
	 * @param scientifiqueFonction the composite use to display buttons from the advanced computation familly
	 */
	private static void creationBouton(Composite composite, String text, int typeBouton, Color pCouleur,
			Composite scientifiqueFonction) {
		Button bouton = new Button(composite, SWT.PUSH);
		GridData boutonData = new GridData(SWT.FILL, SWT.FILL, true, true);
		composite.addListener(SWT.Resize, arg0 -> {
			FontData fontDataBouton = new FontData("Arial", (int) (bouton.getSize().y * 0.30), SWT.BORDER);
			Font fontBouton = new Font(null, fontDataBouton);
			bouton.setLayoutData(boutonData);
			bouton.setFont(fontBouton);
			bouton.setText(text.toString());
			bouton.setBackground(pCouleur);
			if (typeBouton == 4 || typeBouton == 5) {
				boutonData.widthHint = (int) ((int) bouton.getParent().getSize().x * 0.5);
			}
			if (typeBouton == 7) {
				boutonData.widthHint = (int) ((int) bouton.getParent().getSize().x * 0.05);
			}
		});

		if (typeBouton != 6) {
			Comportement.definition(typeBouton, bouton);
		} else {
			bouton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					if (!Calculette.isScientifique) {
						Calculette.isScientifique = true;
					} else {
						Calculette.isScientifique = false;
					}
					scientifiqueFonction.setVisible(isScientifique);
				}
			});
		}

	}
}
