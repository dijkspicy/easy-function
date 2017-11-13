package other.pkg;

import com.dijkspicy.easyfunction.FnNotation;
import com.dijkspicy.easyfunction.FunctionFactory;
import org.junit.jupiter.api.Test;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/12
 */
public class UseCase {
    @Test
    void test() {
        FunctionFactory.registerFunction("", FnNotation.class);
    }
}
