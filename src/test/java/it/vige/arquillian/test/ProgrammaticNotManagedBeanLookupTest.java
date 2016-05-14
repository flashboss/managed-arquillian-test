package it.vige.arquillian.test;

import static org.jboss.shrinkwrap.api.ShrinkWrap.create;
import static org.junit.Assert.fail;

import org.jboss.arquillian.container.test.api.Deployer;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ProgrammaticNotManagedBeanLookupTest extends Lookup {

	@ArquillianResource
	private Deployer deployer;

	@Deployment(name = "normal", managed = false)
	public static JavaArchive createDeployment() {
		return create(JavaArchive.class).addClass(ProgrammaticNotManagedBeanLookupTest.class).addClass(Lookup.class)
				.addAsManifestResource("org/beans.xml", "beans.xml");
	}

	@Test
	public void testLookupBean() throws Exception {
		deployer.deploy("normal");
		try {
			lookupBeanManagerInJndi();
		} catch (Exception ex) {
			fail();
		}
	}
}
