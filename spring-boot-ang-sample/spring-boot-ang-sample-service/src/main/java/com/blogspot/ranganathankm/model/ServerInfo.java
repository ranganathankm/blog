package com.blogspot.ranganathankm.model;

/**
 *
 * @author ranga
 */
public class ServerInfo
{
    private String type;
    private String serverTime;    

    public ServerInfo(String env, String serverTime)
    {
        this.type = env;
        this.serverTime = serverTime;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }
    
    /**
     * @return the serverTime
     */
    public String getServerTime()
    {
        return serverTime;
    }

    /**
     * @param serverTime the serverTime to set
     */
    public void setServerTime(String serverTime)
    {
        this.serverTime = serverTime;
    }

}
