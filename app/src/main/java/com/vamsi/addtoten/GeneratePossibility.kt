package com.vamsi.addtoten

import java.lang.Exception
import kotlin.random.Random

class GeneratePossibility {

    val arr = arrayOf(
        arrayOf(0, 1, 2, 3),
        arrayOf(0, 1, 3, 2),

        arrayOf(0, 2, 1, 3),
        arrayOf(0, 2, 3, 1),

        arrayOf(0, 3, 1, 2),
        arrayOf(0, 3, 2, 1),

        arrayOf(1, 0, 2, 3),
        arrayOf(1, 0, 3, 2),

        arrayOf(1, 2, 0, 3),
        arrayOf(1, 2, 3, 0),

        arrayOf(1, 3, 0, 2),
        arrayOf(1, 3, 2, 0),

        arrayOf(2, 0, 1, 3),
        arrayOf(2, 0, 3, 1),

        arrayOf(2, 1, 0, 3),
        arrayOf(2, 1, 3, 0),

        arrayOf(2, 3, 0, 1),
        arrayOf(2, 3, 1, 0),

        arrayOf(3, 0, 1, 2),
        arrayOf(3, 0, 2, 1),

        arrayOf(3, 1, 0, 2),
        arrayOf(3, 1, 2, 0),

        arrayOf(3, 2, 0, 1),
        arrayOf(3, 2, 1, 0),
    )


    fun generateAllPossibilities(): Int {


        val operators = charArrayOf('+', '-', '/', 'x');

        var count = 0;

        for (num in 0..9999) {

            var numString =
                String.format("%04d", num).replace("", " ").trim().split(" ").toMutableList();

            println("aaa -- --- $numString")


            for (indexI in 0..3) {
                for (indexJ in indexI + 1..3) {

                    numString[indexI] = "(" + numString[indexI];
                    numString[indexJ] = numString[indexJ] + ")";

                    for (op in 0..63) {

                        val op1 = op % 4;
                        val op2 = (op / 4) % 4;
                        val op3 = ((op / 4) / 4) % 4;

                        val exp =
                            numString[0] + operators[op1] + numString[1] + operators[op2] + numString[2] + operators[op3] + numString[3];

                        println("aaa -- - $exp")

                        try {
                            val res = EvaluateExpression().computeExpression(exp)
                            if (res == 10) {
                                count++;
                                println("res = 10 $exp = 10")
                            }
                        } catch (e: Exception) {
                            println("aaa" + e.message)
                        }

                    }

                    numString[indexI] = numString[indexI].replace("(", "");
                    numString[indexJ] = numString[indexJ].replace(")", "");

                }
            }


        }

        return count;

    }

    fun generateExpression(): Int {

        var res = 0;
        var num = 0;

        while (res != 10) {

            val operators = charArrayOf('+', '-', '/', 'x');

            num = Random.nextInt(9999);

            val operator = Random.nextInt(63);
            val braceOpen = Random.nextInt(3);
            val braceClose = braceOpen + 1 + Random.nextInt(3 - braceOpen);

            var numString =
                String.format("%04d", num).replace("", " ").trim().split(" ").toMutableList();

            numString[braceOpen] = "(" + numString[braceOpen];
            numString[braceClose] = numString[braceClose] + ")";

            val op1 = operator % 4;
            val op2 = (operator / 4) % 4;
            val op3 = ((operator / 4) / 4) % 4;

            val exp =
                numString[0] + operators[op1] + numString[1] + operators[op2] + numString[2] + operators[op3] + numString[3];

            try {
                res = EvaluateExpression().computeExpression(exp)
            } catch (e: Exception) {
                println("aaa" + e.message)
            }
        }

        return num;

    }


    fun generateSolution(num: Int): ArrayList<String> {

        val ans = ArrayList<String>();

        val str = String.format("%04d", num);

        val uniqueNumb = HashSet<String>();

        for (i in 0..23) {

            val arrayOfString = arr[i];

            val localStr = ""+str[arrayOfString[0]]+str[arrayOfString[1]]+str[arrayOfString[2]]+str[arrayOfString[3]]

            if (!uniqueNumb.add(localStr)) {
                continue
            }

            val numString = localStr.replace("", " ").trim().split(" ").toMutableList();

            val operators = charArrayOf('+', '-', '/', 'x');

            for (indexI in 0..3) {
                for (indexJ in indexI + 1..3) {

                    numString[indexI] = "(" + numString[indexI];
                    numString[indexJ] = numString[indexJ] + ")";

                    for (op in 0..63) {

                        val op1 = op % 4;
                        val op2 = (op / 4) % 4;
                        val op3 = ((op / 4) / 4) % 4;

                        val exp =
                            numString[0] + operators[op1] + numString[1] + operators[op2] + numString[2] + operators[op3] + numString[3];

                        println("aaa -- - $exp")

                        try {
                            val res = EvaluateExpression().computeExpression(exp)
                            if (res == 10) {
                                ans.add(exp);
                            }
                        } catch (e: Exception) {
                        }

                    }

                    numString[indexI] = numString[indexI].replace("(", "");
                    numString[indexJ] = numString[indexJ].replace(")", "");

                }
            }

        }
        return ans;

    }


}