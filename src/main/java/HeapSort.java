import java.util.Arrays;

/**
 * https://www.runoob.com/w3cnote/heap-sort.html
 * 堆排序 建堆时时间复杂度为O(n), 排序时平均时间复杂度O(nlogn), 最好时间复杂度O(nlogn)， 最坏时间复杂度O(nlogn)， 空间复杂度O(1)
 */
public class HeapSort {

  public int[] sort(int[] sourceArray) {
    int len = sourceArray.length;
    int[] arr = Arrays.copyOf(sourceArray, len);

    buildMaxHeap(arr,len);

    for (int i = len - 1; i > 0; i--) {
      swap(arr, 0, i);
      len--;
      heapify(arr, 0, len);
    }
    return arr;
  }

  private void buildMaxHeap(int[] arr, int len) {
    for(int i = (int)Math.floor(len / 2); i >= 0; i--) {
      heapify(arr, i, len);
    }
  }

  private void heapify(int[] arr, int i, int len) {
    int left = 2 * i + 1;
    int right = 2 * i + 2;
    int largest = i;

    if (left < len && arr[left] > arr[largest]) {
      largest = left;
    }

    if (right < len && arr[right] > arr[largest]) {
      largest = right;
    }

    if (largest != i) {
      swap(arr, i, largest);
      heapify(arr, largest, len);
    }
  }

  private void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }
}
