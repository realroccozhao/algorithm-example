import org.json.JSONObject;

import java.util.Comparator;

/**
 * 这个算法里 src和target在每次排序后， merge前会互换， 因为如果不换的话合并的还是未排序的数组， 这样合并出来的值是错的。
 * JDK1.8中 Arrays.mergeSort 分为两个数组时, mid分为作为两个数组的low和high， 而不是为mid和mid+1， 因为它在元素少于7个时采用的冒泡排序，
 *   也就没有使用low == high的情况
 *
 */
public class MergeSort {

    public static void main(String[] args) {
        Integer[] srcList = new Integer[]{ 222, 333, 9, 555, 111, 3, 5, 2, 7 };
        Integer[] targetLit = srcList.clone();
        System.out.println("Source list:" + JSONObject.valueToString(srcList));
        mergeSort(srcList, targetLit, 0, srcList.length - 1);
        System.out.println("Result list:" + JSONObject.valueToString(targetLit));
    }

    private static void mergeSort(Object[] src, Object[] target, int low, int high) {
        if (low == high) {
            target[low] = src[low];
            return;
        }

        int mid = (low + high) >>> 1;
        mergeSort(target, src, low, mid);
        mergeSort(target, src, mid + 1, high);

        mergeV2(src, target, low, high, mid, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Integer)o1).compareTo((Integer)o2);
            }
        });

//        merge(src, target, low, high, mid, new Comparator() {
//            public int compare(Object o1, Object o2) {
//                return ((Integer)o1).compareTo((Integer)o2);
//            }
//        });
    }

    private static void merge(Object[] src, Object[] dest, int low, int high, int mid, Comparator c) {
        // merge src[low, mid] src[mid+1, high]
        int i =low, j = mid + 1, k=i;
        for (; i <= mid && j<=high;) {
            if (c.compare(src[i], src[j]) < 0) {
                dest[k++] = src[i++];
            } else {
                dest[k++] = src[j++];
            }
        }

        if (i <= mid) {
            // append [i, mid] to dest
            System.arraycopy(src, i, dest, k, mid - i + 1);
        } else if (j <= high) {
            System.arraycopy(src, j, dest, k, high - j + 1);
        }
    }

    /**
     * merge方法和mergeV2的作用是等价的
     * @param src 源数组
     * @param dest 目标数组
     * @param low src中低位起始地址
     * @param high src中结束地址
     * @param mid src中中间地址
     * @param c 比较器
     */
    private static void mergeV2(Object[] src, Object[] dest, int low, int high, int mid, Comparator c) {
        for (int i=low, j=mid + 1, destIndex = low; destIndex<=high;) {
            if (j > high
                    || i <= mid && c.compare(src[i], src[j]) <= 0) {        // <=是stable的基础， 相等用原来的顺序
                dest[destIndex++] = src[i++];
            } else {
                dest[destIndex++] = src[j++];
            }
        }
    }
}
