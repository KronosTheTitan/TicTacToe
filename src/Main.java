class Main{
    public static void main(String[] args) {
        WinValidator validator = new WinValidator();

        int[] test_case = {
                1,0,0,
                0,1,0,
                1,0,1
        };

        System.out.println("Provided test case results in win: " + validator.Validate(1,test_case));
    }
}