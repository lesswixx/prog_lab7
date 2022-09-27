package Commands.interfacess;



import util.Respond;



public interface CommandInterface {
    Respond execute(Object argument);
    int getCountOfArguments();
    
}
