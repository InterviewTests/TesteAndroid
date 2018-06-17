package lzacheu.com.br.santanderinvestimento.base;

/**
 * Created by luiszacheu on 6/16/18.
 */

/**
 * Use cases are the entry points to the domain layer.
 *
 * @param <Q> the request type
 * @param <P> the response type
 */
public abstract class BaseUseCase<Q extends BaseUseCase.RequestValues, P extends BaseUseCase.ResponseValue> {

    private Q mRequestValues;

    private UseCaseCallback<P> mUseCaseCallback;

    public void setRequestValues(Q requestValues) {
        mRequestValues = requestValues;
    }

    public Q getRequestValues() {
        return mRequestValues;
    }

    public UseCaseCallback<P> getUseCaseCallback() {
        return mUseCaseCallback;
    }

    public void setUseCaseCallback(UseCaseCallback<P> useCaseCallback) {
        mUseCaseCallback = useCaseCallback;
    }

    void run() {
        executeUseCase(mRequestValues);
    }

    protected abstract void executeUseCase(Q requestValues);

    /**
     * Data passed to a request.
     */
    public interface RequestValues {
    }

    /**
     * Data received from a request.
     */
    public interface ResponseValue {
    }

    public interface UseCaseCallback<R> {
        void onSuccess(R response);
        void onError();
    }
}