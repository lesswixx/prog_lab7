package util.Interfaces;


import util.Session;

public interface SessionWorkerInterface {

    public Session createSession(String username, String password);
}
