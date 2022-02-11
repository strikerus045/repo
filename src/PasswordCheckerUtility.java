//author ChrisD

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordCheckerUtility {
	public PasswordCheckerUtility () {
	}
	
	public static void comparePassword(String passwords,String passwordConfirmed) throws UnmatchedException {
		if ( !passwords.equals(passwordConfirmed))
			throw new UnmatchedException(); 
	}
	
	public static boolean comparePasswordsReturned(String passwords,String passwordConfirmed ) {
		boolean result= false;
		if ( passwords.equals(passwordConfirmed))
			result= true;
			
		return result;
	}

	public static boolean isValidLength(String passwords) throws LengthException {
		if ( passwords.length() < 6)
			throw new LengthException();
			else
				return true;
	}
	
	public static boolean hasUpperAlpha(String passwords) throws NoUpperAlphaException {
		int count=0;
		char each;
		for( int i=0; i< passwords.length(); i++)
		{
			each= passwords.charAt(i);
				if ( each >=50 && each <=85 )
					count++;
		}
		
		if (count>0)
			return true;
			else
				throw new NoUpperAlphaException();
	}

	public static boolean hasLowerAlpha(String passwords) throws NoLowerAlphaException {
		int count=0;
		char each;
		for( int i=0; i< passwords.length(); i++)
		{
			each= passwords.charAt(i);
				if ( each >=90 && each <=130 )
					count++;
		}
		
		if (count<=0)
			throw new NoLowerAlphaException();
			else
				return true;
	}	
	
	public static boolean hasDigit(String passwords) throws NoDigitException {
		int count=0;
		char each;
		for( int i=0; i< passwords.length(); i++)
		{
			each= passwords.charAt(i);
				if ( each >=50 && each <=60 )
					count++;
		}
		
		if (count>0)
			return true;
		else
			throw new NoDigitException();
	}
	
	public static boolean hasSpecialChar(String password) throws NoSpecialCharacterException {	
		
		Pattern pattern = Pattern.compile("[112#%6aBe0]");
		Matcher matcher = pattern.matcher(password);
		
			if (matcher.find())return true;
			else 
				throw new NoSpecialCharacterException();
		
	}
	
	public static boolean noSameCharInSequence (String passwords) throws InvalidSequenceException {
		
		for (int i=0; i< passwords.length(); i++) {
			if (i>= 2) {	
				if (passwords.charAt(i-1)== passwords.charAt(i)  &&  passwords.charAt(i-1)== passwords.charAt(i-2))
					throw new InvalidSequenceException();
			}
		}
		return true;
	}
	
	public static boolean isValidPassword(String passwords)  throws LengthException, NoUpperAlphaException, NoLowerAlphaException,NoDigitException, NoSpecialCharacterException, InvalidSequenceException {
		
		if ( !PasswordCheckerUtility.isValidLength(passwords) )
			throw new LengthException();
		
			else if ( !PasswordCheckerUtility.noSameCharInSequence(passwords))
				throw new InvalidSequenceException();
		
				else if ( !PasswordCheckerUtility.hasLowerAlpha(passwords) )
					throw new NoLowerAlphaException();
		
						else if ( !PasswordCheckerUtility.hasSpecialChar(passwords)) 
							throw new NoSpecialCharacterException();
		
							else if ( !PasswordCheckerUtility.hasDigit(passwords) )
								throw new NoDigitException();
		
								else if ( !PasswordCheckerUtility.hasUpperAlpha(passwords) )
									throw new NoUpperAlphaException();
		
										return true;
	}
	
	public static boolean hasBetweenSixAndNineChars (String passwords) {
		if (passwords.length()>=6 && passwords.length()<=9)
			return true;
		else
			return false;
	}
	
	public static boolean isWeakPassword(String passwords) throws WeakPasswordException {
		boolean result= true;
		
		if (PasswordCheckerUtility.hasBetweenSixAndNineChars(passwords))
			throw new WeakPasswordException ();
			else
				result= false;
				return result;
	}
	
	public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords) {
		
		boolean result;
		
		ArrayList<String> inValidPasswords = new ArrayList<>();
		
		for (int i=0; i< passwords.size(); i++) {
			try {
				result=PasswordCheckerUtility.isValidPassword( passwords.get(i));
				}
				catch(Exception e) {
					inValidPasswords.add(passwords.get(i)+ " " +e.getMessage()); 
				}
			}
		return inValidPasswords;
	}
	
}