#include <stdio.h>
#include <stdlib.h>

int main(){
    int n;
    printf("Enter the size of the array");
    scanf("%d", &n);

    int *arr = malloc(n* sizeof(int));
    for(int i = 0; i < n; i++){
        arr[i] = i + 1;
        printf("%d", arr[i]);
    }
    free(arr);
    return 0;
}