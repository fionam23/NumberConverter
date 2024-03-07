import java.util.Arrays;
import java.util.HexFormat;

public class NumberConverter {
    private int[] digits;

    private char[] charDigits;
    private int decimal;
    private int base;
    private final String digits10to16 = "0123456789ABCDEFG";
    private final String digits10to64 ="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghigklmnopqrstuvwxyz+/";

    public NumberConverter(String number, int base) {
        if(base <= 10){
            digits = new int[number.length()];
            for (int i = 0; i < number.length(); i++) {
                String single = number.substring(i,i+1);
                int d = Integer.parseInt(single);
                digits[i] = d;
            }

            this.base = base;
        } else {
            charDigits = number.toCharArray();
            this.base = base;
        }

    }

    public String displayOriginalNumber() {
        String o = "";
        if(base <=10){
            for (int i = 0; i < digits.length; i++) {
                o = o + digits[i];
            }
            o = o + "\n";
            return o;
        } else {
            for (int i = 0; i < charDigits.length; i++) {
                o = o + charDigits[i];
            }
            o = o + "\n";
            return o;
        }

    }
    public boolean Verify(){
        boolean success = false;
        if(base <=10){
            for(int digit : getDigits()){
                if(digit>=base || digit < 0){
                    success = false;
                } else {
                    success = true;
                }
            }
            return success;
        } else {
            for(char digit : getCharDigits()){
                if(digits10to64.indexOf(digit)>=base || digits10to64.indexOf(digit) < 0){
                    success = false;
                } else {
                    success = true;
                }
            }
            return success;
        }

        //Make sure input matches base
    }

    public int[] getDigits() {
        return digits;
    }

    public char[] getCharDigits() {
        return charDigits;
    }

    public int[] convertToDecimal() {
        int[] decimalNum = null;
        if(base ==2){
            int sum = 0;
            int position = 0;
            for(int i = digits.length-1; i>=0;i--){
                sum += digits[i]*Math.pow(2,position);
                position++;
            }
            return convertIntToArray(sum);

        } else if(base ==8){
            int sum = 0;
            int position = 0;
            for(int i = digits.length-1; i>=0;i--){
                sum += digits[i]*Math.pow(8,position);
                position++;
            }
            return convertIntToArray(sum);
        } else if (base == 16) {
            int sum = 0;
            int position = 0;
            for(int i = charDigits.length-1; i>=0;i--){
                sum += digits10to16.indexOf(charDigits[i])*Math.pow(16,position);
                position++;
            }
            return convertIntToArray(sum);
        } else if (base == 64) {
            int sum = 0;
            int position = 0;
            for(int i = charDigits.length-1; i>=0;i--){
                sum += digits10to64.indexOf(charDigits[i])*Math.pow(64,position);
                position++;
            }
            return convertIntToArray(sum);
        }
        return decimalNum;
    }

    public int[] convertToBinary() {
        String binaryString = "";
        if(base == 10){
            decimal = convertArrayToInt(getDigits());
            int dividend = decimal;
            int temp = decimal;
            int remainder = 0;
            while (temp>1){
                temp /= 2;
                remainder = dividend-temp*2;
                dividend = temp;
                binaryString = remainder+binaryString;
            }
            binaryString = temp+binaryString;
            return convertIntToArray(Integer.parseInt(binaryString));
        } else if (base == 8|| base ==16 || base == 64) {
            decimal = convertArrayToInt(convertToDecimal());
            int dividend = decimal;
            int temp = decimal;
            int remainder = 0;
            while (temp>1){
                temp /= 2;
                remainder = dividend-temp*2;
                dividend = temp;
                binaryString = remainder+binaryString;
            }
            binaryString = temp+binaryString;
            return convertIntToArray(Integer.parseInt(binaryString));
        }
        return null;
    }

    public int[] convertToOctal() {
        String OctalString = "";
        if(base == 10){
            decimal = convertArrayToInt(getDigits());
            int dividend = decimal;
            int temp = decimal;
            int remainder = 0;
            while (temp>7){
                temp /= 8;
                remainder = dividend-temp*8;
                dividend = temp;
                OctalString = remainder+OctalString;
            }
            OctalString = temp+OctalString;
            return convertIntToArray(Integer.parseInt(OctalString));
        } else if (base == 2 || base == 16 || base == 64) {
            decimal = convertArrayToInt(convertToDecimal());
            int dividend = decimal;
            int temp = decimal;
            int remainder = 0;
            while (temp>7){
                temp /= 8;
                remainder = dividend-temp*8;
                dividend = temp;
                OctalString = remainder+OctalString;
            }
            OctalString = temp+OctalString;
            return convertIntToArray(Integer.parseInt(OctalString));
        }
        return null;
        }

    public char[] convertToHex(){

        String hexString = "";
        
        if(base == 10){
            decimal = convertArrayToInt(getDigits());
            int dividend = decimal;
            int temp = decimal;
            int remainder = 0;
            while(temp>15){
                temp /= 16;
                remainder = dividend-temp*16;
                dividend = temp;
                hexString = digits10to16.charAt(remainder)+hexString;
            }
            hexString = digits10to16.charAt(temp)+hexString;
            return hexString.toCharArray();
        } else if(base ==2 || base==8 || base == 64) {
            decimal = convertArrayToInt(convertToDecimal());
            int dividend = decimal;
            int temp = decimal;
            int remainder = 0;
            while (temp>15){
                temp /= 16;
                remainder = dividend-temp*16;
                dividend = temp;
                hexString = digits10to16.charAt(remainder)+hexString;
            }
            hexString = digits10to16.charAt(temp)+hexString;
            return hexString.toCharArray();
        }
        return null;
    }
    public char[] convertToBase64(){

        String base64String = "";

        if(base == 10){
            decimal = convertArrayToInt(getDigits());
            int dividend = decimal;
            int temp = decimal;
            int remainder = 0;
            while (temp>63){
                temp /= 64;
                remainder = dividend-temp*64;
                dividend = temp;
                base64String = digits10to64.charAt(remainder)+base64String;
            }
            base64String = digits10to64.charAt(temp)+base64String;
            return base64String.toCharArray();
        } else if(base ==2 || base==8 || base == 16) {
            decimal = convertArrayToInt(convertToDecimal());
            int dividend = decimal;
            int temp = decimal;
            int remainder = 0;
            while (temp>63){
                temp /= 64;
                remainder = dividend-temp*64;
                dividend = temp;
                base64String = digits10to64.charAt(remainder)+base64String;
            }
            base64String = digits10to64.charAt(temp)+base64String;
            return base64String.toCharArray();
        }
        return null;
    }
    public int[] convertIntToArray(int input){
        String tempString = Integer.toString(input);
        int[] output = new int[tempString.length()];
        for (int i= 0; i <Integer.toString(input).length(); i++ ){
            output[i] = Integer.parseInt(tempString.substring(i, i+1));
        }
        return output;
    }
    public int convertArrayToInt(int[] input){
        String tempString = "";
        for(int digit:input){
            tempString += digit;
        }
        return Integer.parseInt(tempString);
    }
    public String convertArrayToString(int[] input){
        String tempString = "";
        for(int digit:input){
            tempString += digit;
        }
        return tempString;
    }
    public String convertArrayToString(char[] input){
        String tempString = "";
        for(char digit:input){
            tempString += digit;
        }
        return tempString;
    }
    public char[] parseStringToCharArray(String input){
        return input.toCharArray();
    }
    public String toString(){
        if (Verify()){
            if (base == 2){
                return "Base 8: "+convertArrayToString(convertToOctal())+"\n" +
                        "Base 10: "+convertArrayToString(convertToDecimal())+"\n"+
                        "Base 16: "+ convertArrayToString(convertToHex())+"\n"+
                        "Base 64: "+convertArrayToString(convertToBase64());
            } else if (base == 8) {
                return "Base 2: "+convertArrayToString(convertToBinary()) +"\n" +
                        "Base 10: "+convertArrayToString(convertToDecimal())+"\n"+
                        "Base 16: "+ convertArrayToString(convertToHex())+"\n"+
                        "Base 64: "+convertArrayToString(convertToBase64());

            } else if(base == 10){
                return "Base 2: "+convertArrayToString(convertToBinary())+"\n" +
                        "Base 8: "+convertArrayToString(convertToOctal())+"\n"+
                        "Base 16: "+ convertArrayToString(convertToHex())+"\n"+
                        "Base 64: "+convertArrayToString(convertToBase64());
            } else if (base == 16) {
                return "Base 2: "+convertArrayToString(convertToBinary())+"\n"+
                        "Base 8: "+convertArrayToString(convertToOctal())+"\n"+
                        "Base 10: "+convertArrayToString(convertToDecimal())+"\n"+
                        "Base 64: "+convertArrayToString(convertToBase64());
            } else if (base == 64) {
                return "Base 2: "+convertArrayToString(convertToBinary())+"\n"+
                        "Base 8: "+convertArrayToString(convertToOctal())+"\n"+
                        "Base 10: "+convertArrayToString(convertToDecimal())+"\n"+
                        "Base 16: "+convertArrayToString(convertToHex())+"\n";
            }
        }
        return "Input does not match base";
    }
}

