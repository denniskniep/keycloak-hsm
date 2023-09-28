package de.denniskniep.keycloak.hsm.signatureprovider;

import org.keycloak.crypto.AsymmetricSignatureProvider;
import org.keycloak.crypto.SignatureProvider;
import org.keycloak.models.KeycloakSession;

public abstract class AsymmetricSignatureProviderPickerFactory extends SignatureProviderPickerFactory {

        @Override
    public String getId() {
        return getAlgorithm();
    }

    @Override
    public SignatureProvider createStandard(KeycloakSession session) {
        return new AsymmetricSignatureProvider(session, getAlgorithm());
    }

    @Override
    public boolean isAsymmetricAlgorithm() {
        return true;
    }

}
