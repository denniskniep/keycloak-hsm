# Keycloak HSM 
Keycloak Extension for HSM (Hardware Security Module) support. 
Builtin Keyproviders use the secret keys for signing or encryption directly from the keycloak instance itself.  
This Keycloak Extension provides the foundational building blocks to implement signing and encryption with a HSM.
The HSM allows for example to execute signing and encryption operation without revealing the secret key itself.

## Overview
```
+-----------------+
|                 |                          +-------------+
|   Keycloak      |       SIGN/ENCRYPT       |     HSM     |
|                 +------------------------->|   +-----+   |
|                 |                          |   | KEY |   |
|                 |<-------------------------+   +-----+   |
|                 |                          |             |
|                 |                          +-------------+
|                 |
+-----------------+
```

## Create an Extension
Create an Extension based on "Keycloak HSM" by implementing the following components:
* HsmKeyProviderFactory
* HsmKeyProvider
* HsmKeyWrapper