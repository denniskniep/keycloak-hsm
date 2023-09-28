package de.denniskniep.keycloak.hsm.keyprovider;

import org.keycloak.keys.KeyProviderFactory;

public interface HsmKeyProviderFactory<T extends HsmKeyProvider> extends KeyProviderFactory<T> {
}
