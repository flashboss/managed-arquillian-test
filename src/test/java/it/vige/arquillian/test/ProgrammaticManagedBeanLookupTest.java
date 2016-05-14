package it.vige.arquillian.test;

import static org.jboss.shrinkwrap.api.ShrinkWrap.create;
import static org.junit.Assert.fail;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ProgrammaticManagedBeanLookupTest extends Lookup {

	@Deployment
	public static JavaArchive createDeployment() {
		return create(JavaArchive.class).addClass(ProgrammaticManagedBeanLookupTest.class).addClass(Lookup.class)
				.addAsManifestResource("org/beans.xml", "beans.xml");
	}

	@Test
	public void testLookupBean() throws Exception {
		try {
			lookupBeanManagerInJndi();
		} catch (Exception ex) {
			fail();
		}
	}
}
