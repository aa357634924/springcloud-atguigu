package com.rj.test.juc.com.rj.test.lock;

import lombok.Getter;

/**
 * @Author: rj
 * @Date: 2020-03-15 14:44
 * @Version: 1.0
 */
public enum CountryEnum {
    ONE(1, "齐国"), TWO(2, "楚国"), THREE(3, "燕国"), FOUR(4, "赵国"), FIVE(5, "魏国"), SIX(6, "吴国");

    @Getter
    private Integer code;
    @Getter
    private String name;

    CountryEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static CountryEnum getCountry(int code){
        CountryEnum[] countryEnum =  CountryEnum.values();
        for (CountryEnum temp:countryEnum) {
            if(code == temp.getCode()){
                return temp;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        for (int i = 1; i < 6; i++) {
            String name = CountryEnum.getCountry(i).getName();
            System.out.println(name);
        }
    }
}
