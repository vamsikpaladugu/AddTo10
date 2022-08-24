package com.vamsi.addtoten

class EvaluateExpression {

    // (, ), +, -, x, /
    //you have two arrays one is nums and other is expressions, you need to compute the result

    val operators = "+-x/";

    //(10+(9)+6)
    fun computeExpression(exp:String) : Int{

        println("aaa exp= $exp");

        var newExp = exp;

        if (exp.contains("(")) {

            val bracketExp = exp.substring(exp.indexOf('(')+1,exp.lastIndexOf(')'));

            println("aaa =- $bracketExp");

            val a = computeExpression(bracketExp);
            newExp = exp.replace("($bracketExp)",""+a);
            newExp = newExp.replace("+-","-").replace("--","+").replace("x-","x1-").replace("/-","/1-")

            println("aaa new= $newExp");

        }
        println("aaa new1 = $newExp");

        while (newExp.contains("x")) {

            val indexOfx = newExp.indexOf("x");

            var indexLeft = indexOfx-1;
            while (indexLeft>=0 && !operators.contains(newExp[indexLeft])) {
                indexLeft--;
            }
            val numberOnLeft = Integer.parseInt(newExp.substring(indexLeft+1,indexOfx));

            var indexRight = indexOfx+1;

            while (indexRight < newExp.length && !operators.contains(newExp[indexRight])) {
                indexRight++;
            }

            val numberOnRight = Integer.parseInt(newExp.substring(indexOfx+1,indexRight));

            newExp = newExp.replace("$numberOnLeft".plus("x").plus("$numberOnRight"),""+simpleCompute(numberOnLeft,numberOnRight,'x'));


        }

        while (newExp.contains("/")) {

            val indexOfx = newExp.indexOf("/");

            var indexLeft = indexOfx-1;
            while (indexLeft>=0 && !operators.contains(newExp[indexLeft])) {
                indexLeft--;
            }
            val numberOnLeft = Integer.parseInt(newExp.substring(indexLeft+1,indexOfx));

            var indexRight = indexOfx+1;

            while (indexRight < newExp.length && !operators.contains(newExp[indexRight])) {
                indexRight++;
            }

            val numberOnRight = Integer.parseInt(newExp.substring(indexOfx+1,indexRight));

            if(numberOnRight == 0 || numberOnLeft%numberOnRight != 0) {
                throw Exception("con't compute");
            }

            newExp = newExp.replace("$numberOnLeft".plus("/").plus("$numberOnRight"),""+simpleCompute(numberOnLeft,numberOnRight,'/'));

        }


        val newExpAdd = newExp.split("+");
        var res = 0;

        println("aaa 4 $newExp")

        newExpAdd.forEach { exp ->

            val e = exp.replace("-"," -").trim().split(" ");


            for (str in e) {

                res += Integer.parseInt(str);

            }

        }


        return res;
    }


    fun simpleCompute(a:Int,b:Int,c:Char) :Int {

        return when (c) {
            '+' -> {
                a+b;
            }
            '-' -> {
                a-b;
            }
            '/' -> {
                a/b;
            }
            else -> {
                a*b;
            }
        }


    }


}