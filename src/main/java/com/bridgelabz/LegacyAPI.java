package com.bridgelabz;

public class LegacyAPI {
    @Deprecated
    void oldFeature() {
        System.out.println("oldFeature");
    }

    void newFeature() {
        System.out.println("newFeature");
    }
}

class MainForLegacy {
    public static void main(String[] args) {
        LegacyAPI l = new LegacyAPI();
        l.oldFeature();
        l.newFeature();
    }
}
