package com.example.bigassproject;


public class Sort {
    public String sortingalgorithm = "";
    public long duration;
    public long startTime;
    public long endTime;
    public void bubblesort(int[] arr){
        startTime = System.nanoTime();
        sortingalgorithm = "Bubblesort";
        int n = arr.length;
        for(int i = 0; i < n-1; i++){
            for(int j = 0; j < n - i - 1; j++){
                if(arr[j]>arr[j+1]){
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime);
    }
    public void selectionSort(int[] arr){
        sortingalgorithm = "Selectionsort";
        startTime = System.nanoTime();
        int n = arr.length;
        for(int i = 0; i < n-1; i++){
            for(int j = i; j < n; j++){
                if(arr[j] < arr[i]){
                    swap(arr, i, j);
                }
            }
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime);
    }
    public void bogoSort(int[] arr){
        sortingalgorithm = "Bogosort";
        startTime = System.nanoTime();
        while (!isSorted(arr)){
            shuffle(arr);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime);
    }
    public void shuffle(int[] arr){
        int n = arr.length;
        for(int i = 0; i < n; i++){
            swap(arr, i, (int)(Math.random()*i));
        }
    }
    public void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public boolean isSorted(int[] arr){
        int n = arr.length;
        for(int i = 0; i < n-1; i++){
            if(arr[i] > arr[i+1]){
                return false;
            }
        }
        return true;
    }
    public void quickSort(int[] arr){
        int begin = 0;
        int end = arr.length-1;
        innerQuickSort(arr, begin, end);
    }
    public void innerQuickSort(int[] arr, int begin, int end){
        sortingalgorithm = "Quicksort";
        startTime = System.nanoTime();
        if(begin < end) {
            int partitionIndex = partition(arr, begin, end);

            innerQuickSort(arr, begin, partitionIndex - 1);
            innerQuickSort(arr, partitionIndex + 1, end);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime);
    }
    public int partition(int[] arr, int begin, int end){
        int pivot = arr[end];
        int i = begin-1;
        for(int j = begin; j < end; j++){
            if(arr[j] < pivot){
                ++i;

                int swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }
        int swapTemp = arr[i+1];
        arr[i+1] = arr[end];
        arr[end] = swapTemp;

        return i+1;
    }

    public void mergeSort(int[] arr){
        int n = arr.length;
        innerMergeSort(arr, n);
    }
    public void innerMergeSort(int[] arr, int n){
        sortingalgorithm = "Mergesort";
        startTime = System.nanoTime();
        if (n < 2){
            return;
        }
        int mid = n / 2;
        int[] left = new int[mid];
        int[] right = new int [n - mid];
        for(int i = 0; i < mid; i++){
            left[i] = arr[i];
        }
        for(int i = mid; i < n; i++){
            right[i - mid] = arr[i];
        }
        innerMergeSort(left, mid);
        innerMergeSort(right, n - mid);
        merge(arr, left, right, mid, n-mid);
        endTime = System.nanoTime();
        duration = (endTime - startTime);
    }
    public void merge(int[] arr, int[] left, int[] right, int leftLength, int rightLength){
        int i = 0, j = 0, k = 0;
        while (i<leftLength && j<rightLength){
            if(left[i] <= right[j]){
                arr[k++] = left[i++];
            }
            else{
                arr[k++] = right[j++];
            }
        }
        while (i<leftLength){
            arr[k++] = left[i++];
        }
        while(j < rightLength){
            arr[k++] = right[j++];
        }
    }
    public void insertionSort(int[] arr){
        sortingalgorithm = "Insertionsort";
        startTime = System.nanoTime();
        int n = arr.length;
        for(int i = 1; i < n; i++){
            int key = arr[i];
            int j = i-1;

            while(j >= 0 && arr[j] > key){
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = key;
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime);
    }
    public void heapSort(int[] arr){
        sortingalgorithm = "Heapsort";
        startTime = System.nanoTime();
        int arrlength = arr.length;
        for(int rootNodeIndex = arrlength / 2 - 1; rootNodeIndex >= 0; rootNodeIndex--){
            heapify(arr, arrlength, rootNodeIndex);
        }

        for (int i = arrlength - 1; i > 0; i--){
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime);
    }
    public void heapify(int[] arr, int arrlength, int rootNodeIndex){
        int largest = rootNodeIndex;
        int leftChildIndex = 2 * rootNodeIndex + 1;
        int rightChildIndex = 2 * rootNodeIndex +2;
        if (leftChildIndex < arrlength && arr[leftChildIndex] > arr[largest]){
            largest = leftChildIndex;
        }
        if(rightChildIndex < arrlength && arr[rightChildIndex] > arr[largest]){
            largest = rightChildIndex;
        }
        if(largest != rootNodeIndex){
            int temp = arr[rootNodeIndex];
            arr[rootNodeIndex] = arr[largest];
            arr[largest] = temp;

            heapify(arr, arrlength, largest);
        }
    }
    public int[] generateRandomNumberArray(int n){
        int[] arr;
        arr = new int[n];
        for(int i = 0; i < n; i++){
            int randomNumber = (int)(Math.random() * 101);
            arr[i] = randomNumber;
        }
        return arr;
    }
    public void printArray(int[] arr){
        int n = arr.length;
        for (int j : arr) {
            System.out.printf(j + " ");
        }
        System.out.println();
    }
    public static void main(String[] args){
        Sort Sort = new Sort();
        int[] randArr = Sort.generateRandomNumberArray(10);
        Sort.printArray(randArr);
        Sort.heapSort(randArr);
        System.out.println(""+Sort.sortingalgorithm);
        Sort.printArray(randArr);
        System.out.println("Nanoseconds: "+Sort.duration);
    }
}
