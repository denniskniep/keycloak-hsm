package de.denniskniep.keycloak.hsm.keyprovider;

import org.keycloak.crypto.KeyType;

public class AlgorithmUtils {

    public static String getTypeByAlgorithm(String algorithm){
        // RSA (asymmetric-key algorithm)
        if(algorithm.startsWith("RS") || algorithm.startsWith("PS")){
            return KeyType.RSA;
        }
        // Elliptic Curve (asymmetric-key algorithm)
        else if (algorithm.startsWith("ES") || algorithm.startsWith("EdDSA")) {
            return KeyType.EC;
        }
        // AES (symmetric-key algorithm)
        else if(algorithm.equals("AES")) {
            return KeyType.OCT;
        }
        throw new UnsupportedOperationException( algorithm + "can not be mapped to a KeyType");
    }
}
