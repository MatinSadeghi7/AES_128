import java.util.*;

public class Ramz1 {
   static char[][] SBOX = {
            {0x63, 0x7c, 0x77, 0x7b, 0xf2, 0x6b, 0x6f, 0xc5, 0x30, 0x01, 0x67, 0x2b, 0xfe, 0xd7, 0xab, 0x76},
            {0xca, 0x82, 0xc9, 0x7d, 0xfa, 0x59, 0x47, 0xf0, 0xad, 0xd4, 0xa2, 0xaf, 0x9c, 0xa4, 0x72, 0xc0},
            {0xb7, 0xfd, 0x93, 0x26, 0x36, 0x3f, 0xf7, 0xcc, 0x34, 0xa5, 0xe5, 0xf1, 0x71, 0xd8, 0x31, 0x15},
            {0x04, 0xc7, 0x23, 0xc3, 0x18, 0x96, 0x05, 0x9a, 0x07, 0x12, 0x80, 0xe2, 0xeb, 0x27, 0xb2, 0x75},
            {0x09, 0x83, 0x2c, 0x1a, 0x1b, 0x6e, 0x5a, 0xa0, 0x52, 0x3b, 0xd6, 0xb3, 0x29, 0xe3, 0x2f, 0x84},
            {0x53, 0xd1, 0x00, 0xed, 0x20, 0xfc, 0xb1, 0x5b, 0x6a, 0xcb, 0xbe, 0x39, 0x4a, 0x4c, 0x58, 0xcf},
            {0xd0, 0xef, 0xaa, 0xfb, 0x43, 0x4d, 0x33, 0x85, 0x45, 0xf9, 0x02, 0x7f, 0x50, 0x3c, 0x9f, 0xa8},
            {0x51, 0xa3, 0x40, 0x8f, 0x92, 0x9d, 0x38, 0xf5, 0xbc, 0xb6, 0xda, 0x21, 0x10, 0xff, 0xf3, 0xd2},
            {0xcd, 0x0c, 0x13, 0xec, 0x5f, 0x97, 0x44, 0x17, 0xc4, 0xa7, 0x7e, 0x3d, 0x64, 0x5d, 0x19, 0x73},
            {0x60, 0x81, 0x4f, 0xdc, 0x22, 0x2a, 0x90, 0x88, 0x46, 0xee, 0xb8, 0x14, 0xde, 0x5e, 0x0b, 0xdb},
            {0xe0, 0x32, 0x3a, 0x0a, 0x49, 0x06, 0x24, 0x5c, 0xc2, 0xd3, 0xac, 0x62, 0x91, 0x95, 0xe4, 0x79},
            {0xe7, 0xc8, 0x37, 0x6d, 0x8d, 0xd5, 0x4e, 0xa9, 0x6c, 0x56, 0xf4, 0xea, 0x65, 0x7a, 0xae, 0x08},
            {0xba, 0x78, 0x25, 0x2e, 0x1c, 0xa6, 0xb4, 0xc6, 0xe8, 0xdd, 0x74, 0x1f, 0x4b, 0xbd, 0x8b, 0x8a},
            {0x70, 0x3e, 0xb5, 0x66, 0x48, 0x03, 0xf6, 0x0e, 0x61, 0x35, 0x57, 0xb9, 0x86, 0xc1, 0x1d, 0x9e},
            {0xe1, 0xf8, 0x98, 0x11, 0x69, 0xd9, 0x8e, 0x94, 0x9b, 0x1e, 0x87, 0xe9, 0xce, 0x55, 0x28, 0xdf},
            {0x8c, 0xa1, 0x89, 0x0d, 0xbf, 0xe6, 0x42, 0x68, 0x41, 0x99, 0x2d, 0x0f, 0xb0, 0x54, 0xbb, 0x16}};

    private static String[][] fix = {{"02", "03", "01", "01"},
            {"01", "02", "03", "01"},
            {"01", "01", "02", "03"},
            {"03", "01", "01", "02"}};
    static String[] Rconst = {"01000000", "02000000", "04000000", "08000000", "10000000", "20000000", "40000000", "80000000", "1b000000", "36000000"};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String keyin = sc.next();
        String plaintextin = sc.next();
        int size = 16;
        String[] keyarr = new String[size];
        String[] plainarr = new String[size];
        String[] subkeys = new String[11];
        String[] temp;
        for (int i = 0; i < 32; i = i + 2) {
            keyarr[i / 2] = keyin.substring(i, i + 1).concat(keyin.substring(i + 1, i + 2));
        }
        for (int i = 0; i < 32; i = i + 2) {
            plainarr[i / 2] = plaintextin.substring(i, i + 1).concat(plaintextin.substring(i + 1, i + 2));
        }
        String[][] plaintext = new String[4][4];
        int z = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                plaintext[i][j] = plainarr[z];
                z++;
            }
        }
        temp = keyExpansion(keyarr);
        int q = 0;
        for (int i = 0; i < 44; i = i + 4) {
            for (int j = 0; j < 7; j = j + 2) {

                subkeys[i / 4] += (temp[4 * q].substring(j, j + 2).concat(temp[4 * q + 1].substring(j, j + 2)).concat(temp[4 * q + 2].substring(j, j + 2)).concat(temp[4 * q + 3].substring(j, j + 2)));
            }
            q++;
        }
        plaintext = addRoundKeys(subkeys[0].substring(4, 36), plaintext);
        int round = 10;
        for (int i = 0; i < round - 1; i++) {
            subbyte(plaintext, 4);
            shiftrow(plaintext);
            mixcolumn(plaintext);
            plaintext = addRoundKeys(subkeys[i + 1].substring(4, 36), plaintext);
        }
        subbyte(plaintext, 4);
        shiftrow(plaintext);
        plaintext = addRoundKeys(subkeys[10].substring(4, 36), plaintext);
        for (int i = 0; i < 4; i++) {
            for (int k = 0; k < 4; k++) {
                System.out.print(plaintext[k][i]);
            }


        }
    }

    static String[][] subbyte(String[][] plaintext, int a) {

        //subbyte
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < a; j++) {
                int first = Integer.parseInt(plaintext[i][j].substring(0, 1), 16);
                int second = Integer.parseInt(plaintext[i][j].substring(1, 2), 16);
                plaintext[i][j] = Integer.toHexString(SBOX[first][second]);
                if (plaintext[i][j].length() == 1) {
                    plaintext[i][j] = "0".concat(plaintext[i][j]);
                }
            }
        }
        return (plaintext);
    }

    static void shiftrow(String[][] arr) {
        String temp;
        shift(arr); //shift koli
//row 2
        temp = arr[2][0];
        arr[2][0] = arr[2][1];
        arr[2][1] = temp;

        temp = arr[2][1];
        arr[2][1] = arr[2][2];
        arr[2][2] = temp;

        temp = arr[2][2];
        arr[2][2] = arr[2][3];
        arr[2][3] = temp;
//row 3
        temp = arr[3][0];
        arr[3][0] = arr[3][1];
        arr[3][1] = temp;

        temp = arr[3][1];
        arr[3][1] = arr[3][2];
        arr[3][2] = temp;

        temp = arr[3][2];
        arr[3][2] = arr[3][3];
        arr[3][3] = temp;

        temp = arr[3][0];
        arr[3][0] = arr[3][1];
        arr[3][1] = temp;

        temp = arr[3][1];
        arr[3][1] = arr[3][2];
        arr[3][2] = temp;

        temp = arr[3][2];
        arr[3][2] = arr[3][3];
        arr[3][3] = temp;

    }

    static void shift(String[][] arr) {
        String temp;
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                temp = arr[i][j - 1];
                arr[i][j - 1] = arr[i][j];
                arr[i][j] = temp;

            }

        }
    }

    private static String[] keyExpansion(String[] key) {

        String[] expandedKey = new String[44];
        for (int i = 0; i < 44; i++) {
            if (i < 4) {
                expandedKey[i] = key[i * 4].concat(key[i * 4 + 1]).concat(key[i * 4 + 2]).concat(key[i * 4 + 3]);
            } else {
                if (i % 4 != 0) {
                    expandedKey[i] = myxor(expandedKey[i - 1], expandedKey[i - 4]);

                    if (expandedKey[i].length() < 8) {
                        int count = 8 - expandedKey[i].length();
                        for (int j = 0; j < count; j++) {
                            expandedKey[i] = ("0").concat(expandedKey[i]);
                        }
                    }
                } else {
                    if (expandedKey[i - 1].length() < 8) {
                        int count = 8 - expandedKey[i - 1].length();
                        for (int j = 0; j < count; j++) {
                            expandedKey[i - 1] = ("0").concat(expandedKey[i - 1]);
                        }
                    }
                    if (expandedKey[i - 4].length() < 8) {
                        int count = 8 - expandedKey[i - 4].length();
                        for (int j = 0; j < count; j++) {
                            expandedKey[i - 4] = ("0").concat(expandedKey[i - 4]);
                        }
                    }
                    String t = rotate(expandedKey[i - 1]);
                    String[] tmp = new String[4];
                    String[][] temp = new String[2][2];
                    for (int z = 0; z < 8; z = z + 2) {
                        tmp[z / 2] = t.substring(z, z + 1).concat(t.substring(z + 1, z + 2));
                    }
                    int z = 0;
                    for (int y = 0; y < 2; y++) {
                        for (int j = 0; j < 2; j++) {
                            temp[y][j] = tmp[z];
                            z++;
                        }
                    }
                    String[][] out = subbyte(temp, 2);
                    String subByted = out[0][0].concat(out[0][1]).concat(out[1][0]).concat(out[1][1]);
                    expandedKey[i] = myxor(myxor(subByted, Rconst[(i / 4) - 1]), expandedKey[i - 4]);
                    if (expandedKey[i].length() < 8) {
                        int count = 8 - expandedKey[i].length();
                        for (int j = 0; j < count; j++) {
                            expandedKey[i] = ("0").concat(expandedKey[i]);
                        }
                    }
                }
            }
        }
        return expandedKey;
    }

    static String myxor(String a, String b) {
        char[] chars = new char[a.length()];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = toHex(fromHex(a.charAt(i)) ^ fromHex(b.charAt(i)));
        }
        return new String(chars);
    }

    static int fromHex(char c) {
        if (c >= 'a' && c <= 'f') {
            return c - 'a' + 10;
        }
        if (c >= 'A' && c <= 'F') {
            return c - 'A' + 10;
        }
        if (c >= '0' && c <= '9') {
            return c - '0';
        }

        throw new IllegalArgumentException();
    }

    static private char toHex(int nybble) {
        if (nybble < 0 || nybble > 15) {
            throw new IllegalArgumentException();
        }
        return "0123456789abcdef".charAt(nybble);
    }

    static String rotate(String s) {
        return s.substring(2) + s.substring(0, 2);
    }

    static String[][] addRoundKeys(String sub, String[][] plaintext) {
        String[] subk = new String[16];
        for (int i = 0; i < 32; i = i + 2) {
            subk[i / 2] = sub.substring(i, i + 1).concat(sub.substring(i + 1, i + 2));
        }
        String[][] subkey = new String[4][4];
        int z = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                subkey[i][j] = subk[z];
                z++;
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                subkey[i][j] = myxor(subkey[i][j], plaintext[i][j]);
            }

        }
        return subkey;
    }

    static void mixcolumn(String[][] plaintext) {
        int[][] out = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                out[i][j] = 0;
                for (int k = 0; k < 4; k++) {
                    out[i][j] ^= mult(Integer.parseInt(fix[i][k], 16), Integer.parseInt(plaintext[k][j], 16));
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                plaintext[i][j] = Integer.toHexString(out[i][j]);

                if (Integer.toHexString(out[i][j]).length() == 1) {
                    plaintext[i][j] = "0".concat(Integer.toHexString(out[i][j]));
                }
                if (Integer.toHexString(out[i][j]).length() == 3) {
                    plaintext[i][j] = Integer.toHexString(out[i][j]).substring(1, 3);
                }


            }

        }
    }

    static int mult(int a, int b) {
        int tmp, tmp1, c;
        switch (a){
            case 2:
            tmp = b * a;
            if (tmp > 255) {
                c = tmp ^ 27;
            } else {
                c = tmp;
            }
            break;
            case 3:
            tmp = (b * 2);
            if (tmp > 255) {
                tmp1 = (tmp % 256) ^ 27;
            } else {
                tmp1 = tmp;
            }
            c = tmp1 ^ b;
            if (c > 255) {
                c = (tmp1 % 256) ^ 27;
            }
            break;
            default:
            if (b > 256) {
                c = (b % 256) ^ 27;
            } else {
                c = b;
            }
        }


        return c;
    }
}
