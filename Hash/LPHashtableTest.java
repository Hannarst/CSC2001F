import static org.junit.Assert.*;
import java.io.*;
import org.junit.*;

public class LPHashtableTest{
	
	@Test
	public void test(){
		LPHashtable table = new LPHashtable(11);

		table.insert("meow", new Definition(WordType.NOUN, "sound cat makes"));
		table.insert("dead", new Definition(WordType.ADJECTIVE, "not fun"));
		table.insert("alive", new Definition(WordType.ADJECTIVE, "fun"));
		table.insert("comp sci", new Definition(WordType.NOUN, "super fun"));
		table.insert("problem solving", new Definition(WordType.VERB, "super duper fun"));
		table.insert("listening in class", new Definition(WordType.VERB, "super duper difficult :("));
		
		assertTrue(table.getDefinitions("meow"),"sound cat makes" );
		assertTrue(table.getDefinitions("dead"),"not fun" );		
		assertTrue(table.getDefinitions("alive"),"fun" );		
		assertTrue(table.getDefinitions("comp sci"),"super fun" );
		assertTrue(table.getDefinitions("problem solving"),"super duper fun" );
		assertTrue(table.getDefinitions("listening in class"),"super duper difficult :(" );	
	} 
	

	

	
}
