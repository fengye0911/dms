package com.bzdgs.dms.endpoint;

import com.bzdgs.dms.resault.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @ClassName: RefreshTokenEndPoint.java
 * @Description： TODO
 * @Author: yun
 * @Date: 2020/6/21 0:26
 * @Vsersion: 1.0.0
 **/
@RestController
public class RefreshTokenEndpoint {

    @PostMapping("/token")
    public ResponseEntity<Map<String,String>> refreshToken(HttpServletRequest req, HttpServletResponse resp){

        return null;
    }

}
