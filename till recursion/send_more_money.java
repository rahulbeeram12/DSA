import java.util.*;

public class send_more_money{
    static String s1 = "send";
    static String s2 = "more";
    static String s3 = "money";
    static int[] mapping = new int[26];
    static boolean[] isNumberUsed = new boolean[10];

    public static int StringToNumber(String str){
        int res = 0;
        for(int i = 0; i < str.length(); i++){ //hella
            int rem = mapping[str.charAt(i) - 'a'];
            res = res * 10 + rem;
        }
        return res;
    }

    //finding unique char using freq map
    public static String uniqueChar(String str){
        StringBuilder sb = new StringBuilder();
        boolean freq[] = new boolean[26];
        for(int i = 0; i < str.length(); i++){
            freq[str.charAt(i) - 'a'] = true;
        }

        for(int i = 0; i < freq.length; i++){
            if(freq[i] == true){
                sb.append((char)(i + 'a'));
            }
        }

        return sb.toString();
    }

    public static int cryptoArith(String str,int idx){
        if(idx == str.length()){
            int num1 = StringToNumber(s1);
            int num2 = StringToNumber(s2);
            int num3 = StringToNumber(s3);
            if(num1 + num2 == num3 && mapping[s1.charAt(0) - 'a'] != 0 && mapping[s2.charAt(0) - 'a'] != 0 && mapping[s3
                    .charAt(0) - 'a'] != 0){
                System.out.println(num1);
                System.out.println("+" + num2);
                System.out.println("----");
                System.out.println(num3);
                System.out.println();
                return 1;
            }
            return 0;
        }

        char ch = str.charAt(idx);
        int count = 0;
        for(int num = 0; num <= 9; num++){
            if((ch == s1.charAt(0) || ch == s1.charAt(0) || ch == s1.charAt(0)) && num == 0) continue;
            
            if(!isNumberUsed[num]){
                isNumberUsed[num] = true;
                mapping[ch - 'a'] = num;
                count += cryptoArith(str,idx + 1);
                mapping[ch - 'a'] = 0;
                isNumberUsed[num] = false;
            }
        }
        return count;
    }

    public static void cryptoArith() {
        String str = s1 + s2 + s3;
        // String uniqueStr = uniqueChar(str);
        
        //checking unique char using bits
        int freq = 0;

        for(int i = 0; i < str.length(); i++){
            freq |= (1 << (str.charAt(i) - 'a'));
        }

        str = "";
        for(int i = 0; i < 26; i++){
            int mask = (1 << i);
            if((freq & mask) != 0){
                str += (char)(i + 'a');
            }
        }

        //Collections.fill(mapping,-1);
        System.out.println(cryptoArith(str,0));
        //System.out.println(str);
        //System.out.println(uniqueStr);
    }

    public static void main(String args[]){
        Scanner scn = new Scanner(System.in);
        //System.out.println(cryptoArith());    
        cryptoArith();
        scn.close();
    }
}