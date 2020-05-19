package lesson2;

public class Arrays {
    public static String[][] split(String s) throws IncorrectArraySizeException {
        String[][] result = new String[4][4];
        String buf = "";
        int x = 0;
        int y = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case ' ':
                    result[y][x] = buf;
                    x++;
                    buf = "";
                    break;
                case '\n':
                    result[y][x] = buf;
                    x = 0;
                    y++;
                    buf = "";
                    break;
                default:
                    buf += c;
            }
            if (x > 3 || y > 3)
                throw new IncorrectArraySizeException();
        }
        if (buf.length() > 0)
            result[y][x] = buf;
        return result;
    }

    public static int toNumber(String s) throws NotANumberException {
        if (s.length() == 0)
            throw new NotANumberException(s);
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new NotANumberException(s);
        }
    }

    public static double sum(String[][] arr) throws NotANumberException {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]);
                System.out.print(" ");
                sum += toNumber(arr[i][j]);
            }
            System.out.println();
        }
        return sum / 2d;
    }

    public static void main(String[] args) {
        String s = "10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 0";
        try {
            System.out.println(sum(split(s)));
        } catch (NotANumberException e) {
            e.printStackTrace();
        } catch (IncorrectArraySizeException e) {
            e.printStackTrace();
        }
    }
}
