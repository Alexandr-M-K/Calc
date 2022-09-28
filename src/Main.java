import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main
{
    private static int GetResult ( String operand, int FirstNumber, int SecondNumber)
    {
        int result = 404;
        switch (operand)
        {
            case "+" -> result = FirstNumber + SecondNumber;
            case "-" -> result = FirstNumber - SecondNumber;
            case "/" -> result = FirstNumber / SecondNumber;
            case "*" -> result = FirstNumber * SecondNumber;
        }
        return result;
    }

    public static String Calc(String numbers) throws NoOperand, IncorrectInput, IncorrectValue {
        Map<String, Integer> RomanNumber = new HashMap<>();
        {
            RomanNumber.put("I", 1);
            RomanNumber.put("II", 2);
            RomanNumber.put("III", 3);
            RomanNumber.put("IV", 4);
            RomanNumber.put("V", 5);
            RomanNumber.put("VI", 6);
            RomanNumber.put("VII", 7);
            RomanNumber.put("VIII", 8);
            RomanNumber.put("IX", 9);
            RomanNumber.put("X", 10);
        }
        Map<String, Integer> ArabicNumber = new HashMap<>();
        {
            ArabicNumber.put("1", 1);
            ArabicNumber.put("2", 2);
            ArabicNumber.put("3", 3);
            ArabicNumber.put("4", 4);
            ArabicNumber.put("5", 5);
            ArabicNumber.put("6", 6);
            ArabicNumber.put("7", 7);
            ArabicNumber.put("8", 8);
            ArabicNumber.put("9", 9);
            ArabicNumber.put("10", 10);
        }

        String[] operands = {"/", "+", "*", "-"};
        String operand = "";

        String FirstNumber, SecondNumber;

        int OperandIndex=-1;
        int result;

        for(int i = 0; i <operands.length; i++)
        {
            OperandIndex = numbers.indexOf(operands[i]);
            if (OperandIndex != -1)
            {
                operand = operands[i];
                break;
            }
        }
        if (OperandIndex == -1)
            throw new NoOperand("В примере нет операнда!");

        FirstNumber = numbers.substring(0, OperandIndex).trim();
        SecondNumber = numbers.substring(OperandIndex+1, numbers.length()).trim();

        if (RomanNumber.containsKey(FirstNumber) && RomanNumber.containsKey(SecondNumber))
        {
            result = GetResult(operand, RomanNumber.get(FirstNumber), RomanNumber.get(SecondNumber));
            if(result>=0)
                return RomanConversion.toRoman(result);
            else
                throw new IncorrectValue("Римское число не может быть меньше, либо равно нулю!");
        }
        else if (ArabicNumber.containsKey(FirstNumber) && ArabicNumber.containsKey(SecondNumber))
            result = GetResult(operand,ArabicNumber.get(FirstNumber),ArabicNumber.get(SecondNumber));
        else
            throw new IncorrectInput("Не верно введены числа!");

        return "" + result;

    }
    public static void main(String[] args) throws NoOperand, IncorrectInput, IncorrectValue {
        Scanner in = new Scanner(System.in);
        while(true)
        {
            System.out.println("Введите пример: ");
            String numbers = in.nextLine();
            String result = Calc(numbers);
            System.out.println(""+result);
        }
    }
}