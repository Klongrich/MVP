#include <stdio.h>

int     get_array_length(int *array) {

    int i;

    i = 0;
    while (array[i] != 0)
        i++; 
    return (i);
}

int     get_sum(int *array) {

    int total;
    int i;

    i = 0;
    total = 0;
    while (i < get_array_length(array)) {
        total += array[i];
        i++;
    }
    return (total);
}

float   get_sum_squared(int *array) {

    float total;
    int i;

    i = 0;
    total = 0;
    while (i < get_array_length(array)) {
        total += (array[i] * array[i]);
        i++;
    }

    return (total);
}

float   get_sum_of_xy(int *x, int *y) {

    float total;
    int i;

    i = 0;
    total = 0;
    while (i < get_array_length(x)) {
        total += (x[i] * y[i]);
        i++;
    }

    return (total);
}

void ft_linear_regression(int *x, int *y) {

    float sum_of_x;
    float sum_of_y;

    float sum_of_x_squared;
    float sum_of_y_squared;

    float sum_of_xy;

    float a;
    float b;

    sum_of_x = get_sum(x);
    sum_of_y = get_sum(y);

    sum_of_x_squared = get_sum_squared(x);
    sum_of_y_squared = get_sum_squared(y);

    sum_of_xy = get_sum_of_xy(x, y);

    printf("sum of y: %f\nsum of x: %f\n\n", sum_of_y, sum_of_x);
    printf("sum of x squared: %f\nsum of y squared: %f\n\n", sum_of_x_squared, sum_of_y_squared);
    printf("sum of (x * y): %f\n\n", sum_of_xy);

    a = (sum_of_y * sum_of_x_squared) - (sum_of_x * (sum_of_xy));
    a /= (5 * sum_of_x_squared) - (sum_of_x * sum_of_x);

    b = ((5 * sum_of_xy) - (sum_of_x * sum_of_y));
    b /= ((5 * sum_of_x_squared) - (sum_of_x * sum_of_x));

    printf ("a: %f\n", a);
    printf ("b: %f\n", b);
    //y = a + bx
}

int main() {

    //Make sure what ever array it is it ends in a "0" otherwise errors can occur
    int date[6] = {1, 2, 3, 4, 5, 0};
    int open[6] = {10, 11, 12, 15, 13, 0};

    ft_linear_regression(date, open);

    return (0);
}