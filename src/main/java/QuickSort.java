import org.json.JSONObject;

public class QuickSort {

    public static void main(String[] args) {
        swapWithV1();
        swapWithV2();
        swapWithV3();
    }

    static void swapWithV1() {
        Integer[] src = new Integer[]{99, 3, 4, 2, 2, 1, 101};
        quickSortV1(src, 0, src.length - 1);
        System.out.println("Sorted array with v1:" + JSONObject.valueToString(src));
    }

    static void swapWithV2() {
        Integer[] src = new Integer[]{99, 3, 4, 2, 2, 1, 101};
        quickSortV2(src, 0, src.length - 1);
        System.out.println("Sorted array with v2:" + JSONObject.valueToString(src));
    }


    static void swapWithV3() {
        Integer[] src = new Integer[]{99, 3, 4, 2, 2, 1, 101};
        quickSortV3(src, 0, src.length - 1);
        System.out.println("Sorted array with v3:" + JSONObject.valueToString(src));
    }

    static void swap(Integer[] src, int pos1, int pos2) {
        Integer tmp = src[pos1];
        src[pos1] = src[pos2];
        src[pos2] = tmp;
    }

    // 选择最后一个元素作为枢轴
    //i, j两个指针， i之前， 包含i都是小于枢轴的值， j向后遍历直到high - 1，交换i+1和high， i+1即为正确位置
    // i从low-1开始，因为j要从low开始
    static Integer partition(Integer[] src, int low, int high) {

        int i = low - 1;
        Integer pivot = src[high];
        int j = low;
        for (; j < high; j++) {
            if (src[j] < pivot) {
                i++;
                swap(src, i, j);
            }
        }

        swap(src, i+1, high);
        return i+1;
    }

    // 选择第一个元素作为枢轴, 从low+1开始比较值和pivot， 小于pivot则交换i与j， 最后交换i与low， 这样交换后low左侧均为小于pivot的值
    static Integer partitionV2(Integer src[], int low, int high) {
        int pivot = src[low];
        int i = low, j = low + 1;
        for (; j <= high; j++) {
            if (src[j] < pivot) {
                i++;
                swap(src, i, j);
            }
        }

        swap(src, i, low);
        return i;
    }

    static void quickSortV3(Integer src[], int low, int high) {
        int mid = (low + high) >>> 1;
        int pivot = src[mid];

        int i = low;
        int j = high;

        // -号不可避免， 所以只能让i>mid, j<=mid或i>=mid， <mid， 或i>=mid, j<=mid
        while (i <= j) {
            while (src[i] < pivot) i++;
            while (src[j] > pivot) j--;
            if (i <= j) {
                swap(src, i, j);
                i++;
                j--;
            }
        }

        // 此处因为可能出现最后二分到负数的情况， 比如i=0, j=0; j--后为-1， 此时mid = -1， 越界
        if (j > low) {
            quickSortV3(src, low, mid - 1);
        }

        // 同上可能出现i> high， 从而越界
        if (i < high) {
            quickSortV3(src, mid + 1, high);
        }
    }


    static void quickSortV2(Integer[] src, int low, int high) {

        if (low < high) {
            int position = partitionV2(src, low, high);
            quickSortV2(src, low, position - 1);
            quickSortV2(src, position + 1, high);
        }
    }

    static void quickSortV1(Integer[] src, int low, int high) {

        if (low < high) {
            int position = partition(src, low, high);
            quickSortV1(src, low, position - 1);
            quickSortV1(src, position + 1, high);
        }
    }
}
