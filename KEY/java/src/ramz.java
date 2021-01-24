import java.math.BigInteger;
import java.util.Scanner;

public class ramz {
    static BigInteger tavan(BigInteger paye, BigInteger e) {
        BigInteger t = BigInteger.ONE;
        while (e.signum() > 0) {
            if (e.testBit(0)) t = t.multiply(paye);
            paye = paye.multiply(paye);
            e = e.shiftRight(1);
        }
        return t;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BigInteger peymane, movaled, saber, jaber, saberskey, jaberskey, bagherskey;
        peymane = sc.nextBigInteger();
        movaled = sc.nextBigInteger();
        saber = sc.nextBigInteger();
        jaber = sc.nextBigInteger();
        BigInteger m = tavan(movaled, saber);
        saberskey = m.mod(peymane);
        BigInteger n = tavan(movaled, jaber);
        jaberskey = n.mod(peymane);
        BigInteger k = tavan(jaberskey, saber);
        bagherskey = k.mod(peymane);
        System.out.print(saberskey);
        System.out.print(" ");
        System.out.print(jaberskey);
        System.out.print(" ");
        System.out.print(bagherskey);
    }
}