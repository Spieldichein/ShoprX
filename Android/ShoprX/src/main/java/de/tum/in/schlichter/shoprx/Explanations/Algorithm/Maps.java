package de.tum.in.schlichter.shoprx.Explanations.Algorithm;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by Nicksteal on 29.03.2015.
 */
public class Maps {
    /**
     * Creates a {@code HashMap} instance.
     *
     * @return a newly-created, initially-empty {@code HashMap}
     */
    public static <K, V> HashMap<K, V> newHashMap() {
        return new HashMap<K, V>();
    }

    /**
     * Creates a {@code LinkedHashMap} instance.
     *
     * @return a newly-created, initially-empty {@code HashMap}
     */
    public static <K, V> LinkedHashMap<K, V> newLinkedHashMap() {
        return new LinkedHashMap<K, V>();
    }
}