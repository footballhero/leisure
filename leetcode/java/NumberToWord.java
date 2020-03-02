package leisure.leetcode.java;

class NumberToWord {
    String[] scales = {"", "Thousand ", "Million ", "Billion ", "Trillion "};
    String[] ones = {
        "Zero", "One ", "Two ", "Three ", "Four ", "Five ", "Six ", "Seven ", "Eight ", "Nine ",
        "Ten ", "Eleven ", "Twelve ", "Thirteen ", "Fourteen ", "Fifteen ", "Sixteen ", "Seventeen ", "Eighteen ", "Nineteen "
        };
    String[] tens = {"", "", "Twenty ", "Thirty ", "Forty ", "Fifty ", "Sixty ", "Seventy ", "Eighty ", "Ninety "};
    String hundred = "Hundred ";
    
    public String numberToWords(int num) {
        // special case
        if (num == 0) {
            return ones[0];
        }

        String numInString = addPadding(Integer.toString(num));
        int scaleIndex = Math.floorDiv(numInString.length()-1, 3);
        StringBuffer words = new StringBuffer();
        for (int i=0, n=numInString.length(); i < n; i += 3) {
            String word = getWordsHelper(numInString, i, i+3);
            if (!word.isEmpty()) {
                words.append(word).append(scales[scaleIndex]);
            }
            scaleIndex--;
        }                                                                                                                                                       

        System.out.println(numInString);
        return words.toString();
    }

    private String getWordsHelper(String numInString, int start, int end) {
        StringBuffer words = new StringBuffer();
        boolean nonZero = false;
        for (int i = start; i < end; i++) {
            int place = i - start;
            // get the word for the digit at ith location
            int digit = Integer.parseInt(numInString.substring(i,i+1));
            if (digit != 0) {
                nonZero = true;
            } else if (!nonZero) {
                continue;
            }
            switch (place) {
                case 0: {
                    // hundredth place
                    words.append(ones[digit]);
                    words.append(hundred);
                    break;
                }
                case 1: {
                    // if it is smaller than 20, we process the tenth and one together
                    if (digit == 1) {
                        int num = Integer.parseInt(numInString.substring(i, i+2));
                        words.append(ones[num]);
                        i++;
                    } else {
                        words.append(tens[digit]);
                    }
                    break;
                }
                case 2: {
                    if (digit != 0) {
                        words.append(ones[digit]);
                    }
                    break;
                }
            }

        }
        return words.toString();
    }

    private String addPadding(String input) {
        // pads number string to multiple of 3
        int numDigits = input.length();
        int mod = numDigits % 3;
        int padCount = (mod == 0) ? 0 : 3 - mod;
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < padCount; i++) {
            buffer.append("0");
        }
        buffer.append(input);
        return buffer.toString().trim();
    }

    public static void main(String[] args) {
        NumberToWord nw = new NumberToWord();
        System.out.println(nw.numberToWords(1000000));
    }
}