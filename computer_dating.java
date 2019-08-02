/* Name: Ellis Sentoso
*  Instructor : Dave Harden
*  Class: CS1 - Java
*  Assignment: Lab 8
*  Description: Dating profile compatibility
*  Email: ellissentoso@gmail.com
*  Date: 08/01/2019
*/
package lab8;
import java.lang.Math; 

public class Foothill {

	public static void main(String[] args) {
		// Declare all objects
		DateProfile applicant1 = new DateProfile('f', 'f', 7, 8, "Sarah Somebody");
		DateProfile applicant2 = new DateProfile('m', 'f', 4, 3, "Steve Nobody");
		DateProfile applicant3 = new DateProfile('f', 'f', 10, 6, "Jane Peabody");
		DateProfile applicant4 = new DateProfile('f', 'm', 8, 10, "Helen Anybody");
		
		// Giving fit values one by one
		displayTwoProfiles(applicant1, applicant1);
		displayTwoProfiles(applicant1, applicant2);
		displayTwoProfiles(applicant1, applicant3);
		displayTwoProfiles(applicant1, applicant4);
		System.out.println();
		
		displayTwoProfiles(applicant2, applicant1);
		displayTwoProfiles(applicant2, applicant2);
		displayTwoProfiles(applicant2, applicant3);
		displayTwoProfiles(applicant2, applicant4);
		System.out.println();
		
		displayTwoProfiles(applicant3, applicant1);
		displayTwoProfiles(applicant3, applicant2);
		displayTwoProfiles(applicant3, applicant3);
		displayTwoProfiles(applicant3, applicant4);
		System.out.println();
		
		displayTwoProfiles(applicant4, applicant1);
		displayTwoProfiles(applicant4, applicant2);
		displayTwoProfiles(applicant4, applicant3);
		displayTwoProfiles(applicant4, applicant4);
	}
	
	public static void displayTwoProfiles( DateProfile profile1, DateProfile profile2 ) {
		// output names and fit value
		System.out.println("Fit between " + profile1.getName() + " and " + profile2.getName() + ":\t" + profile1.fitValue(profile2));
	}

}

class DateProfile {
	// Private Class Static Constants
	private static final int MIN_ROMANCE = 1;
	private static final int MAX_ROMANCE = 10;
	private static final int MIN_FINANCE = 1;
	private static final int MAX_FINANCE = 10;
	private static final int MIN_NAME_LEN = 2;
	private static final int MAX_NAME_LEN = 30;
	private static final char DEFAULT_GEND = 'm';
	private static final char DEFAULT_SEARCH_GEND = 'f';
	private static final String DEFAULT_NAME = "(DEFAULT_NAME)";

	// Private Class Instance Members
	private char gender; // a char, the gender of the applicant ('M' or 'F').
	private char searchGender; // a char, the gender of desired partner ('M' or 'F'). This is not the gender of the applicant, but of the applicant's requested partner.
	private int romance; // an int from 1 to 10, indicating the importance of romance to the applicant.
	private int finance; // an int from 1 to 10, indicating the importance of finance to the applicant.
	private String name; // a String indicating the full name of the applicant.

	// A default ctor which initializes all variables to DEFAULT
	public DateProfile() {
		setDefaults ();
	}
	// A constructor that initializes all members according to the passed parameters. 
	// If not valid, it will not change the corresponding variable
	public DateProfile(char gdr, char sgdr, int rom, int fin, String nme) {
		setAll (gdr, sgdr, rom, fin, nme);
	}
	
	// An accessor for gender
	public char getGender () {
		return gender;
	}
	// An accessor for searchGender
	public char getSearchGender () {
		return searchGender;
	}
	// An accessor for romance
	public int getRomance () {
		return romance;
	}
	// An accessor for finance
	public int getFinance () {
		return finance;
	}
	// An accessor for name
	public String getName () {
		return name;
	}

	// A mutator for gender. If not valid, adopts no action
	public boolean setGender (char gdr) {
		if (gdr == 'm' || gdr == 'f') {
			gender = gdr;
			return true;
		}
		return false;
	}
	// A mutator for searchGender. If not valid, adopts no action
	public boolean setSearchGender (char sgdr) {
		if (sgdr == 'm' || sgdr == 'f') {
			searchGender = sgdr;
			return true;
		}
		return false;
	}
	
	// A mutator for romance. If not valid, adopts no action
	public boolean setRomance (int rom) {
		if (rom >= MIN_ROMANCE && rom <= MAX_ROMANCE) {
			romance = rom;
			return true;
		}
		return false;
	}
	// A mutator for finance. If not valid, adopts no action
	public boolean setFinance (int fin) {
		if (fin >= MIN_FINANCE && fin <= MAX_FINANCE) {
			finance = fin;
			return true;
		}
		return false;
	}
	// A mutator for name. If not valid, adopts no action
	public boolean setName (String nme) {
		if (nme.length() >= MIN_NAME_LEN && nme.length() <= MAX_NAME_LEN) {
			name = nme;
			return true;
		}
		return false;
	}

	// A mutator for all values. If not valid, adopts no action for that one
	public void setAll (char gdr, char sgdr, int rom, int fin, String nme) {
		setGender(gdr);
		setSearchGender (sgdr);
		setRomance (rom);
		setFinance (fin);
		setName (nme);
	}
	// A mutator for all values which changes them to default.
	public void setDefaults () {
                setGender(DEFAULT_GEND);
		setSearchGender (DEFAULT_SEARCH_GEND);
		setRomance (MIN_ROMANCE);
		setFinance (MIN_FINANCE);
		setName (DEFAULT_NAME);
	}
	
	public double fitValue(DateProfile partner) {
		if (determineGenderFit(partner) == 1) {
			return ((determineRomanceFit(partner) + determineFinanceFit(partner)) / 2);
		}
		else
			return 0;
	}
	// Private helper method to determine GenderFit
	private double determineGenderFit(DateProfile partner) {
		if (this.searchGender == partner.gender && partner.searchGender == this.gender)
			return 1;
		return 0;
	}

	// Private helper method to determine RomanceFit
	private double determineRomanceFit(DateProfile partner) {
		// The romance numbers should be highest (1.0) if the two values are equal 
		//(both 3, both 5, both 7) and lowest (perhaps a small non-zero value like .1)  if their difference is 9.
		double fit;
		fit = (MAX_ROMANCE - Math.abs(this.romance - partner.romance)) / (double)MAX_ROMANCE;
		return fit;
	}

	// Private helper method to determine FinanceFit
	private double determineFinanceFit(DateProfile partner) {
		// The finance numbers should be highest (1.0) if the two values are equal
		//(both 3, both 5, both 7) and lowest (perhaps a small non-zero value like .1)  if their difference is 9.
		double fit;
		fit = (MAX_FINANCE - Math.abs(this.finance - partner.finance)) / (double)MAX_FINANCE;
		return fit;
	}
}

/*
Fit between Sarah Somebody and Sarah Somebody:	1.0
Fit between Sarah Somebody and Steve Nobody:	0.0
Fit between Sarah Somebody and Jane Peabody:	0.75
Fit between Sarah Somebody and Helen Anybody:	0.0

Fit between Steve Nobody and Sarah Somebody:	0.0
Fit between Steve Nobody and Steve Nobody:	0.0
Fit between Steve Nobody and Jane Peabody:	0.0
Fit between Steve Nobody and Helen Anybody:	0.44999999999999996

Fit between Jane Peabody and Sarah Somebody:	0.75
Fit between Jane Peabody and Steve Nobody:	0.0
Fit between Jane Peabody and Jane Peabody:	1.0
Fit between Jane Peabody and Helen Anybody:	0.0

Fit between Helen Anybody and Sarah Somebody:	0.0
Fit between Helen Anybody and Steve Nobody:	0.44999999999999996
Fit between Helen Anybody and Jane Peabody:	0.0
Fit between Helen Anybody and Helen Anybody:	0.0
 */
