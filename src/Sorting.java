
public class Sorting {
	public static void bubbleSort(int[] array) {  
	    boolean sorted = false;
	    int temp;
	    while(!sorted) {
	        sorted = true;
	        for (int i = 0; i < array.length - 1; i++) {
	            if (array[i] > array[i+1]) {
	                temp = array[i];
	                array[i] = array[i+1];
	                array[i+1] = temp;
	                sorted = false;
	            }
	        }
	    }
	}
	public static void insertionSort(int[] array) {  
	    for (int i = 1; i < array.length; i++) {
	        int current = array[i];
	        int j = i - 1;
	        while(j >= 0 && current < array[j]) {
	            array[j+1] = array[j];
	            j--;
	        }
	         // � ���� ����� �� �����, ��� ��� j ��� �� -1 
	         // ��� � ������ ��������, ��� ������� >= a[j]
	        array[j+1] = current;
	    }
	}
	public static void selectionSort(int[] array) {  
	    for (int i = 0; i < array.length; i++) {
	        int min = array[i];
	        int minId = i;
	        for (int j = i+1; j < array.length; j++) {
	            if (array[j] < min) {
	                min = array[j];
	                minId = j;
	            }
	        }
	        // ������
	        int temp = array[i];
	        array[i] = min;
	        array[minId] = temp;
	    }
	}
	public static void mergeSort(int[] array, int left, int right) {  
	    if (right <= left) return;
	    int mid = (left+right)/2;
	    mergeSort(array, left, mid);
	    mergeSort(array, mid+1, right);
	    merge(array, left, mid, right);
	}
	 void merge(int[] array, int left, int mid, int right) {
		    // ��������� �����
		    int lengthLeft = mid - left + 1;
		    int lengthRight = right - mid;

		    // ������� ��������� ����������
		    int leftArray[] = new int [lengthLeft];
		    int rightArray[] = new int [lengthRight];

		    // �������� ��������������� ������� �� ���������
		    for (int i = 0; i < lengthLeft; i++)
		        leftArray[i] = array[left+i];
		    for (int i = 0; i < lengthRight; i++)
		        rightArray[i] = array[mid+i+1];

		    // ��������� �������� ������� ������ ���������� ����������
		    int leftIndex = 0;
		    int rightIndex = 0;

		    // �������� �� leftArray � rightArray ������� � ������  
		    for (int i = left; i < right + 1; i++) {
		        // ���� �������� ��������������� �������� � R � L, �������� �����������
		        if (leftIndex < lengthLeft && rightIndex < lengthRight) {
		            if (leftArray[leftIndex] < rightArray[rightIndex]) {
		                array[i] = leftArray[leftIndex];
		                leftIndex++;
		            }
		            else {
		                array[i] = rightArray[rightIndex];
		                rightIndex++;
		            }
		        }
		        // ���� ��� �������� ���� ����������� �� rightArray, ����������� ��������� �� leftArray
		        else if (leftIndex < lengthLeft) {
		            array[i] = leftArray[leftIndex];
		            leftIndex++;
		        }
		        // ���� ��� �������� ���� ����������� �� leftArray, ����������� ��������� �� rightArray
		        else if (rightIndex < lengthRight) {
		            array[i] = rightArray[rightIndex];
		            rightIndex++;
		        }
		    }
		}
}
