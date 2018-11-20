/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.beryx.jar.example.nqueens

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.api.Assertions.assertEquals


class NQueensTest {
    @DisplayName("Number of solutions")
    @ParameterizedTest(name = "The {0}x{0} problem should have {1} solutions")
    @CsvSource("1, 1", "2, 0", "3, 0", "4, 2", "5, 10", "6, 4", "7, 40", "8, 92", "9, 352", "10, 724")
    fun solutionCount(n: Int, count: Int) {
        assertEquals(count, NQueens.solve(n).size)
    }

    @Test
    fun two_solutions_for_size_4() {
        val solutions = NQueens.solve(4);
        assertEquals(2, solutions.size);
        assertEquals("(1, 2), (2, 4), (3, 1), (4, 3)", solutions.get(0).toString());
        assertEquals("(1, 3), (2, 1), (3, 4), (4, 2)", solutions.get(1).toString());
    }
}
