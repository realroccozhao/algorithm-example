public class BubbleSort {

  public static void main(String[] args) {
    int[] arr = new int[]{2, 3, 2, 6, 7, 8};
    sort(arr);
    for (int i = 0; i < arr.length; i++) {
      System.out.println(arr[i]);
    }
  }


  /**
   * 按照从小到大的顺序排序
   * 最优情况是有序的， 则外层次数为 n-1， 内层次数, 1 + 2 + 3 + ... + n = n * (n -1) / 2 =  O(n^2)
   *
   * 比较相邻的两个数，如果第一个数比第二个数大，则两数交换。
   * 对之后的相邻元素进行同样的工作，从开始到最后一对，这样进行一次排序后，数据的最后一位会是最大值 ，第一次循环进行的次数为 arr.length-1。
   * 之后对所有的元素重复以上的步骤，且以后每次循环的次数为arr.length-1-i （i为循环第几次 ，i 从零开始）
   * 1 + 2 + 3 + ... + (n-1) = n*(n-1)/2
   * https://zhuanlan.zhihu.com/p/61094267
   *
   * 由于冒泡排序中只有缓存的temp变量需要内存空间, 因此空间复杂度为常量O(1)
   * @param arr
   */
  private static void sort(int[] arr) {
    if (arr == null) return;

    int temp;
    for (int len = arr.length, i = len - 1; i > 0; --i) {
      for (int j = 0; j < i ; ++j) {
        if (arr[j] > arr[j+1]) {

          /**
           * 关于空间复杂度， 是否若为调用函数， 则最优为O(0), 最差为和时间复杂度一样？- 即n * (n - 1) / 2
           */
//          swap(arr, j, j + 1);
          temp = arr[i];
          arr[i] = arr[j];
          arr[j] = temp;
        }
      }
    }
  }

  /**
   * 冒泡排序的改进版本， 最优时间复杂度为O(n)
   * @param arr
   */
  private static void sortV2(int[] arr) {
    if (arr == null) return;

    for (int len = arr.length, i = len - 1; i > 0; --i) {
      boolean didSwap = false;
      for (int j = 0; j < i ; ++j) {
        if (arr[j] > arr[j+1]) {
          swap(arr, j, j + 1);
          didSwap = true;
        }
      }

      if (!didSwap) break;
    }
  }

  private static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }
}
