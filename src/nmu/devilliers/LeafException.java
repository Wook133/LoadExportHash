package nmu.devilliers;

public class LeafException extends RuntimeException  {

    /**
     * Constructs a new Leaf exception with the specified detail message
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method
     */
    public LeafException(String message) {
        super(message);
    }

    /**
     * Constructs a new Leaf exception with the specified detail message and cause
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method
     * @param  cause the cause (which is saved for later retrieval by the
     *               {@link #getCause()} method). A {@code null} value is
     *               permitted, and indicates that the cause is nonexistent
     *               or unknown
     */
    public LeafException(String message, Throwable cause) {
        super(message, cause);
    }

}
