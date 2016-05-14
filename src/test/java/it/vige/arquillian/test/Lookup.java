package it.vige.arquillian.test;
import static javax.naming.InitialContext.doLookup;

import javax.enterprise.inject.spi.BeanManager;
import javax.naming.NamingException;

public class Lookup {

	public BeanManager lookupBeanManagerInJndi() throws Exception {

		try {
			// in an application server
			return (BeanManager) doLookup("java:comp/BeanManager");
		} catch (NamingException e) {
			// silently ignore
		}

		try {
			// in a servlet container
			return (BeanManager) doLookup("java:comp/env/BeanManager");
		} catch (NamingException e) {
			// silently ignore
		}

		throw new Exception(
				"Could not lookup beanmanager in jndi. If no jndi is avalable, set the beanmanger to the 'localInstance' property of this class.");
	}

}
