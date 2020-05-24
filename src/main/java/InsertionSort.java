import org.json.JSONObject;

public class InsertionSort {

    public static void main(String[] args) {

//        Integer[] src = new Integer[]{1,2, 3, 4, 6};
//        insertionSort(src, 5);

        binaryInsertionSort(new Integer[]{2, 4, 6, 1, 22, 99, 55}, 0, 6, true);
    }

    static void compareInsortV2WithBinartyInsertionSort() {
        System.out.println("Start timestamp:" + System.currentTimeMillis());
        for (int i = 0; i< 100000; i++) {
            insertionSortV2(new Integer[]{4, 2, 6, 1, 22, 99, 55}, 0, 6, false);
        }
        System.out.println("End timestamp:" + System.currentTimeMillis());

        System.out.println("Start timestamp:" + System.currentTimeMillis());
        for (int i = 0; i< 100000; i++) {
            binaryInsertionSort(new Integer[]{2, 4, 6, 1, 22, 99, 55}, 0, 6, false);
        }
        System.out.println("End timestamp:" + System.currentTimeMillis());

    }

    static Integer[] insertionSort(Integer[] src, Integer num) {
        Integer[] target = new Integer[src.length + 1];
        System.arraycopy(src, 0, target, 0, src.length);

        for (int len = target.length - 1, j = len -1; j>=0; j--) {
            if (num <target[j]) {
                target[j+1] = target[j];
                continue;
            }

            target[j+1] = num;
            break;
        }
        System.out.println("result list:" + JSONObject.valueToString(target));
        return target;
    }

    // 时间复杂度： 1 + 2 + 3 + ... + (high - low) = (high - low) * (high - low) / 2
    private static void insertionSortV2(Integer[] src, int low, int high, boolean printResult) {
        for (int i = low; i<= high; i++) {
            int j = i;
            int value;

            // 每次都从i到low遍历， 内层相当于冒泡？ 把小的数字冒泡到前面
            while (j > low && src[j] < src[j-1]) {
                value = src[j];
                src[j] = src[j-1];
                src[j-1] = value;
                j--;
            }
        }

        if (printResult){
            System.out.println("result list:" + JSONObject.valueToString(src));
        }
    }

    // 排序一个已经有序的数组
    private static void binaryInsertionSort(Integer[] src, int low, int high, boolean printResult) {
        for (int i= low; i<=high; i++) {
            Integer value = src[i];
            int p=low,q=i - 1;
            while (p<=q) {
                int mid = (p+q) >>> 1;
                if (src[mid] > value) {
                    q = mid - 1;
                } else {
                    p = mid + 1;
                }
            }
            // 因为src[mid]大于value时才使得p=mid， 因为最终一定会找到某个src[mid]<=src[i]，
            // 因此插入的位置即为mid+1, 即为p， 即为q+1， 因为最终一定是p=q+1（因为二分法）
            for (int j = i; j>p ;j--) {
                src[j] = src[j-1];
            }

            // p为找到的位置
            src[p] = value;
        }

        if (printResult){
            System.out.println("result list:" + JSONObject.valueToString(src));
        }
    }
}
