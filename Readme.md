# Keycloak HSM 
Keycloak Extension for HSM (Hardware Security Module) support. 
Keycloaks builtin Keyproviders use the secret keys for signing or encryption directly from the keycloak instance itself.  
This Keycloak Extension provides the foundational building blocks to implement signing and encryption with a HSM.
The HSM allows for example to execute signing and encryption operation without revealing the secret key itself.

## Overview

### Keylocation
Keylocation with builtin Keyproviders
```
+---------------------------+
|        Keycloak           |
|                           |
|                   +-----+ |
|                   | KEY | |
|                   +-----+ |
|                           |
|                           |
|                           |
+---------------------------+
```

Keylocation with Keycloak HSM Extension
```
+---------------------------+
|        Keycloak           |                          +-------------+
|                           |      SIGN/ENCRYPT        |     HSM     |
|                           +------------------------->|   +-----+   |
|                           |                          |   | KEY |   |
|                           |<-------------------------+   +-----+   |
|                           |                          |             |
|                           |                          +-------------+
|                           |
+---------------------------+
```

### Framework Concept
This project provides abstract classes for implementing a concrete HsmKeyProvider with signing and verification functionality.
Implementations of `HsmKeyProvider` creates `HsmKeyWrapper`. 
The implementation of the `HsmKeyWrapper` can contain additional information about the key location (Url of HSM WebService, mTLS configs, etc.) and the functionality how the signing and verification is done for that particular key.

This extension overwrites every algorithm that should be possible to be handled by an HSM (see package [de.denniskniep.keycloak.hsm.signatureprovider.alg](./src/main/java/de/denniskniep/keycloak/hsm/signatureprovider/alg))
These algorithm factories creates a `SignatureProviderPicker`, which picks the correct `SignatureSignerContext`/`SignatureVerifierContext` based on if it is a `KeyWrapper` or a `HsmKeyWrapper`.


## Create an Extension
Create an Extension based on "Keycloak HSM" by implementing the following components:
* HsmKeyProviderFactory
* HsmKeyProvider
* HsmKeyWrapper

Add the following [dependency](https://central.sonatype.com/artifact/de.denniskniep/keycloak-hsm):
```
<dependency>
    <groupId>de.denniskniep</groupId>
    <artifactId>keycloak-hsm</artifactId>
    <version>x.x.x</version>
    <scope>provided</scope>
</dependency>
```

Copy both extension jars into the `/opt/keycloak/providers/` folder.

See [Keycloak HSM Crypki](https://github.com/denniskniep/keycloak-hsm-crypki) as an example

