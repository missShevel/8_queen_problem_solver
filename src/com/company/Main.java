package com.company;
import java.util.*;
public class Main {


    public static void main(String[] args) throws CloneNotSupportedException {


        ArrayList<Integer> input = new ArrayList<Integer>();
        input.add(4);
        input.add(15);
        input.add(17);
        input.add(32);
        input.add(37);
        input.add(44);
        input.add(50);
        input.add(59);
//
        String method = "IDS";

        Solution one = new Solution(input, method);
        System.out.println(one.getSolution());
        System.out.println("  +++++++++++++++  ");
        System.out.println(one.getTraversal());

        }
    }


