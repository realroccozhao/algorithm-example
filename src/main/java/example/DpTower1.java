package example;

public class DpTower1 {

  public static void main(String[] args) {
    int[][] array = {
        {3},
        {1, 5},
        {8, 4, 3},
        {2, 6, 7, 9},
        {6, 2, 3, 5, 1}
    };

    int result1 = normalTopToDown(array);
    int result2 = topToDownDynamicArray(array);
    System.out.println("result1:" + result1);
    System.out.println("result2:" + result2);
  }

  static int normalTopToDown(int n[][]) {
    int max = 0;
    int dp[][] = new int[n.length][n.length];
    dp[0][0] = n[0][0];
    for (int i = 1; i < n.length; i++) {
      for (int j = 0; j <= i; j++) {
        if (j == 0) {
          //如果是第一列，直接跟他上面数字相加
          dp[i][j] = dp[i - 1][j] + n[i][j];
        } else {
          //如果不是第一列，比较他上面跟上面左面数字谁大，谁大就跟谁相加，放到这个位置
          dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + n[i][j];
        }
        max = Math.max(dp[i][j], max);
      }
    }
    return max;
  }

  static int topToDownDynamicArray(int n[][]) {
    int[] temp = new int[n.length];
    temp[0] = n[0][0];
    for(int i=1;i<n.length;i++){
      for(int j=i;j>=0;j--){
        if(j==i){
          temp[i]=temp[i-1]+n[i][j];
        }else if(j==0){
          temp[0]+=n[i][0];
        }else{
          temp[j]=Math.max(temp[j], temp[j-1])+n[i][j];
        }
      }
    }
    int max = temp[0];
    //从temp数组里取出最大的值
    for(int i=1;i<temp.length;i++){
      if(temp[i]>max){
        max = temp[i];
      }
    }
    return max;
  }
}
