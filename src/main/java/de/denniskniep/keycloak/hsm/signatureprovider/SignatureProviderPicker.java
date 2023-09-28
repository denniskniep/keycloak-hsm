package de.denniskniep.keycloak.hsm.signatureprovider;

import de.denniskniep.keycloak.hsm.keyprovider.AlgorithmUtils;
import de.denniskniep.keycloak.hsm.keyprovider.HsmKeyWrapper;
import org.keycloak.common.VerificationException;
import org.keycloak.crypto.*;
import org.keycloak.models.KeycloakSession;

public class SignatureProviderPicker implements SignatureProvider {

    private final KeycloakSession session;
    private final String algorithm;
    private final String keyType;
    private final boolean isAsymmetricAlgorithm;
    private final SignatureProvider standardSignatureProvider;

    public SignatureProviderPicker(KeycloakSession session, String algorithm, boolean isAsymmetricAlgorithm, SignatureProvider standardSignatureProvider) {
        this.session = session;
        this.algorithm = algorithm;
        this.keyType = AlgorithmUtils.getTypeByAlgorithm(algorithm);
        this.isAsymmetricAlgorithm = isAsymmetricAlgorithm;
        this.standardSignatureProvider = standardSignatureProvider;
    }

    @Override
    public SignatureSignerContext signer() throws SignatureException {
        KeyWrapper key = session.keys().getActiveKey(session.getContext().getRealm(), KeyUse.SIG, algorithm);
        if (key == null) {
            throw new SignatureException("Active key for " + algorithm + " not found");
        }

        if (key instanceof HsmKeyWrapper){
            return ((HsmKeyWrapper)key).createSignatureSignerContext();
        }

        return standardSignatureProvider.signer();
    }

    @Override
    public SignatureSignerContext signer(KeyWrapper key) throws SignatureException {
        SignatureProvider.checkKeyForSignature(key, algorithm, keyType);

        if (key instanceof HsmKeyWrapper){
            return ((HsmKeyWrapper)key).createSignatureSignerContext();
        }

        return standardSignatureProvider.signer(key);
    }

    @Override
    public SignatureVerifierContext verifier(String kid) throws VerificationException {
        KeyWrapper key = session.keys().getKey(session.getContext().getRealm(), kid, KeyUse.SIG, algorithm);
        if (key == null) {
            throw new VerificationException("Key not found");
        }

        if (key instanceof HsmKeyWrapper){
            return ((HsmKeyWrapper)key).createSignatureVerifierContext();
        }

        return standardSignatureProvider.verifier(kid);
    }

    @Override
    public SignatureVerifierContext verifier(KeyWrapper key) throws VerificationException {
        SignatureProvider.checkKeyForVerification(key, algorithm, keyType);

        if (key instanceof HsmKeyWrapper){
            return ((HsmKeyWrapper)key).createSignatureVerifierContext();
        }

        return standardSignatureProvider.verifier(key);
    }

    @Override
    public boolean isAsymmetricAlgorithm() {
        return isAsymmetricAlgorithm;
    }
}
