package itpu.diyoramirzaeva.dao.exception;

/**
 * A runtime exception to signal data-access and CSV reading/parsing errors
 * in DAO layer. Prefer throwing this instead of generic RuntimeException
 * to provide a clear contract for callers.
 */
public class DaoException extends RuntimeException {
    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
