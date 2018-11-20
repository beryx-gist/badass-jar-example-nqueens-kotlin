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

import org.beryx.streamplify.permutation.Permutations
import java.util.function.Predicate

import java.util.stream.Collectors

data class Position(val line: Int, val column: Int) {
    override fun toString(): String {
        return "(${line + 1}, ${column + 1})"
    }
}

class Solution {
    val positions = mutableListOf<Position>()
    override fun toString(): String {
        return positions.stream().map(Position::toString).collect(Collectors.joining(", "))
    }
}

class NQueens {
    companion object {
        fun solve(n: Int): List<Solution> {
            if(n < 1 || n > 12) throw IllegalArgumentException("Invalid size: $n. Accepted values: 1 .. 12")
            return Permutations(n)
                    .parallelStream()
                    .filter(object: Predicate<IntArray> {
                        override fun test(perm: IntArray): Boolean {
                            for (i in 0 until perm.size - 1) {
                                for (j in i + 1 until perm.size) {
                                    if (Math.abs(perm[j] - perm[i]) == j - i) return false
                                }
                            }
                            return true
                        }
                    })
                    .map{perm -> Solution().apply {
                        for(i in 0 until perm.size) {
                            positions.add(Position(i, perm[i]))
                        }
                    }}
                    .collect(Collectors.toList());
        }
    }
}
