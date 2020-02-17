package utils;

public class parse_data_helper {


    public parse_data_helper() {};

    public String remove_comma(String data) {
        String temp[];

        if (data.contains(",")) {
            temp = data.split(",");
            return (temp[0] + temp[1]);
        } else {
            return (data);
        }
    }

    //Could just spilt using regex to search for number
    private String remove_whitespace(String data)
    {
        int i;
        String temp;

        temp = null;
        i = 0;

        while (i < data.length()) {


        }

        return (temp);
    }

}
