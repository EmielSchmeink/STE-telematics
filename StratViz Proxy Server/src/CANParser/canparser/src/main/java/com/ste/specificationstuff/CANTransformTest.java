package com.ste.specificationstuff;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
//
// Functions actually used to be imported into this file for testing
//
public class CANTransformTest {

    // Sanity test
    @Test
    public void testAdd() {
        String str = "Junit is working fine";
        assertEquals("Junit is working fine", str);
    }
    
    // Test to see if all hexadecimal characters get converted to correct equivalent binary number
    @Test
    public void testHexStringToBinAllCharacters() {
        String str = "0123456789ABCDEF";
        String expected = "0000000100100011010001010110011110001001101010111100110111101111";
        // Function name to be replaced with actual function name
        assertEquals(expected, HexStringToBin(str));
    }

    // Test to see if all zero sequence gets converted to corresponding all zero sequence
    @Test
    public void testHexStringToBinZeros() {
        String str = "0000";
        String expected = "0000000000000000";
        // Function name to be replaced with actual function name
        assertEquals(expected, HexStringToBin(str));
    }

    // Test to see if empty string is handled correctly
    @Test
    public void testHexStringToBinEmpty() {
        String str = "";
        String expected = "";
        // Function name to be replaced with actual function name
        assertEquals(expected, HexStringToBin(str));
    }

    // Test to see if short hex string gets converted correctly
    @Test
    public void testHexStringToBinShort() {
        String str = "625AC";
        String expected = "01100010010110101100";
        // Function name to be replaced with actual function name
        assertEquals(expected, HexStringToBin(str));
    }

    // Test to see if long hex string get converted correctly
    @Test
    public void testHexStringToBinLong() {
        String str = "F3625B52A7D37AA9";
        String expected = "1111001101100010010110110101001010100111110100110111101010101001";
        // Function name to be replaced with actual function name
        assertEquals(expected, HexStringToBin(str));        
    }

    // Test to see if an illegal argument provides the expected error message
    @Test(expected = IllegalArgumentException.class)
    public void testHexStringToBinIllegalArg() {
        // Function name to be replaced with actual function name
        HexStringToBin(true); // should only accept strings, so this boolean should throw an error
    }
}
