public class HanNuo {

    public static void main(String[] arg) {

        hanNuo(3, 'A', 'B', 'C');
    }

    private static void hanNuo(int num, char A, char B, char C) {

        if (num == 1) {
            System.out.println(num + ":" + A + "->" + C);
            return;
        }

        hanNuo(num - 1, A, C, B);
        System.out.println(num + ":" + A + "->" + C);
        hanNuo(num - 1, B, A, C);
    }
}
