package Tests;

import Generator.StatusIndicators;

import static org.assertj.core.api.Assertions.assertThat;

public class StatusIndicatorsTest {
    public void test(){
        assertThat(StatusIndicators.HEAD).isEqualTo(3).as("Checking correctness of constants defining cell states 1.");
        assertThat(StatusIndicators.TAIL).isEqualTo(2).as("Checking correctness of constants defining cell states 1.");
        assertThat(StatusIndicators.CONDUCTOR).isEqualTo(1).as("Checking correctness of constants defining cell states 1.");
        assertThat(StatusIndicators.EMPTY).isEqualTo(0).as("Checking correctness of constants defining cell states 1.");
    }
}
