import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        List aList = new ArrayList();
    }

    private static <T> T[] ensureCapacity(int minCapacity) {
        T[] tmp = null;
        int tmpLen = 2;
        int tmpBase = 0;
        if (tmpLen < minCapacity) {
            // Compute smallest power of 2 > minCapacity
            int newSize = minCapacity;
            newSize |= newSize >> 1;
            newSize |= newSize >> 2;
            newSize |= newSize >> 4;
            newSize |= newSize >> 8;
            newSize |= newSize >> 16;
            newSize++;

            if (newSize < 0) // Not bloody likely!
                newSize = minCapacity;
            else ;
//                newSize = Math.min(newSize, a.length >>> 1);
            System.out.println("newSize:" + newSize);
//            @SuppressWarnings({"unchecked", "UnnecessaryLocalVariable"})
//            T[] newArray = (T[])java.lang.reflect.Array.newInstance
//                    (a.getClass().getComponentType(), newSize);
//            tmp = newArray;
//            tmpLen = newSize;
//            System.out.println("tmpLen:" + tmpLen);
//            tmpBase = 0;
        }
        return tmp;
    }

    private static int minRunLength(int n) {
        assert n >= 0;
        int r = 0;      // Becomes 1 if any 1 bits are shifted off
        while (n >= 32) {
            r |= (n & 1);
            n >>= 1;
        }
        return n + r;
    }


  /**
   * 2 * arr.lenth - 3
   * @param arr
   */
  public void BubbleSort(int[] arr){
        boolean reverse = true;
        int length = arr.length;
        while (reverse) {
            reverse = false;
            for (int j = 0; j < length - 1; j++) {
                if (arr[j] > arr[j + 1]) { //相邻两个元素作比较，如果前面元素大于后面，进行交换
                    reverse = true; //线性数组中仍存在逆序
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
            length--; //已归位的部分无需再比较了
        }
    }

    // Default hint is len - 1
    private static <T> int gallopLeft(T key, T[] a, int base, int len, int hint,
                                      Comparator<? super T> c) {
        assert len > 0 && hint >= 0 && hint < len;
        int lastOfs = 0;
        int ofs = 1;
        if (c.compare(key, a[base + hint]) > 0) {
            // Gallop right until a[base+hint+lastOfs] < key <= a[base+hint+ofs]
            int maxOfs = len - hint;
            while (ofs < maxOfs && c.compare(key, a[base + hint + ofs]) > 0) {  // [base + hint + 1, len - 1]
                lastOfs = ofs;
                ofs = (ofs << 1) + 1;
                if (ofs <= 0)   // int overflow
                    ofs = maxOfs;
            }
            if (ofs > maxOfs)
                ofs = maxOfs;

            // Make offsets relative to base
            lastOfs += hint;
            ofs += hint;
        } else { // key <= a[base + hint]
            // Gallop left until a[base+hint-ofs] < key <= a[base+hint-lastOfs]
            final int maxOfs = hint + 1;
            while (ofs < maxOfs && c.compare(key, a[base + hint - ofs]) <= 0) {  // [-1, base + hint]
                lastOfs = ofs;
                ofs = (ofs << 1) + 1;
                if (ofs <= 0)   // int overflow
                    ofs = maxOfs;
            }
            if (ofs > maxOfs)
                ofs = maxOfs;

            // Make offsets relative to base
            int tmp = lastOfs;
            lastOfs = hint - ofs;
            ofs = hint - tmp;
        }
        assert -1 <= lastOfs && lastOfs < ofs && ofs <= len;

        /*
         * Now a[base+lastOfs] < key <= a[base+ofs], so key belongs somewhere
         * to the right of lastOfs but no farther right than ofs.  Do a binary
         * search, with invariant a[base + lastOfs - 1] < key <= a[base + ofs].
         */
        lastOfs++;
        while (lastOfs < ofs) {
            int m = lastOfs + ((ofs - lastOfs) >>> 1);

            if (c.compare(key, a[base + m]) > 0)
                lastOfs = m + 1;  // a[base + m] < key
            else
                ofs = m;          // key <= a[base + m]
        }
        assert lastOfs == ofs;    // so a[base + ofs - 1] < key <= a[base + ofs]
        return ofs;
    }
}
