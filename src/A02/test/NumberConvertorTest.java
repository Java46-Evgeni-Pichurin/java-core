package A02.test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import A02method.NumberConvertor;
public class NumberConvertorTest {
    @Test
    void getConvertorFromZero(){
        assertEquals(Integer.toBinaryString(0), NumberConvertor.toBinaryStr(0));
    }
    @Test
    void getConvertorFromNegative(){
        assertEquals("0", NumberConvertor.toBinaryStr(-7));
    }
    @Test
    void getConvertor1(){
        assertEquals(Integer.toBinaryString(15), NumberConvertor.toBinaryStr(15));
    }
    @Test
    void getConvertor2(){
        assertEquals(Integer.toBinaryString(1111), NumberConvertor.toBinaryStr(1111));
    }
    @Test
    void getConvertor3(){
        assertEquals(Integer.toBinaryString(65145), NumberConvertor.toBinaryStr(65145));
    }

}
