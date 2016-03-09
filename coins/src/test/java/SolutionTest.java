import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by jiri.peinlich on 29/02/2016.
 */
public class SolutionTest {

    Solution solution = new Solution();


    @Test
    public void testBasicExample() throws Exception {
        int[] input = {1, 2, 5};

        assertThat(solution.coinChange(input, 11), is(3));


    }

    @Test
    public void testNoSolution() throws Exception {
        int[] input = {2};
        assertThat(solution.coinChange(input, 3), is(-1));

    }

    @Test
    public void testSomethingBigger() throws Exception {
        int[] uk = {1, 2, 5, 10, 20, 50, 100, 200};

        assertThat(solution.coinChange(uk, 2345), is(11 + 1 + 2 + 1));

    }
}
