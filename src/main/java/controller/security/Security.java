package controller.security;

import model.exception.HashException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.validator.routines.EmailValidator;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;

public class Security {

    // Hashing parameters
    private static final int ITERATIONS = 200000;
    private static final int KEY_LENGTH = 512; // in bits
    private static final int SALT_LENGTH = 128; // in bits

    //regex
    public static final String CC_NUMBER_REGEX = "(^4[0-9]{12}(?:[0-9]{3})?$)|(^(?:5[1-5][0-9]{2}|222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]{12}$)|(3[47][0-9]{13})|(^3(?:0[0-5]|[68][0-9])[0-9]{11}$)|(^6(?:011|5[0-9]{2})[0-9]{12}$)|(^(?:2131|1800|35\\d{3})\\d{11}$)";
    public static final String CC_NAME_REGEX = "(?<! )[-a-zA-Z' ]{2,26}";
    public static final String CC_EXPIRATION_REGEX = "^(0[1-9]|1[0-2])\\/?([0-9]{2})$";
    public static final String CC_CVV_REGEX = "^[0-9]{3,4}$";
    public static final String URL_REGEX = "^https.*";

    public static String hashPassword(final String password) {
        byte[] salt = new byte[SALT_LENGTH / 8];
        (new SecureRandom()).nextBytes(salt);
        byte[] hashedBytes = null;
        try{
            hashedBytes = hashPassword(password.toCharArray(), salt);
        }catch (Exception exception){
            throw new HashException(exception.getMessage());
        }
        return Hex.encodeHexString(hashedBytes) + Hex.encodeHexString(salt);
    }

    private static byte[] hashPassword(final char[] password, final byte[] salt)  throws Exception{
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
        SecretKey key = skf.generateSecret(spec);
        return key.getEncoded();
    }

    public static boolean isPasswordCorrect(final String password, final String passwordSaltHexed) throws Exception {
        if (password == null || passwordSaltHexed == null)
            return false;
        String passwordHexed = passwordSaltHexed.substring(0, KEY_LENGTH / 8 * 2);
        byte[] salt = Hex.decodeHex(passwordSaltHexed.substring(KEY_LENGTH / 8 * 2).toCharArray());
        byte[] hashedBytes = hashPassword(password.toCharArray(), salt);
        return Hex.encodeHexString(hashedBytes).equals(passwordHexed);
    }

    public static boolean isEmailValid(final String email)  {
        return email != null && EmailValidator.getInstance().isValid(email);
    }

    /**
     * Password validation
     */
    public static boolean isPasswordValid(final String password)  {
        return password != null && password.length() >= 8 && password.length() <= 64;
    }

    /**
     * Primitive phone number validation
     */
    public static boolean isPhoneValid(String phoneNumber) {
        return phoneNumber != null && !phoneNumber.isBlank() && phoneNumber.matches("\\d+") && phoneNumber.length() > 8;
    }

    public static boolean isCreditCardNameValid(String creditCardName) {
        return creditCardName != null && !creditCardName.isBlank() && creditCardName.matches(CC_NAME_REGEX);
    }

    public static boolean isCreditCardNumberValid(String creditCardNumber) {
        return creditCardNumber != null && !creditCardNumber.isBlank() && creditCardNumber.matches(CC_NUMBER_REGEX);
    }

    public static boolean isCreditCardExpirationValid(String creditCardExpiration) {
        return creditCardExpiration != null && !creditCardExpiration.isBlank() && creditCardExpiration.matches(CC_EXPIRATION_REGEX);
    }

    public static boolean isCreditCardCvvValid(String creditCardCvv) {
        return creditCardCvv != null && !creditCardCvv.isBlank() && creditCardCvv.matches(CC_CVV_REGEX);
    }

    public static boolean isUrlValid(String imageUrl) {
        return imageUrl != null && !imageUrl.isBlank() && imageUrl.matches(URL_REGEX);
    }




}

