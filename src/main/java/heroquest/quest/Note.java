package heroquest.quest;

public class Note
{
    private String _description;

    // TODO Need a way to tie in rewards/monsters/special traps/etc

    public Note()
    {
        _description = "";
    }

    public String getDescription(){ return _description; }
    public void setDescription( String d ){ _description = d; }
}
