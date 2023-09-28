package de.denniskniep.keycloak.hsm.signatureprovider;

import org.keycloak.crypto.SignatureProvider;
import org.keycloak.crypto.SignatureProviderFactory;
import org.keycloak.models.KeycloakSession;

public abstract class SignatureProviderPickerFactory implements SignatureProviderFactory {

    @Override
    public SignatureProvider create(KeycloakSession session) {
        return new SignatureProviderPicker(session, getAlgorithm(), isAsymmetricAlgorithm(), createStandard(session));
    }

    public abstract SignatureProvider createStandard(KeycloakSession session);

    public abstract boolean isAsymmetricAlgorithm();

    public abstract String getAlgorithm();

    @Override
    public int order() {
        return 1;
    }
}
