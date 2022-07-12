package tests;

import classes.AddTask04;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddTaskTest {
    @Test
    void TestTrue() {
        assertTrue(AddTask04.hasaValidBrackets("[{hello}()]"));
        assertTrue(AddTask04.hasaValidBrackets("[{}()]hello"));
        assertTrue(AddTask04.hasaValidBrackets("[{hello}(hello)]"));
        assertTrue(AddTask04.hasaValidBrackets("hello[{}()]hello"));
        assertTrue(AddTask04.hasaValidBrackets("hello[hello{hello}(hello)]"));
        assertTrue(AddTask04.hasaValidBrackets("[{hello}()hello]hello"));
        assertTrue(AddTask04.hasaValidBrackets("[{()}][{()}][{()}]"));
        assertTrue(AddTask04.hasaValidBrackets("[(),(),(),[{}]]"));
    }

    @Test
    void TestFalse() {
        assertFalse(AddTask04.hasaValidBrackets(")("));
        assertFalse(AddTask04.hasaValidBrackets("(hello[)]"));
        assertFalse(AddTask04.hasaValidBrackets("}()()()"));
        assertFalse(AddTask04.hasaValidBrackets("()()()["));
        assertFalse(AddTask04.hasaValidBrackets("())()[]"));
    }
}
