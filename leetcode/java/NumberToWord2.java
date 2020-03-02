package leisure.leetcode.java;

import java.util.HashMap;
import java.util.Map;

class NumberToWord2 {
    private int BILLION = 1000000000;
    private int MILLION = 1000000;
    private int THOUSAND = 1000;
    private int HUNDRED = 100;
    

    Map<Integer, String> scaleMap = new HashMap<>();
    Map<Integer, String> wordsMap = new HashMap<>();
    Map<Integer, String> tensMap = new HashMap<>();

    NumberToWord2() {
        scaleMap.put(HUNDRED, "Hundred ");
        scaleMap.put(THOUSAND, "Thousand ");
        scaleMap.put(MILLION, "Million ");
        scaleMap.put(BILLION, "Billion ");
        wordsMap.put(0, "Zero");
        wordsMap.put(1, "One ");
        wordsMap.put(2, "Two ");
        wordsMap.put(3, "Three ");
        wordsMap.put(4, "Four ");
        wordsMap.put(5, "Five ");
        wordsMap.put(6, "Six ");
        wordsMap.put(7, "Seven ");
        wordsMap.put(8, "Eight ");
        wordsMap.put(9, "Nine ");
        wordsMap.put(10, "Ten ");
        wordsMap.put(11, "Eleven ");
        wordsMap.put(12, "Twelve ");
        wordsMap.put(13, "Thirteen ");
        wordsMap.put(14, "Fourteen ");
        wordsMap.put(15, "Fifteen ");
        wordsMap.put(16, "Sixteen ");
        wordsMap.put(17, "Seventeen ");
        wordsMap.put(18, "Eighteen ");
        wordsMap.put(19, "Nineteen ");
        tensMap.put(20, "Twenty ");
        tensMap.put(30, "Thirty ");
        tensMap.put(40, "Forty ");
        tensMap.put(50, "Fifty ");
        tensMap.put(60, "Sixty ");
        tensMap.put(70, "Seventy ");
        tensMap.put(80, "Eighty ");
        tensMap.put(90, "Ninty ");

    }

    public String numberToWords(int num) {
        // special case
        if (num == 0) {
            return wordsMap.get(num);
        }
        return convert(num).trim();
    }

    /**
     * recursively convert a number to words
     * @param num
     * @return
     */
    private String convert(int num) {
        if (num == 0) {
            return "";
        }

        int remainder = 0, ans = 0;
        if (num >= BILLION) {
            return convertHelper(num, BILLION);
        } else if (num >= MILLION) {
            return convertHelper(num, MILLION);
        } else if (num >= THOUSAND) {
            return convertHelper(num, THOUSAND);
        } else if (num >= HUNDRED) {
            return convertHelper(num, HUNDRED);
        } else if (num >= 20) {
            remainder = num % 20;
            ans = num - remainder;
            return tensMap.get(ans) + convert(remainder);
        } else {
            return wordsMap.get(num);
        }
    }

    private String convertHelper(int num, int base) {
        int remainder = num % base;
        int ans = Math.floorDiv(num, base);
        return convert(ans) + scaleMap.get(base) + convert(remainder);
    }

    public static void main(String[] args) {
        NumberToWord2 nw = new NumberToWord2();
        System.out.println(nw.numberToWords(1112186000));
    }
}