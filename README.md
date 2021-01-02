# ldaps-java
Interact with LDAPS from Mule flow using Java

Mule LDAP connector only supports LDAP over TLS (port 389), and not LDAP over SSL (port 636).

This example shows how to interact with LDAPS (port 636) using a custom Java method.

To use the code, first modify the values of 'settings.properties' to link to your LDAPS server.

After that, import the code in Anypoint Studio and run it as a Mule App. By using this request you will get the telephone number stored for that user in the LDAP server.

http://localhost:8081/userinfo?name=Bob%20Jones

