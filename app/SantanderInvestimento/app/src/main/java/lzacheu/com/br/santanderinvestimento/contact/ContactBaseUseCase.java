package lzacheu.com.br.santanderinvestimento.contact;

import lzacheu.com.br.santanderinvestimento.base.BaseUseCase;

/**
 * Created by luiszacheu on 6/16/18.
 */

public class ContactBaseUseCase extends BaseUseCase<ContactBaseUseCase.RequestValues, ContactBaseUseCase.ResponseValues> {

    @Override
    protected void executeUseCase(RequestValues requestValues) {

    }

    public static final class RequestValues implements BaseUseCase.RequestValues{

    }

    public static final class ResponseValues implements  BaseUseCase.ResponseValue{

    }

}
