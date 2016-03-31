package com.claire;

import org.apache.commons.collections.list.GrowthList;

public class Sample {
    private final String name;

    public Sample(String name) {
        this.name = name;
        new GrowthList();
    }

    public String getName() {
        return name;
    }
}
