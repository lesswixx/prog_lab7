package util;


import util.Interfaces.SessionWorkerInterface;

public class SessionWorker implements SessionWorkerInterface {

    private final Console console;

    public SessionWorker(Console aConsole) {
        console = aConsole;
    }

    @Override
    public Session createSession(String username,String password) {
        return new Session(username, password);
    }
}
