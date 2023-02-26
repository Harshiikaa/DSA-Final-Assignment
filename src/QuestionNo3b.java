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

class Pattern {
    public static boolean matches(String input, String pattern) {
        // Initialize two integer variables "inputIndex" and "patternIndex" to 0
        int inputIndex = 0;
        int patternIndex = 0;
        // Get the length of the input and the pattern strings
        int inputLength = input.length();
        int patternLength = pattern.length();
        // Start a while loop that continues as long as both "inputIndex" and "patternIndex" are less than their respective lengths
        while (inputIndex < inputLength && patternIndex < patternLength) {
            // Get the current character of the pattern at the "patternIndex" position
            char currentChar = pattern.charAt(patternIndex);
            // If the current character is '@'
            if (currentChar == '@') {
                // Increment the "patternIndex" variable by 1
                patternIndex++;
                // If "patternIndex" has reached the end of the pattern, return true
                if (patternIndex == patternLength) {
                    return true; // The '@' character is at the end of the pattern, so it matches the rest of the input
                }
                // Get the next character of the pattern at the new "patternIndex" position
                char nextChar = pattern.charAt(patternIndex);
                // Start a while loop that continues as long as "inputIndex" is less than "inputLength" and the current character of the input is not equal to the next character of the pattern
                while (inputIndex < inputLength && input.charAt(inputIndex) != nextChar) {
                    // Increment the "inputIndex" variable by 1
                    inputIndex++;
                }
                // If "inputIndex" has reached the end of the input, return false
                if (inputIndex == inputLength) {
                    return false; // Couldn't find the next character after '@' in the input
                }
                // If the current character is '#', increment both "inputIndex" and "patternIndex" by 1
            } else if (currentChar == '#') {
                inputIndex++;
                patternIndex++;
                // If the current character of the input matches the current character of the pattern, increment both "inputIndex" and "patternIndex" by 1
            } else if (input.charAt(inputIndex) == currentChar) {
                inputIndex++;
                patternIndex++;
                // If none of the above conditions are true, return false
            } else {
                return false; // The current character in the pattern does not match the corresponding character in the input
            }
        }
        // If "inputIndex" has reached the end of the input and "patternIndex" has reached the end of the pattern, return true, otherwise return false
        return (inputIndex == inputLength && patternIndex == patternLength);
    }

    // This is the main method that runs when the program is executed
    public static void main(String[] args) {
        // Create a string variable "input" and assign it the value "tt"
        String input = "tt";
        // Create a string variable "pattern" and assign it the value "@"
        String pattern = "@";
        // Call the "matches" method with "input" and "pattern" as arguments and store the result in a boolean variable "isMatch"
        boolean isMatch = matches(input, pattern);
        // Print the value of "isMatch"
        System.out.println(isMatch);

        String input2 = "ta";
        String pattern2 = "t";
        boolean isMatch2 = matches(input2, pattern2);
        System.out.println(isMatch2);

        // Create another string variable "input2" and assign it the value "ta"
        String input3 = "ta";
        // Create another string variable "pattern2" and assign it the value "t"
        String pattern3 = "t#";
        // Call the "matches" method with "input2" and "pattern2" as arguments and store the result in a boolean
        boolean isMatch3 = matches(input3, pattern3);
        System.out.println(isMatch3);
    }
}
