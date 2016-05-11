package com.ingg.puzzles.suffixtree;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by jiri.peinlich on 04/04/2016.
 */
public interface SuffixTreeFactory {
    default SuffixTree createSuffixTree(InputStream inputStream) throws IOException {
        SuffixTree tree = createEmptySuffixTree();
        int c;
        while ((c = inputStream.read()) != -1) {
            tree = appendCharacter(tree, c);
        }
        return tree;
    }

    SuffixTree createEmptySuffixTree();
    SuffixTree appendCharacter(SuffixTree tree, int character);
}
