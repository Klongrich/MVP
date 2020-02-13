#include <stdio.h>

int     get_sum(int *array) {

    int total;
    int i;

    i = 0;
    total = 0;
    while (i < 5) {
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
    while (i < 5) {
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
    while (i < 5) {
        total += (x[i] + y[i]);
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

    printf("sum of x: %f\nsum of y: %f\n", sum_of_x_squared, sum_of_y_squared);

    a = (sum_of_y * sum_of_x_squared) - (sum_of_x * (sum_of_xy));
    a /= (5 * sum_of_x_squared) - (sum_of_x * sum_of_x);


    printf ("a: %f\n", a);
    //printf("y: %f\nx: %f\n", sum_of_y, sum_of_x);
    //y = a + bx
}

int main() {

    int date[5] = {1, 2, 3, 4, 5};
    int open[5] = {10, 11, 12, 15, 13};

    ft_linear_regression(date, open);

    return (0);
}