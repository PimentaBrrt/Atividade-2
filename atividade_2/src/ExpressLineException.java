public class ExpressLineException extends RuntimeException {
    
    public ExpressLineException(String desc) {
        super(desc);
    }

    public ExpressLineException(String desc, Throwable t) {
        super(desc, t);
    }

}
