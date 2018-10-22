package service;

import org.junit.Test;

import static org.junit.Assert.*;

public class Md5PasswordServiceTest {
    @Test
    public void testtomd5(){
        /*
        for(Integer i=1;i<=25;i++) {
            System.out.println(Md5PasswordService.toMd5("000",i));
        }
        */
        System.out.println(Md5PasswordService.toMd5("000",1));
    }

}