public class WinValidator {
    private int[] case_0 = {
            1,1,1,
            0,0,0,
            0,0,0
    };
    private int[] case_1 = {
            0,0,0,
            1,1,1,
            0,0,0
    };
    private int[] case_2 = {
            0,0,0,
            0,0,0,
            1,1,1
    };
    private int[] case_3 = {
            1,0,0,
            1,0,0,
            1,0,0
    };
    private int[] case_4 = {
            0,1,0,
            0,1,0,
            0,1,0
    };
    private int[] case_5 = {
            0,0,1,
            0,0,1,
            0,0,1
    };
    private int[] case_6 = {
            1,0,0,
            0,1,0,
            0,0,1
    };
    private int[] case_7 = {
            0,0,1,
            0,1,0,
            1,0,0
    };

    private int[][] cases = new int[8][9];

    public WinValidator() {
        cases[0] = case_0;
        cases[1] = case_1;
        cases[2] = case_2;
        cases[3] = case_3;
        cases[4] = case_4;
        cases[5] = case_5;
        cases[6] = case_6;
        cases[7] = case_7;
    }

    public boolean Validate(int test_value, int[] test_case) {
        for(int i = 0; i < 8; i++){
            int result = 0;

            for(int j = 0; j < 9; j++){
                if(cases[i][j] == 1 && test_case[j] == test_value) {
                    result++;
                }
            }

            if(result == 3)
                return true;
        }
        return false;
    }
}
