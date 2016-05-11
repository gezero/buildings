package com.ingg.puzzles.suffixtree;

import org.junit.Test;

import java.io.InputStream;

/**
 * Created by jiri.peinlich on 04/04/2016.
 */
public class TrieTest {

    SuffixTreeFactory factory = new TrieFactory();

    @Test
    public void createNewSuffixTreeTest() throws Exception {
        InputStream in = this.getClass().getClassLoader()
                .getResourceAsStream("example.txt");

        factory.createSuffixTree(in);
    }


}
