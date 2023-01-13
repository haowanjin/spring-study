package com.tman.javastudy.arithmetic;

/**
 * @author zhangdong<br>
 * @desc <br>
 * @date 2019/09/28 11:27 <br>
 */
public class JedisTest {

    public static void main(String[] args) {
        final String[] split = "eoms-workflow-bj,eoms-workflow-pub,eoms-workflow-hq,eoms-workflow-tw,eoms-workflow-sd,eoms-workflow-ha,eoms-workflow-zj,eoms-workflow-js,eoms-workflow-fj,eoms-workflow-cq,eoms-workflow-sn,eoms-workflow-ah,eoms-workflow-gd,eoms-workflow-gs,eoms-workflow-gx,eoms-workflow-gz,eoms-workflow-hb,eoms-workflow-he,eoms-workflow-hi,eoms-workflow-hl,eoms-workflow-hn,eoms-workflow-jl,eoms-workflow-jx,eoms-workflow-ln,eoms-workflow-nm,eoms-workflow-nx,eoms-workflow-qh,eoms-workflow-sc,eoms-workflow-sh,eoms-workflow-sx,eoms-workflow-tj,eoms-workflow-xj,eoms-workflow-xz,eoms-workflow-yn".split(",");
        System.out.println(split.length);
    }


    public static int[] result(int arr[]){
        return result(arr,0,arr.length);
    }



    public static int[] result(int arr[],int left,int right){
        if(right-left ==1){
            return new int[]{arr[left]*arr[left],left,left};
        }
        int[] ints = totalAndMinIndex(arr,left,right);
        int result[] = new int[]{ints[0]*ints[1],left,right};
        int minIndex = ints[2];
        if(minIndex==left){
            int rightResult[] = result(arr, minIndex + 1, right);
            if(result[0]>rightResult[0]){
                return result;
            }
            return rightResult;
        }else if(minIndex==right-1){
            int leftResult[] = result(arr, left, minIndex);
            if(result[0]>leftResult[0]){
                return result;
            }
            return leftResult;
        }
        int rightResult[] = result(arr, minIndex + 1, right);
        int leftResult[] = result(arr, left, minIndex);
        int max = Math.max(Math.max(result[0], leftResult[0]), rightResult[0]);
        if(rightResult[0]==max){
            return rightResult;
        }else if (leftResult[0]>max){
            return leftResult;
        }
        return result;
    }


    public static int[] totalAndMinIndex(int arr[],int left,int right) {
        int total = 0;
        int min = arr[left];
        int minIndex = left;
        for (int i = left; i < right; i++) {
            int i1 = arr[i];
            total += i1;
            if (i1 < min) {
                min = i1;
                minIndex = i;
            }
        }
        return new int[]{total, min,minIndex};
    }
}
