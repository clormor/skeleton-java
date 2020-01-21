package io.github.clormor.skeleton.concurrency;

import com.google.common.collect.Lists;

import javax.swing.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class WordLength implements Runnable {

    private static final int NUM_WORKERS = 8;
    private int length = 0;
    private Collection<String> words;

    WordLength(Collection<String> words) {
        this.words = words;
    }

    @Override
    public void run() {
        this.length = 0;
        for (String word : words) {
            length += word.length();
        }
    }

    int getLength() {
        return length;
    }

    public static void main(String[] args) throws InterruptedException {
        List<String> input = Collections.nCopies(100, "devskills");

        WordLength[] workers = new WordLength[NUM_WORKERS];
        for (int i = 0; i < NUM_WORKERS; i++) {
            Collection<String> words = Lists.newArrayList();
            for (int j = i; j < input.size(); j += NUM_WORKERS) {
                words.add(input.get(j));
            }
            workers[i] = new WordLength(words);
        }

        Thread[] threads = new Thread[NUM_WORKERS];
        for (int i = 0; i < NUM_WORKERS; i++) {
            threads[i] = new Thread(workers[i]);
            threads[i].start();
        }

        for (int i = 0; i < NUM_WORKERS; i++) {
            threads[i].join();
        }

        int result = 0;
        for (int i = 0; i < NUM_WORKERS; i++) {
            result += workers[i].getLength();
        }

        System.out.println(result);
    }
}