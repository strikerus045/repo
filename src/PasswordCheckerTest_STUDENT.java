import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author Chris D
 *
 */
public class PasswordCheckerTest_STUDENT {
    
    ArrayList<String> password;

    @Before
    public void setUp() throws Exception {
        String[] c = {"CHD#9090", "C"};
        password = new ArrayList<String>();
        password.addAll(Arrays.asList(c));
    }

    @After
    public void tearDown() throws Exception {
        password = null;
    
    }

    /**
     * Test if the password is less than 6 characters long.
     * This test should throw a LengthException for second case.
     */
    @Test
    public void testIsValidPasswordTooShort() {
        try{
            assertTrue(PasswordCheckerUtility.isValidPassword("chd123"));
        }
        catch(LengthException e)
        {
            assertTrue("Successfully threw a lengthExcepetion",true);
        }
        catch(Exception e)
        {
            assertTrue("Threw some other exception besides lengthException",false);
        }
    }
    
    /**
     * Test if the password has at least one uppercase alpha character
     * This test should throw a NoUpperAlphaException for second case
     */
    @Test
    public void testIsValidPasswordNoUpperAlpha() {
        try{
            assertTrue(PasswordCheckerUtility.isValidPassword("chd45#"));
        }
        catch(NoUpperAlphaException e)
        {
            assertTrue("Successfully threw a NoUpperAlphaException",true);
        }
        catch(Exception e)
        {
            assertTrue("Threw some other exception besides NoUpperAlphaException",false);
        }
    }

    /**
     * Test if the password has at least one lowercase alpha character
     * This test should throw a NoLowerAlphaException for second case
     */
    @Test
    public void testIsValidPasswordNoLowerAlpha() {
        try{
            assertTrue(PasswordCheckerUtility.isValidPassword("CHD45#"));
        }
        catch(NoLowerAlphaException e)
        {
            assertTrue("Successfully threw a NoLowerAlphaException",true);
        }
        catch(Exception e)
        {
            assertTrue("Threw some other exception besides NoLowerAlphaException",false);
        }
    }
    /**
     * Test if the password has more than 2 of the same character in sequence
     * This test should throw a InvalidSequenceException for second case
     */
    @Test
    public void testIsWeakPassword() {
        try{
            assertTrue(PasswordCheckerUtility.isWeakPassword("Chd045"));
        }
        catch(WeakPasswordException e)
        {
            assertTrue("Successfully threw a WeakPasswordException",true);
        }
        catch(Exception e)
        {
            assertTrue("Threw some other exception besides WeakPasswordException",false);
        }
    }
    
    /**
     * Test if the password has more than 2 of the same character in sequence
     * This test should throw a InvalidSequenceException for second case
     */
    @Test
    public void testIsValidPasswordInvalidSequence() {
        try{
            assertTrue(PasswordCheckerUtility.isValidPassword("aaBE12$"));
        }
        catch(InvalidSequenceException e)
        {
            assertTrue("Successfully threw a InvalidSequenceException",true);
        }
        catch(Exception e)
        {
            assertTrue("Threw some other exception besides InvalidSequenceException",false);
        }
    }
    

    /**
     * Test if the password has at least one digit
     * One test should throw a NoDigitException
     */
    @Test
    public void testIsValidPasswordNoDigit() {
        try{
            assertTrue(PasswordCheckerUtility.isValidPassword("abcAbc"));
        }
        catch(NoDigitException e)
        {
            assertTrue("Successfully threw a NoDigitException",true);
        }
        catch(Exception e)
        {
            assertTrue("Threw some other exception besides NoDigitException",false);
        }
    }
    
    /**
     * Test correct passwords
     * This test should not throw an exception
     */
    @Test
    public void testIsValidPasswordSuccessful()
    {
        try 
        {
            assertTrue(PasswordCheckerUtility.isValidPassword("CHd045!"));    
        }
        catch(Exception e) 
        {
            assertTrue("This password is invalid", false);
        }
    }
    
    /**
     * Test the invalidPasswords method
     * Check the results of the ArrayList of Strings returned by the validPasswords method
     */
    @Test
    public void testInvalidPasswords() {
        
        ArrayList<String> results;
        results = PasswordCheckerUtility.getInvalidPasswords(password);
        
        Scanner scan = new Scanner(results.get(0)); 
        assertEquals(scan.next(), "ABE12%T");
        String nextResults = scan.nextLine().toLowerCase();
        assertTrue(nextResults.contains("lowercase"));
        
        scan = new Scanner(results.get(1));  
        assertEquals(scan.next(), "AAbe12%t");
        nextResults = scan.nextLine().toLowerCase(); 
        assertTrue(nextResults.contains("sequence"));
    
    }
}