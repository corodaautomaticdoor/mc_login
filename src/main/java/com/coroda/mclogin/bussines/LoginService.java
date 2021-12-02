package com.coroda.mclogin.bussines;

import com.coroda.mclogin.model.api.request.Request;
import com.coroda.mclogin.model.api.response.Response;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface LoginService {

    Completable save(Request request);
    Completable delete (Long operationId);
    Completable update (Request request);
    Single<Response> getById (Long operationId);
    Observable<Response> findAll();
    Maybe<Response> getValidate (Request request);
}
