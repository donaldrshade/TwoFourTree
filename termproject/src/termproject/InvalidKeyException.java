/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package termproject;

/**
 * This class is an exception in the termproject package. 
 * @author Donald R. Shade 
 * @verison 1.0
 * File: InvalidKeyException.java
 * Summary of Modifications
 * DD MMM 2017 - DRS - Edit
 * Description:
 *
 */
public class InvalidKeyException extends TwoFourTreeException {

    /**
     * Creates a new instance of InvalidKeyException without detail message.
     */
    public InvalidKeyException() {
    }


    /**
     * Constructs an instance of InvalidKeyException with the specified detail message.
     * @param msg the detail message.
     */
    public InvalidKeyException(String msg) {
        super(msg);
    }
}
