package de.denniskniep.keycloak.hsm.signatureprovider.alg;

import de.denniskniep.keycloak.hsm.signatureprovider.AsymmetricSignatureProviderPickerFactory;
import org.keycloak.crypto.Algorithm;

public  class RS256SignatureProviderFactory extends AsymmetricSignatureProviderPickerFactory {

    public static final String ALGORITHM = Algorithm.RS256;

    @Override
    public String getAlgorithm() {
        return ALGORITHM;
    }
}
