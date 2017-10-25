public class BadTransactionException extends Exception{
	
	public BadTransactionException(int amount){
		super("Invalid amount " + amount);
	}
}