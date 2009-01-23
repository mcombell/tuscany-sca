

import junit.framework.TestCase;

import org.apache.tuscany.sca.host.embedded.SCADomain;

import calculator.CalculatorService;



/**
 * Test SCADomain.newInstance and invocation of a service.
 * 
 * @version $Rev: 608205 $ $Date: 2008-01-02 20:29:05 +0000 (Wed, 02 Jan 2008) $
 */
public class ContributionFolderTestCaseFIXME extends TestCase {

    private SCADomain domain;
    
    @Override
    protected void setUp() throws Exception {
        domain = SCADomain.newInstance("myDomain", "src/test/resources/repository2/folderWithJars", null );
    }

    public void testInvoke() throws Exception {
        CalculatorService service = domain.getService(CalculatorService.class, "CalculatorServiceComponent");
        assertEquals(3.0, service.add(1, 2));
    }

    @Override
    protected void tearDown() throws Exception {
        domain.close();
    }

}
