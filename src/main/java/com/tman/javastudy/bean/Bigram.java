package com.tman.javastudy.bean;

import java.util.HashSet;

public class Bigram {
    private final char first;
    private final char second;

    public static void main(String[] args) {
        HashSet<Bigram> hashSet = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            for (char j ='a';j<'z'; j++) {
                hashSet.add(new Bigram(j, j));
            }
        }
        System.out.println(hashSet.size());
    }

    public Bigram(char first, char second) {
        this.first = first;
        this.second = second;
    }


    public boolean equals(Bigram b) {
        return b.first == this.first && b.second == this.second;
    }

    public int hashCode() {
        return 31 * first + second;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Bigram)) {
            return false;
        }
        Bigram b = (Bigram) o;
        return b.first == this.first && b.second == this.second;
    }
}
