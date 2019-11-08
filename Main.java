package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    int[] computeLPSArray(String pat)
    {
        //adding a blank so as to shift all the characters by 1 to the right to match 1-based indexing
        pat = " " + pat;

        int m = pat.length();
        int pi[] = new int[m+1];
        pi[1] = 0;
        int k = 0;

        for(int q = 2; q < m; q++){
            while(k > 0 && pat.charAt(k+1)!=pat.charAt(q))
                k = pi[k];

            if(pat.charAt(k+1)==pat.charAt(q))
                k++;

            pi[q] = k;
        }

        return pi;
    }

    void KMP(String text, String pattern){

        int pi[] = computeLPSArray(pattern); //uses 1-based indexing

        //prepare the string for 1-based indexing
        text = " " + text;
        pattern = " " + pattern;
        int n = text.length();
        int m = pattern.length();
        int q = 0;
        boolean notFound = true;

        for(int i = 1; i < n; i++){
            while(q > 0 && pattern.charAt(q+1)!=text.charAt(i))
                q = pi[q];

            if(pattern.charAt(q+1)==text.charAt(i))
                q++;

            if(q==m-1){
                notFound = false;
                System.out.println("Pattern found at " + (i - m + 2));
                q = pi[q];
            }
        }

        if(notFound)
            System.out.println("No such pattern exists in the text");
    }

    public static void main(String[] args) throws java.lang.Exception
    {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter text and pattern : ");
        String txt = stdin.readLine();
        String pat = stdin.readLine();


        new Main().KMP(txt, pat);

    }
}
