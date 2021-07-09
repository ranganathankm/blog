package com.blogspot.ranganathankm.rest;

import com.blogspot.ranganathankm.model.ServerInfo;
import java.time.LocalDateTime;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ranga
 */
@RestController
@RequestMapping(path="api/time")
public class ServerInfoController
{
    @GetMapping("local")
    public ServerInfo getInfoFromSever()
    {
        return new ServerInfo("Local",
                LocalDateTime.now().toString());
    }
}
