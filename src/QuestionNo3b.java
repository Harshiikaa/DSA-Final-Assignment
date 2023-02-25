/*Question 3

        b)
        you are provided certain string and pattern, return true if pattern entirely matches the string otherwise return false.
        Note: if pattern contains char @ it matches entire sequence of characters and # matches any single character within string.
        Input: String a=“tt”, pattern =”@”
        Output: true
        Input: String a=“ta”, pattern =”t”
        Output: false
        Input: String a=“ta”, pattern =”t#”
        Output: true*/

class PatterMatching {
    public boolean solve(String str, String pattern, int i, int j ){
        if(i<0&&j<0){
            return true;
        }if(i>=0&&j<0){
            return  false;
        }
        if(i<0&&j>=0){
            for(int k=0;k<=j; k++){
                if(pattern.charAt(k)!='@'){
                    return false;
                }
            }
        }

        if(str.charAt(i)==pattern.charAt(j)||pattern.charAt(j)=='#'){
            return solve(str,pattern,i-1,j-1);
        }
        else if(pattern.charAt(j)=='@'){
            return (solve(str,pattern,i-1,j)||solve(str,pattern,i,j-1));
        }

        else{
            return false;
        }
    }

    public static void main(String[] args) {

        PatterMatching pm = new PatterMatching();
        String str ="abcde";
        String pattern="a@cde";
        System.out.println(pm.solve(str,pattern,4,4));
    }
}


