package com.company;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Введите пример для вычисления:");
        String lineToCompute = reader.readLine();
        System.out.println(showResult(lineToCompute));

    }

    public static boolean isRoman(char c) {
            if (c == 'I' || c == 'V' || c == 'X') {
                return true;
            } else try {
                throw new Exception();
            } catch (Exception ex){
                System.out.println("Ошибка! Введены некорректные римские числа!");
                System.exit(1);
            }
            return false;
    }

    public static int romanToArabicDigit(char c) {

        if (c == 'I')
            return 1;
        if (c == 'V')
            return 5;
        if (c == 'X')
            return 10;
        return -1;

    }

    public static int romanToArabic(String s) {
        int result, number1, number2;
        result = 0;
        for (int i = 0; i <= s.length() - 1; i++) {
            if (isRoman(s.charAt(i))) {
                number1 = romanToArabicDigit(s.charAt(i));
                if (i + 1 < s.length()) {
                    if (isRoman(s.charAt(i + 1))) {
                        number2 = romanToArabicDigit(s.charAt(i + 1));
                        if (number1 >= number2) {
                            result = result + number1;
                        } else result = result - number1;
                    } else result = result + number1;
                } else result = result + number1;
            }
        }
        return result;
    }

    public static int strToInt(String s) {
        int result;
        result = 0;

        for (int i = 0; i <= s.length() - 1; i++) {
            int num1, num2;
            num1 = Character.getNumericValue(s.charAt(i));

            if (Character.isDigit(s.charAt(i))) {
                if (i + 1 < s.length()) {
                    if (Character.isDigit(s.charAt(i + 1))) {

                        num2 = Character.getNumericValue(s.charAt(i+1));

                        result = num1 * 10 + num2;
                        i++;
                    } else result = result + num1;
                } else result = result + num1;
            }
        }
        return result;
    }

    

    public static int performOperation(String math_operator, int num1, int num2) {
        int result;

        try {
            if (num1 < 0 || num1 > 10 || num2 < 0 || num2 > 10){
                throw new Exception();
            }
        } catch (Exception ex){
            System.out.println("Ошибка! Введены числа больше 10 или меньше 0");
            System.exit(1);
        }

        switch (math_operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "/":
                result = num1 / num2;
                break;
            case "*":
                result = num1 * num2;
                break;

            default:
                    return -1;
        }
        return result;
    }

    public static String romanToArabic(int number){

        String result;
        result = "";
        int counter = 0;



        String[] romanNumbers = new String[] {"C","XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] arabicNumbers = new int[] {100, 90, 50, 40, 10, 9, 5, 4,  1};

        for (int i = 0; i <= arabicNumbers.length - 1; i++) {
            counter = number / arabicNumbers[i];
            number %= arabicNumbers[i];
            while (counter > 0) {
                result += romanNumbers[i];
                counter--;
            }
        }
        return result;
    }
    
    public static String showResult(String expression) {
        int num1, num2;
        String num1S, num2S;
        String math_operator;

        String resultString;
        resultString = "";

        int result;
        int operatorIndex;
        operatorIndex = 0;
        result = 0;

        math_operator = "";
        num1S = "";
        num2S = "";

        char[] mathOperators = {'+', '-', '/', '*'};

        for (int i = 0; i < expression.length(); i++) {
            for (int j = 0; j < mathOperators.length; j++) {
                if (expression.charAt(i) == mathOperators[j]) {
                    math_operator += expression.charAt(i);
                    operatorIndex = i;
                }
            }
        }

        if (math_operator == "")   try {
            throw new Exception();
        } catch (Exception ex){
            System.out.println("Ошибка! Введен некорректный арифметический оператор!");
            System.exit(1);
        }


                        for (int k = 0; k < operatorIndex; k++) {
                            if (expression.charAt(k) != ' ') {
                                num1S += expression.charAt(k);
                            }
                        }

                        for (int k = operatorIndex+1; k < expression.length(); k++) {
                            if (expression.charAt(k) != ' ') {
                                num2S += expression.charAt(k);
                            }
                        }


        if (Character.isDigit(num1S.charAt(0)) && Character.isDigit(num2S.charAt(0))){
            num1 = strToInt(num1S);
            num2 = strToInt(num2S);
            result = performOperation(math_operator, num1, num2);
            resultString =  String.valueOf(result);

        } else if (isRoman(num1S.charAt(0)) && isRoman(num2S.charAt(0))){
        num1 = romanToArabic(num1S);
        num2 = romanToArabic(num2S);

        result = performOperation(math_operator, num1, num2);
        resultString = romanToArabic(result);
            }
        return resultString;
    }
}
