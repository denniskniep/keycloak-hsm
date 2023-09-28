package de.denniskniep.keycloak.hsm.keyprovider;

import org.keycloak.crypto.KeyWrapper;
import org.keycloak.keys.KeyProvider;

import java.util.stream.Stream;

public abstract class HsmKeyProvider implements KeyProvider {

    @Override
    public Stream<KeyWrapper> getKeysStream() {
        return getHsmKeysStream().map(k -> k);
    }

    public abstract Stream<HsmKeyWrapper> getHsmKeysStream();
}
