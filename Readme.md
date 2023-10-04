# Keycloak HSM 
Keycloak Extension for HSM (Hardware Security Module) support. 
Keycloaks builtin Keyproviders use the secret keys for signing or encryption directly from the keycloak instance itself.  
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

