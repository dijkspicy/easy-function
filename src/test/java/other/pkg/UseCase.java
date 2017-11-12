package other.pkg;

import com.dijkspicy.easyfunction.FnFactory;
import com.dijkspicy.easyfunction.FnNotation;
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
        FnFactory.registerFunction("", FnNotation.class);
    }
}
