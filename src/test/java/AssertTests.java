import org.junit.Assert;
import org.junit.Test;

import br.ce.wcaquino.entidades.Usuario;

public class AssertTests {

	@Test
	public void test() {

		Assert.assertFalse(false);
		Assert.assertTrue(true);

		Assert.assertEquals(1, 1);
		Assert.assertEquals(0.512, 0.512, 0.001);
		Assert.assertEquals(Math.PI, 3.14, 0.01);
		
		int i = 5;
		Integer i2 = 5;
		//Assert.assertEquals(i, i2); 					//Doesn't work
		Assert.assertEquals(i, i2.intValue());			//It works
		Assert.assertEquals(Integer.valueOf(i), i2);	//It works
		
		Assert.assertEquals("bola", "bola");
		//Assert.assertEquals("bola", "Bola");			//Doesn't work
		Assert.assertNotEquals("bola", "casa");
		Assert.assertTrue("bola".equalsIgnoreCase("Bola"));
		Assert.assertTrue("bola".startsWith("bo"));
		
		Usuario u1 = new Usuario("Usuario 1");
		Usuario u2 = new Usuario("Usuario 1");
		Usuario u3 = u2;
		//Assert.assertEquals(u1, u2); 	//Doesn't work, needs method "Equals"
		Assert.assertEquals(u1, u2);	//Now this works
		
		//Assert.assertSame(u1, u2); //Doesn't work, the objects are not the same
		Assert.assertSame(u1, u1);	 //It works
		Assert.assertSame(u2, u3);	 //It works
		
		Usuario u4 = null;
		Assert.assertTrue(u4 == null);
		Assert.assertNull(u4);
		
	}
}
