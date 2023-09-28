package de.denniskniep.keycloak.hsm.keyprovider;

import org.keycloak.common.VerificationException;
import org.keycloak.crypto.KeyWrapper;
import org.keycloak.crypto.SignatureException;
import org.keycloak.crypto.SignatureSignerContext;
import org.keycloak.crypto.SignatureVerifierContext;

public abstract class HsmKeyWrapper extends KeyWrapper {

    public abstract SignatureSignerContext createSignatureSignerContext() throws SignatureException;

    public abstract SignatureVerifierContext createSignatureVerifierContext() throws VerificationException;
}
