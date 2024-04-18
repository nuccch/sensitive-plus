package com.yhq.sensitive.converter;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

/**
 * @author chench
 * @date 2024.04.17
 */
public class ReplaceTest {
    @Test
    public void test() {
        String regex = "(\\d{3})(\\d{4})(\\d{4})"; //
        String message = "13987870086";
        String replacement = "$1****$3";
        System.out.println(Pattern.compile(regex).matcher(message).replaceAll(replacement));
    }

}