package com.assessment.app.hcf;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SolutionTest {

    @Test(expected = IllegalArgumentException.class)
    public void highestCommonFactor_throwIllegalException_numbersIsNull() {
        Solution.highestCommonFactor(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void highestCommonFactor_throwIllegalException_numbersIsEmpty() {
        Solution.highestCommonFactor(new int[] {});
    }

    @Test(expected = IllegalArgumentException.class)
    public void highestCommonFactor_throwIllegalException_numbersHasOneOrLessElements() {
        Solution.highestCommonFactor(new int[] {1});
    }

    @Test
    public void highestCommonFactor_returnHighestCommonFactor() {
        assertEquals(2, Solution.highestCommonFactor(new int[] {2, 5, 6, 9, 10}));
    }

}