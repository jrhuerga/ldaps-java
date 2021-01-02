import java.util.Hashtable;

import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

public class LDAPSRead {
	public static String getUserInfo(String url, String principal, String credentials, String search, String name) {
		String[] attrIDs = { "sn", "cn", "telephoneNumber" };
		String phone = "111";
	    SearchControls ctls = new SearchControls();	    
	    ctls.setReturningAttributes(attrIDs);
	    ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);
	    String filter = "(cn="+name+")";
		Hashtable env = new Hashtable();
		env.put(DirContext.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(DirContext.PROVIDER_URL, url );
		env.put(DirContext.SECURITY_AUTHENTICATION, "simple");
		env.put(DirContext.SECURITY_PRINCIPAL, principal);
		env.put(DirContext.SECURITY_CREDENTIALS, credentials);
		env.put("java.naming.ldap.factory.socket", "MySSLSocketFactory");
		DirContext dirContext = null;
		try {
			dirContext  = new InitialDirContext(env);
			NamingEnumeration e = dirContext.search(search, filter,ctls);
			while (e.hasMore()) {
				SearchResult entry = (SearchResult) e.next();
				System.out.println(entry.getName());
				Attributes attrs = entry.getAttributes();
				Attribute attr = attrs.get("telephoneNumber");				
				phone = (String) attr.get();
			}
		}
		catch (Exception e) {
			System.err.println(e);
		}
		return phone;
		
	}
}
