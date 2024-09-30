public Class User
{
    private int userId;
    private String userName;
    private String salt;
    private String passWord;
    private String firstName;
    private String lastName;

    public int getUserId (void)
    {
        return this.userId;
    }
    public void setUserId (int userId)
    {
        this.userId = userId;
    }

    public String getUserName (void)
    {
        return this.userName;
    }
    public void setUserName (String userName)
    {
        this.userName = userName;
    }
    
    public String getSalt (void)
    {
        return this.salt;
    }
    public void setSalt (String salt)
    {
        this.salt = salt;
    }
    
    public String getPassword (void)
    {
        return this.passWord;
    }
    public void setPassword (String passWord)
    {
        this.passWord = passWord;
    }
    
    public String getFirstName (void)
    {
        return this.firstName;
    }
    public void setFirstName (String firstName)
    {
        this.firstName = firstName;
    }
    public String getLastName (void)
    {
        return this.lastName;
    }
    public void setLastName (String lastName)
    {
        this.lastName = lastName;
    }
}